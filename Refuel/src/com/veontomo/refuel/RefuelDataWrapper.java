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

	private static final String TAG = "Refuel";
	private float precision = 0.01f;
	private Float km;
	private Float price;
	private Float paid;
	private Float quantity;
	private String fuelStationAddr;
	
	private DBSaver dbSaver;

	public RefuelDataWrapper(Float km, Float price, Float paid, Float quantity,
			String fuelStationAddr, Context context) {
		this.km = km;
		this.paid = paid;
		this.price = price;
		this.quantity = quantity;
		this.fuelStationAddr = fuelStationAddr;
		this.dbSaver = new DBSaver(context);
	}

	public long save() {
		return dbSaver.save(this.km, this.paid, this.price, this.quantity, this.fuelStationAddr);
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
