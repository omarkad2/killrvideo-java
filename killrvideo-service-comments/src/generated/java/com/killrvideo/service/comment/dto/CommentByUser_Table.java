package com.killrvideo.service.comment.dto;

import com.datastax.oss.driver.api.core.cql.Row;
import java.lang.Class;
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

public class CommentByUser_Table implements TableMetadata<CommentByUser> {
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

  public static final ColumnMetadata<UUID, UUID> videoid = new ColumnMetadata<UUID, UUID>() {
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
  };

  public static final ClusteringKeyColumnMetadata<UUID, UUID> commentid = new ClusteringKeyColumnMetadata<UUID, UUID>() {
    public String getName() {
      return "commentid";
    }

    public Class getFieldClass() {
      return java.util.UUID.class;
    }

    public UUID serialize(UUID field) {
      return field;
    }

    public UUID deserialize(Row row) {
      if (row == null || row.isNull("commentid")) return null;
      return row.get("commentid", java.util.UUID.class);
    }

    public int getClusteringKeyIndex() {
      return 0;
    }

    public ClusteringOrder getClusteringOrder() {
      return ClusteringOrder.ASC;
    }
  };

  public static final ColumnMetadata<String, String> comment = new ColumnMetadata<String, String>() {
    public String getName() {
      return "comment";
    }

    public Class getFieldClass() {
      return java.lang.String.class;
    }

    public String serialize(String field) {
      return field;
    }

    public String deserialize(Row row) {
      if (row == null || row.isNull("comment")) return null;
      return row.get("comment", java.lang.String.class);
    }
  };

  public static final CommentByUser_Table comments_by_user = new CommentByUser_Table();

  public static final String KEYSPACE_NAME = "killrvideo";

  public static final String TABLE_NAME = "comments_by_user";

  private CommentByUser_Table() {
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
    results.put("videoid", videoid);
    results.put("commentid", commentid);
    results.put("comment", comment);
    return results;
  }

  public Map<String, ColumnMetadata> getPartitionKeyColumns() {
    Map<String, ColumnMetadata> results = new HashMap<>();
    results.put("userid", userid);
    return results;
  }

  public Map<String, ColumnMetadata> getClusteringKeyColumns() {
    Map<String, ColumnMetadata> results = new HashMap<>();
    results.put("commentid", commentid);
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

  public void setGeneratedValues(CommentByUser entity) {
    if (entity != null) {
    }
  }

  public void setCreationDate(CommentByUser entity, Instant creationDate) {
    if (entity != null) {
    }
  }

  public void setLastUpdatedDate(CommentByUser entity, Instant lastUpdatedDate) {
    if (entity != null) {
    }
  }

  public Map<String, Object> serialize(CommentByUser entity) {
    if (entity == null) return null;
    Map<String, Object> columnValueMap = new HashMap<>();
    columnValueMap.put("userid", userid.serialize(entity.getUserid()));
    columnValueMap.put("videoid", videoid.serialize(entity.getVideoid()));
    columnValueMap.put("commentid", commentid.serialize(entity.getCommentid()));
    columnValueMap.put("comment", comment.serialize(entity.getComment()));
    return columnValueMap;
  }

  public CommentByUser deserialize(Row row) {
    if (row == null) return null;
    CommentByUser entity = new CommentByUser();
    entity.setUserid(userid.deserialize(row));
    entity.setVideoid(videoid.deserialize(row));
    entity.setCommentid(commentid.deserialize(row));
    entity.setComment(comment.deserialize(row));
    return entity;
  }
}
