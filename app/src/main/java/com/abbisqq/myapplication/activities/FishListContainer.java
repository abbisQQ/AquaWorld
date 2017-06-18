package com.abbisqq.myapplication.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.abbisqq.myapplication.R;
import com.abbisqq.myapplication.data.FishContract;
import com.abbisqq.myapplication.fragments.FishListFragment;

public class FishListContainer extends AppCompatActivity {

    ActionBar bar;
    Fragment fish_fragment;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish_list_container);

        bar = getSupportActionBar();
        //animation from left to right
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);


        if (savedInstanceState == null) {
            position = getIntent().getExtras().getInt("position");

            switch (position) {
                case 0:
                    fish_fragment = FishListFragment.newInstance(FishContract.TABLE_NAME_CICHLIDS);

                    makingFragments(fish_fragment);
                    break;
                case 1:
                    fish_fragment = FishListFragment.newInstance(FishContract.TABLE_NAME_TETRAS);

                    makingFragments(fish_fragment);
                    break;
                case 2:
                    fish_fragment = FishListFragment.newInstance(FishContract.TABLE_NAME_CATFISHES);

                    makingFragments(fish_fragment);
                    break;
                default:

            }
        }
    }


    void makingFragments(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().
                replace(R.id.fish_list_container, fragment)
                .commit();

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }


    @Override
    protected void onResume() {
        super.onResume();
            position = getIntent().getExtras().getInt("position");
            bar.setTitle(setBarTitle(position));


    }


    private String setBarTitle(int position) {
        String title;
        switch (position) {
            case 0:
                title = FishContract.TABLE_NAME_CICHLIDS;
                break;
            case 1:
               title = FishContract.TABLE_NAME_TETRAS;
                break;
            case 2:
               title = FishContract.TABLE_NAME_CATFISHES;
                break;
            default:
                title = String.valueOf(R.string.app_name);

        }
        return title;

    }

}
