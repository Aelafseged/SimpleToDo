package com.example.aelaf.simpletodo.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by aelaf on 8/18/17.
 */

public class SimpleToDoDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "SimpleToDo.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INTEGER";

    private static final String DATE_TYPE = " DATE";



    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_TODO =
            "CREATE TABLE " + SimpleToDoDbContract.TODOENTRY.TABLE_NAME + " (" +
                    SimpleToDoDbContract.TODOENTRY._ID + INT_TYPE + " PRIMARY KEY AUTOINCREMENT, " +
                    SimpleToDoDbContract.TODOENTRY.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                    SimpleToDoDbContract.TODOENTRY.COLUMN_NAME_DETAILS + TEXT_TYPE + COMMA_SEP +
                    SimpleToDoDbContract.TODOENTRY.COLUMN_NAME_DATE + DATE_TYPE + COMMA_SEP +
                    SimpleToDoDbContract.TODOENTRY.COLUMN_NAME_PRIORITY + TEXT_TYPE +
                    " )";


    public SimpleToDoDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TODO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


}
