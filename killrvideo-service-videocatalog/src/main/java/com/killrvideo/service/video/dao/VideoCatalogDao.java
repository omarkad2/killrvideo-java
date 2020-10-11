package com.killrvideo.service.video.dao;

import com.killrvideo.dse.dao.DaoSupport;
import com.killrvideo.dse.dto.CustomPagingState;
import com.killrvideo.dse.dto.ResultListPage;
import com.killrvideo.dse.dto.Video;
import com.killrvideo.dse.dto.Video_Table;
import com.killrvideo.service.video.dto.LatestVideo;
import com.killrvideo.service.video.dto.LatestVideo_Table;
import com.killrvideo.service.video.dto.LatestVideosPage;
import com.killrvideo.service.video.dto.UserVideo;
import com.killrvideo.service.video.dto.UserVideo_Table;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import ma.markware.charybdis.batch.Batch;
import ma.markware.charybdis.dsl.Record;
import ma.markware.charybdis.query.PageRequest;
import ma.markware.charybdis.query.PageResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

/**
 * Implementations of operation for Videos.
 *
 * @author DataStax Developer Advocates team.
 */
@Repository
public class VideoCatalogDao extends DaoSupport {

  /**
   * Constants.
   */
  public static final int MAX_DAYS_IN_PAST_FOR_LATEST_VIDEOS = 7;
  public static final int LATEST_VIDEOS_TTL_SECONDS = MAX_DAYS_IN_PAST_FOR_LATEST_VIDEOS * 24 * 3600;
  public static final Pattern PARSE_LATEST_PAGING_STATE = Pattern.compile("((?:[0-9]{8}_){7}[0-9]{8}),([0-9]),(.*)");

  /**
   * Formatting date.
   */
  public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMdd");
  public static final DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

  /**
   * Table Name of Latest Video.
   */
  public static final String TABLENAME_LATEST_VIDEOS = "latest_videos";
  public static final String TABLENAME_USER_VIDEOS = "user_videos";

  /**
   * Loger for that class.
   */
  private static Logger LOGGER = LoggerFactory.getLogger(VideoCatalogDao.class);

  /**
   * Insert a VIDEO in the DB.
   */
  public void insertVideo(Video v) {
    createStatementInsertVideo(v).execute();
  }

  /**
   * Build the first paging state if one does not already exist and return an object containing 3 elements representing the initial state
   * (List<String>, Integer, String).
   *
   * @return CustomPagingState
   */
  public CustomPagingState buildFirstCustomPagingState() {
    return new CustomPagingState().currentBucket(0)
                                  .cassandraPagingState(null)
                                  .listOfBuckets(LongStream.rangeClosed(0L, 7L)
                                                           .boxed()
                                                           .map(Instant.now()
                                                                       .atZone(ZoneId.systemDefault())::minusDays)
                                                           .map(x -> x.format(VideoCatalogDao.DATEFORMATTER))
                                                           .collect(Collectors.toList()));
  }

  /**
   * Insert a VIDEO in the DB (ASYNC).
   */
  public CompletableFuture<Boolean> insertVideoAsync(Video v) {
    return cqlTemplate.dsl()
                      .withConsistency(ma.markware.charybdis.model.option.ConsistencyLevel.LOCAL_QUORUM)
                      .insertInto(Video_Table.videos, Video_Table.videoid, Video_Table.userid, Video_Table.description, Video_Table.location,
                                  Video_Table.locationType, Video_Table.tags, Video_Table.addedDate)
                      .values(v.getVideoid(), v.getUserid(), v.getDescription(), v.getLocation(), v.getLocationType(), v.getTags(), v.getAddedDate())
                      .executeAsync();
  }

  public CompletableFuture<Video> getVideoById(UUID videoid) {
    return cqlTemplate.dsl()
                      .selectFrom(Video_Table.videos)
                      .where(Video_Table.videoid.eq(videoid))
                      .fetchOneAsync()
                      .thenApply(record -> {
                        if (record != null) {
                          return new Video(record.get(Video_Table.videoid), record.get(Video_Table.userid), record.get(Video_Table.name),
                                           record.get(Video_Table.description), record.get(Video_Table.location),
                                           record.get(Video_Table.locationType), record.get(Video_Table.previewImageLocation),
                                           record.get(Video_Table.tags), record.get(Video_Table.addedDate));
                        }
                        return null;
                      });
  }

