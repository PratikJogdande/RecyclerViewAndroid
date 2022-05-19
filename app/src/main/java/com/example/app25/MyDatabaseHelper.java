package com.example.app25;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "UserData.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "Details";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "user_name";
    private static final String COLUMN_ADDRESS = "user_address";
    private static final String COLUMN_DOB = "user_dob";

     MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                        " (" +  COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_ADDRESS + " TEXT, " +
                        COLUMN_DOB + " INTEGER);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //add data into database
    void addUser(String name, String address, String dob){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues(); //data into table

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_ADDRESS, address);
        cv.put(COLUMN_DOB, dob);
        long result = db.insert(TABLE_NAME, null, cv); //insert data into table
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();
            /*Intent intent1 = new Intent(get, MainActivity.class);
            startActivity(intent1);*/
        }
    }

    //Read data from database
    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    //update data
    void updateData(String row_id, String name, String address, String dob){
         SQLiteDatabase db = this.getWritableDatabase();
         ContentValues cv = new ContentValues();
         cv.put(COLUMN_NAME, name);
         cv.put(COLUMN_ADDRESS, address);
         cv.put(COLUMN_DOB, dob);

         long result = db.update(TABLE_NAME, cv, "id=?", new String[]{row_id});
         if(result == -1){
             Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
         } else{
             Toast.makeText(context, "Successfully Updated", Toast.LENGTH_SHORT).show();
         }

    }

    //delete data
    void deleteOneRow(String row_id){
         SQLiteDatabase db =this.getWritableDatabase();
         long result = db.delete(TABLE_NAME, "id=?", new String[]{row_id});
         if(result == -1){
             Toast.makeText(context, "Failed to Delete", Toast.LENGTH_SHORT).show();
         } else{
             Toast.makeText(context, "Successfully Deleted", Toast.LENGTH_SHORT).show();
         }
     }
}
