package com.killrvideo.service.statistic.dto;

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

public class VideoPlaybackStats_Table implements TableMetadata<VideoPlaybackStats> {
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

  public static final ColumnMetadata<Long, Long> views = new ColumnMetadata<Long, Long>() {
    public String getName() {
      return "views";
    }

    public Class getFieldClass() {
      return java.lang.Long.class;
    }

    public Long serialize(Long field) {
      return field;
    }

    public Long deserialize(Row row) {
      if (row == null || row.isNull("views")) return null;
      return row.get("views", java.lang.Long.class);
    }
  };

  public static final VideoPlaybackStats_Table video_playback_stats = new VideoPlaybackStats_Table();

  public static final String KEYSPACE_NAME = "killrvideo";

  public static final String TABLE_NAME = "video_playback_stats";

  private VideoPlaybackStats_Table() {
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
    results.put("views", views);
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

  public void setGeneratedValues(VideoPlaybackStats entity) {
    if (entity != null) {
    }
  }

  public void setCreationDate(VideoPlaybackStats entity, Instant creationDate) {
    if (entity != null) {
    }
  }

  public void setLastUpdatedDate(VideoPlaybackStats entity, Instant lastUpdatedDate) {
    if (entity != null) {
    }
  }

  public Map<String, Object> serialize(VideoPlaybackStats entity) {
    if (entity == null) return null;
    Map<String, Object> columnValueMap = new HashMap<>();
    columnValueMap.put("videoid", videoid.serialize(entity.getVideoid()));
    columnValueMap.put("views", views.serialize(entity.getViews()));
    return columnValueMap;
  }

  public VideoPlaybackStats deserialize(Row row) {
    if (row == null) return null;
    VideoPlaybackStats entity = new VideoPlaybackStats();
    entity.setVideoid(videoid.deserialize(row));
    entity.setViews(views.deserialize(row));
    return entity;
  }
}