  public CompletableFuture<List<Video>> getVideoPreview(List<UUID> listofVideoId) {
    Assert.notNull(listofVideoId, "videoid list cannot be null");

    // Create a future for each entry
    final List<CompletableFuture<Video>> futureList = listofVideoId.stream()
                                                                   .map(videoId -> cqlTemplate.dsl()
                                                                                              .selectFrom(Video_Table.videos)
                                                                                              .where(Video_Table.videoid.eq(videoId))
                                                                                              .fetchOneAsync()
                                                                                              .thenApply(
                                                                                                  record -> new Video(record.get(Video_Table.videoid),
                                                                                                                      record.get(Video_Table.userid),
                                                                                                                      record.get(Video_Table.name),
                                                                                                                      record.get(
                                                                                                                          Video_Table.description),
                                                                                                                      record.get(
                                                                                                                          Video_Table.location),
                                                                                                                      record.get(
                                                                                                                          Video_Table.locationType),
                                                                                                                      record.get(
                                                                                                                          Video_Table.previewImageLocation),
                                                                                                                      record.get(Video_Table.tags),
                                                                                                                      record.get(
                                                                                                                          Video_Table.addedDate))))
                                                                   .collect(Collectors.toList());

    // List <Future> => Future<List> ! Amazing
    return CompletableFuture.allOf(futureList.toArray(new CompletableFuture[futureList.size()]))
                            .thenApply(v -> futureList.stream()
                                                      .map(CompletableFuture::join)
                                                      .collect(Collectors.toList()));
  }

  /**
   * Read a page of video preview for a user.
   *
   * @param userId user unique identifier
   * @param startingVideoId starting video if paging
   * @param startingAddedDate added date if paging
   * @param pagingState paging state if paging
   * @return requested video (page)
   */
  public CompletableFuture<ResultListPage<UserVideo>> getUserVideosPreview(UUID userId, Optional<UUID> startingVideoId,
      Optional<Instant> startingAddedDate, int pageSize, Optional<String> pagingState) {

    var selectExpression = cqlTemplate.dsl()
                                      .withConsistency(ma.markware.charybdis.model.option.ConsistencyLevel.LOCAL_QUORUM)
                                      .selectFrom(UserVideo_Table.user_videos)
                                      .where(UserVideo_Table.userid.eq(userId));
    /*
     * If startingAddedDate and startingVideoId are provided,
     * we do NOT use the paging state
     */
    if (startingVideoId.isPresent() && startingAddedDate.isPresent()) {
      /*
       * The startingPointPrepared statement can be found at the top
       * of the class within PostConstruct
       */
      selectExpression.and(UserVideo_Table.addedDate.eq(startingAddedDate.get()))
                      .and(UserVideo_Table.videoid.eq(startingVideoId.get()));
    }

    CompletableFuture<PageResult<Record>> pageResultAsync;
    if (pagingState.isPresent()) {
      pageResultAsync = selectExpression.fetchPageAsync(PageRequest.fromString(pagingState.get(), pageSize));
    } else {
      pageResultAsync = selectExpression.fetchPageAsync(PageRequest.of(null, pageSize));
    }

    // Execute Query
    return pageResultAsync.thenApply(pageResult -> {
      List<UserVideo> resultList = pageResult.getResults()
                                             .stream()
                                             .map(record -> new UserVideo(record.get(UserVideo_Table.userid), record.get(UserVideo_Table.videoid),
                                                                          record.get(UserVideo_Table.name),
                                                                          record.get(UserVideo_Table.previewImageLocation),
                                                                          record.get(UserVideo_Table.addedDate)))
                                             .collect(Collectors.toList());
      return new ResultListPage<>(resultList, pageResult.getPagingState());
    });
  }

