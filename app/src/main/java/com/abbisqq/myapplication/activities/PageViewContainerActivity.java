package com.abbisqq.myapplication.activities;

import android.database.Cursor;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.abbisqq.myapplication.R;
import com.abbisqq.myapplication.adapters.ViewPagerAdapter;
import com.abbisqq.myapplication.data.FishDatabaseHelper;

public class PageViewContainerActivity extends FragmentActivity {

    Cursor mCursor;
    FishDatabaseHelper helper;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_view_container);

        //animation from left to right
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        // Instantiate a ViewPager and a PagerAdapter.
        String mTableName = getIntent().getStringExtra("table_name");
        int mPosition = getIntent().getIntExtra("position",5);

        helper = new FishDatabaseHelper(this, mTableName);
        mCursor = helper.getFishes();
        mPager = (ViewPager) findViewById(R.id.my_view_pager);
        mPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),mCursor);
        mPager.setAdapter(mPagerAdapter);
        mPager.setCurrentItem(mPosition);







    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCursor.close();
        helper.close();
    }
}
