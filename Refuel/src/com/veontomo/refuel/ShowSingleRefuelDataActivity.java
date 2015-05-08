package com.veontomo.refuel;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ShowSingleRefuelDataActivity extends Activity {

	private static final String TAG = "Refuel";
	
	private static final String KEY_ID = "ID";
	
	private DBHelper dbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i(TAG, "inside single refuel");
		super.onCreate(savedInstanceState);
		this.dbHelper = new DBHelper(getApplicationContext());
		Log.i(TAG, "dbHelper is initialized");
		setContentView(R.layout.activity_show_single_refuel_data);
		Intent intent = getIntent();
		Log.i(TAG, "intent is initialized");
		long id = intent.getLongExtra(KEY_ID, -1);
		Log.i(TAG, "id: " + String.valueOf(id));
		if (id == -1) {
			Toast.makeText(getApplicationContext(), R.string.invalidToken,
					Toast.LENGTH_SHORT).show();
			return;
		}
		ContentValues values = dbHelper.getById(id);
		
		if (values == null) {
			Log.i(TAG, "values is null");
			Toast.makeText(getApplicationContext(), R.string.noDataAvailable,
					Toast.LENGTH_SHORT).show();
			return;
		}
		
		this.fillIn(values);
		
		
		Button buttonInsertNew = (Button) findViewById(R.id.buttonInsertNew);
		buttonInsertNew.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ShowSingleRefuelDataActivity.this,
						RefuelDataActivity.class);
				ShowSingleRefuelDataActivity.this.startActivity(intent);
				
			}
		});
		
		Button buttonShowAll = (Button) findViewById(R.id.buttonShowAll);
		buttonShowAll.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ShowSingleRefuelDataActivity.this,
						ShowAllRefuelDataActivity.class);
				ShowSingleRefuelDataActivity.this.startActivity(intent);
				
			}
		});

		
		Button buttonDelete = (Button) findViewById(R.id.buttonDelete);
		buttonDelete.setOnClickListener(new OnClickListener() {
			
			@Override 
			public void onClick(View v) {
				Intent intent = getIntent();
				long id = intent.getLongExtra(KEY_ID, -1);
				if (id != -1){
					if (dbHelper.deleteById(id)){
						Toast.makeText(getApplicationContext(), "deleted id " + String.valueOf(id), Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(getApplicationContext(), "failed to delete id " + String.valueOf(id), Toast.LENGTH_SHORT).show();
					}
				}
				Intent mInsertDataIntent = new Intent(ShowSingleRefuelDataActivity.this,
						RefuelDataActivity.class);
				ShowSingleRefuelDataActivity.this.startActivity(mInsertDataIntent);

				
			}
		});


	}
	/**
	 * Fills in activity's layout with data.
	 * @since 0.1
	 * @param data
	 */
	private void fillIn(ContentValues data) {
		Log.i(TAG, "inside fillIn");
		TextView kmView = (TextView) findViewById(R.id.kmValue);
		kmView.setText(data.getAsString("km"));
		
		TextView priceView = (TextView) findViewById(R.id.priceValue);
		priceView.setText(data.getAsString("price"));

		TextView quantityView = (TextView) findViewById(R.id.quantityValue);
		quantityView.setText(data.getAsString("qty"));
		
		TextView paidView = (TextView) findViewById(R.id.paidValue);
		paidView.setText(data.getAsString("paid"));

        TextView addressView = (TextView) findViewById(R.id.addressValue);
        addressView.setText(data.getAsString("address"));


    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_single_refuel_data, menu);
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
