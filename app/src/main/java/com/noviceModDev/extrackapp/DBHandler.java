package com.noviceModDev.extrackapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "exTrack_db"; // use this
//    private static final String DB_NAME = "exT_db"; // use this
    private static final int DB_VERSION = 1;  // use this

    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String houseHoldTable = "CREATE TABLE household(id INTEGER PRIMARY KEY AUTOINCREMENT, item TEXT, date TEXT, cost TEXT)";
        String usersTable = "CREATE TABLE users(id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT)";

        db.execSQL(houseHoldTable);
        db.execSQL(usersTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String tableHouseHold = "DROP TABLE IF EXISTS household";
        String tableUsers = "DROP TABLE IF EXISTS users";
        db.execSQL(tableHouseHold);
        db.execSQL(tableUsers);
        onCreate(db);
    }

    public boolean insertDataIntoHouseHoldTable(String item, String date, String cost){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("item", item); // key is column name
        values.put("date", date);
        values.put("cost", cost);

        db.insert("household", null, values);

        db.close(); // use it at the end only

        return true;
    }

    public boolean populateUsersTable(String userName, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("username", userName); // key is column name
        values.put("password", password);

        db.insert("users", null, values);
        db.close();

        return true;
    }


//    public boolean retrieveData(){
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery("SELECT column1,column2,column3 FROM first_table ", null);
//        if (c.moveToFirst()){
//            do {
//                // Passing values
//                String column1 = c.getString(0);
//                String column2 = c.getString(1);
////                String column3 = c.getString(2);
//                // Do something Here with values
//                Log.d("info", "MSG: db_table data");
//            } while(c.moveToNext());
//        }
//        c.close();
//        db.close();
//
//        return true;
//    }


}
