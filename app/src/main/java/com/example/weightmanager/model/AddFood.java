package com.example.weightmanager.model;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.weightmanager.R;

public class AddFood extends AppCompatActivity {

    private Button cancel, register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        cancel = (Button)findViewById(R.id.addfoodActivity_cancel);
        register = (Button)findViewById(R.id.addfoodActivity_register);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), SearchFood.class);
                startActivity(intent);
            }
        });
    }
}
