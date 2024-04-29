package com.noviceModDev.extrackapp;

import static android.app.PendingIntent.getActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHandler extends SQLiteOpenHelper {
//    private static final String DB_NAME = "exTrack_db.db"; // use this
//    private static final String DB_NAME = "exTrack_db"; // use this
    private static final String DB_NAME = "exT_db"; // use this
    private static final int DB_VERSION = 1;  // use this
    private static final String TABLE_NAME = "household";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "DATE";
    private static final String COL_3 = "description";
    private static final String COL_4 = "cost";
    private static final String COL_5 = "total";


    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
//        String table1 = "CREATE TABLE tableOne(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR)";
//        String tableTotal = "CREATE TABLE totalTable(id INTEGER PRIMARY KEY AUTOINCREMENT, total VARCHAR)";

        //String db_name = "DROP DATABSE exTrack_db IF EXISTS";

        String houseHoldTable = "CREATE TABLE household(id INTEGER PRIMARY KEY AUTOINCREMENT, item TEXT, date TEXT, cost TEXT)";
        db.execSQL(houseHoldTable);
//        db.execSQL(table1);
//        db.execSQL(tableTotal);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        String table1 = "DROP TABLE IF EXISTS tableOne";
//        String table2 = "DROP TABLE IF EXISTS totalTable";
        String tableHouseHold = "DROP TABLE IF EXISTS household";

//        db.execSQL(table1);
//        db.execSQL(table2);
        db.execSQL(tableHouseHold);

        onCreate(db);
    }

    //
    // this method is use to add new course to our sqlite database.
//    public void addNewItem(String itemDescription, String date, String cost) {
//    public void addNewItem(String itemDescription, String date, String cost, String total) {
//    public void addNewItem(String itemDescription, String date, String cost, String total) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(COL_2, date);
//        values.put(COL_4, cost);
//        values.put(COL_3, itemDescription);
//        values.put(COL_5, total);
//
//        db.insert(TABLE_NAME, null, values);
//        db.close();
//    }

    // second method
//    public boolean insertData(String name) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("name", name); // key is column name
//
//        db.insert("tableOne", null, values);
//
//        db.close();
//
//        return true;
//    }

    public boolean insertDataIntoHouseHoldTable(String item, String date, String cost){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("item", item); // key is column name
        values.put("date", date);
        values.put("cost", cost);

        db.insert("household", null, values);

        db.close();

        return true;
    }


    //db table 2
//    public boolean insertTotal(String total){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//
//        MainActivity mainActivity = (MainActivity) getActivity();
//        double totalSum = mainActivity.getHouseHoldTotalCost();
//        mainActivity.getHouseHoldTotalCost();
//
//        values.put("name", name); // key is column name
//
//        db.insert("totalTable", null, values);
//
//        db.close();
//        return true;
//    }

    public boolean retrieveData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT column1,column2,column3 FROM first_table ", null);
        if (c.moveToFirst()){
            do {
                // Passing values
                String column1 = c.getString(0);
                String column2 = c.getString(1);
//                String column3 = c.getString(2);
                // Do something Here with values
                Log.d("info", "MSG: db_table data");
            } while(c.moveToNext());
        }
        c.close();
        db.close();

        return true;
    }
}
