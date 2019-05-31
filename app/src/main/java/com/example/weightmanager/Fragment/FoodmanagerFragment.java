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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.weightmanager.R;
import com.example.weightmanager.model.AddFood;
import com.example.weightmanager.model.MyDBHelper;
import com.example.weightmanager.model.SearchFood;

import java.util.ArrayList;
import java.util.List;

public class FoodmanagerFragment extends Fragment {
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
        getDatabase();
        setBreakfast();
        setlaunch();
        setDinner();
        setSnack();
        breakfastButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), SearchFood.class);
                startActivity(intent);
            }
        });
        launchButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchFood.class);
                startActivity(intent);
            }
        });
        dinnerButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchFood.class);
                startActivity(intent);
            }
        });
        snakButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchFood.class);
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
 //       for (int i = 0; i < 3; i++) {
   //         list1.add(breakfastMenu[i]);
     //   }
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

        RecyclerView recyclerView = (RecyclerView)fragmentView.findViewById(R.id.foodmanagerFragment_listview_snak) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext())) ;
        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
        SimpleTextAdapter adapter = new SimpleTextAdapter(list1) ;
        recyclerView.setAdapter(adapter) ;
    }

    @Override
    public void onResume() {
        myDBHelper =new MyDBHelper(getContext());
        sqlDB = myDBHelper.getReadableDatabase();
        String sqlSelect = "SELECT * FROM Foodset";
        Cursor cursor = (Cursor) sqlDB.rawQuery(sqlSelect, null);


        super.onResume();
    }
}
