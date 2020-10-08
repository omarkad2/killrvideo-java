package com.killrvideo.service.user.dto;

import com.datastax.oss.driver.api.core.cql.Row;
import java.lang.Class;
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

public class UserCredentials_Table implements TableMetadata<UserCredentials> {
  public static final PartitionKeyColumnMetadata<String, String> email = new PartitionKeyColumnMetadata<String, String>() {
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

    public int getPartitionKeyIndex() {
      return 0;
    }
  };

  public static final ColumnMetadata<String, String> password = new ColumnMetadata<String, String>() {
    public String getName() {
      return "password";
    }

    public Class getFieldClass() {
      return java.lang.String.class;
    }

    public String serialize(String field) {
      return field;
    }

    public String deserialize(Row row) {
      if (row == null || row.isNull("password")) return null;
      return row.get("password", java.lang.String.class);
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

  public static final UserCredentials_Table user_credentials = new UserCredentials_Table();

  public static final String KEYSPACE_NAME = "killrvideo";

  public static final String TABLE_NAME = "user_credentials";

  private UserCredentials_Table() {
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
    results.put("email", email);
    results.put("password", password);
    results.put("userid", userid);
    return results;
  }

  public Map<String, ColumnMetadata> getPartitionKeyColumns() {
    Map<String, ColumnMetadata> results = new HashMap<>();
    results.put("email", email);
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

  public void setGeneratedValues(UserCredentials entity) {
    if (entity != null) {
    }
  }

  public void setCreationDate(UserCredentials entity, Instant creationDate) {
    if (entity != null) {
    }
  }

  public void setLastUpdatedDate(UserCredentials entity, Instant lastUpdatedDate) {
    if (entity != null) {
    }
  }

  public Map<String, Object> serialize(UserCredentials entity) {
    if (entity == null) return null;
    Map<String, Object> columnValueMap = new HashMap<>();
    columnValueMap.put("email", email.serialize(entity.getEmail()));
    columnValueMap.put("password", password.serialize(entity.getPassword()));
    columnValueMap.put("userid", userid.serialize(entity.getUserid()));
    return columnValueMap;
  }

  public UserCredentials deserialize(Row row) {
    if (row == null) return null;
    UserCredentials entity = new UserCredentials();
    entity.setEmail(email.deserialize(row));
    entity.setPassword(password.deserialize(row));
    entity.setUserid(userid.deserialize(row));
    return entity;
  }
}
