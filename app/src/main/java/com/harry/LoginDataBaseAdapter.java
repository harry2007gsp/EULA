package com.harry;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LoginDataBaseAdapter extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "login.db";
    static final int DATABASE_VERSION = 1;
    static final String DATABASE_CREATE = "create table " + "LOGIN" +
            "( " + "ID" + " integer primary key autoincrement," + "USERNAME  text,PASSWORD text); ";
    public SQLiteDatabase db;
//    SQLiteDatabase.CursorFactory factory;

    public LoginDataBaseAdapter(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public LoginDataBaseAdapter open() throws SQLException {
        db = getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + "TEMPLATE");
        // Create a new one.
        onCreate(db);
    }

    public SQLiteDatabase getDatabaseInstance() {
        return db;
    }

    public void insertEntry(String userName, String password) {
        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put("USERNAME", userName);
        newValues.put("PASSWORD", password);

        // Insert the row into your table
        db.insert("LOGIN", null, newValues);
        ///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
    }

    public int deleteEntry(String UserName) {
        //String id=String.valueOf(ID);
        String where = "USERNAME=?";
        int numberOFEntriesDeleted = db.delete("LOGIN", where, new String[]{UserName});
        // Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        return numberOFEntriesDeleted;
    }

    public String getSinlgeEntry(String userName) {
        Cursor cursor = db.query("LOGIN", null, " USERNAME=?", new String[]{userName}, null, null, null);
        if (cursor.getCount() < 1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return password;
    }

    public void updateEntry(String userName, String password) {
        // Define the updated row content.
        ContentValues updatedValues = new ContentValues();
        // Assign values for each row.
        updatedValues.put("USERNAME", userName);
        updatedValues.put("PASSWORD", password);

        String where = "USERNAME = ?";
        db.update("LOGIN", updatedValues, where, new String[]{userName});
    }
}