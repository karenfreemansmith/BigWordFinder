package com.microacademylabs.bigwordfinder;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by Karen Freeman-Smith on 6/6/2017.
 */

public class WordAdapter extends ArrayAdapter {
  private Context mContext;
  private ArrayList<String> mWords;

  public WordAdapter(Context context, int resource, ArrayList<String> words) {
    super(context, resource);
    this.mContext = context;
    this.mWords = words;
  }

  @Override
  public Object getItem(int position) {
    String word = mWords.get(position);
    return String.format("%s (%s)", word, Words.getScore(word));
  }

  @Override
  public int getCount() {
    return mWords.size();
  }


}
