package com.abbisqq.myapplication.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.abbisqq.myapplication.R;
import com.abbisqq.myapplication.data.FishContract;
import com.abbisqq.myapplication.fragments.FishListFragment;

public class FishListContainer extends AppCompatActivity {

    MediaPlayer mp, negative, positive;
    Fragment fish_fragment;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish_list_container);

        //animation from left to right
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        mp = MediaPlayer.create(this, R.raw.splashlow);
        negative = MediaPlayer.create(this, R.raw.fail);
        positive = MediaPlayer.create(this, R.raw.positive);
        if (savedInstanceState == null) {
            position = getIntent().getExtras().getInt("position");

            switch (position) {
                case 0:
                    Intent intent =  new Intent(this,Favorite_container_activity.class);
                    startActivity(intent);
                    //finish will help you clear the previous container so you wont be backed in a empty white space
                    finish();
                    break;
                case 1:
                    fish_fragment = FishListFragment.newInstance(FishContract.TABLE_NAME_CICHLIDS);
                    makingFragments(fish_fragment);
                    break;
                case 2:
                    fish_fragment = FishListFragment.newInstance(FishContract.TABLE_NAME_TETRAS);
                    makingFragments(fish_fragment);
                    break;
                case 3:
                    fish_fragment = FishListFragment.newInstance(FishContract.TABLE_NAME_CATFISHES);
                    makingFragments(fish_fragment);
                    break;
                case 4:
                    fish_fragment = FishListFragment.newInstance(FishContract.TABLE_NAME_GOURAMI);
                    makingFragments(fish_fragment);
                    break;
                case 5:
                    fish_fragment = FishListFragment.newInstance(FishContract.TABLE_NAME_BARBS);
                    makingFragments(fish_fragment);
                    break;
                case 6:
                    fish_fragment = FishListFragment.newInstance(FishContract.TABLE_NAME_LOACHES);
                    makingFragments(fish_fragment);
                    break;
                case 7:
                    fish_fragment = FishListFragment.newInstance(FishContract.TABLE_NAME_DISCUS);
                    makingFragments(fish_fragment);
                    break;
                case 8:
                    fish_fragment = FishListFragment.newInstance(FishContract.TABLE_NAME_ANGELFIH);
                    makingFragments(fish_fragment);
                    break;
                case 9:
                    fish_fragment = FishListFragment.newInstance(FishContract.TABLE_NAME_GOLDFISH);
                    makingFragments(fish_fragment);
                    break;
                case 10:
                    fish_fragment = FishListFragment.newInstance(FishContract.TABLE_NAME_MOLLIES);
                    makingFragments(fish_fragment);
                    break;
                case 11:
                    fish_fragment = FishListFragment.newInstance(FishContract.TABLE_NAME_SWORDTAILS);
                    makingFragments(fish_fragment);
                    break;
                default:

            }
        }
    }


    void makingFragments(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().
                replace(R.id.fish_list_container, fragment,"fish_fragment")
                .commit();

    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.back_button_out_animation,R.anim.back_button_in_animation);

    }


    public void favoriteClicked(View view) {
        positive = MediaPlayer.create(this, R.raw.positive);
        positive.start();
        FragmentManager fm = getSupportFragmentManager();
        FishListFragment fragment = (FishListFragment) fm.findFragmentByTag("fish_fragment");
        fragment.cancelDialog();
        fragment.addToFavorites();

    }

    public void cancelFavoriteClicked(View view) {
        negative.start();
        FragmentManager fm = getSupportFragmentManager();
        FishListFragment fragment = (FishListFragment) fm.findFragmentByTag("fish_fragment");
        fragment.cancelDialog();

    }

    public void goBackToCategories(View view) {
        mp.start();
        onBackPressed();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mp!=null){
            mp.stop();
            mp.release();
        }
        if(negative!=null){
            negative.stop();
            negative.release();
        }
        if(positive!=null){
            positive.stop();
            positive.release();
        }



    }
}





