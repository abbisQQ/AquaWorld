package com.abbisqq.myapplication.activities;

import android.database.Cursor;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.abbisqq.myapplication.R;
import com.abbisqq.myapplication.adapters.ViewPagerAdapter;
import com.abbisqq.myapplication.data.CustomDatabaseHelper;
import com.abbisqq.myapplication.data.FishContract;
import com.abbisqq.myapplication.data.FishDatabaseHelper;

public class PageViewContainerActivity extends FragmentActivity {

    Cursor mCursor;
    FishDatabaseHelper helper;
    CustomDatabaseHelper customHelper;

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


        String mTableName = getIntent().getStringExtra("table_name");
        int mPosition = getIntent().getIntExtra("position",0);

        if(mTableName.equals(FishContract.TABLE_NAME_CUSTOM)){
           customHelper =  new CustomDatabaseHelper(this);
            mCursor = customHelper.getAllData();
        }else {
            // Instantiate a ViewPager and a PagerAdapter.
            helper = new FishDatabaseHelper(this, mTableName);
            mCursor = helper.getFishes();

        }

        mPager = (ViewPager) findViewById(R.id.my_view_pager);
        mPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), mCursor);
        mPager.setAdapter(mPagerAdapter);
        mPager.setCurrentItem(mPosition);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mCursor!=null)
            mCursor.close();
        if(helper!=null)
            helper.close();
        if(customHelper!=null)
            customHelper.close();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.back_button_out_animation,R.anim.back_button_in_animation);
    }


    public void goBackToFishList(View view) {
        onBackPressed();
    }
}
