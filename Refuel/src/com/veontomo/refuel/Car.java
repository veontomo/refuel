package com.veontomo.refuel;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Represents information about a car
 * Created by Andrea on 09/05/2015.
 */
public class Car extends ActiveRecord {
    private static final String TAG = "Car";
    /**
     * Car model.
     * Example: BMW 320
     *
     * @since 0.1
     */
    private String model = null;

    /**
     * Car plate
     *
     * @since 0.1
     */
    private String plate = null;

    /**
     * Car's name (how to owner refers to it)
     *
     * @since 0.1
     */
    private String name = null;

    /**
     * Production year
     *
     * @since 0.1
     */
    private Integer yearProd = null;

    /**
     * How many km the car has run from time of its production.
     */
    private Integer km = null;


    public Integer getKm() {
        return km;
    }

    public void setKm(Integer km) {
        this.km = km;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYearProd() {
        return yearProd;
    }

    public void setYearProd(Integer yearProd) {
        this.yearProd = yearProd;
    }

    public Long save() {
        return null;
    }

    public JSONObject serialize() throws JSONException{
        String[] attributes = {"model", "plate", "name", "yearProd", "km"};
        JSONObject json = new JSONObject();
        json.put("activeRecordName", this.getActiveRecordName());
        for (String attr : attributes){
            String attrCapitalized = capitalize(attr);
            if (attrCapitalized != null){
                String getterName = "get" + attrCapitalized;
                try {
                    Method method = this.getClass().getDeclaredMethod(getterName, null);
                    Object value = method.invoke(this);
                    if (value != null){
                        json.put(attr, value);
                    }

                } catch (NoSuchMethodException e) {
                    Log.d(TAG, "method " + getterName + " is not found.");
                }
                catch (InvocationTargetException e){
                    Log.d(TAG, "Invocation target exception for method " + getterName);
                } catch (IllegalAccessException e){
                    Log.d(TAG, "Illegal access exception for method " + getterName);
                }
            }
        }
        return json;
    }

    /**
     * Capitalaze the first character of the string.
     *
     * Distinguishes between empty strings, single and multi character ones.
     * @param str
     * @return  a string with first character capitalized
     * @since 0.1
     */
    private String capitalize(String str){
        if (str == null || str.equals("")){
            return str;
        }
        if (str.length() == 1){
            return str.toUpperCase();
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public Car() {
        super();
    }



}
