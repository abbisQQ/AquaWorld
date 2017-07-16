package com.abbisqq.myapplication.fragments;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.abbisqq.myapplication.R;
import com.abbisqq.myapplication.activities.PageViewContainerActivity;
import com.abbisqq.myapplication.adapters.RecVAdapter;
import com.abbisqq.myapplication.data.CustomDatabaseHelper;
import com.abbisqq.myapplication.data.FishContract;
import com.abbisqq.myapplication.data.FishDatabaseHelper;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FishListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FishListFragment extends Fragment implements RecVAdapter.ItemClickCallBack{

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_TABLE_NAME = "table name";
    private View view;

    RecyclerView recyclerView;
    RecVAdapter adapter;
    FishDatabaseHelper helper;
    Cursor cursor;
    Context context;
    Toolbar bar;
    AlertDialog dialog;
    int longClickPosition;
    private ImageView backImage;

    private String mTableName;
    CustomDatabaseHelper databaseHelper;

    public FishListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment FishListFragment.
     */

    public static FishListFragment newInstance(String param1) {
        FishListFragment fragment = new FishListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TABLE_NAME, param1);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTableName = getArguments().getString(ARG_TABLE_NAME);
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fish_list_fragment, container, false);

        bar = (Toolbar) view.findViewById(R.id.fish_list_toolbar);
        bar.setTitle(mTableName);
        backImage = (ImageView)view.findViewById(R.id.go_back_to_categories);


        if (getResources().getConfiguration().orientation == 2) {
            bar.setTitleTextAppearance(getContext(),R.style.Toolbar_TitleText_Big);
            backImage.setImageResource(R.drawable.big_back);
        }else {
            bar.setTitleTextAppearance(getContext(),R.style.Toolbar_TitleText_Small);
            backImage.setImageResource(R.drawable.back);
        }




        context = getContext();
        helper = new FishDatabaseHelper(context, mTableName);
        cursor = helper.getFishes();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView = (RecyclerView) view.findViewById(R.id.fish_recycler_view);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecVAdapter(cursor, context);
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
        view.setBackgroundColor(getResources().getColor(R.color.white_background));
        // Inflate the layout for this fragment
        return view;
    }


    @Override
    public void onItemClick(View view,int p) {
        Intent intent = new Intent(getActivity(), PageViewContainerActivity.class);
        intent.putExtra("table_name", mTableName);
        intent.putExtra("position", p);
        startActivity(intent);


    }

    @Override
    public void onLongClick(View view, int position) {
        longClickPosition = position;
        view.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        setView(view);
        if(dialog==null) {
            LayoutInflater inflater =  getActivity().getLayoutInflater();
            // 1. Instantiate an AlertDialog.Builder with its constructor
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setView(inflater.inflate(R.layout.favorite_fish_dialog,null));

            //2 Create the AlertDialog



            dialog = builder.create();
            dialog.setCanceledOnTouchOutside(false);

        }
        dialog.show();
    }



    //i am getting the view for the item that was long click so i can change the background back to normal
    // when the dialog disappears.
    private void setView(View viewItem){
        view = viewItem;
    }
    public View getItemView(){
        return view;
    }






    public void addToFavorites(){
        cursor.moveToPosition(longClickPosition);

        String sciName = cursor.getString(cursor.getColumnIndex(FishContract.SCINAME));
        String commonName = cursor.getString(cursor.getColumnIndex(FishContract.COMMONNAME));
        String size = cursor.getString(cursor.getColumnIndex(FishContract.SIZE));
        String ph = cursor.getString(cursor.getColumnIndex(FishContract.PH));
        String aggression = cursor.getString(cursor.getColumnIndex(FishContract.AGGRESSION));
        String diet = cursor.getString(cursor.getColumnIndex(FishContract.DIET));
        String water = cursor.getString(cursor.getColumnIndex(FishContract.WATER_HARDNESS));
        String difficult = cursor.getString(cursor.getColumnIndex(FishContract.DIFFICULT));
        String temperature = cursor.getString(cursor.getColumnIndex(FishContract.TEMPERATURE));
        String image = cursor.getString(cursor.getColumnIndex(FishContract.IMAGE));
        String breeding = cursor.getString(cursor.getColumnIndex(FishContract.BREEDING));
        String overview = cursor.getString(cursor.getColumnIndex(FishContract.OVERVIEW));

        databaseHelper = new CustomDatabaseHelper(getContext());
        boolean insert = databaseHelper.insertData(sciName,commonName,size,ph,breeding,temperature,aggression,diet,overview,difficult,image,water);


    }


    public void cancelDialog(){
        dialog.cancel();
        getItemView().setBackgroundColor(getResources().getColor(R.color.white_background));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cursor.close();
        helper.close();
    }


    void changeElevation(int elev) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            bar.setElevation(elev);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(databaseHelper!=null)
        databaseHelper.close();
        if(cursor!=null)
        cursor.close();
    }
}












