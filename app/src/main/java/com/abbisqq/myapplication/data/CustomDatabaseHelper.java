package com.abbisqq.myapplication.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class CustomDatabaseHelper extends SQLiteOpenHelper {




    private static final int DATABASE_VERSION = 2;

    private SQLiteDatabase db;



    public CustomDatabaseHelper(Context context) {
        super(context, FishContract.CUSTOM_DATABASE_NAME, null, DATABASE_VERSION);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ FishContract.TABLE_NAME_CUSTOM+ " ( " +
                FishContract.DATABASE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FishContract.SCINAME + " TEXT, " +
                FishContract.COMMONNAME + " TEXT, "+
                FishContract.SIZE+ " TEXT, " +
                FishContract.TEMPERATURE+ " TEXT, " +
                FishContract.PH + " TEXT, " +
                FishContract.BREEDING + " TEXT, "+
                FishContract.DIET+" TEXT, " +
                FishContract.DIFFICULT+ " TEXT, " +
                FishContract.AGGRESSION + " TEXT, "+
                FishContract.OVERVIEW + " TEXT, " +
                FishContract.IMAGE + " TEXT, "+
                FishContract.WATER_HARDNESS + " TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop the table if already exists and start a new one
        db.execSQL("DROP TABLE IF EXISTS " + FishContract.TABLE_NAME_CUSTOM);

        onCreate(db);

    }


    public boolean insertData(String sciName, String com, String size,String ph, String breeding, String temperature,
                              String aggression , String diet, String overview,String difficult, String image, String water){
        ContentValues contentValues = new ContentValues();
        contentValues.put(FishContract.SCINAME,sciName);
        contentValues.put(FishContract.COMMONNAME,com);
        contentValues.put(FishContract.SIZE,size);
        contentValues.put(FishContract.PH,ph);
        contentValues.put(FishContract.DIFFICULT,difficult);
        contentValues.put(FishContract.BREEDING,breeding);
        contentValues.put(FishContract.TEMPERATURE,temperature);
        contentValues.put(FishContract.AGGRESSION,aggression);
        contentValues.put(FishContract.DIET,diet);
        contentValues.put(FishContract.OVERVIEW,overview);
        contentValues.put(FishContract.IMAGE,image);
        contentValues.put(FishContract.WATER_HARDNESS,water);
        getWritableDatabase();
        //if data is not inserted results will be -1
        long result = db.insert(FishContract.TABLE_NAME_CUSTOM,null,contentValues);

        return result != -1;
    }




    public boolean updateData(String id,String sciName, String com, String size,String ph, String breeding, String temperature,
                              String aggression , String diet, String overview,String difficult, String image, String water){

        ContentValues contentValues = new ContentValues();
        contentValues.put(FishContract.SCINAME,sciName);
        contentValues.put(FishContract.COMMONNAME,com);
        contentValues.put(FishContract.SIZE,size);
        contentValues.put(FishContract.PH,ph);
        contentValues.put(FishContract.DIFFICULT,difficult);
        contentValues.put(FishContract.BREEDING,breeding);
        contentValues.put(FishContract.TEMPERATURE,temperature);
        contentValues.put(FishContract.AGGRESSION,aggression);
        contentValues.put(FishContract.DIET,diet);
        contentValues.put(FishContract.OVERVIEW,overview);
        contentValues.put(FishContract.IMAGE,image);
        contentValues.put(FishContract.WATER_HARDNESS,water);
        db = getWritableDatabase();
        db.update(FishContract.TABLE_NAME_CUSTOM,contentValues,"ID = ?",new String[] {id});
        return true;
    }




    public boolean deleteDataSwipping(String id){
        return db.delete(FishContract.TABLE_NAME_CUSTOM, FishContract.DATABASE_ID + "=" + id, null) > 0;
    }



    public Cursor getAllData(){
        db = getWritableDatabase();
        return db.rawQuery("select * from "+FishContract.TABLE_NAME_CUSTOM,null);

    }
}
