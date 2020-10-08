package com.killrvideo.service.rating.dto;

import com.datastax.oss.driver.api.core.cql.Row;
import java.lang.Class;
import java.lang.Integer;
import java.lang.Object;
import java.lang.String;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import ma.markware.charybdis.model.field.metadata.ClusteringKeyColumnMetadata;
import ma.markware.charybdis.model.field.metadata.ColumnMetadata;
import ma.markware.charybdis.model.field.metadata.PartitionKeyColumnMetadata;
import ma.markware.charybdis.model.field.metadata.TableMetadata;
import ma.markware.charybdis.model.option.ClusteringOrder;
import ma.markware.charybdis.model.option.ConsistencyLevel;
import ma.markware.charybdis.model.option.SerialConsistencyLevel;

public class VideoRatingByUser_Table implements TableMetadata<VideoRatingByUser> {
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

  public static final ClusteringKeyColumnMetadata<UUID, UUID> userid = new ClusteringKeyColumnMetadata<UUID, UUID>() {
    public String getName() {
      return "userid";
    }

    public Class getFieldClass() {
      return java.util.UUID.class;
    }

    public UUID serialize(UUID field) {
      return field;
    }

    public UUID deserialize(Row row) {
      if (row == null || row.isNull("userid")) return null;
      return row.get("userid", java.util.UUID.class);
    }

    public int getClusteringKeyIndex() {
      return 0;
    }

    public ClusteringOrder getClusteringOrder() {
      return ClusteringOrder.ASC;
    }
  };

  public static final ColumnMetadata<Integer, Integer> rating = new ColumnMetadata<Integer, Integer>() {
    public String getName() {
      return "rating";
    }

    public Class getFieldClass() {
      return java.lang.Integer.class;
    }

    public Integer serialize(Integer field) {
      return field;
    }

    public Integer deserialize(Row row) {
      if (row == null || row.isNull("rating")) return null;
      return row.get("rating", java.lang.Integer.class);
    }
  };

  public static final VideoRatingByUser_Table video_ratings_by_user = new VideoRatingByUser_Table();

  public static final String KEYSPACE_NAME = "killrvideo";

  public static final String TABLE_NAME = "video_ratings_by_user";

  private VideoRatingByUser_Table() {
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
    results.put("userid", userid);
    results.put("rating", rating);
    return results;
  }

  public Map<String, ColumnMetadata> getPartitionKeyColumns() {
    Map<String, ColumnMetadata> results = new HashMap<>();
    results.put("videoid", videoid);
    return results;
  }

  public Map<String, ColumnMetadata> getClusteringKeyColumns() {
    Map<String, ColumnMetadata> results = new HashMap<>();
    results.put("userid", userid);
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

  public void setGeneratedValues(VideoRatingByUser entity) {
    if (entity != null) {
    }
  }

  public void setCreationDate(VideoRatingByUser entity, Instant creationDate) {
    if (entity != null) {
    }
  }

  public void setLastUpdatedDate(VideoRatingByUser entity, Instant lastUpdatedDate) {
    if (entity != null) {
    }
  }

  public Map<String, Object> serialize(VideoRatingByUser entity) {
    if (entity == null) return null;
    Map<String, Object> columnValueMap = new HashMap<>();
    columnValueMap.put("videoid", videoid.serialize(entity.getVideoid()));
    columnValueMap.put("userid", userid.serialize(entity.getUserid()));
    columnValueMap.put("rating", rating.serialize(entity.getRating()));
    return columnValueMap;
  }

  public VideoRatingByUser deserialize(Row row) {
    if (row == null) return null;
    VideoRatingByUser entity = new VideoRatingByUser();
    entity.setVideoid(videoid.deserialize(row));
    entity.setUserid(userid.deserialize(row));
    entity.setRating(rating.deserialize(row));
    return entity;
  }
}
