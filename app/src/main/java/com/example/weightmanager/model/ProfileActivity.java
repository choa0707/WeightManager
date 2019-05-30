package com.example.weightmanager.model;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weightmanager.R;

public class ProfileActivity extends AppCompatActivity {

    DatePicker mDate;
    TextView mTxtDate;
    int goal;
    Button cancel;
    Button register;
    EditText name, weight, height, goal_weight;
    private String s_name;
    private double s_weight, s_height, s_goal_weight;

    MyDBHelper myDBHelper;
    SQLiteDatabase sqlDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        myDBHelper = new MyDBHelper(this);


        Intent intent = getIntent();
        goal  = intent.getExtras().getInt("weightLevel");
        mDate = (DatePicker)findViewById(R.id.datepicker);
        cancel = (Button)findViewById(R.id.cancel);
        register = (Button)findViewById(R.id.register);
        name = (EditText)findViewById(R.id.name);
        weight = (EditText)findViewById(R.id.weight);
        height = (EditText)findViewById(R.id.height);
        goal_weight = (EditText)findViewById(R.id.goal_weight);

        Spinner spinner = (Spinner) findViewById(R.id.gender_spinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.gender, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

        mDate.init(mDate.getYear(), mDate.getMonth(), mDate.getDayOfMonth(), new DatePicker.OnDateChangedListener() {
            //값이 바뀔때마다 텍스트뷰의 값을 바꿔준다.
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                //monthOfYear는 0값이 1월을 뜻하므로 1을 더해줌 나머지는 같다.
                mTxtDate.setText(String.format("%d/%d/%d", year,monthOfYear + 1, dayOfMonth));
            }
        });

        cancel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                finish();
            }
        });
        register.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                sqlDB = myDBHelper.getWritableDatabase();
                //String updateQuery = "INSERT INTO User VALUES ("+name+","+name+"0, 1996/03/29,"+s_height+","+s_weight+",1,1,1);";
                String updateQuery = "INSERT INTO User VALUES (2, '123', '123', 0, 1996-03-29, 168.2, 62.3, 1, 1, 1);";
                Toast.makeText(ProfileActivity.this, updateQuery, Toast.LENGTH_SHORT).show();
                sqlDB.execSQL(updateQuery);
                sqlDB.close();
            }
        });
    }
}
