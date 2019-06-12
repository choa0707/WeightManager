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
    int goal;//체중감량,증가,유지 (체중관리방법)
    Button cancel;//취소버튼(체중관리방법 재선택 가능)
    Button register;//등록버튼(등록한 내용이 userDB에 등록)
    EditText name, weight, height, goal_weight, age;
    private String s_name;
    private String s_birth, gender, waterTime;
    private double s_weight, s_height, s_goal_weight, s_goal_kcal;
    private int s_gender, s_age;
    long now = System.currentTimeMillis();
    Date date = new Date(now);//현재시간(등록한 시간을 구별할 때 사용)
    MyDBHelper myDBHelper;
    SQLiteDatabase sqlDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        myDBHelper = new MyDBHelper(this);

        final Intent intent = getIntent();
        goal  = intent.getExtras().getInt("weightLevel");//체중관리방법 구별값을 가져옴
        mDate = (DatePicker)findViewById(R.id.datepicker);
        cancel = (Button)findViewById(R.id.cancel);
        register = (Button)findViewById(R.id.register);
        name = (EditText)findViewById(R.id.name);
        weight = (EditText)findViewById(R.id.weight);
        height = (EditText)findViewById(R.id.height);
        age = (EditText)findViewById(R.id.age);
        goal_weight = (EditText)findViewById(R.id.goal_weight);
        SimpleDateFormat currentTime = new SimpleDateFormat("yyyy/M/d");//연월일 형식 지정하여 정장해 주는 부분
        s_birth = currentTime.format(date);//현재 시간 받아오는 부분
        waterTime = new String(s_birth);
        final Spinner spinner = (Spinner) findViewById(R.id.gender_spinner);
        //gender부분 리소스의 string.xml에 저장하여 사용
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.gender, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

        //날짜 초기화 시켜주는 부분
        mDate.init(mDate.getYear(), mDate.getMonth(), mDate.getDayOfMonth(), new DatePicker.OnDateChangedListener() {
            //값이 바뀔때마다 텍스트뷰의 값을 바꿔준다.
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //monthOfYear는 0값이 1월을 뜻하므로 1을 더해줌
                s_birth = String.format("%d/%d/%d", year,monthOfYear + 1, dayOfMonth);//스피너에서 선택한 날짜를 형태를 지정하여 s_birth에 저장
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
                //try~catch문으로 항목 다 입력하지 않았을 경우, 해당 데이터형으로 입력하지 않았을 경우 에러띄움
                try{
                    s_name = name.getText().toString();
                    s_height = Double.parseDouble(height.getText().toString());
                    s_weight = Double.parseDouble(weight.getText().toString());
                    s_goal_weight = Double.parseDouble(goal_weight.getText().toString());
                    gender = spinner.getSelectedItem().toString();
                    s_age = Integer.parseInt(age.getText().toString());
                    if(gender.equals("남자"))//성별이 남자일 경우
                    {
                        s_goal_kcal = ((6.25*s_height)+(10*s_goal_weight)-(5*s_age)+5)*1.54;//설정한 목표체중값을 이용하여 일일 칼로리 섭취량 계산
                        s_gender = 0;
                    }
                    else //여자일 경우 동일한 방식 사용
                    {
                        s_goal_kcal = ((6.25*s_height)+(10*s_goal_weight)-(5*s_age)-161)*1.54;
                        s_gender = 1;
                    }

                    sqlDB = myDBHelper.getWritableDatabase();
                    //User테이블에 입력하는 쿼리
                    String updateQuery = "INSERT INTO User (name,nickname,gender,birth,heigh,weight,goal,goal_weight,goal_kcal, age) VALUES ('"+s_name+"','"+s_name+"',"+s_gender+", '"+s_birth+"',"+s_height+","+s_weight+","+goal+","+s_goal_weight+","+s_goal_kcal+","+s_age+");";



                    sqlDB.execSQL(updateQuery);//쿼리문 실행
                    //Water테이블에 가입한 날 등록(아직 다른 기능에 사용하는 일 없음)
                    sqlDB.execSQL("INSERT INTO Water (user_id, date, amount) VALUES (1, '"+s_birth+"', 0)");

                    sqlDB.close();

                    Intent intent2;
                    //스플래쉬 액티비티로 이동하면 유저정보가 등록되어 있기 때문에 바로 메인화면으로 넘어감
                    intent2 = new Intent(getApplicationContext(), SplashActivity.class);
                    startActivity(intent2);
                }catch (NumberFormatException e)
                {
                    //에러가 날 때 토스트 메세지 띄워줌
                    Toast.makeText(ProfileActivity.this, "모든 항목을 입력해주세요.", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
