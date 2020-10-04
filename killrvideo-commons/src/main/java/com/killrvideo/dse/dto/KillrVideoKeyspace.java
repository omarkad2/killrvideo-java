package com.killrvideo.dse.dto;

import static com.killrvideo.model.CommonConstants.KILLRVIDEO_KEYSPACE;

import ma.markware.charybdis.model.annotation.Keyspace;

@Keyspace(name = KILLRVIDEO_KEYSPACE, replicationFactor = 1)
public class KillrVideoKeyspace {

}
