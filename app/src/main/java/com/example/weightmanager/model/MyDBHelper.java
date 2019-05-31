package com.example.weightmanager.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {

    public MyDBHelper(Context context)
    {
        super(context,"FoodDB",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE User(user_id      INT    PRIMARY KEY AUTOINCREMENT, name       VARCHAR(45) , nickname     VARCHAR(45)   , gender       INT           ,  birth       DATE          ,     heigh       DOUBLE        ,     weight     DOUBLE        ,     goal       INT           ,     goal_weight  DOUBLE        ,     goal_kcal    DOUBLE         );");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("DROP TABLE IF EXISTS User");
        //onCreate(db);
    }

}
