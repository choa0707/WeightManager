package com.example.weightmanager.model;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.weightmanager.R;

public class StartActivity extends AppCompatActivity {

    MyDBHelper myDBHelper;
    SQLiteDatabase sqlDB;
    Button button_weightup;
    Button button_weightmaintain;
    Button button_weightdown;
    private int weightLevel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        myDBHelper =new MyDBHelper(this);
        button_weightup = (Button) findViewById(R.id.weight_up);
        button_weightmaintain = (Button)findViewById(R.id.weight_maintain);
        button_weightdown = (Button)findViewById(R.id.weight_down);

        button_weightup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                weightLevel = 1;
                ChangeActivity();
            }
        });
        button_weightmaintain.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                weightLevel = 0;
                ChangeActivity();
            }
        });
        button_weightdown.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                weightLevel = -1;
                ChangeActivity();
            }
        });
    }

    private void ChangeActivity() {
        Intent intent = new Intent(StartActivity.this, ProfileActivity.class);
        intent.putExtra("weightLevel", weightLevel);
        startActivity(intent);
    }
}
