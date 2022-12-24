package com.example.finderx_mad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TaskDatabase extends SQLiteOpenHelper {
    public static final int DB_VERSION = 2;
    public static String DB_NAME = "TasksDB.db";
    public static String DB_TABLE = "TasksTable";

    public static String COLUMN_ID = "TasksID";
    public static String COLUMN_TITLE = "TasksTitle";
    public static String COLUMN_DETAILS = "TasksDetails";
    public static String COLUMN_DATE = "TasksDate";
    public static String COLUMN_TIME = "TasksTime";

    public TaskDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String query = "CREATE TABLE " + DB_TABLE +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_DETAILS + " TEXT, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_TIME + " TEXT" + ")";
        sqLiteDatabase.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        if(i >= i1)
            return;
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB_NAME);
        onCreate(sqLiteDatabase);

    }

    public long AddTask(TaskModel taskModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TITLE, taskModel.getTaskTitle());
        contentValues.put(COLUMN_DETAILS, taskModel.getTaskDetails());
        contentValues.put(COLUMN_DATE, taskModel.getTaskDate());
        contentValues.put(COLUMN_TIME, taskModel.getTaskTime());

        Long ID = db.insert(DB_TABLE, null, contentValues);
        Log.d("Inserted", "id ->" + ID);
        return ID;
    }

    public List<TaskModel> getTask(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<TaskModel> allTask = new ArrayList<>();

        String queryStatement = "SELECT * FROM " + DB_TABLE;
        Cursor cursor = db.rawQuery(queryStatement, null);

        if(cursor.moveToFirst()){
            do{

                TaskModel taskModel = new TaskModel();
                taskModel.setId(cursor.getInt(0));
                taskModel.setTaskTitle(cursor.getString(1));
                taskModel.setTaskDetails(cursor.getString(2));
                taskModel.setTaskDate(cursor.getString(3));
                taskModel.setTaskTime(cursor.getString(4));

                allTask.add(taskModel);
            }while (cursor.moveToNext());
        }
        return  allTask;
    }

//    public List<TaskModel> getTasks() {
//        SQLiteDatabase db = this.getReadableDatabase();
//        String[] query = new String[]{COLUMN_ID, COLUMN_TITLE, COLUMN_DETAILS, COLUMN_DATE, COLUMN_TIME};
//        Cursor cursor = db.query(DB_TABLE, query, COLUMN_ID += "=?", new String[]{String.valueOf(id)}, null, null, null, null);
//
//        if (cursor != null)
//            cursor.moveToFirst();
//
//        return new TaskModel(
//                Integer.parseInt(cursor.getString(0)),
//                cursor.getString(1),
//                cursor.getString(2),
//                cursor.getString(3),
//                cursor.getString(4))
//
//
//    }




    }

