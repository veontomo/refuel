package com.veontomo.refuel.test;

import com.veontomo.refuel.Car;

import junit.framework.TestCase;

/**
 * Car's class tests
 * @author veontomo@gmail.com
 * @since 0.1
 */
public class CarTest extends TestCase {
    Car model = null;

    public void setUp(){
        this.model = new Car();
    }



    public void testKmSetterGetter(){
        model.setKm(101138);
        assertEquals((int) model.getKm(), 101138);
    }

    public void testModelSetterGetter(){
        model.setModel("bmw");
        assertEquals(model.getModel(), "bmw");
    }

    public void testNameSetterGetter(){
        model.setName("fast car");
        assertEquals(model.getName(), "fast car");
    }

    public void testNameIsNull(){
        assertNull(model.getName());
    }


    public void testNameIsEqualToCar() throws Exception {
        assertEquals("wrong name attribute of the class Car", model.getActiveRecordName(), "Car");
    }

}
