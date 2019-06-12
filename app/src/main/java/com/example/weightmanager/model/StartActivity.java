package com.example.weightmanager.model;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.weightmanager.R;

public class StartActivity extends AppCompatActivity {

    Button button_weightup;//체중증가버튼
    Button button_weightmaintain;//체중유지버튼
    Button button_weightdown;//체중감소버튼
    private int weightLevel;//인텐트로 넘길때 값을 넣어두기 위한 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        //객체화
        button_weightup = (Button) findViewById(R.id.weight_up);
        button_weightmaintain = (Button)findViewById(R.id.weight_maintain);
        button_weightdown = (Button)findViewById(R.id.weight_down);

        //체중증가, 유지, 감량 버튼을 눌었을 경우 weightLevel에 구별값 넣고, changeActivity함수 호출
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
        Intent intent = new Intent(StartActivity.this, ProfileActivity.class);//프로필등록하는 화면을 넘어감
        intent.putExtra("weightLevel", weightLevel);
        startActivity(intent);
        finish();
    }
}
