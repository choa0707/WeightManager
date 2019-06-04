package com.example.weightmanager.Fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weightmanager.R;
import com.example.weightmanager.model.MyDBHelper;

public class WatermanagerFragment extends Fragment {

    View fragmentView;
    TextView title;
    private int amount;
    MyDBHelper myDBHelper;
    SQLiteDatabase sqlDB;
    ImageView water1,water2,water3,water4,water5,water6;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_watermanager, container, false);
        myDBHelper = new MyDBHelper(getContext());
        title = (TextView)fragmentView.findViewById(R.id.title);

        water1 = (ImageView)fragmentView.findViewById(R.id.water1);
        water2 = (ImageView)fragmentView.findViewById(R.id.water2);
        water3 = (ImageView)fragmentView.findViewById(R.id.water3);
        water4 = (ImageView)fragmentView.findViewById(R.id.water4);
        water5 = (ImageView)fragmentView.findViewById(R.id.water5);
        water6 = (ImageView)fragmentView.findViewById(R.id.water6);

        water1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDB();
                if (amount == 0)
                {
                    Log.d("water", "1눌러짐");
                    amount = 1;
                }
                else if (amount == 1)
                {
                    amount = 0;
                }
                titleSetting();
                settingWater();
                dbSetting();
            }
        });
        water2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDB();
                if (amount == 1)
                {
                    amount = 2;
                }
                else if (amount == 2)
                {
                    amount = 1;
                }
                titleSetting();
                settingWater();
                dbSetting();
            }
        });
        water3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDB();
                if (amount == 2)
                {
                    amount = 3;
                }
                else if (amount == 3)
                {
                    amount = 2;
                }
                titleSetting();
                settingWater();
                dbSetting();
            }
        });
        water4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDB();
                if (amount == 3)
                {
                    amount = 4;
                }
                else if (amount == 4)
                {
                    amount = 3;
                }
                titleSetting();
                settingWater();
                dbSetting();
            }
        });
        water5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDB();
                if (amount == 4)
                {
                    amount = 5;
                }
                else if (amount == 5)
                {
                    amount = 4;
                }
                titleSetting();
                settingWater();
                dbSetting();
            }
        });
        water6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDB();
                if (amount == 5)
                {
                    amount = 6;
                }
                else if (amount == 6)
                {
                    amount = 5;
                }
                titleSetting();
                settingWater();
                dbSetting();
            }
        });

        return fragmentView;

    }

    @Override
    public void onResume() {
        myDBHelper = new MyDBHelper(getContext());
        sqlDB = myDBHelper.getWritableDatabase();
        getDB();
        titleSetting();
        settingWater();


        super.onResume();
    }

    private void getDB() {
        String userQuery = "SELECT amount FROM Water;";
        Cursor cursor = (Cursor) sqlDB.rawQuery(userQuery, null);
        cursor.moveToFirst();
        amount = cursor.getInt(0);
    }

    private void settingWater() {
        water1.setImageResource(R.drawable.water_white);
        water2.setImageResource(R.drawable.water_white);
        water3.setImageResource(R.drawable.water_white);
        water4.setImageResource(R.drawable.water_white);
        water5.setImageResource(R.drawable.water_white);
        water6.setImageResource(R.drawable.water_white);
        switch (amount){
            case 0:
                water1.setImageResource(R.drawable.water_plus);
                break;
            case 1:
                water1.setImageResource(R.drawable.water_full);
                water2.setImageResource(R.drawable.water_plus);
                break;
            case 2:
                water1.setImageResource(R.drawable.water_full);
                water2.setImageResource(R.drawable.water_full);
                water3.setImageResource(R.drawable.water_plus);
                break;
            case 3:
                water1.setImageResource(R.drawable.water_full);
                water2.setImageResource(R.drawable.water_full);
                water3.setImageResource(R.drawable.water_full);
                water4.setImageResource(R.drawable.water_plus);
                break;
            case 4:
                water1.setImageResource(R.drawable.water_full);
                water2.setImageResource(R.drawable.water_full);
                water3.setImageResource(R.drawable.water_full);
                water4.setImageResource(R.drawable.water_full);
                water5.setImageResource(R.drawable.water_plus);
                break;
            case 5:
                water1.setImageResource(R.drawable.water_full);
                water2.setImageResource(R.drawable.water_full);
                water3.setImageResource(R.drawable.water_full);
                water4.setImageResource(R.drawable.water_full);
                water5.setImageResource(R.drawable.water_full);
                water6.setImageResource(R.drawable.water_plus);
                break;
            case 6:
                water1.setImageResource(R.drawable.water_full);
                water2.setImageResource(R.drawable.water_full);
                water3.setImageResource(R.drawable.water_full);
                water4.setImageResource(R.drawable.water_full);
                water5.setImageResource(R.drawable.water_full);
                water6.setImageResource(R.drawable.water_full);
                break;
        }
    }

    private void titleSetting()
    {
        if (amount < 2)
        {
            title.setText("목말라요");
        }
        else if (amount < 4)
        {
            title.setText("잘하고 있어요");
        }
        else
        {
            title.setText("수분 빵빵");
        }
    }
    private void dbSetting()
    {
        String userQuery = "UPDATE Water SET amount = "+amount+";";
        sqlDB.execSQL(userQuery);
    }

    @Override
    public void onDestroy() {
        sqlDB.close();
        super.onDestroy();
    }
}

