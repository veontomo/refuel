package com.veontomo.refuel.test;

import com.veontomo.refuel.ActiveRecord;

import junit.framework.TestCase;

import org.json.JSONObject;


/**
 * ActiveRecord's class tests
 * @author veontomo@gmail.com
 * @since 0.1
 */
public class ActiveRecordTest extends TestCase {

    private ActiveRecord model = null;

    public void setUp(){
    }

    public void testGetIdOfNewRecord() throws Exception {
        assertNull("id must be null of a new record", this.model.getId());
    }

/*
    public void testGetIdOfExistingRecord() throws Exception {
        this.activeRecord.save();
        assertNotNull("id must be not null of existing record", this.activeRecord.getId());
    }
*/

}
