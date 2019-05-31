package com.example.weightmanager.model;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.weightmanager.R;

import java.util.ArrayList;
import java.util.List;

public class SearchFood extends AppCompatActivity {

    Button addfoodButton;
    MyDBHelper myDBHelper;
    SQLiteDatabase sqlDB;

    private List<FoodData> list;          // 데이터를 넣은 리스트변수
    private ListView listView;          // 검색을 보여줄 리스트변수
    private EditText editSearch;        // 검색어를 입력할 Input 창
    private SearchAdapter adapter;      // 리스트뷰에 연결할 아답터
    private ArrayList<FoodData> arraylist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_food);

        addfoodButton = (Button) findViewById(R.id.addfoodButton);
        editSearch = (EditText) findViewById(R.id.edit_search);
        listView = (ListView) findViewById(R.id.search_food_listview);


        // 리스트를 생성한다.
        list = new ArrayList<FoodData>();

        // 검색에 사용할 데이터을 미리 저장한다.
        settingList();

        // 리스트의 모든 데이터를 arraylist에 복사한다.// list 복사본을 만든다.
        arraylist = new ArrayList<FoodData>();
        arraylist.addAll(list);

        // 리스트에 연동될 아답터를 생성한다.
        adapter = new SearchAdapter(list, this);

        // 리스트뷰에 아답터를 연결한다.
        listView.setAdapter(adapter);

        // input창에 검색어를 입력시 "addTextChangedListener" 이벤트 리스너를 정의한다.
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                // input창에 문자를 입력할때마다 호출된다.
                // search 메소드를 호출한다.
                String text = editSearch.getText().toString();
                search(text);
            }
        });

        addfoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //값받게 resultfor로 바꿀예정
                Intent intent = new Intent(getApplicationContext(), AddFood.class);
                startActivity(intent);
            }
        });
    }

    // 검색을 수행하는 메소드
    public void search(String charText) {

        // 문자 입력시마다 리스트를 지우고 새로 뿌려준다.
        list.clear();

        // 문자 입력이 없을때는 모든 데이터를 보여준다.
        if (charText.length() == 0) {
            list.addAll(arraylist);
        }
        // 문자 입력을 할때..
        else {
            // 리스트의 모든 데이터를 검색한다.
            for (int i = 0; i < arraylist.size(); i++) {
                // arraylist의 모든 데이터에 입력받은 단어(charText)가 포함되어 있으면 true를 반환한다.
                if (arraylist.get(i).name.toLowerCase().contains(charText)) {
                    // 검색된 데이터를 리스트에 추가한다.
                    list.add(arraylist.get(i));
                }
            }
        }
        // 리스트 데이터가 변경되었으므로 아답터를 갱신하여 검색된 데이터를 화면에 보여준다.
        adapter.notifyDataSetChanged();
    }

    // 검색에 사용될 데이터를 리스트에 추가한다.
    private void settingList() {
        myDBHelper = new MyDBHelper(this);
        sqlDB = myDBHelper.getReadableDatabase();
        String sqlSelect = "SELECT * FROM Food";
        Cursor cursor = (Cursor) sqlDB.rawQuery(sqlSelect, null);
        FoodData getFoodData = new FoodData();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            getFoodData.name = cursor.getString(1);
            getFoodData.kcal = cursor.getDouble(2);
            getFoodData.carb = cursor.getDouble(3);
            getFoodData.protein = cursor.getDouble(4);
            getFoodData.fat = cursor.getDouble(5);

            list.add(getFoodData);

            cursor.moveToNext();
        }
        cursor.close();
        sqlDB.close();
    }

}
// INSERT INTO Food (name, kcal, carb, protein, fat) VALUES ('apple', 12.5,11.8,65.2,11.6);
// "INSERT INTO User (name,nickname,gender,birth,heigh,weight,goal,goal_weight,goal_kcal, age) VALUES ('"+s_name+"','"+s_name+"',"+s_gender+", '"+s_birth+"',"+s_height+","+s_weight+","+goal+","+s_goal_weight+","+s_goal_kcal+","+s_age+");";
