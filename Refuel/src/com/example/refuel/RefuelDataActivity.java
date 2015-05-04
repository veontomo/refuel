package com.example.refuel;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class RefuelDataActivity extends Activity {

	protected static final String TAG = "Refuel";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_refuel_data);

		Button buttonSave = (Button) findViewById(R.id.buttonSave);
		buttonSave.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Float km = getInputAsFloat(R.id.kmInput);
				Float price = getInputAsFloat(R.id.priceInput);
				Float paid = getInputAsFloat(R.id.paidInput);
				Float quantity = getInputAsFloat(R.id.quantityInput);
				String fuelStation = getInputAsString(R.id.fuelStationInput);
				
				RefuelDataWrapper dataWrapper = new RefuelDataWrapper(km, price, paid, quantity, fuelStation);
				dataWrapper.validate();
				dataWrapper.save();

				Log.i(TAG, "data" + String.valueOf(km) + price + paid
						+ quantity + fuelStation);
				
				String addr = ((EditText) findViewById(R.id.fuelStationInput)).getEditableText().toString();
				Log.i(TAG, addr);

			}

			/**
			 * Gets string value of edit text field. 
			 * @param int id
			 * @return String
			 * @since 0.1
			 */
			private String getInputAsString(int id) {
				EditText inputView = (EditText) findViewById(id);
				String inputValue = null;
				if (inputView != null) {
					Editable editable = (Editable) inputView.getEditableText();
					if (editable != null) {
						inputValue = editable.toString();
					}
				}
				Log.i(TAG, "returning " + inputValue);
				return inputValue; 

			}

			/**
			 * Gets float value of edit text field. 
			 * @param int id
			 * @return String
			 * @since 0.1
			 */
			private Float getInputAsFloat(int id) {
				String inputValue = getInputAsString(id);
				Float result = null;
				try {
					result = Float.parseFloat(inputValue);
				} catch (NullPointerException e) {
					Log.i(TAG, "null pointer exception detected");
					result = null;
				} catch (NumberFormatException e) {
					Log.i(TAG, "number format exception detected");
					result = null;
				}
				return result;
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.refuel_data, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
