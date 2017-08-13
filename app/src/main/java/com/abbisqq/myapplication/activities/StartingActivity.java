package com.abbisqq.myapplication.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.abbisqq.myapplication.R;
import com.squareup.picasso.Picasso;


//This Activity is only for the starting animation

public class StartingActivity extends AppCompatActivity {

    ImageView image;

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.starting_activity);

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.splash);
        mp.setVolume(0.7f,0.7f);

        //Calling  method i made to rotate the image
        clockwise();

        if(savedInstanceState==null) {

            mp.start();




            new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

                @Override
                public void run() {
                    // This method will be executed once the timer is over
                    // Start your app main activity
                    // close this activity
                    Intent intent = new Intent(StartingActivity.this, CategoryListsContainerActivity.class);
                    startActivity(intent);
                    mp.stop();
                    mp.release();
                    finish();
                }
            }, SPLASH_TIME_OUT);

        }

    }


    public void clockwise(){
        image = (ImageView)findViewById(R.id.starting_animation_image_view);
        Picasso.with(this).load(R.drawable.starting_animation_image).into(image);
        Animation animation = AnimationUtils.loadAnimation(this,
                R.anim.anim);
        image.startAnimation(animation);
    }
}
