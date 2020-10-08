package com.killrvideo.service.rating.dto;

import com.datastax.oss.driver.api.core.cql.Row;
import java.lang.Class;
import java.lang.Long;
import java.lang.Object;
import java.lang.String;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import ma.markware.charybdis.model.field.metadata.ColumnMetadata;
import ma.markware.charybdis.model.field.metadata.PartitionKeyColumnMetadata;
import ma.markware.charybdis.model.field.metadata.TableMetadata;
import ma.markware.charybdis.model.option.ConsistencyLevel;
import ma.markware.charybdis.model.option.SerialConsistencyLevel;

public class VideoRating_Table implements TableMetadata<VideoRating> {
  public static final PartitionKeyColumnMetadata<UUID, UUID> videoid = new PartitionKeyColumnMetadata<UUID, UUID>() {
    public String getName() {
      return "videoid";
    }

    public Class getFieldClass() {
      return java.util.UUID.class;
    }

    public UUID serialize(UUID field) {
      return field;
    }

    public UUID deserialize(Row row) {
      if (row == null || row.isNull("videoid")) return null;
      return row.get("videoid", java.util.UUID.class);
    }

    public int getPartitionKeyIndex() {
      return 0;
    }
  };

  public static final ColumnMetadata<Long, Long> ratingCounter = new ColumnMetadata<Long, Long>() {
    public String getName() {
      return "rating_counter";
    }

    public Class getFieldClass() {
      return java.lang.Long.class;
    }

    public Long serialize(Long field) {
      return field;
    }

    public Long deserialize(Row row) {
      if (row == null || row.isNull("rating_counter")) return null;
      return row.get("rating_counter", java.lang.Long.class);
    }
  };

  public static final ColumnMetadata<Long, Long> ratingTotal = new ColumnMetadata<Long, Long>() {
    public String getName() {
      return "rating_total";
    }

    public Class getFieldClass() {
      return java.lang.Long.class;
    }

    public Long serialize(Long field) {
      return field;
    }

    public Long deserialize(Row row) {
      if (row == null || row.isNull("rating_total")) return null;
      return row.get("rating_total", java.lang.Long.class);
    }
  };

  public static final VideoRating_Table video_ratings = new VideoRating_Table();

  public static final String KEYSPACE_NAME = "killrvideo";

  public static final String TABLE_NAME = "video_ratings";

  private VideoRating_Table() {
  }

  public String getKeyspaceName() {
    return KEYSPACE_NAME;
  }

  public String getTableName() {
    return TABLE_NAME;
  }

  public ConsistencyLevel getDefaultReadConsistency() {
    return ConsistencyLevel.NOT_SPECIFIED;
  }

  public ConsistencyLevel getDefaultWriteConsistency() {
    return ConsistencyLevel.NOT_SPECIFIED;
  }

  public SerialConsistencyLevel getDefaultSerialConsistency() {
    return SerialConsistencyLevel.NOT_SPECIFIED;
  }

  public Map<String, ColumnMetadata> getColumnsMetadata() {
    Map<String, ColumnMetadata> results = new HashMap<>();
    results.put("videoid", videoid);
    results.put("rating_counter", ratingCounter);
    results.put("rating_total", ratingTotal);
    return results;
  }

  public Map<String, ColumnMetadata> getPartitionKeyColumns() {
    Map<String, ColumnMetadata> results = new HashMap<>();
    results.put("videoid", videoid);
    return results;
  }

  public Map<String, ColumnMetadata> getClusteringKeyColumns() {
    Map<String, ColumnMetadata> results = new HashMap<>();
    return results;
  }

  public Map<String, ColumnMetadata> getPrimaryKeys() {
    Map<String, ColumnMetadata> result = new HashMap<>();
    result.putAll(getPartitionKeyColumns());
    result.putAll(getClusteringKeyColumns());
    return result;
  }

  public ColumnMetadata getColumnMetadata(String columnName) {
    return getColumnsMetadata().get(columnName);
  }

  public boolean isPrimaryKey(String columnName) {
    return getPartitionKeyColumns().containsKey(columnName) || getClusteringKeyColumns().containsKey(columnName);
  }

  public int getPrimaryKeySize() {
    return getPartitionKeyColumns().size() + getClusteringKeyColumns().size();
  }

  public int getColumnsSize() {
    return getColumnsMetadata().size();
  }

  public void setGeneratedValues(VideoRating entity) {
    if (entity != null) {
    }
  }

  public void setCreationDate(VideoRating entity, Instant creationDate) {
    if (entity != null) {
    }
  }

  public void setLastUpdatedDate(VideoRating entity, Instant lastUpdatedDate) {
    if (entity != null) {
    }
  }

  public Map<String, Object> serialize(VideoRating entity) {
    if (entity == null) return null;
    Map<String, Object> columnValueMap = new HashMap<>();
    columnValueMap.put("videoid", videoid.serialize(entity.getVideoid()));
    columnValueMap.put("rating_counter", ratingCounter.serialize(entity.getRatingCounter()));
    columnValueMap.put("rating_total", ratingTotal.serialize(entity.getRatingTotal()));
    return columnValueMap;
  }

  public VideoRating deserialize(Row row) {
    if (row == null) return null;
    VideoRating entity = new VideoRating();
    entity.setVideoid(videoid.deserialize(row));
    entity.setRatingCounter(ratingCounter.deserialize(row));
    entity.setRatingTotal(ratingTotal.deserialize(row));
    return entity;
  }
}
