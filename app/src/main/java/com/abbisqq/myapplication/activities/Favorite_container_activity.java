package com.abbisqq.myapplication.activities;

import android.app.Dialog;
import android.app.job.JobScheduler;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.abbisqq.myapplication.R;
import com.abbisqq.myapplication.adapters.FavoriteAdapter;
import com.abbisqq.myapplication.data.CustomDatabaseHelper;
import com.abbisqq.myapplication.data.FishContract;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

/**
 * Created by chart on 25/6/2017.
 */

public class Favorite_container_activity extends AppCompatActivity implements FavoriteAdapter.ItemClickCallBack{

    RecyclerView recyclerView;
    FavoriteAdapter adapter;
    Cursor mCursor;
    Toolbar bar;
    CustomDatabaseHelper customFishDB;
    String id;
    LayoutInflater inflater;
    AlertDialog dialog;
    MediaPlayer mp, positive, negative;
    AdView thirddAD;
    TextView insteadOfADD3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite_layout);
        recyclerView = (RecyclerView)findViewById(R.id.favorite_recycler_view);
        CustomDatabaseHelper databaseHelper = new CustomDatabaseHelper(this);
        mCursor = databaseHelper.getAllData();
        adapter = new FavoriteAdapter(this,mCursor);
        adapter.setItemClickCallBack(this);
        LinearLayoutManager manager =  new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        ImageView backImage = (ImageView) findViewById(R.id.go_back_from_favorites);

        mp = MediaPlayer.create(this, R.raw.splashlow);

        negative = MediaPlayer.create(this, R.raw.fail);
        positive = MediaPlayer.create(this, R.raw.positive);

        insteadOfADD3 = (TextView)findViewById(R.id.instead_of_add3);

        thirddAD = (AdView)findViewById(R.id.third_banner);

        MobileAds.initialize(this,"ca-app-pub-6680942670958253~1248857286");



        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();

        thirddAD.loadAd(adRequest);









        inflater = getLayoutInflater();



        customFishDB = new CustomDatabaseHelper(this);
        bar = (Toolbar)findViewById(R.id.favorite_toolbar);
        bar.setTitle(R.string.title_favorites);

        if(getResources().getConfiguration().orientation==2){
            bar.setTitleTextAppearance(this,R.style.Toolbar_TitleText_Big);
            backImage.setImageResource(R.drawable.big_back);
        }else {
            bar.setTitleTextAppearance(this,R.style.Toolbar_TitleText_Small);
            backImage.setImageResource(R.drawable.back);
        }


        if(!isNetworkAvailable()){
            thirddAD.setVisibility(View.GONE);
            insteadOfADD3.setVisibility(View.VISIBLE);
        }




        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_DOWN) {
                    changeElevation(0);
                    return false;
                } else {
                    changeElevation(15);
                    return false;

                }

            }
        });


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT| ItemTouchHelper.RIGHT) {


            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                id = (String)viewHolder.itemView.getTag();
                if(dialog==null) {

                showDeleteDialog(id);
                }
                dialog.show();
            }


        }).attachToRecyclerView(recyclerView);




    //animation from left to right
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

    }

    public void showDeleteDialog(String id){
        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(inflater.inflate(R.layout.delete_fish_dialog, null));
        // 2. Chain together various setter methods to set the dialog characteristics
        // Create the AlertDialog
        dialog = builder.create();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
    }



    void changeElevation(int elev) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            bar.setElevation(elev);
        }
    }




    public boolean isNetworkAvailable()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo !=null &&activeNetworkInfo.isConnected();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.back_button_out_animation,R.anim.back_button_in_animation);
    }


    @Override
    public void onItemClick(View view, int p) {
        mp.start();
        Intent intent = new Intent(this, PageViewContainerActivity.class);
        intent.putExtra("table_name", FishContract.TABLE_NAME_CUSTOM);
        intent.putExtra("position", p);
        startActivity(intent);
    }

    @Override
    public void onLongClick(View view, int position) {
        mp.start();
        Intent intent = new Intent(this,FishEditActivity.class);
        intent.putExtra("table_name", FishContract.TABLE_NAME_CUSTOM);
        intent.putExtra("position", position);
        startActivity(intent);
    }

    public void goBackToFromFavorites(View view) {
        onBackPressed();
    }


    @Override
    protected void onDestroy() {
        if(mp!=null){
            mp.stop();
            mp.release();
        }
        super.onDestroy();
        if(mCursor!=null)
            mCursor.close();
        if(customFishDB!=null)
            customFishDB.close();

    }

    public void deleteClicked(View view) {
        positive = MediaPlayer.create(this, R.raw.positive);
        positive.start();
        customFishDB.deleteDataSwipping(id);
        adapter.swapCursor(customFishDB.getAllData());
        dialog.cancel();

    }

    public void dontDeleteClicked(View view) {
        negative = MediaPlayer.create(this, R.raw.fail);
        negative.start();
        customFishDB.deleteDataSwipping(id);
        adapter.swapCursor(customFishDB.getAllData());
        dialog.cancel();
    }



    @Override
    protected void onResume() {
        super.onResume();
        adapter.swapCursor(customFishDB.getAllData());

    }
}





