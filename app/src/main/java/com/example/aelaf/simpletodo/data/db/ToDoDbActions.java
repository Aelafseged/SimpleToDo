package com.example.aelaf.simpletodo.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.support.annotation.NonNull;
import android.util.Log;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import  com.example.aelaf.simpletodo.data.db.SimpleToDoDbContract.TODOENTRY;

import static android.content.ContentValues.TAG;

/**
 * Created by aelaf on 8/18/17.
 */

public class ToDoDbActions {

    private SimpleToDoDbHelper msimpleToDoDbHelper;
    private Context mContext;
    public ToDoDbActions(Context context) {
        this.mContext = context;
        this.msimpleToDoDbHelper = new SimpleToDoDbHelper(mContext);
    }
    public List<ToDo> getToDoItems(){
        //sql actions
        List<ToDo> toDoList = new ArrayList<>();
        //sql to read data and populate arrayList
        SQLiteDatabase db = msimpleToDoDbHelper.getReadableDatabase();

        String[] projection = {
                TODOENTRY.COLUMN_NAME_TITLE,
                TODOENTRY.COLUMN_NAME_DETAILS,
                TODOENTRY.COLUMN_NAME_DATE,
                TODOENTRY.COLUMN_NAME_PRIORITY
        };
        //db querying
        Cursor cur = db.query(
                TODOENTRY.TABLE_NAME, projection, null, null, null, null, null);

        for (cur.moveToFirst();!cur.isAfterLast();cur.moveToNext()){

                String title = cur.getString(cur.getColumnIndexOrThrow(TODOENTRY.COLUMN_NAME_TITLE));
                String description =
                        cur.getString(cur.getColumnIndexOrThrow(TODOENTRY.COLUMN_NAME_DETAILS));
                String date =
                         cur.getString(cur.getColumnIndexOrThrow(TODOENTRY.COLUMN_NAME_DATE));

            String priority =
                    cur.getString(cur.getColumnIndexOrThrow(TODOENTRY.COLUMN_NAME_PRIORITY));


       // String result = timeFormatter(date);
                    ToDo task = new ToDo(0,title,description,date,priority);
                toDoList.add(task);
            }

        if (cur != null) {
            cur.close();
        }

        db.close();

        return toDoList;
    }

  /*  private String timeFormatter(String date) {

        String finale = "";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date r =  formatter.parse(date);
             finale = formatter.format(r);
         }
        catch(ParseException ex){
            Log.e(TAG, "timeFormatter: "+ex.getMessage(),ex );
        }
        return finale;
    }*/

    public long addItem(ToDo toDo){
        SQLiteDatabase db = msimpleToDoDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TODOENTRY.COLUMN_NAME_TITLE, toDo.getName());
        values.put(TODOENTRY.COLUMN_NAME_DETAILS, toDo.getDetail());
        values.put(TODOENTRY.COLUMN_NAME_DATE, toDo.getDate());
        values.put(TODOENTRY.COLUMN_NAME_PRIORITY, toDo.getPriority());


        long item_id =   db.insert(TODOENTRY.TABLE_NAME, null, values);

        db.close();
        return item_id;

    }


    public void deleteItem(@NonNull ToDo todo) {
        SQLiteDatabase db = msimpleToDoDbHelper.getReadableDatabase();
        db.delete(TODOENTRY.TABLE_NAME,TODOENTRY.COLUMN_NAME_DATE +" = "+"'"+todo.getDate()+"'",null);
        db.close();
    }
    public void updateItem(@NonNull ToDo todo) {
        SQLiteDatabase db = msimpleToDoDbHelper.getWritableDatabase();
        ContentValues cn = new ContentValues();
        cn.put(TODOENTRY.COLUMN_NAME_TITLE,todo.getName());
        cn.put(TODOENTRY.COLUMN_NAME_DETAILS, todo.getDetail());
        db.update(TODOENTRY.TABLE_NAME, cn, TODOENTRY.COLUMN_NAME_DATE + " = " + "'" + todo.getDate()+ "'", null);

        db.close();
    }
}
