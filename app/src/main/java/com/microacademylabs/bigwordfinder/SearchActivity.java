package com.microacademylabs.bigwordfinder;

import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.microacademylabs.bigwordfinder.data.WordContract.WordEntry;
import com.microacademylabs.bigwordfinder.data.WordDbHelper;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
  private ArrayList<String> mWords;
  private ListView mWordList;

  private void getWords() {
    mWords = new ArrayList<>();
    WordDbHelper mWordDbHelper = new WordDbHelper(this);
    SQLiteDatabase mDb = mWordDbHelper.getReadableDatabase();
    Cursor mCursor = mDb.rawQuery("SELECT * FROM " + WordEntry.TABLE_NAME + ";", null);
    try {
      if (mCursor.getCount() != 0) {
        while (!mCursor.isLast()) {
          mCursor.moveToNext();
          String word = mCursor.getString(mCursor.getColumnIndex(WordEntry.COLUMN_WORD));
          mWords.add(word);
        }
      } else {
        mWords.add("Database Missing...");
      }
    } finally {
      mCursor.close();
      mDb.close();
    }
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_search);

    getWords();
    mWordList = (ListView) findViewById(R.id.wordList);

    WordAdapter adapter = new WordAdapter(SearchActivity.this, android.R.layout.simple_list_item_1, mWords);
    mWordList.setAdapter(adapter);
    mWordList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=" + mWords.get(position)));
        startActivity(webIntent);
      }
    });
  }
}
