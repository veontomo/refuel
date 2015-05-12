package com.veontomo.refuel;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Provides an object-oriented approach for accessing and manipulating
 * data stored in databases.
 * Created by Andrea on 10/05/2015.
 * since 0.1
 */
abstract public class ActiveRecord {


    private static final String TAG = "ActiveRecord";

    /**
     * ActiveRecord activeRecordName. To be used in order to create a table in a database.
     * @since 0.1
     */
    private String activeRecordName = null;

    /**
     * Instance of a class that is able to save content of the Active Record.
     */
    private IStorage saver = null;

    /**
     * Returns id with which the instance has been saved.
     *
     * @return id
     * @since 0.1
     */
    public Long getId() {
        return id;
    }

    /**
     * Id of the ActiveRecord instance.
     * If is null, then the instance has not been saved yet.
     * @since 0.1
     */
    private Long id = null;

    /**
     * activeRecordName getter
     *
     * @return  value of activeRecordName
     * @since 0.1
     */
    public String getActiveRecordName() {
        return activeRecordName;
    }

    /**
     * Constructor.
     *
     * Initializes activeRecordName attribute.
     * @since 0.1
     */
    public ActiveRecord() {
        setActiveRecordName();
    }

    /**
     * Sets the value of activeRecordName field to be equal to the class's activeRecordName
     *
     * @since 0.1
     */
    private void setActiveRecordName() {
        String name = this.getClass().getSimpleName();
        System.out.println(name);
        if (name != null){
            this.activeRecordName = name;
        }
    }

    /**
     * Saves the model.
     *
     * In case of success, returns id with which the model is saved. Otherwise, null is returned.
     *
     * @return Long id of the model under which it has been saved
     * @since 0.1
     */
    public Long save(){
        return saver.save();
    }

    /**
     * Creates a json object whose keys are taken from getSaveAttributes() method.
     * @return json object
     * @throws JSONException
     */
    public JSONObject serialize() throws JSONException{
        ArrayList<String> attributes = this.getSaveAttributes();
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
     * Capitalize the first character of the string.
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

    /**
     * Returns array of field names (private, public or protected)
     * @return array of strings
     * @since 0.1
     */
    public ArrayList<String> getSaveAttributes(){
        ArrayList<String> result = new ArrayList<String>();
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields){
            result.add(field.getName());
        }
        return result;
    }



    /**
     * Returns an instance of ActiveRecord that is saved with given id.
     * @param id ActiveRecord's id
     * @return  instance of ActiveRecord or null
     */
    public static ActiveRecord findById(long id){
        return null;
    }

    /**
     * Returns key-value pairs where key is a name of attribute to save and
     * value is a type of the key.
     * @return key-value string-valued pairs
     * @since 0.1
     */
    abstract public HashMap<String, String> getStructure();

}
