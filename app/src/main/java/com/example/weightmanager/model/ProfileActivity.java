package com.example.weightmanager.model;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weightmanager.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProfileActivity extends AppCompatActivity {

    DatePicker mDate;
    TextView mTxtDate;
    int goal;
    Button cancel;
    Button register;
    EditText name, weight, height, goal_weight, age;
    private String s_name;
    private String s_birth, gender, waterTime;
    private double s_weight, s_height, s_goal_weight, s_goal_kcal;
    private int s_gender, s_age;
    long now = System.currentTimeMillis();
    Date date = new Date(now);
    MyDBHelper myDBHelper;
    SQLiteDatabase sqlDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        myDBHelper = new MyDBHelper(this);


        final Intent intent = getIntent();
        goal  = intent.getExtras().getInt("weightLevel");
        mDate = (DatePicker)findViewById(R.id.datepicker);
        cancel = (Button)findViewById(R.id.cancel);
        register = (Button)findViewById(R.id.register);
        name = (EditText)findViewById(R.id.name);
        weight = (EditText)findViewById(R.id.weight);
        height = (EditText)findViewById(R.id.height);
        age = (EditText)findViewById(R.id.age);
        goal_weight = (EditText)findViewById(R.id.goal_weight);
        SimpleDateFormat currentTime = new SimpleDateFormat("yyyy/M/d");
        s_birth = currentTime.format(date);
        waterTime = new String(s_birth);
        final Spinner spinner = (Spinner) findViewById(R.id.gender_spinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.gender, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

        mDate.init(mDate.getYear(), mDate.getMonth(), mDate.getDayOfMonth(), new DatePicker.OnDateChangedListener() {
            //값이 바뀔때마다 텍스트뷰의 값을 바꿔준다.
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                //monthOfYear는 0값이 1월을 뜻하므로 1을 더해줌 나머지는 같다.
                s_birth = String.format("%d/%d/%d", year,monthOfYear + 1, dayOfMonth);
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
                try{
                    s_name = name.getText().toString();
                    s_height = Integer.parseInt(height.getText().toString());
                    s_weight = Integer.parseInt(weight.getText().toString());
                    s_goal_weight = Integer.parseInt(goal_weight.getText().toString());
                    gender = spinner.getSelectedItem().toString();
                    s_age = Integer.parseInt(age.getText().toString());
                    if(gender.equals("남자"))
                    {
                        s_goal_kcal = ((6.25*s_height)+(10*s_goal_weight)-(5*s_age)+5)*1.54;
                        s_gender = 0;
                    }
                    else
                    {
                        s_goal_kcal = ((6.25*s_height)+(10*s_goal_weight)-(5*s_age)-161)*1.54;
                        s_gender = 1;
                    }

                    sqlDB = myDBHelper.getWritableDatabase();
                    String updateQuery = "INSERT INTO User (name,nickname,gender,birth,heigh,weight,goal,goal_weight,goal_kcal, age) VALUES ('"+s_name+"','"+s_name+"',"+s_gender+", '"+s_birth+"',"+s_height+","+s_weight+","+goal+","+s_goal_weight+","+s_goal_kcal+","+s_age+");";

                    //Log.d("dbtest", updateQuery);
                    //Toast.makeText(ProfileActivity.this, updateQuery, Toast.LENGTH_SHORT).show();

                    sqlDB.execSQL(updateQuery);
                    sqlDB.execSQL("INSERT INTO Water (user_id, date, amount) VALUES (1, '"+s_birth+"', 0)");

                    sqlDB.close();

                    Intent intent2;
                    intent2 = new Intent(getApplicationContext(), SplashActivity.class);
                    startActivity(intent2);
                }catch (NumberFormatException e)
                {
                    Toast.makeText(ProfileActivity.this, "모든 항목을 입력해주세요.", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
