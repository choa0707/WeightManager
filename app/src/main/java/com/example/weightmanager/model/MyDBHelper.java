package com.example.weightmanager.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDBHelper extends SQLiteOpenHelper {

    public MyDBHelper(Context context)
    {
        super(context,"FoodDB",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //테이블이 존재하지 않으면 만들어줌
        db.execSQL("CREATE TABLE IF NOT EXISTS User(user_id      INTEGER    PRIMARY KEY AUTOINCREMENT, name       VARCHAR(45)    , nickname     VARCHAR(45)    , gender       INT            ,  birth       DATE           ,     heigh       DOUBLE         ,     weight     DOUBLE         ,     goal       INT            ,     goal_weight  DOUBLE         ,     goal_kcal    DOUBLE         ," +
                "age INT );");
        db.execSQL("CREATE TABLE IF NOT EXISTS Food" +
                "(" +
                "    food_id  INTEGER    PRIMARY KEY AUTOINCREMENT," +
                "    name     VARCHAR(45)    , " +
                "    kcal     DOUBLE         , " +
                "    carb     DOUBLE         , " +
                "    protein  DOUBLE         , " +
                "    fat      DOUBLE         " +
                ");");

        db.execSQL("CREATE TABLE IF NOT EXISTS Foodset" +
                "(" +
                "    `foodset_id`  INTEGER    PRIMARY KEY AUTOINCREMENT," +
                "    `user_id`     INT     , " +
                "    `food_id`     INT     , " +
                "    `date`        DATE    , " +
                "    `timing`      INT     , " +
                "   FOREIGN KEY (food_id)    REFERENCES Food (food_id) ON DELETE RESTRICT ON UPDATE RESTRICT," +
                "   FOREIGN KEY (user_id)     REFERENCES User (user_id) ON DELETE RESTRICT ON UPDATE RESTRICT" +
                ");");
        db.execSQL("CREATE TABLE IF NOT EXISTS Water" +
                "(" +
                "    `water_id` INTEGER    PRIMARY KEY AUTOINCREMENT, " +
                "    `user_id`   INT     , " +
                "    `date`      DATE    , " +
                "    `amount`    INT     , " +
                " FOREIGN KEY (user_id) REFERENCES User (user_id) ON DELETE RESTRICT ON UPDATE RESTRICT" +
                ");");
        db.execSQL("CREATE TABLE IF NOT EXISTS Board" +
                "(" +
                "    `board_id`   INTEGER    PRIMARY KEY AUTOINCREMENT," +
                "    `goal`         INT             , " +
                "    `title`        VARCHAR(45)     , " +
                "    `text`         VARCHAR(800)    , " +
                "    `image` VARCHAR(100) " +
                ");");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("DROP TABLE IF EXISTS User");
        //onCreate(db);
    }

}
