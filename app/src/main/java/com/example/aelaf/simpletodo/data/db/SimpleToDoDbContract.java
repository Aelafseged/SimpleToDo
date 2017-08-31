package com.example.aelaf.simpletodo.data.db;

import android.provider.BaseColumns;

/**
 * Created by aelaf on 8/18/17.
 */

public class SimpleToDoDbContract {

    //INNER CLASS
    public static abstract class TODOENTRY implements BaseColumns {
        public static final String TABLE_NAME = "todo";
      //  public static final String COLUMN_NAME_ENTRY_ID = "todoid";
        public static final String COLUMN_NAME_TITLE = "name";
        public static final String COLUMN_NAME_DETAILS = "details";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_PRIORITY = "priority";

    }
}
