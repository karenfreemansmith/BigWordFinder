package com.microacademylabs.bigwordfinder;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


/**
 * Created by Karen Freeman-Smith on 6/1/2017.
 */

public class LetterAdapter extends BaseAdapter {
  private Context mContext;
  private char[] mLetters;

  public LetterAdapter (Context context, char[] letters) {
    this.mContext = context;
    mLetters = letters;
    for(int i=0; i<mLetters.length; i++) {
      Log.i("array", String.valueOf(mLetters[i]));
    }
  }

  @Override
  public int getCount() {
    return mLetters.length;
  }

  @Override
  public Object getItem(int position) {
    return mLetters[position];
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
      letterView.setText(String.valueOf(mLetters[position]));
    } else {
      gridView = convertView;
    }
    return gridView;
  }

}