  /**
   * Latest video partition key is the Date. As such we need to perform a query per date. As the user ask for a number of video on a given page we may
   * have to trigger several queries, on for each day. To do it we implement a couple
   *
   * For those of you wondering where the call to fetchMoreResults() is take a look here for an explanation.
   * https://docs.datastax.com/en/drivers/java/3.2/com/datastax/driver/core/PagingIterable.html#getAvailableWithoutFetching--
   *
   * Quick summary, when getAvailableWithoutFetching() == 0 it automatically calls fetchMoreResults() We could use it to force a fetch in a "prefetch"
   * scenario, but that is not what we are doing here.
   *
   * @throws ExecutionException error duing invoation
   * @throws InterruptedException error in asynchronism
   */
  public LatestVideosPage getLatestVideoPreviews(CustomPagingState cpState, int pageSize, Optional<Instant> startDate, Optional<UUID> startVid)
      throws InterruptedException, ExecutionException {
    LatestVideosPage returnedPage = new LatestVideosPage();
    LOGGER.debug("Looking for {} latest video(s)", pageSize);

    // Flag to syncrhonize usage of cassandra paging state
    final AtomicBoolean isCassandraPageState = new AtomicBoolean(false);

    do {

      // (1) - Paging state (custom or cassandra)
      final Optional<String> pagingState = Optional.ofNullable(cpState.getCassandraPagingState())  // Only if present .get()
                                                   .filter(StringUtils::isNotBlank)                // ..and not empty
                                                   .filter(pg -> !isCassandraPageState.get());     // ..and cassandra paging is off

      // (2) - Execute Query Asynchronously for a single bucket (=a single date)
      CompletableFuture<PageResult<Record>> pageResultFuture = fetchLatestVideoPageAsync(cpState.getCurrentBucketValue(),
                                                                                         // Current Bucket Date yyyymmdd
                                                                                         pagingState,
                                                                                         // Custom or cassandra pageing state
                                                                                         isCassandraPageState,
                                                                                         // Flag to use and update cassandra paging
                                                                                         startDate, startVid,
                                                                                         // Optional Parameters for filtering
                                                                                         pageSize
                                                                                             - returnedPage.getResultSize());// Number of element to retrieve from current query

      // (3) - Map result
      CompletableFuture<LatestVideosPage> latestVideosPageFuture = pageResultFuture.thenApply(this::mapLatestVideosResultAsPage);

      // (4) - Wait for result before triggering auery for page N+1
      LatestVideosPage currentPage = latestVideosPageFuture.get();
      returnedPage.getListOfPreview()
                  .addAll(currentPage.getListOfPreview());
      if (LOGGER.isDebugEnabled()) {
        LOGGER.debug(" + bucket:{}/{} with results:{}/{} and pagingState:{}", cpState.getCurrentBucket(), cpState.getListOfBucketsSize(),
                     returnedPage.getResultSize(), pageSize, returnedPage.getCassandraPagingState());
      }

      // (5) Update NEXT PAGE BASE on current status
      if (returnedPage.getResultSize() == pageSize) {
        if (!StringUtils.isBlank(currentPage.getCassandraPagingState())) {
          returnedPage.setNextPageState(
              createPagingState(cpState.getListOfBuckets(), cpState.getCurrentBucket(), currentPage.getCassandraPagingState()));
          LOGGER.debug(" + Exiting because we got enought results.");
        }
        // --> Start from the beginning of the next bucket since we're out of rows in this one
      } else if (cpState.getCurrentBucket() == cpState.getListOfBucketsSize() - 1) {
        returnedPage.setNextPageState(createPagingState(cpState.getListOfBuckets(), cpState.getCurrentBucket() + 1, ""));
        LOGGER.debug(" + Exiting because we are out of Buckets even if not enough results");
      }

      // (6) Move to next BUCKET
      cpState.incCurrentBucketIndex();

    } while ((returnedPage.getListOfPreview()
                          .size() < pageSize)               // Result has enough element to fill the page
        && cpState.getCurrentBucket() < cpState.getListOfBucketsSize()); // No nore bucket available

    return returnedPage;
  }

