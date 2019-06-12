package com.example.weightmanager.model;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.weightmanager.Fragment.BoardFragment;
import com.example.weightmanager.Fragment.FoodmanagerFragment;
import com.example.weightmanager.Fragment.ProfileFragment;
import com.example.weightmanager.Fragment.WatermanagerFragment;
import com.example.weightmanager.R;

public class MainActivity extends AppCompatActivity {

    // FrameLayout에 각 메뉴의 Fragment를 바꿔 줌
    private FragmentManager fragmentManager = getSupportFragmentManager();
    // 4개의 메뉴에 들어갈 Fragment(식단관리, 수분관리, 건강게시판, 회원수정 및 탈퇴)
    private FoodmanagerFragment foodmanagerFragment = new FoodmanagerFragment();
    private WatermanagerFragment watermanagerFragment = new WatermanagerFragment();
    private BoardFragment boardFragment = new BoardFragment();
    private ProfileFragment profileFragment = new ProfileFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.mainActivity_bottom_navigation_view);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //식단관리 창을 첫 메인화면으로 설정
        transaction.replace(R.id.mainActivity_frame_layout, foodmanagerFragment).commitAllowingStateLoss();

       //바턴네비게이션의 각 아이템을 클릭했을 시
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                 FragmentTransaction transaction = fragmentManager.beginTransaction();
                switch (menuItem.getItemId()) {
                    case R.id.navigation_food: {
                        //프레그먼트 화면이 대체됨(Mainactivity의 frmae_layout에서 id검색)
                        transaction.replace(R.id.mainActivity_frame_layout, foodmanagerFragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.navigation_water: {
                        transaction.replace(R.id.mainActivity_frame_layout, watermanagerFragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.navigation_board: {
                        transaction.replace(R.id.mainActivity_frame_layout, boardFragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.navigation_profile: {
                        transaction.replace(R.id.mainActivity_frame_layout, profileFragment).commitAllowingStateLoss();
                        break;
                    }
                }
                return true;
            }
        });

    }

}
