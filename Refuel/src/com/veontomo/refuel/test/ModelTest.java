package com.veontomo.refuel.test;

import com.veontomo.refuel.Model;

import junit.framework.TestCase;

/**
 * Created by Andrea on 10/05/2015.
 */
public class ModelTest extends TestCase {
    private Model model = null;
    public void setUp(){
        this.model = new Model();
    }
    public void testGetName() throws Exception {
        assertEquals("wrong name", this.model.getName(), "Model");
    }
}