  /**
   * Dynamically build statement based on arguments startingDate, videoId.
   */
  private CompletableFuture<PageResult<Record>> fetchLatestVideoPageAsync(String yyyymmdd, Optional<String> pagingState,
      AtomicBoolean cassandraPagingStateUsed, Optional<Instant> startingAddedDate, Optional<UUID> startingVideoId, int recordNeeded) {
    var selectLatestVideoExpression = cqlTemplate.dsl()
                                                 .withConsistency(ma.markware.charybdis.model.option.ConsistencyLevel.ONE)
                                                 .selectFrom(LatestVideo_Table.latest_videos)
                                                 .where(LatestVideo_Table.yyyymmdd.eq(yyyymmdd));
    if (startingAddedDate.isPresent() && startingVideoId.isPresent()) {
      selectLatestVideoExpression.and(LatestVideo_Table.addedDate.lte(startingAddedDate.get()))
                                 .and(LatestVideo_Table.videoid.lte(startingVideoId.get()));
    }

    if (pagingState.isPresent()) {
      cassandraPagingStateUsed.compareAndSet(false, true);
      return selectLatestVideoExpression.fetchPageAsync(PageRequest.fromString(pagingState.get(), recordNeeded));
    }
    return selectLatestVideoExpression.fetchPageAsync(PageRequest.of(null, recordNeeded));
  }

  /**
   * Create statment to populate 3 tables in the same time.
   */
  private Batch createStatementInsertVideo(Video v) {
    final Instant now = Instant.now();
    final String yyyyMMdd = SDF.format(now);

    /** Logged batch insert for automatic retry. */
    Batch batch = cqlTemplate.batch()
                             .withConsistency(ma.markware.charybdis.model.option.ConsistencyLevel.LOCAL_QUORUM)
                             .logged();

    cqlTemplate.dsl(batch)
               .insertInto(Video_Table.videos, Video_Table.videoid, Video_Table.userid, Video_Table.name, Video_Table.description,
                           Video_Table.location, Video_Table.locationType, Video_Table.previewImageLocation, Video_Table.tags, Video_Table.addedDate)
               .values(v.getVideoid(), v.getUserid(), v.getName(), v.getDescription(), v.getLocation(), v.getLocationType(),
                       v.getPreviewImageLocation(), v.getTags(), now)
               .execute();

    cqlTemplate.dsl(batch)
               .insertInto(UserVideo_Table.user_videos, UserVideo_Table.videoid, UserVideo_Table.userid, UserVideo_Table.name,
                           UserVideo_Table.previewImageLocation, UserVideo_Table.addedDate)
               .values(v.getVideoid(), v.getUserid(), v.getName(), v.getPreviewImageLocation(), now)
               .execute();

    cqlTemplate.dsl(batch)
               .insertInto(LatestVideo_Table.latest_videos, LatestVideo_Table.yyyymmdd, LatestVideo_Table.videoid, LatestVideo_Table.userid,
                           LatestVideo_Table.name, LatestVideo_Table.previewImageLocation, LatestVideo_Table.addedDate)
               .values(yyyyMMdd, v.getVideoid(), v.getUserid(), v.getName(), v.getPreviewImageLocation(), now)
               .execute();

    return batch;
  }

  /**
   * Create a paging state string from the passed in parameters
   *
   * @return String
   */
  private String createPagingState(List<String> buckets, int bucketIndex, String rowsPagingState) {
    StringJoiner joiner = new StringJoiner("_");
    buckets.forEach(joiner::add);
    return joiner.toString() + "," + bucketIndex + "," + rowsPagingState;
  }

  private LatestVideosPage mapLatestVideosResultAsPage(PageResult<Record> pageResult) {
    LatestVideosPage resultPage = new LatestVideosPage();
    final ByteBuffer state = pageResult.getPagingState();
    if (null != state) {
      resultPage.setCassandraPagingState(state.toString());
    }
    for (Record record : pageResult.getResults()) {
      LatestVideo latestVideo = new LatestVideo(record.get(LatestVideo_Table.yyyymmdd), record.get(LatestVideo_Table.userid),
                                                record.get(LatestVideo_Table.videoid), record.get(LatestVideo_Table.name),
                                                record.get(LatestVideo_Table.previewImageLocation), record.get(LatestVideo_Table.addedDate));
      LOGGER.debug("Processing video: " + latestVideo.getVideoid());
      // Add each row to results
      resultPage.addLatestVideos(latestVideo);
    }
    return resultPage;
  }

}
