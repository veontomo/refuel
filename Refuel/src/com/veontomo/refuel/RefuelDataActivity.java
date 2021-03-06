package com.veontomo.refuel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

				RefuelDataWrapper dataWrapper = new RefuelDataWrapper(km,
						price, paid, quantity, fuelStation,
						getApplicationContext());
				boolean isValid = dataWrapper.validate();
				if (isValid) {
					long id = dataWrapper.save();
					if (id != -1) {
						cleanModule();
						Intent intent = new Intent(RefuelDataActivity.this,
								ShowSingleRefuelDataActivity.class);
						intent.putExtra("ID", id);
						RefuelDataActivity.this.startActivity(intent);
					} else {
						Toast.makeText(getApplicationContext(),
								"Could not save the data", Toast.LENGTH_SHORT).show();

					}
				} else {
					Toast.makeText(getApplicationContext(), "Non valid input",
							Toast.LENGTH_SHORT).show();
				}

			}

			/**
			 * Gets string value of edit text field.
			 * 
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
			 * 
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

		Button buttonCancel = (Button) findViewById(R.id.buttonCancel);
		buttonCancel.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				cleanModule();
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
	
	/**
	 * Cleans the edit fields from user input.
	 * 
	 * @since 0.1
	 */
	public void cleanModule() {
		int[] inputFields = { R.id.kmInput, R.id.paidInput,
				R.id.priceInput, R.id.quantityInput,
				R.id.fuelStationInput };
		for (int id : inputFields) {
			EditText inputField = (EditText) findViewById(id);
			inputField.setText("");
		}

		
	}

}
