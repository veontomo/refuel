package com.veontomo.refuel.test;

import com.veontomo.refuel.ActiveRecord;

import junit.framework.TestCase;


/**
 * ActiveRecord's class tests
 * @author veontomo@gmail.com
 * @since 0.1
 */
public class ActiveRecordTest extends TestCase {
    private ActiveRecord activeRecord = null;
    public void setUp(){
        this.activeRecord = new ActiveRecord();
    }
    public void testGetName() throws Exception {
        assertEquals("wrong name", this.activeRecord.getName(), "ActiveRecord");
    }

    public void testGetNameOfSubClass() throws Exception {
        class Foo extends ActiveRecord {}
        Foo foo = new Foo();
        assertEquals("wrong name attribute in subclass", foo.getName(), "Foo");
    }

    public void testGetIdOfNewRecord() throws Exception {
        assertNull("id must be null of a new record", this.activeRecord.getId());
    }

//  public void testGetIdOfExistingRecord() throws Exception {
//        this.activeRecord.save();
//        assertNotNull("id must be not null of existing record", this.activeRecord.getId());
//    }


}
