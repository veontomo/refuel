package com.veontomo.refuel;

/**
 * Represents information about gas (petrol)
 * Created by Andrea on 09/05/2015.
 */
public class Fuel {

    /**
     * Name of the fuel (i.e. petrol, diesel, gpl)
     * @since 0.1
     */
    private String name = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Fuel(String name) {
        this.name = name;
    }
}
