package com.veontomo.refuel;

import android.content.ContentValues;
import android.content.Context;
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
        this.saver = new DBHelper(this.getStructure());
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
     * If the model contains attribute id, then in fact the instance gets updated.
     * Otherwise it gets saved.
     *
     * In case of success, returns id with which the model is saved. Otherwise, null is returned.
     *
     * @return Long id of the model under which it has been saved
     * @since 0.1
     */
    public Long save(){
        ContentValues data = this.serialize();
        Long id = this.getId();
        if (id == null){
            return saver.save(getActiveRecordName(), data);
        }
        Boolean outcome = saver.update(getActiveRecordName(), id, data);
        if (outcome) {
            return id;
        }
        return null;
    }

    /**
     * Returns true if the instance is a new one, false otherwise.
     *
     * An Active Record instance is considered a new one if it has no id (and hence, it has
     * not been saved yet).
     * @return whether the instance has already been saved
     * @since 0.1
     */
    private boolean isNew() {
        return false;
    }

    /**
     * Returns a ContentValues instance with keys taken from getSaveAttributes() method and values
     * taken from corresponding getter.
     * @return ContentValues instance
     */
    public ContentValues serialize(){
        ArrayList<String> attributes = this.getSaveAttributes();
        ContentValues contentValues = new ContentValues();
        for (String attr : attributes){
            String attrCapitalized = capitalize(attr);
            if (attrCapitalized != null){
                String getterName = "get" + attrCapitalized;
                try {
                    Method method = this.getClass().getDeclaredMethod(getterName, null);
                    Class returnType = method.getReturnType();
                    Object value = method.invoke(this);
                    if (value != null){
                        if (value instanceof Integer){
                            contentValues.put(attr, (Integer) value);
                        } else if (value instanceof Float){
                            contentValues.put(attr, (Float) value);
                        } else if (value instanceof Double){
                            contentValues.put(attr, (Double) value);
                        } else if (value instanceof Character || value instanceof String){
                            contentValues.put(attr, (String) value);
                        } else if (value instanceof Boolean){
                            contentValues.put(attr, (Boolean) value);
                        }
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
        return contentValues;
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
