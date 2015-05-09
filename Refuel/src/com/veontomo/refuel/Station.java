package com.veontomo.refuel;

/**
 * Represents information about gas (petrol) station
 * Created by Andrea on 09/05/2015.
 */
public class Station {
    /**
     * Name of the gas station
     * @since 0.1
     */
    private String name = null;

    /**
     * Geographical address of the station
     * @since 0.1
     */
    private String address = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
