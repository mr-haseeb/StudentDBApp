package com.example.studentdbapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="studentDB";
    public static final String TABLE_NAME="student";

    public static final String COL_1="studentId";
    public static final String COL_2="name";
    public static final String COL_3="fName";
    public static final String COL_4="Age";
    public static final String COL_5="Address";
    public static final String COL_6="phoneNo";



    public DatabaseHelper( Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (studentId INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,fname TEXT, Age INTEGER,Address TEXT,phoneNo TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
       onCreate(db);
    }
    public boolean insertData(String name,String fname,String age,String address,String phoneNumber){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contantValues=new ContentValues();
        contantValues.put(COL_2,name);
        contantValues.put(COL_3,fname);
        contantValues.put(COL_4,age);
        contantValues.put(COL_5,address);
        contantValues.put(COL_6,phoneNumber);
        long result= db.insert(TABLE_NAME,null,contantValues);
        if (result==-1){
            return false;
        }
        else {
            return true;
        }
    }
    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor response=db.rawQuery("select * from "+TABLE_NAME,null);
        return response;
    }
    public boolean updateData(String studentId,String name,String fname,String age,String address,String phoneNumber){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contantValues=new ContentValues();
        contantValues.put(COL_1,studentId);
        contantValues.put(COL_2,name);
        contantValues.put(COL_3,fname);
        contantValues.put(COL_4,age);
        contantValues.put(COL_5,address);
        contantValues.put(COL_6,phoneNumber);
        db.update(TABLE_NAME,contantValues,"studentId = ?",new String[]{studentId});
        return true;

    }
    public Integer deleteData(String studentId){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAME,"studentId =? ",new String[] {studentId});

    }

}
