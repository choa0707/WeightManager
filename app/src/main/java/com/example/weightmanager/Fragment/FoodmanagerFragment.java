package com.example.weightmanager.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.weightmanager.R;

import java.util.ArrayList;
import java.util.List;

public class FoodmanagerFragment extends Fragment {
    View fragmentView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_foodmanager, container,false);

        setBreakfast();
        setlaunch();
        setDinner();
        setSnack();

        return fragmentView;
    }

    private void setBreakfast() {
        ListView listview = (ListView)fragmentView.findViewById(R.id.foodmanagerFragment_listview_breakfast);
        //나중에 데이터받아서 셋팅
        String[] LIST_MENU = {"고구마 75kcal", "감자 35kcal", "망고 74kcal"} ;
        final ArrayList<String> list1 = new ArrayList<>();
        //arraylist에 담아줌
        for (int i = 0; i < 3; i++) {
            list1.add(LIST_MENU[i]);

        }

        //custom하려면 따로 layout만듬. 현재는 한줄
        final ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(),  android.R.layout.simple_list_item_1, list1);
        // ListView 객체에 adapter 객체를 연결.
        listview.setAdapter(adapter1);
        // ListView 객체의 특정 아이템 클릭시 이벤트.
        // 해당 음식을 식단에서 뺄때 사용.
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view, int position, long id) {
                // 클릭한 아이템의 문자열을 가져옴
                String selected_item = (String)adapterView.getItemAtPosition(position);
                //해당 아이템을 ArrayList 객체에서 제거
                list1.remove(selected_item);
                // 어댑터 객체에 변경 내용을 반영.
                adapter1.notifyDataSetChanged();
                //DB연동하여 db에서도 제거
            }
        });
    }

    private void setlaunch(){
        ListView listview = (ListView)fragmentView.findViewById(R.id.foodmanagerFragment_listview_launch);
        //나중에 데이터받아서 셋팅
        String[] LIST_MENU = {"고구마 75kcal", "감자 35kcal", "망고 74kcal"} ;
        final ArrayList<String> list = new ArrayList<>();
        //arraylist에 담아줌
        for (int i = 0; i < 3; i++) {
            list.add(LIST_MENU[i]);

        }

        //custom하려면 따로 layout만듬. 현재는 한줄
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),  android.R.layout.simple_list_item_1, list);
        // ListView 객체에 adapter 객체를 연결.
        listview.setAdapter(adapter);
        // ListView 객체의 특정 아이템 클릭시 이벤트.
        // 해당 음식을 식단에서 뺄때 사용.
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view, int position, long id) {
                // 클릭한 아이템의 문자열을 가져옴
                String selected_item = (String)adapterView.getItemAtPosition(position);
                //해당 아이템을 ArrayList 객체에서 제거
                list.remove(selected_item);
                // 어댑터 객체에 변경 내용을 반영.
                adapter.notifyDataSetChanged();
                //DB연동하여 db에서도 제거
            }
        });
    }
    private void setDinner(){
        ListView listview = (ListView)fragmentView.findViewById(R.id.foodmanagerFragment_listview_dinner);
        //나중에 데이터받아서 셋팅
        String[] LIST_MENU = {"고구마 75kcal", "감자 35kcal", "망고 74kcal"} ;
        final ArrayList<String> list = new ArrayList<>();
        //arraylist에 담아줌
        for (int i = 0; i < 3; i++) {
            list.add(LIST_MENU[i]);

        }

        //custom하려면 따로 layout만듬. 현재는 한줄
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),  android.R.layout.simple_list_item_1, list);
        // ListView 객체에 adapter 객체를 연결.
        listview.setAdapter(adapter);
        // ListView 객체의 특정 아이템 클릭시 이벤트.
        // 해당 음식을 식단에서 뺄때 사용.
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view, int position, long id) {
                // 클릭한 아이템의 문자열을 가져옴
                String selected_item = (String)adapterView.getItemAtPosition(position);
                //해당 아이템을 ArrayList 객체에서 제거
                list.remove(selected_item);
                // 어댑터 객체에 변경 내용을 반영.
                adapter.notifyDataSetChanged();
                //DB연동하여 db에서도 제거
            }
        });
    }
    private void setSnack(){
        ListView listview = (ListView)fragmentView.findViewById(R.id.foodmanagerFragment_listview_snak);
        //나중에 데이터받아서 셋팅
        String[] LIST_MENU = {"고구마 75kcal", "감자 35kcal", "망고 74kcal"} ;
        final ArrayList<String> list = new ArrayList<>();
        //arraylist에 담아줌
        for (int i = 0; i < 3; i++) {
            list.add(LIST_MENU[i]);

        }

        //custom하려면 따로 layout만듬. 현재는 한줄
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),  android.R.layout.simple_list_item_1, list);
        // ListView 객체에 adapter 객체를 연결.
        listview.setAdapter(adapter);
        // ListView 객체의 특정 아이템 클릭시 이벤트.
        // 해당 음식을 식단에서 뺄때 사용.
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view, int position, long id) {
                // 클릭한 아이템의 문자열을 가져옴
                String selected_item = (String)adapterView.getItemAtPosition(position);
                //해당 아이템을 ArrayList 객체에서 제거
                list.remove(selected_item);
                // 어댑터 객체에 변경 내용을 반영.
                adapter.notifyDataSetChanged();
                //DB연동하여 db에서도 제거
            }
        });
    }
}
