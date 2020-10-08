package com.killrvideo.dse.dto;

import java.lang.String;
import ma.markware.charybdis.model.field.metadata.KeyspaceMetadata;

public class KillrVideoKeyspace_Keyspace implements KeyspaceMetadata {
  public static final KillrVideoKeyspace_Keyspace killrvideo = new KillrVideoKeyspace_Keyspace();

  public static final String KEYSPACE_NAME = "killrvideo";

  private KillrVideoKeyspace_Keyspace() {
  }

  public String getKeyspaceName() {
    return KEYSPACE_NAME;
  }
}
