package csis3175.mobiledevelopmentproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper{
    final static String DATABASE_NAME = "expensetracker.db";
    final static int DATABASE_VERSION = 1;

    public String TABLE_NAME = "user";
    final static String COL_1 = "id";
    final static String COL_2 = "date";
    final static String COL_3 = "category";
    final static String COL_4 = "value";
    final static String COL_5 = "detail";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       // String query = "CREATE TABLE "+ TABLE_NAME +"(billId INTEGER PRIMARY KEY, billName TEXT, billAmount TEXT, billDate TEXT, billNote TEXT)";
        //db.execSQL(query);
       /*
        query = "CREATE TABLE income(incomeId INTEGER PRIMARY KEY, incomeName TEXT, incomeAmount TEXT, incomeDate TEXT, incomeNote TEXT)";
        db.execSQL(query);
        query = "CREATE TABLE budget(budgetId INTEGER PRIMARY KEY, budgetName TEXT, budgetAmount TEXT)";
        db.execSQL(query);
        */
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("DROP table if exists " + TABLE_NAME);
        onCreate(db);
    }

    public void createTable(String tableName){
        //TABLE_NAME = tableName;
        SQLiteDatabase db = this.getReadableDatabase();

              String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
                      COL_1 + " INTEGER PRIMARY KEY, " +
                      COL_2 + " TEXT, " +
                      COL_3 + " TEXT, " +
                      COL_4 + " TEXT, " +
                      COL_5 + " TEXT)";
              db.execSQL(query);
    }

    //add record method
    public boolean addRec(String date, String category, String value, String detail){
        //writable is moved from under super(context...) to here
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_2, date);
        values.put(COL_3, category);
        values.put(COL_4, value);
        values.put(COL_5, detail);

        long r = db.insert(TABLE_NAME, null, values);
        if(r==-1)
            return false;
        else
            return true;
    }

    public Cursor viewRec(String tableName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from " + TABLE_NAME;
        Cursor res = db.rawQuery(query,null);
        return res;
    }

    //delete using sql
    public boolean deleteRec(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE from " + TABLE_NAME + " WHERE id = '" + id + "'";
        db.execSQL(query);
        return true;
    }

}
