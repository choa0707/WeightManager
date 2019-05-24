package com.example.weightmanager.model;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.weightmanager.R;

public class SearchFood extends AppCompatActivity {

    Button addfoodButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_food);
        addfoodButton = (Button)findViewById(R.id.addfoodButton);

        addfoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //값받게 resultfor로 바꿀예정
                Intent intent = new Intent(getApplicationContext(), AddFood.class);
                startActivity(intent);
            }
        });
    }
}
