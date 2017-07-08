package com.microacademylabs.bigwordfinder.data;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import com.microacademylabs.bigwordfinder.Words;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.nio.channels.FileChannel;
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

  public static void copyDb(Context context) {
    Log.d("copyDb", "arrived in copyDb");
    try {
      AssetManager am = context.getAssets();
      //File sd = Environment.getExternalStorageDirectory();
      File sd = context.getDatabasePath(DATABASE_NAME);

      Log.d("copyDb", "External Storage at: " + sd.getPath());
      //File data = Environment.getDataDirectory();
      //Log.d("copyDb", "Data at: " + data.getPath());
      File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
      File file2 = new File(path, "/" + DATABASE_NAME);

      if (sd.canWrite()) {
        Log.d("copyDb", "sd can write");
        //String currentDBPath = "/data/data/com.microacademylabs.bigwordfinder/databases/" + DATABASE_NAME;
        String currentDBPath = sd.getPath();
        Log.d("copyDb", "currentDBPath: " + currentDBPath);
        String backupDBPath = DATABASE_NAME;
        Log.d("copyDb", "backupDBPath: " + backupDBPath);

        File currentDB = new File(currentDBPath);
        File backupDB = new File(backupDBPath);

        if (currentDB.exists()) {
          Log.d("copyDb", "currentDB exists");
          FileChannel src = new FileInputStream(currentDB).getChannel();
          FileChannel dst = new FileOutputStream(file2).getChannel();
          dst.transferFrom(src, 0, src.size());
          Log.d("backup data", "transfer complete");
          src.close();
          dst.close();
        }
      } else {
        Log.d("copyDb", "Cannot write to external storage.");
      }
    } catch (Exception e) {
      Log.d("copyDb", "oops...something went wrong");
        e.printStackTrace();
    }
  }

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
