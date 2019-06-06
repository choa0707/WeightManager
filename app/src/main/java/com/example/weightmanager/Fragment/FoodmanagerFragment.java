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
    String foodName, foodKcal, foodString;
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
        delete(recyclerView, adapter, list1, 1);
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

      delete(recyclerView, adapter, list1, 2);
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
        delete(recyclerView, adapter, list1, 3);
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
        //삭제 함수
        delete(recyclerView, adapter, list1, 4);

        recyclerView.setAdapter(adapter) ;
    }
    public void delete(final RecyclerView recyclerView, final SimpleTextAdapter adapter, final ArrayList<String> list1, final int ttiming)
    {
        adapter.setItemClick(new SimpleTextAdapter.ItemClick() {
            @Override
            public void onClick(View view, int position) {
                //음식 텍스트 받아오기(ex. rice 300kcal)
                //split하여 음식 이름과 칼로리 받아오기
                //음식 이름과 칼로리와 일치하는 food_id받아오기
                //food_id를 가지고 있고 timing이 클릭한 timing(아점저간)과 일치하는 foodset 데이터 삭제
                //삭제후 세로고침.
                foodString = list1.get(position);
                String[] foodSplit1 = foodString.split("  ");
                foodName = foodSplit1[0];
                foodKcal = foodSplit1[1];
                String[] foodSplit2 = foodKcal.split(" ");
                foodKcal = foodSplit2[0];

                Log.d("testest", foodName);
                Log.d("testest", foodKcal);

                String deleteFoodid = "SELECT food_id FROM Food WHERE name = '"+foodName+"';";
                Cursor deletecursor = (Cursor)sqlDB.rawQuery(deleteFoodid, null);
                deletecursor.moveToFirst();
                int food_id = deletecursor.getInt(0);

                String deleteQuery = "SELECT foodset_id FROM Foodset WHERE timing = "+ttiming+" and food_id = "+food_id+";";
                deletecursor = (Cursor)sqlDB.rawQuery(deleteQuery, null);
                deletecursor.moveToFirst();
                int foodset_id = deletecursor.getInt(0);

                deleteQuery = "DELETE FROM Foodset WHERE foodset_id = "+foodset_id+";";
                sqlDB.execSQL(deleteQuery);
                Toast.makeText(getContext(), "삭제 되었습니다.", Toast.LENGTH_SHORT).show();
                list1.remove(position);
               recyclerView.removeViewAt(position);
                adapter.notifyItemRemoved(position);
                adapter.notifyItemRangeChanged(position, list1.size());
                adapter.notifyDataSetChanged();
            }
        });
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
        snackMenu = new String[100];

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

        setBreakfast();
        setlaunch();
        setDinner();
        setSnack();

        super.onResume();
    }

    @Override
    public void onDestroy() {
        sqlDB.close();
        super.onDestroy();
    }
}
