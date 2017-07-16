package com.abbisqq.myapplication.activities;



import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.abbisqq.myapplication.R;
import com.abbisqq.myapplication.fragments.CategoryFragment;


public class CategoryListsContainerActivity extends AppCompatActivity {


    LayoutInflater inflater;
    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categoty_lists_container_activity);

        inflater = getLayoutInflater();

        //animation from left to right
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);


        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.recycler_view_container, new CategoryFragment())
                    .commit();
        }

    }


    @Override
    public void onBackPressed() {
       if(dialog==null) {
           // 1. Instantiate an AlertDialog.Builder with its constructor
           AlertDialog.Builder builder = new AlertDialog.Builder(this);
           builder.setView(inflater.inflate(R.layout.exit_dialog, null));
           // 2. Chain together various setter methods to set the dialog characteristics
           // Create the AlertDialog
           dialog = builder.create();
           dialog.setCanceledOnTouchOutside(false);

       }
        dialog.show();
    }

    public void exitClicked(View view) {
        finish();
        System.exit(0);
    }

    public void cancelClicked(View view) {
        dialog.cancel();
    }



    public void goBack(View view) {
        onBackPressed();
    }




}





