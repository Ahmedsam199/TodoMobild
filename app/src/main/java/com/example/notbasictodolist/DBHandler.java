package com.example.notbasictodolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Todo";
    private static final String TABLE_TEST = "Test";
    private static final String KEY_ID = "id";
    private static final String KEY_TEXT = "text";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TEST_TABLE = "CREATE TABLE " + TABLE_TEST + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_TEXT + " TEXT,"
                + "IsDone TEXT DEFAULT 0" + ")";
        sqLiteDatabase.execSQL(CREATE_TEST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Drop older table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TEST);

        // Create tables again
        onCreate(sqLiteDatabase);
    }
    void addTask(String dataModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TEXT, dataModel); // Contact Name

        // Inserting Row
        db.insert(TABLE_TEST, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }
    public List<DataModel> getAllContacts() {
        SQLiteDatabase db = this.getWritableDatabase();
        List<DataModel> contacts = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_TEST;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                DataModel contact = new DataModel();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setTask(cursor.getString(1));
                contact.setisDone(cursor.getString(2));
                contacts.add(contact);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        Log.d("DBHandler", "getAllContacts() returned " + contacts.size() + " items");
        return contacts;
    }
    public int updateContact(String text, int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TEXT, text);

        // updating row
        return db.update(TABLE_TEST, values, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }
    public void deleteContact(DataModel contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TEST, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getId()) });
        db.close();
    }
    public void updateIsDone(int id, String isDone) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("IsDone", isDone);

        db.update(TABLE_TEST, values, KEY_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }
}
