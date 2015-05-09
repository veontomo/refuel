package com.veontomo.refuel;

/**
 * Represents information about a car
 * Created by Andrea on 09/05/2015.
 */
public class Car {
    /**
     * Car model.
     * Example: BMW 320
     * @since 0.1
     */
    private String model = null;

    /**
     * Car plate
     * @since 0.1
     */
    private String plate = null;

    /**
     * Car's name (how to owner refers to it)
     * @since 0.1
     */
    private String name = null;

    /**
     * Production year
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



}
