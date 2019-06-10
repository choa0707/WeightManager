package com.example.weightmanager.model;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.weightmanager.R;

public class SplashActivity extends AppCompatActivity {
    MyDBHelper myDBHelper;
    SQLiteDatabase sqlDB;

    @Override
    protected void onDestroy() {
        sqlDB.close();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        myDBHelper =new MyDBHelper(this);
        sqlDB = myDBHelper.getWritableDatabase();
        myDBHelper.onCreate(sqlDB);


        String checkinfo = "SELECT * FROM Board;";
        Cursor cursor = (Cursor)sqlDB.rawQuery(checkinfo, null);
        cursor.moveToFirst();
        if(cursor.getCount() == 0)
        {
            sqlDB.execSQL("INSERT INTO Board (goal, title, text, image) VALUES (-1,'뱃살 멀리하는 생활습관!', '1. 칼로리 마시지 않기\n\n" +
                    "감량을 실패하게 만드는 가장 큰 원인 중 하나는 청량음료를 마시거나 당분이 많은 주스를 마시거나 혹은 저녁 시간에 술을 마신다거나 하는 등 칼로리 음료를 \n" +
                    "섭취하는 것!! 음료 대신 물을 자주 마시도록 하고 레몬, 라임 민트 등을 물에 넣어 먹으며 디톡스 효과를 봐서 체중감량에 도움이 됩니다.\n\n" +
                    "2. 배에 힘주기\n\n" +
                    "배에 힘을 주고 생활하는 것은 뱃살을 없앨 수 있습니다. 드로인 뱃살 운동이라고 하는데 서서 있거나 앉아 있을 때 혹은 누워있을 때 배에 힘을 주고 허리를 곧게 펴서\n" +
                    "자세를 유지하는 것이 좋아요. 운동을 통해 복직은의 힘이 길러지면서 신진대사를 촉진하고 체지방 분해효과를 볼 수 있어요.\n\n" +
                    "3. 정갈한 식단 고수\n\n" +
                    "뱃살을 빠르게 줄일 수 있는 간단한 방법 중 하나는 가공식품을 끊고 신선한 식품으로 냉장고를 채우는 것\n" +
                    "가능할 떄마다 자연식품을 구입하고 선택해 건강한 식습관을 유지하는 것을 추천해요.\n" +
                    "식이요법을 정리하고 정크푸드를 식단에서 치우면 엄청난 신체의 변화를 느낄 수 있어요.\n\n" +
                    "4. 공복에 따뜻한 물 마시기\n\n" +
                    "공복에 마시는 따뜻한 물 한 잔이 주는 건강효과는 무시할 수 없어요.\n" +
                    "몸의 체온을 상승시키기 때문에 신진대사 속도를 빠르게 만들어서 칼로리 소모에 도움이 됩니다.\n" +
                    "지방보빅을 분해하는데 효과적이고 노폐물 배출, 변비예방, 소화력향상 등 체중감량에 직간접적으로 많은 도움을 줘요.', -1);");

            sqlDB.execSQL("INSERT INTO Board (goal, title, text, image) VALUES (0,'요요현상 없이 체중을 유지하는 방법' ,'1. 체중을 주기적으로 체크한다.\n\n" +
                    "요요현상이 온 사람은 대부분 6개월 이내의 기간에서 감량한 체중의 80%이상이 돌아오는 경우가 많습니다.\n" +
                    "그러나 짧은 기간에 많은 체중이 찌는 경우는 없기 때문에 1~2kg이 늘어날 즈음에 우리는 체중이 늘어났음을 알아차리고 이에 대처해 주는 것이 중요해요.\n" +
                    "그러므로 최소 2주에 한 번씩 체중을 꾸준히최소 2주에 한 번씩 체중을 꾸준히 체크해 주면서 나의 신체 상태를 확인하는 것이 필요합니다\n\n" +
                    "2. 감량 이후에도 운동을 꾸준히 한다.\n\n" +
                    "살을 빼는 데에는 칼로리 및 식단 조절이 필수인 것은 분명한 사실입니다. 하지만 빠진 체중을 잡아두고 유지하는 데 있어서는 운동이 필수적인데요.\n" +
                    "감량 이후에도 우리는 저열량 식단 등을 꾸준히 유지할 수 없기 때문이에요. 또한 우리 몸 내부의 시스템이 감량한 체중에 적응할 수 있도록 충분한 적응 기간을 가지면서\n" +
                    "체중을 꼼꼼하게 체크하고 운동하며 몸을 유지해주어야 합니다~', 0);");

            sqlDB.execSQL("INSERT INTO Board (goal, title, text, image) VALUES (1,'체중증가가 안되는 이유' ,'보통 식습관이 원인인 경우가 대부분이기 때문에 평소에 편식하는 습관을 갖는다거나 섭취하는 음식량이 적은 경우에는 올바른 식습관을 갖는 것이 좋은데요.\n\n" +
                    "만약 식습관과 상관없이 마른 체형을 유지하고 계신다면, 단순히 식습솬의 문제가 아닌 다른 원인일 가능성이 높습니다.\n" +
                    "실제로 체질적인 영향을 받는다거나 소화기 계통에 문제 생겼을 때 주로 살이 찌지 않기 때문인데요.\n" +
                    "이 경우, 기력이 약해지는 시기이기 때문에 몸의 기운을 회복하기 위해서 고열량의 음식, 영양성분이 풍부한 음식을 섭취하는 것이 좋습니다.', 1);");

        }

        checkinfo = "SELECT * FROM Food;";
        cursor = (Cursor)sqlDB.rawQuery(checkinfo, null);
        cursor.moveToFirst();
        if(cursor.getCount() == 0)
        {
            sqlDB.execSQL("INSERT INTO Food (name, kcal, carb, protein, fat) VALUES ('백미', 191, 41.04, 3.56, 0.83);");
            sqlDB.execSQL("INSERT INTO Food (name, kcal, carb, protein, fat) VALUES ('현미', 215, 44.42, 4.99, 1.74);");
            sqlDB.execSQL("INSERT INTO Food (name, kcal, carb, protein, fat) VALUES ('감자', 70, 15.71, 1.68, 0.10);");
            sqlDB.execSQL("INSERT INTO Food (name, kcal, carb, protein, fat) VALUES ('당근', 41, 9.58, 0.93, 0.24);");
            sqlDB.execSQL("INSERT INTO Food (name, kcal, carb, protein, fat) VALUES ('닭가슴살', 195,0, 29.55, 7.72);");
            sqlDB.execSQL("INSERT INTO Food (name, kcal, carb, protein, fat) VALUES ('돼지고기', 363, 0, 36.64, 22.83);");
            sqlDB.execSQL("INSERT INTO Food (name, kcal, carb, protein, fat) VALUES ('쇠고기', 348, 0, 31.86, 23.64);");
            sqlDB.execSQL("INSERT INTO Food (name, kcal, carb, protein, fat) VALUES ('베이컨', 27, 0.07, 1.85, 2.09);");
            sqlDB.execSQL("INSERT INTO Food (name, kcal, carb, protein, fat) VALUES ('샐러드', 9, 1.76, 0.84, 0.13);");
            sqlDB.execSQL("INSERT INTO Food (name, kcal, carb, protein, fat) VALUES ('우유', 122, 11.49, 8.09, 4.88);");
            sqlDB.execSQL("INSERT INTO Food (name, kcal, carb, protein, fat) VALUES ('마늘빵', 53, 7.30, 1.24, 2.04);");
            sqlDB.execSQL("INSERT INTO Food (name, kcal, carb, protein, fat) VALUES ('베이글', 270, 53.02, 10.52, 1.70);");
            sqlDB.execSQL("INSERT INTO Food (name, kcal, carb, protein, fat) VALUES ('브라우니', 129, 21.26, 1.62, 4.68);");
            sqlDB.execSQL("INSERT INTO Food (name, kcal, carb, protein, fat) VALUES ('고등어', 169, 0, 19.32, 9.36);");
            sqlDB.execSQL("INSERT INTO Food (name, kcal, carb, protein, fat) VALUES ('딸기', 4, 0.92, 0.08, 0.04);");
            sqlDB.execSQL("INSERT INTO Food (name, kcal, carb, protein, fat) VALUES ('바나나', 105, 26.95, 1.29, 0.39);");

        }


        String sqlSelect = "SELECT * FROM User;" ;
       cursor = (Cursor) sqlDB.rawQuery(sqlSelect, null);

        if (cursor.getCount() == 0)
        {
            Intent intent = new Intent(getApplicationContext(), StartActivity.class);
            startActivity(intent);
            finish();
        }
        else
        {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }

    }
}
