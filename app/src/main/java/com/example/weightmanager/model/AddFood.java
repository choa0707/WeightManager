package com.example.weightmanager.model;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.weightmanager.R;

public class AddFood extends AppCompatActivity {

    private Button cancel, register;
    private EditText foodName, foodKcal, foodProtein, foodCarb,foodFat;
    private String name;
    private int kcal;
    private int protein;
    private int carb;
    private int fat;

    MyDBHelper myDBHelper;
    SQLiteDatabase sqlDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        myDBHelper =new MyDBHelper(this);
        cancel = (Button)findViewById(R.id.addfoodActivity_cancel);
        register = (Button)findViewById(R.id.addfoodActivity_register);
        foodName = (EditText)findViewById(R.id.addfood_name);
        foodKcal = (EditText)findViewById(R.id.addfood_kcal);
        foodProtein = (EditText)findViewById(R.id.addfood_protein);
        foodCarb = (EditText)findViewById(R.id.addfood_carb);
        foodFat = (EditText)findViewById(R.id.addfood_fat);


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    name = foodName.getText().toString();
                    kcal = Integer.parseInt(foodKcal.getText().toString());
                    protein = Integer.parseInt(foodProtein.getText().toString());
                    carb = Integer.parseInt(foodCarb.getText().toString());
                    fat = Integer.parseInt(foodFat.getText().toString());

                    sqlDB = myDBHelper.getWritableDatabase();
                    String query = "INSERT INTO Food (name, kcal, carb, protein, fat) VALUES ('"+name+"',"+kcal+","+carb+","+protein+","+fat+")";
                    sqlDB.execSQL(query);
                    sqlDB.close();
                    Intent intent = new Intent(getApplicationContext(), SearchFood.class);
                    intent.putExtra("timing", 0);
                    startActivity(intent);
                }catch (NumberFormatException e)
                {
                    Toast.makeText(AddFood.this, "모든 항목을 입력해주세요.", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
