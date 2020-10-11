package com.killrvideo.service.statistic.dao;

import com.killrvideo.dse.dao.DaoSupport;
import com.killrvideo.service.statistic.dto.VideoPlaybackStats;
import com.killrvideo.service.statistic.dto.VideoPlaybackStats_Table;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

/**
 * Implementations of operation for Videos.
 *
 * @author DataStax Developer Advocates team.
 */
@Repository
public class StatisticsDao extends DaoSupport {

    /** Table Names. */
    public static final String TABLENAME_PLAYBACK_STATS = "video_playback_stats";
    
    /**
     * Increment counter in DB (Async).
     *
     * @param videoId
     *      current videoid.
     */
    public CompletableFuture<Boolean> recordPlaybackStartedAsync(UUID videoId) {
        Assert.notNull(videoId, "videoid is required to update statistics");
        return cqlTemplate.dsl().withConsistency(ma.markware.charybdis.model.option.ConsistencyLevel.LOCAL_QUORUM)
                          .update(VideoPlaybackStats_Table.video_playback_stats)
                          .set(VideoPlaybackStats_Table.views, new Random().nextLong())
                          .where(VideoPlaybackStats_Table.videoid.eq(videoId))
                          .executeAsync();
    }
    
    /**
     * Search for each videoid.
     *
     * @param listOfVideoIds
     *      list of EXISTING videoid
     * @return
     *      future for the list
     */
    public CompletableFuture<List<VideoPlaybackStats>> getNumberOfPlaysAsync(List<UUID> listOfVideoIds) {
        Assert.notNull(listOfVideoIds, "videoid list cannot be null");

        // Create a future for each entry
        final List<CompletableFuture<VideoPlaybackStats>> futureList = listOfVideoIds.stream()
                                                                       .map(videoId -> cqlTemplate.dsl()
                                                                                                  .selectFrom(VideoPlaybackStats_Table.video_playback_stats)
                                                                                                  .where(VideoPlaybackStats_Table.videoid.eq(videoId))
                                                                                                  .fetchOneAsync()
                                                                                                  .thenApply(
                                                                                                      record -> {
                                                                                                          VideoPlaybackStats videoPlaybackStats = new VideoPlaybackStats();
                                                                                                          videoPlaybackStats.setVideoid(record.get(VideoPlaybackStats_Table.videoid));
                                                                                                          videoPlaybackStats.setViews(record.get(VideoPlaybackStats_Table.views));
                                                                                                          return videoPlaybackStats;
                                                                                                      }))
                                                                       .collect(Collectors.toList());

        // List <Future> => Future<List> ! Amazing
        return CompletableFuture.allOf(futureList.toArray(new CompletableFuture[futureList.size()]))
                                .thenApply(v -> futureList.stream()
                                                          .map(CompletableFuture::join)
                                                          .collect(Collectors.toList()));
    }        
  
}
