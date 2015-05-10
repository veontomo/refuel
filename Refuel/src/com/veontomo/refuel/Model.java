package com.veontomo.refuel;

/**
 * Provides an object-oriented approach for accessing and manipulating
 * data stored in databases.
 * Created by Andrea on 10/05/2015.
 * since 0.1
 */
public class Model {
    /**
     * Model name. To be used in order create a table in a database.
     * @since 0.1
     */
    private String name = null;

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
    public Model() {
        setName();
    }

    /**
     * Sets the value of name field to be equal to the class's name
     *
     * @since 0.1
     */
    private void setName() {
        String name = getClass().getName();
        if (name != null){
            this.name = name;
        }

    }
}
