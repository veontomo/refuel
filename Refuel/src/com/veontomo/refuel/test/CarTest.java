package com.veontomo.refuel.test;

import android.util.Log;

import com.veontomo.refuel.Car;

import junit.framework.TestCase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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
        assertEquals(101138, (int) car.getKm());
    }

    public void testModelSetterGetter(){
        car.setModel("bmw");
        assertEquals( "bmw", car.getModel());
    }

    public void testNameSetterGetter(){
        car.setName("fast car");
        assertEquals("fast car", car.getName());
    }

    public void testNameIsNull(){
        assertNull(car.getName());
    }


    public void testNameIsEqualToCar() throws Exception {
        assertEquals("wrong name attribute of the class Car", car.getActiveRecordName(), "Car");
    }


    public void testSerializeModelIfExists() throws JSONException{
        car.setModel("BMW");
        JSONObject json = car.serialize();
        String model = (String) json.get("model");
        assertEquals("BMW", model);
    }

    public void testSerializeModelIfNotExists() throws JSONException{
        JSONObject json = car.serialize();
        assertFalse("key model must be absent", json.has("model"));
    }


    public void testGetAllFields(){
        ArrayList<String> fields = car.getSaveAttributes();
        assertEquals(5, fields.size());
        assertTrue("Field list must contain 'km'", fields.contains("km"));
        assertTrue("Field list must contain 'plate'", fields.contains("plate"));
        assertTrue("Field list must contain 'model'", fields.contains("model"));
        assertTrue("Field list must contain 'yearProd'", fields.contains("yearProd"));
        assertTrue("Field list must contain 'name'", fields.contains("name"));
    }


}
