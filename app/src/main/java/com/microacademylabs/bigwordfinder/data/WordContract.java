package com.microacademylabs.bigwordfinder.data;

import android.provider.BaseColumns;

/**
 * Created by Karen Freeman-Smith on 7/2/2017.
 */

public final class WordContract {

  public static abstract class WordEntry implements BaseColumns {

    public static final String TABLE_NAME = "words";
    public static final String _ID = "_id";
    public static final String COLUMN_WORD = "word";
    public static final String COLUMN_DEFINITION = "definition";
    public static final String COLUMN_CLASS = "class";
    public static final String COLUMN_IMAGE = "image_name";

    public static final int CLASS_12 = 12;
    public static final int CLASS_11 = 11;
    public static final int CLASS_10 = 10;
    public static final int CLASS_9 = 9;
    public static final int CLASS_8 = 8;
    public static final int CLASS_7 = 7;
    public static final int CLASS_6 = 6;
    public static final int CLASS_5 = 5;
    public static final int CLASS_4 = 4;
    public static final int CLASS_3 = 3;

    public static final String SQL_CREATE_ENTRIES = "CREATE TABLE " +
        TABLE_NAME + "( " +
        _ID + " INTEGER PRIMARY KEY, " +
        COLUMN_CLASS + " TEXT, " +
        COLUMN_WORD + " TEXT, " +
        COLUMN_DEFINITION + " TEXT, " +
        COLUMN_IMAGE + " TEXT)";

    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " +
        TABLE_NAME;
  }
}
