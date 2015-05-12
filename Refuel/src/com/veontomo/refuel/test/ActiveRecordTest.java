package com.veontomo.refuel.test;

import android.content.ContentValues;

import com.veontomo.refuel.ActiveRecord;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * ActiveRecord's class tests
 * @author veontomo@gmail.com
 * @since 0.1
 */
public class ActiveRecordTest extends TestCase {

    private class Foo0 extends ActiveRecord{
        public HashMap<String, String> getStructure(){
            return null;
        }
    }


    private class Foo2 extends ActiveRecord{
        // a public field with getter
        public int attrInt = 1;
        // a private field with getter
        private Double attrDouble = 1.3d;
        // a public field without getter
        public Integer attrInteger = null;
        // a private field without getter
        private double attrDoub = 23.9d;

        public int getAttrInt() {
            return attrInt;
        }
        public Double getAttrDouble() {
            return attrDouble;
        }

        public HashMap<String, String> getStructure(){
            return null;
        }
    }

    public void setUp(){
    }

    public void testGetSaveAttributes0(){
        ArrayList<String> list = (new Foo0()).getSaveAttributes();
        //assertEquals("save-attribute-list must be empty", 0, list.size());
        String[] arr = new String[1];
        list.toArray(arr);
        assertEquals("show the key name", "---", arr[0]);
    }

    public void testGetSaveAttributes4(){
        ArrayList<String> list = (new Foo2()).getSaveAttributes();
        assertEquals("save-attribute-list must have 4 attributes", 4, list.size());
        assertTrue("save-attribute-list must contain attribute \"attrInt\"", list.contains("attrInt"));
        assertTrue("save-attribute-list must contain attribute \"attrDouble\"", list.contains("attrDouble"));
        assertTrue("save-attribute-list must contain attribute \"attrInteger\"", list.contains("attrInteger"));
        assertTrue("save-attribute-list must contain attribute \"attrDoub\"", list.contains("attrDoub"));
    }


    public void testSerializeClassWithNoFields() throws Exception {
        ContentValues contentValues = (new Foo0()).serialize();
        assertEquals("the content must contain no keys", 0, contentValues.size());
    }


    public void testSerializeClassWith2Fields() throws Exception {
        ContentValues contentValues = (new Foo2()).serialize();
        assertEquals("the content must contain 2 keys", 2, contentValues.size());
        assertEquals("the content must contain key \"attrInt\" with value 1", 1, contentValues.get("attrInt"));
        assertEquals(1.3, (Double) contentValues.get("attrDouble"), 0.01d);
    }


}
