package com.veontomo.refuel.test;

import com.veontomo.refuel.Model;

import junit.framework.TestCase;


/**
 * Model's class tests
 * @author veontomo@gmail.com
 * @since 0.1
 */
public class ModelTest extends TestCase {
    private Model model = null;
    public void setUp(){
        this.model = new Model();
    }
    public void testGetName() throws Exception {
        assertEquals("wrong name", this.model.getName(), "Model");
    }

    public void testGetNameOfSubClass() throws Exception {
        class Foo extends Model{}
        Foo foo = new Foo();
        assertEquals("wrong name attribute in subclass", foo.getName(), "Foo");
    }

    public void testGetIdOfNewRecord() throws Exception {
        assertNull("id must be null of a new record", this.model.getId());
    }

    public void testGetIdOfExistingRecord() throws Exception {
        this.model.save();
        assertNotNull("id must be not null of existing record", this.model.getId());
    }


}
