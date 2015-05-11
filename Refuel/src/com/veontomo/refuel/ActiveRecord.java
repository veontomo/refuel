package com.veontomo.refuel;

/**
 * Provides an object-oriented approach for accessing and manipulating
 * data stored in databases.
 * Created by Andrea on 10/05/2015.
 * since 0.1
 */
public class ActiveRecord {
    /**
     * ActiveRecord name. To be used in order create a table in a database.
     * @since 0.1
     */
    private String name = null;

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
     * name getter
     *
     * @return  value of name
     * @since 0.1
     */
    public String getName() {
        return name;
    }

    /**
     * Constructor.
     *
     * Initializes name attribute.
     * @since 0.1
     */
    public ActiveRecord() {
        setName();
    }

    /**
     * Sets the value of name field to be equal to the class's name
     *
     * @since 0.1
     */
    private void setName() {
        String name = this.getClass().getSimpleName();
        if (name != null){
            this.name = name;
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
        // !!! stub
        return null;
    }


    /**
     * Returns an instance of ActiveRecord that is saved with given id.
     * @param id ActiveRecord's id
     * @return  instance of ActiveRecord or null
     */
    public static ActiveRecord findById(long id){
        return null;
    }

}
