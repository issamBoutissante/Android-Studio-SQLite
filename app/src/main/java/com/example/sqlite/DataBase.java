package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {
    static final String DATABASE_NAME="MyDataBase";
    static int DATABASE_VERSION=1;
    static final String CAR_TBL_NAME="car";
    static final String CAR_CLN_MODEL="model";
    static final String CAR_CLN_COLOR="color";
    public DataBase(Context context) {
        super(context,DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+CAR_TBL_NAME+"(id Integer primary key autoincrement, " +
                ""+CAR_CLN_MODEL+" TEXT, " +
                ""+CAR_CLN_COLOR+" TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //we can use this method to upgrade our database structure
        //but first we have to change the database version
    }
    public boolean insert(Car car){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(CAR_CLN_MODEL,car.getModel());
        values.put(CAR_CLN_COLOR,car.getColor());
        long result=db.insert(CAR_TBL_NAME,null,values);
        return result!=-1;
    }
    public boolean update(Car car){
        ContentValues values=new ContentValues();
        values.put(CAR_CLN_MODEL,car.getModel());
        values.put(CAR_CLN_COLOR,car.getColor());
        SQLiteDatabase db=getWritableDatabase();
        int nbRowsAffected = db.update(CAR_TBL_NAME, values, "id=?", new String[]{car.getId() + ""});
        return nbRowsAffected > 0;
    }
    public  boolean delete(Integer carId){
        SQLiteDatabase db=getWritableDatabase();
        int nbRowsDeleted = db.delete(CAR_TBL_NAME, "id=?", new String[]{carId.toString()});
        return nbRowsDeleted > 0;
    }
    public long getCarCount(){
        SQLiteDatabase db=getWritableDatabase();
        return DatabaseUtils.queryNumEntries(db,CAR_TBL_NAME);
    }
    public ArrayList<Car> getAllCars(){
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + CAR_TBL_NAME, null);
        ArrayList<Car> cars=new ArrayList<>();
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do{
                    int id=cursor.getInt(cursor.getColumnIndex("id"));
                    String model=cursor.getString(cursor.getColumnIndex(CAR_CLN_MODEL));
                    String color = cursor.getString(cursor.getColumnIndex(CAR_CLN_COLOR));
                    cars.add(new Car(id,model,color));
                }while(cursor.moveToNext());
                cursor.close();
            }
        }
        return cars;
    }
}
