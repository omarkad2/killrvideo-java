package com.killrvideo.service.user.dto;

import com.datastax.oss.driver.api.core.cql.Row;
import java.lang.Class;
import java.lang.Object;
import java.lang.String;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import ma.markware.charybdis.model.field.metadata.ColumnMetadata;
import ma.markware.charybdis.model.field.metadata.PartitionKeyColumnMetadata;
import ma.markware.charybdis.model.field.metadata.TableMetadata;
import ma.markware.charybdis.model.option.ConsistencyLevel;
import ma.markware.charybdis.model.option.SerialConsistencyLevel;

public class User_Table implements TableMetadata<User> {
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

  public static final ColumnMetadata<String, String> firstname = new ColumnMetadata<String, String>() {
    public String getName() {
      return "firstname";
    }

    public Class getFieldClass() {
      return java.lang.String.class;
    }

    public String serialize(String field) {
      return field;
    }

    public String deserialize(Row row) {
      if (row == null || row.isNull("firstname")) return null;
      return row.get("firstname", java.lang.String.class);
    }
  };

  public static final ColumnMetadata<String, String> lastname = new ColumnMetadata<String, String>() {
    public String getName() {
      return "lastname";
    }

    public Class getFieldClass() {
      return java.lang.String.class;
    }

    public String serialize(String field) {
      return field;
    }

    public String deserialize(Row row) {
      if (row == null || row.isNull("lastname")) return null;
      return row.get("lastname", java.lang.String.class);
    }
  };

  public static final ColumnMetadata<String, String> email = new ColumnMetadata<String, String>() {
    public String getName() {
      return "email";
    }

    public Class getFieldClass() {
      return java.lang.String.class;
    }

    public String serialize(String field) {
      return field;
    }

    public String deserialize(Row row) {
      if (row == null || row.isNull("email")) return null;
      return row.get("email", java.lang.String.class);
    }
  };

  public static final ColumnMetadata<Date, Date> createdAt = new ColumnMetadata<Date, Date>() {
    public String getName() {
      return "created_date";
    }

    public Class getFieldClass() {
      return java.util.Date.class;
    }

    public Date serialize(Date field) {
      return field;
    }

    public Date deserialize(Row row) {
      if (row == null || row.isNull("created_date")) return null;
      return row.get("created_date", java.util.Date.class);
    }
  };

  public static final User_Table users = new User_Table();

  public static final String KEYSPACE_NAME = "killrvideo";

  public static final String TABLE_NAME = "users";

  private User_Table() {
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
    results.put("firstname", firstname);
    results.put("lastname", lastname);
    results.put("email", email);
    results.put("created_date", createdAt);
    return results;
  }

  public Map<String, ColumnMetadata> getPartitionKeyColumns() {
    Map<String, ColumnMetadata> results = new HashMap<>();
    results.put("userid", userid);
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

  public void setGeneratedValues(User entity) {
    if (entity != null) {
    }
  }

  public void setCreationDate(User entity, Instant creationDate) {
    if (entity != null) {
    }
  }

  public void setLastUpdatedDate(User entity, Instant lastUpdatedDate) {
    if (entity != null) {
    }
  }

  public Map<String, Object> serialize(User entity) {
    if (entity == null) return null;
    Map<String, Object> columnValueMap = new HashMap<>();
    columnValueMap.put("userid", userid.serialize(entity.getUserid()));
    columnValueMap.put("firstname", firstname.serialize(entity.getFirstname()));
    columnValueMap.put("lastname", lastname.serialize(entity.getLastname()));
    columnValueMap.put("email", email.serialize(entity.getEmail()));
    columnValueMap.put("created_date", createdAt.serialize(entity.getCreatedAt()));
    return columnValueMap;
  }

  public User deserialize(Row row) {
    if (row == null) return null;
    User entity = new User();
    entity.setUserid(userid.deserialize(row));
    entity.setFirstname(firstname.deserialize(row));
    entity.setLastname(lastname.deserialize(row));
    entity.setEmail(email.deserialize(row));
    entity.setCreatedAt(createdAt.deserialize(row));
    return entity;
  }
}
