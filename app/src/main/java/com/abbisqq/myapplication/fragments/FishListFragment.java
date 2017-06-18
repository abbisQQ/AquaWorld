package com.abbisqq.myapplication.fragments;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.abbisqq.myapplication.R;
import com.abbisqq.myapplication.activities.PageViewContainerActivity;
import com.abbisqq.myapplication.adapters.RecVAdapter;
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


    RecyclerView recyclerView;
    RecVAdapter adapter;
    FishDatabaseHelper helper;
    Cursor cursor;
    Context context;


    private String mTableName;


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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fish_list_fragment, container, false);


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


        // Inflate the layout for this fragment
        return view;
    }



    @Override
    public void onItemClick(int p) {
        Intent intent = new Intent(getActivity(), PageViewContainerActivity.class);
        intent.putExtra("table_name",mTableName);
        intent.putExtra("position",p);
        startActivity(intent);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cursor.close();
        helper.close();
    }


    void changeElevation(int elev) {
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();

        if (actionBar != null) {
            actionBar.setElevation(elev);
        }
    }

}