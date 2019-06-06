package com.example.weightmanager.Fragment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weightmanager.R;
import com.example.weightmanager.model.MyDBHelper;
import com.example.weightmanager.model.ProfileActivity;
import com.example.weightmanager.model.SplashActivity;
import com.example.weightmanager.model.StartActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProfileFragment extends Fragment {
    View fragmentView;
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

    String hint_name, hint_birth;
    Double hint_heigh, hint_weight, hint_goal_weight;
    int hint_gender, hint_age;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentView =inflater.inflate(R.layout.fragment_profile, container, false);

        myDBHelper = new MyDBHelper(getContext());
        sqlDB = myDBHelper.getWritableDatabase();

        mDate = (DatePicker)fragmentView.findViewById(R.id.datepicker);
        cancel = (Button)fragmentView.findViewById(R.id.cancel);
        register = (Button)fragmentView.findViewById(R.id.register);
        name = (EditText)fragmentView.findViewById(R.id.name);
        weight = (EditText)fragmentView.findViewById(R.id.weight);
        height = (EditText)fragmentView.findViewById(R.id.height);
        age = (EditText)fragmentView.findViewById(R.id.age);
        SimpleDateFormat currentTime = new SimpleDateFormat("yyyy/M/d");
        s_birth = currentTime.format(date);
        goal_weight = (EditText)fragmentView.findViewById(R.id.goal_weight);
        final Spinner spinner = (Spinner)fragmentView.findViewById(R.id.gender_spinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getContext(), R.array.gender, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

        mDate.init(mDate.getYear(), mDate.getMonth(), mDate.getDayOfMonth(), new DatePicker.OnDateChangedListener() {
            //값이 바뀔때마다 텍스트뷰의 값을 바꿔준다.
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                //monthOfYear는 0값이 1월을 뜻하므로 1을 더해줌 나머지는 같다.
                s_birth = String.format("%d/%d/%d", year,monthOfYear + 1, dayOfMonth);
            }
        });

        String sqlQuery = "SELECT * from User;";
        Cursor cursor = (Cursor) sqlDB.rawQuery(sqlQuery, null);
        cursor.moveToFirst();
        hint_name = cursor.getString(1);
        hint_gender = cursor.getInt(3);
        hint_birth = cursor.getString(4);
        hint_heigh = cursor.getDouble(5);
        hint_weight = cursor.getDouble(6);
        hint_goal_weight = cursor.getDouble(8);
        hint_age = cursor.getInt(10);

        name.setHint(hint_name);
        spinner.setSelection(hint_gender);
        height.setHint(Double.toString(hint_heigh));
        weight.setHint(Double.toString(hint_weight));
        goal_weight.setHint(Double.toString(hint_goal_weight));
        age.setHint(Double.toString(hint_age));



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
                    String updateQuery = "UPDATE User SET name = '"+s_name+"', gender = "+s_gender+", birth='"+s_birth+"', heigh="+s_height+", weight = "+s_height+", weight = "+s_weight+", goal_weight="+s_goal_weight+", age = "+s_age+", goal_kcal = "+s_goal_kcal+";";
                    sqlDB.execSQL(updateQuery);
                    sqlDB.close();
                    Toast.makeText(getContext(), "수정이 완료 되었습니다.", Toast.LENGTH_LONG).show();

                }catch (NumberFormatException e)
                {
                    Toast.makeText(getContext(), "모든 항목을 입력해주세요.", Toast.LENGTH_LONG).show();
                }

            }
        });

        return fragmentView;
    }
}
