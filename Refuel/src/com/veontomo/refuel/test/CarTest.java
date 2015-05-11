package com.veontomo.refuel.test;

import com.veontomo.refuel.Car;

import junit.framework.TestCase;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Car's class tests
 * @author veontomo@gmail.com
 * @since 0.1
 */
public class CarTest extends TestCase {
    Car car = null;

    public void setUp(){
        this.car = new Car();
    }



    public void testKmSetterGetter(){
        car.setKm(101138);
        assertEquals((int) car.getKm(), 101138);
    }

    public void testModelSetterGetter(){
        car.setModel("bmw");
        assertEquals(car.getModel(), "bmw");
    }

    public void testNameSetterGetter(){
        car.setName("fast car");
        assertEquals(car.getName(), "fast car");
    }

    public void testNameIsNull(){
        assertNull(car.getName());
    }


    public void testNameIsEqualToCar() throws Exception {
        assertEquals("wrong name attribute of the class Car", car.getActiveRecordName(), "Car");
    }


    public void testSerializeModel() throws JSONException{
        car.setModel("BMW");
        JSONObject json = car.serialize();
        String model = (String) json.get("model");
        assertEquals(model, "BMW");

    }

}
