package com.amriksinghpadam.myfileupload;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabase extends SQLiteOpenHelper {

    private static final String DB_NAME = "MY_DB";
    private static final String TABLE_NAME = "MY_TABLE";
    private static final int VERSION = 1;
    private static final String COL0 = "id";
    private static final String COL1 = "name";
    private static final String COL2 = "price";
    private static final String COL3 = "image";

    public MyDatabase(Context context) {
        super(context,DB_NAME,null,VERSION);
    }

    @Override

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+" ("+COL0+" INTEGER PRIMARY KEY, "+COL1+" VARCHAR,"+COL2+" TEXT,"+COL3+" BLOG)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    }

    public boolean insertData(String name, int price, byte[] image){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1,name);
        contentValues.put(COL2,price);
        contentValues.put(COL3,image);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if (result==-1){
            return false;
        }else{
            return true;
        }
    }
    public Cursor getData(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor data =  db.rawQuery("SELECT "+COL1+","+COL2+" FROM "+TABLE_NAME,null);
        return data;
    }
    public Cursor getImages(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor data =  db.rawQuery("SELECT "+COL3+" FROM "+TABLE_NAME,null);
        return data;
    }
}
