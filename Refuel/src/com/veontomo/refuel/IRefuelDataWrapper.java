package com.veontomo.refuel;

/**
 * Interface for getting and saving data that the user inserts on the RefuelData Intent.
 * @author veontomo@gmail.com
 * @since 0.1
 *
 */
public interface IRefuelDataWrapper {
	/**
	 * Saves the information about single refuel.
	 * Returns an id by means of which it would be possible to 
	 * determine whether the saving is successful or not. 
	 * @return long
	 * @since 0.1
	 */
	public long save();
	
	
	/**
	 * Validates the information about single refuel.
	 * 
	 * Tries to calculate missing pieces of data (i.e. given price and quantity, calculate paid amount) 
	 * @return boolean
	 * @since 0.1
	 */
	public boolean validate();
}
