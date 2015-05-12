package com.veontomo.refuel;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class ShowAllRefuelDataActivity extends Activity {
	
	private DBHelper dbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_all_refuel_data);
		
		// this.dbHelper = new DBHelper(getApplicationContext());
		
		ArrayList<RefuelDataWrapper> data = this.dbHelper.getAll();

		ListView list = (ListView) findViewById(R.id.allRefuelDataList);

		View header = (View) getLayoutInflater().inflate(
				R.layout.single_refuel_one_line, null);

		list.addHeaderView(header);
		RefuelDataWrapperAdapter mAdapter = new RefuelDataWrapperAdapter(
				getApplicationContext(), data);
		list.setAdapter(mAdapter);
		mAdapter.notifyDataSetChanged();
		
		Button buttonInsertNew = (Button) findViewById(R.id.all_buttonInsertNew);
		buttonInsertNew.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				Intent intent = new Intent(ShowAllRefuelDataActivity.this,
						RefuelDataActivity.class);
				ShowAllRefuelDataActivity.this.startActivity(intent);

			}
		});
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_all_refuel_data, menu);
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
