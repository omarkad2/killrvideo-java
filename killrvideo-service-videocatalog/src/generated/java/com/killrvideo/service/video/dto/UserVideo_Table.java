package com.killrvideo.service.video.dto;

import com.datastax.oss.driver.api.core.cql.Row;
import java.lang.Class;
import java.lang.Object;
import java.lang.String;
import java.time.Instant;
import java.util.Date;
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

public class UserVideo_Table implements TableMetadata<UserVideo> {
  public static final PartitionKeyColumnMetadata<UUID, UUID> userid = new PartitionKeyColumnMetadata<UUID, UUID>() {
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

    public int getPartitionKeyIndex() {
      return 0;
    }
  };

  public static final ColumnMetadata<String, String> name = new ColumnMetadata<String, String>() {
    public String getName() {
      return "name";
    }

    public Class getFieldClass() {
      return java.lang.String.class;
    }

    public String serialize(String field) {
      return field;
    }

    public String deserialize(Row row) {
      if (row == null || row.isNull("name")) return null;
      return row.get("name", java.lang.String.class);
    }
  };

  public static final ColumnMetadata<String, String> previewImageLocation = new ColumnMetadata<String, String>() {
    public String getName() {
      return "preview_image_location";
    }

    public Class getFieldClass() {
      return java.lang.String.class;
    }

    public String serialize(String field) {
      return field;
    }

    public String deserialize(Row row) {
      if (row == null || row.isNull("preview_image_location")) return null;
      return row.get("preview_image_location", java.lang.String.class);
    }
  };

  public static final ClusteringKeyColumnMetadata<Date, Date> addedDate = new ClusteringKeyColumnMetadata<Date, Date>() {
    public String getName() {
      return "added_date";
    }

    public Class getFieldClass() {
      return java.util.Date.class;
    }

    public Date serialize(Date field) {
      return field;
    }

    public Date deserialize(Row row) {
      if (row == null || row.isNull("added_date")) return null;
      return row.get("added_date", java.util.Date.class);
    }

    public int getClusteringKeyIndex() {
      return 0;
    }

    public ClusteringOrder getClusteringOrder() {
      return ClusteringOrder.ASC;
    }
  };

  public static final ClusteringKeyColumnMetadata<UUID, UUID> videoid = new ClusteringKeyColumnMetadata<UUID, UUID>() {
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

    public int getClusteringKeyIndex() {
      return 1;
    }

    public ClusteringOrder getClusteringOrder() {
      return ClusteringOrder.ASC;
    }
  };

  public static final UserVideo_Table user_videos = new UserVideo_Table();

  public static final String KEYSPACE_NAME = "killrvideo";

  public static final String TABLE_NAME = "user_videos";

  private UserVideo_Table() {
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
    results.put("userid", userid);
    results.put("name", name);
    results.put("preview_image_location", previewImageLocation);
    results.put("added_date", addedDate);
    results.put("videoid", videoid);
    return results;
  }

  public Map<String, ColumnMetadata> getPartitionKeyColumns() {
    Map<String, ColumnMetadata> results = new HashMap<>();
    results.put("userid", userid);
    return results;
  }

  public Map<String, ColumnMetadata> getClusteringKeyColumns() {
    Map<String, ColumnMetadata> results = new HashMap<>();
    results.put("added_date", addedDate);
    results.put("videoid", videoid);
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

  public void setGeneratedValues(UserVideo entity) {
    if (entity != null) {
    }
  }

  public void setCreationDate(UserVideo entity, Instant creationDate) {
    if (entity != null) {
    }
  }

  public void setLastUpdatedDate(UserVideo entity, Instant lastUpdatedDate) {
    if (entity != null) {
    }
  }

  public Map<String, Object> serialize(UserVideo entity) {
    if (entity == null) return null;
    Map<String, Object> columnValueMap = new HashMap<>();
    columnValueMap.put("userid", userid.serialize(entity.getUserid()));
    columnValueMap.put("name", name.serialize(entity.getName()));
    columnValueMap.put("preview_image_location", previewImageLocation.serialize(entity.getPreviewImageLocation()));
    columnValueMap.put("added_date", addedDate.serialize(entity.getAddedDate()));
    columnValueMap.put("videoid", videoid.serialize(entity.getVideoid()));
    return columnValueMap;
  }

  public UserVideo deserialize(Row row) {
    if (row == null) return null;
    UserVideo entity = new UserVideo();
    entity.setUserid(userid.deserialize(row));
    entity.setName(name.deserialize(row));
    entity.setPreviewImageLocation(previewImageLocation.deserialize(row));
    entity.setAddedDate(addedDate.deserialize(row));
    entity.setVideoid(videoid.deserialize(row));
    return entity;
  }
}
