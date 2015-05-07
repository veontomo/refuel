package com.veontomo.refuel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

	private SQLiteDatabase database;

	private static final String DATABASE_NAME = "Refuel";

	private static final int DATABASE_VERSION = 1;

	private static final String TAG = "Refuel";

	private static final String TABLE_NAME = "RefuelData";

	private static final String COLUMN_ID = "_id";

	private static final String COLUMN_KM = "km";

	private static final String COLUMN_QUANTITY = "qty";

	private static final String COLUMN_PRICE = "price";

	private static final String COLUMN_PAID = "paid";

	private static final String COLUMN_FUELTYPE = "fuel_type";

	private static final String COLUMN_FUELSTATION = "address";

	private static final String[] allColumns = { COLUMN_ID, COLUMN_KM,
			COLUMN_QUANTITY, COLUMN_PRICE, COLUMN_PAID, COLUMN_FUELTYPE,
			COLUMN_FUELSTATION };

	private static final String DATABASE_CREATE = "create table " + TABLE_NAME
			+ "(" + COLUMN_ID + " integer primary key autoincrement, "
			+ COLUMN_KM + " float, " 
			+ COLUMN_QUANTITY + " float not null, "
			+ COLUMN_PRICE + " float not null, " 
			+ COLUMN_PAID + " float not null, " 
			+ COLUMN_FUELTYPE + " tinyint unsigned, "
			+ COLUMN_FUELSTATION + " varchar(255)" + ");";

	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		open();
	}

	public void onCreate(SQLiteDatabase database) {
		Log.i(TAG, "creating db");
		database.execSQL(DATABASE_CREATE);
	}

	public void open() throws SQLException {
		database = this.getWritableDatabase();
	}

	public void close() {
		super.close();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
				+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}

	public long save(Float km, Float paid, Float price, Float quantity,
			String fuelStationAddr) {
		ContentValues values = new ContentValues();
		long insertId;
		values.put(COLUMN_KM, km);
		values.put(COLUMN_PAID, paid);
		values.put(COLUMN_PRICE, price);
		values.put(COLUMN_QUANTITY, quantity);
		values.put(COLUMN_FUELTYPE, 1);
		values.put(COLUMN_FUELSTATION, fuelStationAddr);
		insertId = database.insert(TABLE_NAME, null, values);
		if (insertId == -1) {
			Log.i(TAG, "problem with saving " + km + ", " + paid + ", " + price
					+ ", " + quantity + ", " + fuelStationAddr);
		}

		return insertId;
	}

	public ContentValues getById(long id) {
		ContentValues result =  new ContentValues();;
		if (this.database == null) {
			return null;
		}

		Cursor cursor = this.database.query(TABLE_NAME, allColumns, COLUMN_ID + " = ?",
				new String[] { String.valueOf(id) }, null, null, null, null);

		if (cursor == null) {
			return null;
		}
		cursor.moveToFirst();
		result.put(COLUMN_ID, Long.parseLong(cursor.getString(0)));
		result.put(COLUMN_KM, Float.parseFloat(cursor.getString(1)));
		result.put(COLUMN_PAID, Float.parseFloat(cursor.getString(4)));
		result.put(COLUMN_PRICE, Float.parseFloat(cursor.getString(3)));
		result.put(COLUMN_QUANTITY, Float.parseFloat(cursor.getString(2)));
		result.put(COLUMN_FUELTYPE, Integer.parseInt(cursor.getString(5)));
		result.put(COLUMN_FUELSTATION, cursor.getString(6));
		
		return result;
		
	}

	/**
	 * Deletes record with given id.
	 * 
	 * @param id
	 * @since 0.1
	 */
	public void deleteById(long id) {
		/// !!! stub
		Log.i(TAG, "deleting record with id " + String.valueOf(id));
		
	}
}
