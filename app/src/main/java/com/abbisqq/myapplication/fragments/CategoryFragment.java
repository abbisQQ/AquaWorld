package com.abbisqq.myapplication.fragments;


import android.content.Context;
import android.content.Intent;

import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.abbisqq.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;


import com.abbisqq.myapplication.activities.FishListContainer;
import com.abbisqq.myapplication.adapters.CategoryRecViewAdapter;

import com.abbisqq.myapplication.data.FishFamiliesData;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment implements CategoryRecViewAdapter.ItemClickCallBack {

    RecyclerView recyclerView;
    CategoryRecViewAdapter adapter;
    private ArrayList listData;
    android.support.v7.widget.Toolbar bar;
    ImageView backImage;
    MediaPlayer mp;
    AlertDialog dialog;
    LayoutInflater  alert_inflater;
    AdView firstAD;
    TextView insteadofADD;
    AdRequest adRequest;

    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.category_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        bar = (android.support.v7.widget.Toolbar) view.findViewById(R.id.main_toolbar);
        backImage = (ImageView)view.findViewById(R.id.go_back);

        firstAD = (AdView)view.findViewById(R.id.first_banner);

        MobileAds.initialize(getContext(),"ca-app-pub-6680942670958253~1248857286");

        insteadofADD = (TextView)view.findViewById(R.id.instead_of_add);

        adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();

        firstAD.loadAd(adRequest);


        mp = MediaPlayer.create(getContext(), R.raw.splashlow);

        alert_inflater =  getActivity().getLayoutInflater();


         if(getResources().getConfiguration().orientation==2){
             bar.setTitleTextAppearance(getContext(),R.style.Toolbar_TitleText_Big);
             backImage.setImageResource(R.drawable.big_back);
         }else {
             bar.setTitleTextAppearance(getContext(),R.style.Toolbar_TitleText_Small);
             backImage.setImageResource(R.drawable.back);
         }


         if(!isNetworkAvailable()){
             firstAD.setVisibility(View.GONE);
             insteadofADD.setVisibility(View.VISIBLE);
         }


        //resets the elevation
        changeElevation(0);

        listData = (ArrayList) FishFamiliesData.getListData();
        adapter = new CategoryRecViewAdapter(listData, getActivity());
        adapter.setItemClickCallBack(this);
        recyclerView.setAdapter(adapter);
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


        // Inflate the layout for this fragment
        return view;
    }



    public boolean isNetworkAvailable()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo !=null &&activeNetworkInfo.isConnected();
    }





    @Override
    public void onItemClick(int p) {
        mp.start();
        Intent intent = new Intent(getContext(), FishListContainer.class);
        intent.putExtra("position",p);
        startActivity(intent);
    }

    @Override
    public void onLongClick(int position) {
        giveInfo(position);
    }

    private void giveInfo(int position) {
        String text = "";
        String title = "";
        switch (position){
            case 0:
                title = "Help/Users Guide";
                text = "Help/Users Guide\n" +
                        "\n" +
                        "Favorite/User Made\n" +
                        "Single Click: Favorite Fish List(If any fish has been favorite before).\n" +
                        "Long Click: Help/Users Guide\n" +
                        "\n" +
                        "Category List:\n" +
                        "Single Click: Fish List.\n" +
                        "Long Click: General Information About That Category.\n" +
                        "\n" +
                        "Fish List\n" +
                        "Single Click: Details about that fish(You can see all the fish in that category by scrolling left and right.)\n" +
                        "Long Click: Add Fish to your Favorites(You can Favorite a Fish as Many Times as you Like).";
                break;
            case 1:
                title = " Cichild";
                text = getResources().getString(R.string.cichilds_info);
                break;
            case 2:
                title = "Tetra";
                text = getResources().getString(R.string.tetras_info);
                break;
            case 3:
                title = "Catfish";
                text = getResources().getString(R.string.catfish_info);
                break;
            case 4:
                title = "Gourami";
                text = getResources().getString(R.string.gourami_info);
                break;
            case 5:
                title = "Barb";
                text = getResources().getString(R.string.barbs_info);
                break;
            case 6:
                title = "Loaches";
                text = getResources().getString(R.string.loaches_info);
                break;
            case 7:
                title = "Discus";
                text = getResources().getString(R.string.discus_info);
                break;
            case 8:
                title = "Freshwater Angelfish";
                text = getResources().getString(R.string.angelfish);
                break;
            case 9:
                title = "Fancy Goldfish";
                text = getResources().getString(R.string.goldfish);
                break;
            case 10:
                title = "Mollies";
                text = getResources().getString(R.string.molies_info);
                break;
            case 11:
                title = "Swordtails";
                text = getResources().getString(R.string.swordtails_info);
                break;

        }


            // 1. Instantiate an AlertDialog.Builder with its constructor
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setView(alert_inflater.inflate(R.layout.category_info_dialog,null));
            // 2. Chain together various setter methods to set the dialog characteristics
            // Create the AlertDialog
            dialog = builder.create();
            dialog.setTitle(title);
            dialog.setMessage(text);
            dialog.setCanceledOnTouchOutside(true);
            dialog.show();
    }



    void changeElevation(int elev) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            bar.setElevation(elev);
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        if(isNetworkAvailable()){
            firstAD.setVisibility(View.VISIBLE);
            insteadofADD.setVisibility(View.GONE);
            firstAD.loadAd(adRequest);
        }
    }
}
