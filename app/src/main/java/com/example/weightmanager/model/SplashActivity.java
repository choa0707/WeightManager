package com.example.weightmanager.model;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.weightmanager.R;

public class SplashActivity extends AppCompatActivity {
    MyDBHelper myDBHelper;
    SQLiteDatabase sqlDB;

    @Override
    protected void onDestroy() {
        sqlDB.close();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        myDBHelper =new MyDBHelper(this);
        sqlDB = myDBHelper.getReadableDatabase();
        String sqlSelect = "SELECT * FROM User" ;
        Cursor cursor = (Cursor) sqlDB.rawQuery(sqlSelect, null);

        if (cursor.getCount() == 0)
        {
            Intent intent = new Intent(getApplicationContext(), StartActivity.class);
            startActivity(intent);
            finish();
        }
        else
        {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }

    }
}
