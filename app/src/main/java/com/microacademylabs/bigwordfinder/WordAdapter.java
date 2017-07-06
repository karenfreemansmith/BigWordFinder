package com.microacademylabs.bigwordfinder;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Karen Freeman-Smith on 6/6/2017.
 */

public class WordAdapter extends ArrayAdapter {
  private Context mContext;
  private String[] mAllWords;
  private ArrayList<String> mWords;

  public WordAdapter(Context context, int resource, ArrayList<String> words) {
    super(context, resource);
    this.mContext = context;
    this.mWords = words;
    this.mAllWords = words.toArray(new String[words.size()]);
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

  public void filter(String text) {
    mWords.clear();
    if(text.isEmpty()) {
      Collections.addAll(mWords, mAllWords);
    } else {
      text = text.toLowerCase();
      for(String word: mAllWords) {
        if(word!=null) {
          if(word.toLowerCase().contains(text)) {
            mWords.add(word);
          }
        }
      }
    }
    notifyDataSetChanged();
  }

}
