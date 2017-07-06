package com.microacademylabs.bigwordfinder.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.microacademylabs.bigwordfinder.Words;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static com.microacademylabs.bigwordfinder.data.WordContract.WordEntry.COLUMN_WORD;
import static com.microacademylabs.bigwordfinder.data.WordContract.WordEntry.SQL_CREATE_ENTRIES;
import static com.microacademylabs.bigwordfinder.data.WordContract.WordEntry.SQL_DELETE_ENTRIES;
import static com.microacademylabs.bigwordfinder.data.WordContract.WordEntry.TABLE_NAME;

/**
 * Created by Karen Freeman-Smith on 7/3/2017.
 */

public class WordDbHelper extends SQLiteOpenHelper {
  public static final int DATABASE_VERSION = 1;
  public static final String DATABASE_NAME = "words.db";

  public WordDbHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

// used for loading words from static array to database - can delete once db is working fine
//  public void addWords(ArrayList words, SQLiteDatabase db) {
//    for (int i = 0; i < words.size(); i++) {
//      String sql = "INSERT INTO " + TABLE_NAME + " (" + COLUMN_WORD + ") VALUES ('" + words.get(i) + "');";
//      Log.d("addWords", sql);
//      db.execSQL(sql);
//    }
//  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL(SQL_CREATE_ENTRIES);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL(SQL_DELETE_ENTRIES);
    onCreate(db);
  }
}
