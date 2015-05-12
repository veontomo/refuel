package com.veontomo.refuel;

import android.content.Context;
import android.util.Log;

/**
 * Collects information inserted by user, analyzes it and saves.
 * 
 * @author veontomo@gmail.com
 * @since 0.1
 */
public class RefuelDataWrapper implements IRefuelDataWrapper {

	/**
	 * @return the km
	 */
	public Float getKm() {
		return km;
	}

	/**
	 * @param km the km to set
	 */
	public void setKm(Float km) {
		this.km = km;
	}

	/**
	 * @return the price
	 */
	public Float getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Float price) {
		this.price = price;
	}

	/**
	 * @return the paid
	 */
	public Float getPaid() {
		return paid;
	}

	/**
	 * @param paid the paid to set
	 */
	public void setPaid(Float paid) {
		this.paid = paid;
	}

	/**
	 * @return the quantity
	 */
	public Float getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the fuelStationAddr
	 */
	public String getFuelStationAddr() {
		return fuelStationAddr;
	}

	/**
	 * @param fuelStationAddr the fuelStationAddr to set
	 */
	public void setFuelStationAddr(String fuelStationAddr) {
		this.fuelStationAddr = fuelStationAddr;
	}

	private static final String TAG = "Refuel";
	private float precision = 0.01f;
	private Float km;
	private Float price;
	private Float paid;
	private Float quantity;
	private String fuelStationAddr;
	
	private DBHelper dbSaver;

	public RefuelDataWrapper(Float km, Float price, Float paid, Float quantity,
			String fuelStationAddr, Context context) {
		this.km = km;
		this.paid = paid;
		this.price = price;
		this.quantity = quantity;
		this.fuelStationAddr = fuelStationAddr;
		//this.dbSaver = new DBHelper(context);
	}

	public long save() {
		//return dbSaver.save(this.km, this.paid, this.price, this.quantity, this.fuelStationAddr);
		return -1;
	}

	public boolean validate() {
		if (price != null && paid != null && quantity != null) {
			Log.i(TAG, String.valueOf(Math.abs(price * quantity / paid - 1)));
			return Math.abs(price * quantity / paid - 1) <= precision;
		}
		if (price != null && paid != null && quantity == null) {
			this.quantity = this.paid / this.price;
		}
		if (price != null && paid == null && quantity != null) {
			this.paid = this.quantity * this.price;
		}
		if (price == null && paid != null && quantity != null) {
			this.price = this.paid / this.quantity;
		}
		return true;
	}

	public String toString() {
		return "km: " + String.valueOf(km) + ", price: "
				+ String.valueOf(price) + ", quantity: "
				+ String.valueOf(quantity) + ", paid: " + String.valueOf(paid);
	}
	
	

}
