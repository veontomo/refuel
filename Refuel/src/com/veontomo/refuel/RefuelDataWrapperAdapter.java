package com.veontomo.refuel;

import java.util.ArrayList;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

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
				this.inflateThumbnailLayout(row, this.data.get(position));
				/// fill in the row with data
		
		}
         return row;  
    }
	
	
	private void inflateThumbnailLayout(View row, RefuelDataWrapper data) {
		TextView kmView = (TextView) row.findViewById(R.id.oneLineKm);
		kmView.setText(String.valueOf(data.getKm()));
		TextView priceView = (TextView) row.findViewById(R.id.oneLinePrice);
		priceView.setText(String.valueOf(data.getPrice()));
		TextView paidView = (TextView) row.findViewById(R.id.oneLinePaid);
		paidView.setText(String.valueOf(data.getPaid()));


	}
}
