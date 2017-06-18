package com.abbisqq.myapplication.activities;



import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.abbisqq.myapplication.R;
import com.abbisqq.myapplication.fragments.CategoryFragment;


public class CategoryListsContainerActivity extends AppCompatActivity {

    ActionBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categoty_lists_container_activity);

        //animation from left to right
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        bar = getSupportActionBar();
        if (bar != null) {
            bar.setTitle("Fish Categories");
        }
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.recycler_view_container, new CategoryFragment())
                    .commit();
        }

    }


    }





