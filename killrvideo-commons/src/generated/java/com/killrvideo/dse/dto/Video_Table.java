package com.killrvideo.dse.dto;

import com.datastax.oss.driver.api.core.cql.Row;
import java.lang.Class;
import java.lang.Integer;
import java.lang.Object;
import java.lang.String;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import ma.markware.charybdis.model.field.metadata.ColumnMetadata;
import ma.markware.charybdis.model.field.metadata.PartitionKeyColumnMetadata;
import ma.markware.charybdis.model.field.metadata.SetColumnMetadata;
import ma.markware.charybdis.model.field.metadata.TableMetadata;
import ma.markware.charybdis.model.option.ConsistencyLevel;
import ma.markware.charybdis.model.option.SerialConsistencyLevel;

public class Video_Table implements TableMetadata<Video> {
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

  public static final ColumnMetadata<UUID, UUID> userid = new ColumnMetadata<UUID, UUID>() {
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
  };

  public static final ColumnMetadata<String, String> description = new ColumnMetadata<String, String>() {
    public String getName() {
      return "description";
    }

    public Class getFieldClass() {
      return java.lang.String.class;
    }

    public String serialize(String field) {
      return field;
    }

    public String deserialize(Row row) {
      if (row == null || row.isNull("description")) return null;
      return row.get("description", java.lang.String.class);
    }
  };

  public static final ColumnMetadata<String, String> location = new ColumnMetadata<String, String>() {
    public String getName() {
      return "location";
    }

    public Class getFieldClass() {
      return java.lang.String.class;
    }

    public String serialize(String field) {
      return field;
    }

    public String deserialize(Row row) {
      if (row == null || row.isNull("location")) return null;
      return row.get("location", java.lang.String.class);
    }
  };

  public static final ColumnMetadata<Integer, Integer> locationType = new ColumnMetadata<Integer, Integer>() {
    public String getName() {
      return "location_type";
    }

    public Class getFieldClass() {
      return java.lang.Integer.class;
    }

    public Integer serialize(Integer field) {
      return field;
    }

    public Integer deserialize(Row row) {
      if (row == null || row.isNull("location_type")) return null;
      return row.get("location_type", java.lang.Integer.class);
    }
  };

  public static final SetColumnMetadata<String, String> tags = new SetColumnMetadata<String, String>() {
    public String getName() {
      return "tags";
    }

    public Class getFieldClass() {
      return java.util.Set.class;
    }

    public Set<String> serialize(Set<String> field) {
      return field;
    }

    public Set<String> deserialize(Row row) {
      if (row == null || row.isNull("tags")) return null;
      return row.getSet("tags", java.lang.String.class);
    }
  };

  public static final ColumnMetadata<Date, Date> addedDate = new ColumnMetadata<Date, Date>() {
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

  public static final Video_Table videos = new Video_Table();

  public static final String KEYSPACE_NAME = "killrvideo";

  public static final String TABLE_NAME = "videos";

  private Video_Table() {
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
    results.put("description", description);
    results.put("location", location);
    results.put("location_type", locationType);
    results.put("tags", tags);
    results.put("added_date", addedDate);
    results.put("name", name);
    results.put("preview_image_location", previewImageLocation);
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

  public void setGeneratedValues(Video entity) {
    if (entity != null) {
    }
  }

  public void setCreationDate(Video entity, Instant creationDate) {
    if (entity != null) {
    }
  }

  public void setLastUpdatedDate(Video entity, Instant lastUpdatedDate) {
    if (entity != null) {
    }
  }

  public Map<String, Object> serialize(Video entity) {
    if (entity == null) return null;
    Map<String, Object> columnValueMap = new HashMap<>();
    columnValueMap.put("videoid", videoid.serialize(entity.getVideoid()));
    columnValueMap.put("userid", userid.serialize(entity.getUserid()));
    columnValueMap.put("description", description.serialize(entity.getDescription()));
    columnValueMap.put("location", location.serialize(entity.getLocation()));
    columnValueMap.put("location_type", locationType.serialize(entity.getLocationType()));
    columnValueMap.put("tags", tags.serialize(entity.getTags()));
    columnValueMap.put("added_date", addedDate.serialize(entity.getAddedDate()));
    columnValueMap.put("name", name.serialize(entity.getName()));
    columnValueMap.put("preview_image_location", previewImageLocation.serialize(entity.getPreviewImageLocation()));
    return columnValueMap;
  }

  public Video deserialize(Row row) {
    if (row == null) return null;
    Video entity = new Video();
    entity.setVideoid(videoid.deserialize(row));
    entity.setUserid(userid.deserialize(row));
    entity.setDescription(description.deserialize(row));
    entity.setLocation(location.deserialize(row));
    entity.setLocationType(locationType.deserialize(row));
    entity.setTags(tags.deserialize(row));
    entity.setAddedDate(addedDate.deserialize(row));
    entity.setName(name.deserialize(row));
    entity.setPreviewImageLocation(previewImageLocation.deserialize(row));
    return entity;
  }
}
