package com.veontomo.refuel;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * An adapter for RefuelDataWrapper
 * @author veontomo@gmail.com
 * @since 0.1
 */
public class RefuelDataWrapperAdapter extends BaseAdapter {
	
	private static final String TAG = "Refuel";
	
	private static final int LAYOUT_TEXT = R.layout.single_refuel_one_line;

	private Context  mContext;
	
	private ArrayList<RefuelDataWrapper> data;
	
	public RefuelDataWrapperAdapter(Context context, ArrayList<RefuelDataWrapper> data) {
		this.mContext = context;
		this.data = data;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int pos) {
		return data.get(pos);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	
	
	@Override  
    public View getView(int position, View convertView, ViewGroup parent) {
		Log.i(TAG, "get view: position = " + String.valueOf(position));
		View row = convertView;
		if (row == null){
			LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				row = inflater.inflate(LAYOUT_TEXT, parent, false);
				/// fill in the row with data
		
		}
         return row;  
    }
}
