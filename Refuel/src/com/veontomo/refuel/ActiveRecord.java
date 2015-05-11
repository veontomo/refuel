package com.veontomo.refuel;

import org.json.JSONObject;

/**
 * Provides an object-oriented approach for accessing and manipulating
 * data stored in databases.
 * Created by Andrea on 10/05/2015.
 * since 0.1
 */
abstract public class ActiveRecord {
    /**
     * ActiveRecord activeRecordName. To be used in order to create a table in a database.
     * @since 0.1
     */
    private String activeRecordName = null;

    /**
     * Instance of a class that is able to save content of the Active Record.
     */
    private IStorage dbHelper = null;

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
     * @return Long id of the model
     * @since 0.1
     */
    public Long save(){
        return null;
    }

    /**
     * Returns json object containing information that should be saved.
     * @return json object
     * @since 0.1
     */
    abstract public JSONObject serialize();


    /**
     * Returns an instance of ActiveRecord that is saved with given id.
     * @param id ActiveRecord's id
     * @return  instance of ActiveRecord or null
     */
    public static ActiveRecord findById(long id){
        return null;
    }

}
