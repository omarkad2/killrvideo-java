package com.killrvideo.dse.dao;

import com.killrvideo.model.CommonConstants;
import ma.markware.charybdis.CqlTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Mutualization of code for DAO.
 *
 * @author DataStax Developer Advocates team.
 */
public abstract class DaoSupport implements CommonConstants {

    /** Loger for that class. */
    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    
    /** Code for Solr QUERY. */
    public static final String SOLR_QUERY = "solr_query";

    @Autowired
    protected CqlTemplate cqlTemplate;
    
    protected void assertNotNull(String mName, String pName, Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Assertion failed: param " + pName + " is required for method " + mName);
        }
    }

}
