package com.microacademylabs.bigwordfinder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Karen Freeman-Smith on 6/1/2017.
 */

public class LetterAdapter extends BaseAdapter {
  private Context mContext;
  private String[] mLetters;

  public LetterAdapter (Context context, String[] letters) {
    this.mContext = context;
    //Convert List to String[]?
    this.mLetters = letters;
  }

  @Override
  public int getCount() {
    return mLetters.length;
  }

  @Override
  public Object getItem(int position) {
    return null;
  }

  @Override
  public long getItemId(int position) {
    return 0;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View gridView;
    if(convertView==null) {
      gridView = inflater.inflate(R.layout.hint_grid_item, null);
      TextView letterView = (TextView) gridView.findViewById(R.id.grid_item_letter);
      letterView.setText(mLetters[position].toUpperCase());
   } else {
      gridView = (View) convertView;
    }
    return gridView;
  }

}
