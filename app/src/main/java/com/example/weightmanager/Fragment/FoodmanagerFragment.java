package com.example.weightmanager.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weightmanager.R;
import com.example.weightmanager.model.AddFood;
import com.example.weightmanager.model.FoodData;
import com.example.weightmanager.model.MyDBHelper;
import com.example.weightmanager.model.SearchFood;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class FoodmanagerFragment extends Fragment {
    public int count_breakfast=0, count_launch=0, count_diner=0, count_snak = 0;
    double total_kcal, oneday_kcal;
    String userName, s_oneday_kcal;
    TextView total,oneday;
    View fragmentView;
    MyDBHelper myDBHelper;
    SQLiteDatabase sqlDB;
    String[] breakfastMenu, launchMenu, dinnerMenu, snackMenu;
    Button breakfastButton, launchButton, dinnerButton, snakButton;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_foodmanager, container,false);

        breakfastButton = (Button)fragmentView.findViewById(R.id.breakfastButton);
        launchButton = (Button)fragmentView.findViewById(R.id.launchButton);
        dinnerButton = (Button)fragmentView.findViewById(R.id.dinnerButton);
        snakButton = (Button)fragmentView.findViewById(R.id.snackButton);
        total = (TextView)fragmentView.findViewById(R.id.total_kcal);
        oneday = (TextView)fragmentView.findViewById(R.id.oneday_kcal);
        getDatabase();
        setBreakfast();
        setlaunch();
        setDinner();
        setSnack();
        breakfastButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), SearchFood.class);
                intent.putExtra("timing", 1);
                startActivity(intent);
            }
        });
        launchButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchFood.class);
                intent.putExtra("timing", 2);
                startActivity(intent);
            }
        });
        dinnerButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchFood.class);
                intent.putExtra("timing", 3);
                startActivity(intent);
            }
        });
        snakButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchFood.class);
                intent.putExtra("timing", 4);
                startActivity(intent);
            }
        });
        return fragmentView;
    }

    private void getDatabase() {

    }

    private void setBreakfast() {
        //나중에 데이터받아서 셋팅

        final ArrayList<String> list1 = new ArrayList<>();
        //arraylist에 담아줌
        for (int i = 0; i < count_breakfast; i++) {
            Log.d("realtest", Integer.toString(i));
            list1.add(breakfastMenu[i]);
        }
        RecyclerView recyclerView = (RecyclerView)fragmentView.findViewById(R.id.foodmanagerFragment_listview_breakfast) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext())) ;
        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
        SimpleTextAdapter adapter = new SimpleTextAdapter(list1) ;
        recyclerView.setAdapter(adapter) ;
    }
    private void setlaunch() {
        //나중에 데이터받아서 셋팅

        final ArrayList<String> list1 = new ArrayList<>();
        //arraylist에 담아줌
        for (int i = 0; i < count_launch; i++) {
            list1.add(launchMenu[i]);
        }
        RecyclerView recyclerView = (RecyclerView)fragmentView.findViewById(R.id.foodmanagerFragment_listview_launch) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext())) ;
        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
        SimpleTextAdapter adapter = new SimpleTextAdapter(list1) ;
        recyclerView.setAdapter(adapter) ;
    }
    private void setDinner() {
        //나중에 데이터받아서 셋팅

        final ArrayList<String> list1 = new ArrayList<>();
        //arraylist에 담아줌
        for (int i = 0; i < count_diner; i++) {
            list1.add(dinnerMenu[i]);
        }
        RecyclerView recyclerView = (RecyclerView)fragmentView.findViewById(R.id.foodmanagerFragment_listview_dinner) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext())) ;
        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
        SimpleTextAdapter adapter = new SimpleTextAdapter(list1) ;
        recyclerView.setAdapter(adapter) ;
    }
    private void setSnack() {
        //나중에 데이터받아서 셋팅

        final ArrayList<String> list1 = new ArrayList<>();
        //arraylist에 담아줌
        for (int i = 0; i < count_snak; i++) {
            list1.add(snackMenu[i]);
        }
        RecyclerView recyclerView = (RecyclerView)fragmentView.findViewById(R.id.foodmanagerFragment_listview_snak) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext())) ;
        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
        SimpleTextAdapter adapter = new SimpleTextAdapter(list1) ;
        recyclerView.setAdapter(adapter) ;
    }

    @Override
    public void onResume() {
        count_breakfast = 0;
        count_snak = 0;
        count_diner = 0;
        count_launch = 0;
        total_kcal = 0;
        breakfastMenu = new String[100];
        launchMenu = new String[100];
        dinnerMenu = new String[100];

        myDBHelper = new MyDBHelper(getContext());
        sqlDB = myDBHelper.getReadableDatabase();

        //User이름 가져오기
        String userQuery = "SELECT * FROM User";
        Cursor cursor = (Cursor) sqlDB.rawQuery(userQuery, null);
        cursor.moveToFirst();
        userName = cursor.getString(1);
        oneday_kcal = cursor.getDouble(9);

        DecimalFormat format = new DecimalFormat(".##");
        String real_oneday_kcal = format.format(oneday_kcal);

        s_oneday_kcal = userName+"님의 하루 권장 섭취량은 "+real_oneday_kcal+"kcal입니다.";
        oneday.setText(s_oneday_kcal);

        String sqlSelect = "SELECT * FROM Foodset";
        cursor = (Cursor) sqlDB.rawQuery(sqlSelect, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int foodID;
            int timing;
            String foodName;
            int foodKcal;

            String tmpData;

            foodID = cursor.getInt(2);
            timing  = cursor.getInt(4);

            String sqlQuery = "SELECT name FROM Food WHERE Food.food_id = "+foodID+";";
            Cursor namecursor = (Cursor) sqlDB.rawQuery(sqlQuery, null);
            namecursor.moveToFirst();
            foodName = namecursor.getString(0);

            sqlQuery = "SELECT kcal FROM Food WHERE Food.food_id = "+foodID+";";
            namecursor = (Cursor) sqlDB.rawQuery(sqlQuery, null);
            namecursor.moveToFirst();
            foodKcal = namecursor.getInt(0);
            tmpData = foodName+"  "+foodKcal+" kcal";

            total_kcal += foodKcal;
            if (timing == 1)
            {
                breakfastMenu[count_breakfast++] = tmpData;
            } else if (timing == 2)
            {
                launchMenu[count_launch++] = tmpData;

            } else if (timing == 3)
            {
                dinnerMenu[count_diner++] = tmpData;
            } else if (timing == 4)
            {
                snackMenu[count_snak++] = tmpData;
            }
            cursor.moveToNext();
        }
        total.setText(Integer.toString((int) total_kcal)+" kcal");

        cursor.close();
        sqlDB.close();
        setBreakfast();
        setlaunch();
        setDinner();
        setSnack();

        super.onResume();
    }
}
