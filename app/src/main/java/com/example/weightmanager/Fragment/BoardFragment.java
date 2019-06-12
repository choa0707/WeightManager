package com.example.weightmanager.Fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.weightmanager.R;
import com.example.weightmanager.model.BoardAdapter;
import com.example.weightmanager.model.BoardData;
import com.example.weightmanager.model.FoodData;
import com.example.weightmanager.model.MyDBHelper;
import com.example.weightmanager.model.SearchAdapter;

import java.util.ArrayList;
import java.util.List;

public class BoardFragment extends Fragment {

    MyDBHelper myDBHelper;
    SQLiteDatabase sqlDB;
    private int goal;
    private List<BoardData> list;          // 데이터를 넣은 리스트변수
    private ListView listView;          // 검색을 보여줄 리스트변수
    private BoardAdapter adapter;      // 리스트뷰에 연결할 아답터
    private ArrayList<BoardData> arraylist;
    private int timing;
    View fragmentView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_board, container,false);
        listView = (ListView)fragmentView.findViewById(R.id.listview_board);

        // 리스트를 생성한다.
        list = new ArrayList<BoardData>();

      settingList();

        // 리스트의 모든 데이터를 arraylist에 복사한다.// list 복사본을 만든다.
        arraylist = new ArrayList<BoardData>();
        arraylist.addAll(list);
        //TODO:check

        // 리스트에 연동될 아답터를 생성한다.
        adapter = new BoardAdapter(list, getContext());

        // 리스트뷰에 아답터를 연결한다.
        listView.setAdapter(adapter);

        return fragmentView;
    }

    private void settingList() {
        myDBHelper = new MyDBHelper(getContext());
        sqlDB = myDBHelper.getReadableDatabase();

        String getgoal = "SELECT goal FROM User;";
        Cursor cursor = (Cursor) sqlDB.rawQuery(getgoal, null);
        cursor.moveToFirst();
        goal = cursor.getInt(0);

        String sqlSelect = "SELECT * FROM Board WHERE goal = "+goal+";";
        cursor = (Cursor) sqlDB.rawQuery(sqlSelect, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            BoardData getBoardData = new BoardData();
            getBoardData.title = cursor.getString(2);
            getBoardData.text = cursor.getString(3);
            getBoardData.imageUrl = cursor.getString(4);
            list.add(getBoardData);
            cursor.moveToNext();
        }
        cursor.close();
        sqlDB.close();
    }
}
// INSERT INTO Board ('title', 'text') VALUES ('this is title3', 't');
