package com.abbisqq.myapplication.activities;

import android.database.Cursor;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.abbisqq.myapplication.R;
import com.abbisqq.myapplication.adapters.ViewPagerAdapter;
import com.abbisqq.myapplication.data.CustomDatabaseHelper;
import com.abbisqq.myapplication.data.FishContract;
import com.abbisqq.myapplication.data.FishDatabaseHelper;
import com.squareup.picasso.Picasso;

public class FishEditActivity extends AppCompatActivity {

    private ImageView imageView;
    private EditText imageET, sciNameET, commNameET, phET,
            dietET, temperatureET, breedingET, aggressionET,
            overviewET, difficultET, sizeET,waterEV;

    CustomDatabaseHelper customHelper;
    Cursor mCursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish_edit);


        //animation from left to right
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);


        String mTableName = getIntent().getStringExtra("table_name");
        int mPosition = getIntent().getIntExtra("position",0);

        if(mTableName.equals(FishContract.TABLE_NAME_CUSTOM)){
            customHelper =  new CustomDatabaseHelper(this);
            mCursor = customHelper.getAllData();
            mCursor.moveToPosition(mPosition);
        }else {
            return;

        }


        imageView = (ImageView)findViewById(R.id.edit_image_view);
        Picasso.with(this)
                .load(mCursor.getString(mCursor.getColumnIndex(FishContract.IMAGE)))
                .error(R.drawable.error_image)
                .placeholder(R.drawable.loading_animation)
                .fit().into(imageView);



        imageET = (EditText)findViewById(R.id.image_et);
        sciNameET = (EditText)findViewById(R.id.sciName_et);
        commNameET = (EditText)findViewById(R.id.common_name_ev);
        phET = (EditText)findViewById(R.id.ph_ev);
        dietET = (EditText)findViewById(R.id.diet_ev);
        temperatureET = (EditText)findViewById(R.id.temperature_ev);
        aggressionET = (EditText)findViewById(R.id.temper_ev);
        breedingET = (EditText)findViewById(R.id.breeding_ev);
        overviewET = (EditText)findViewById(R.id.overview_ev);
        difficultET = (EditText)findViewById(R.id.difficult_ev);
        sizeET = (EditText)findViewById(R.id.size_ev);
        waterEV = (EditText)findViewById(R.id.water_ev);


        imageET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus&&imageET.getText()!=null&&imageET.getText().length()!=0){
                    Picasso.with(getBaseContext())
                            .load(imageET.getText().toString())
                            .error(R.drawable.error_image)
                            .placeholder(R.drawable.loading_animation)
                            .fit().into(imageView);

                }
            }
        });

    }


    public String getText(EditText view, String noChange) {
        if (view.getText().length() >0) {
            return view.getText().toString();
        } else {
            return noChange;

        }
    }


    public void saveEverythin(View view) {
        String sciName = getText(sciNameET, mCursor.getString(mCursor.getColumnIndex(FishContract.SCINAME)));
        String commonName = getText(commNameET, mCursor.getString(mCursor.getColumnIndex(FishContract.COMMONNAME)));
        String size = getText(sizeET, mCursor.getString(mCursor.getColumnIndex(FishContract.SIZE)));
        String ph = getText(phET, mCursor.getString(mCursor.getColumnIndex(FishContract.PH)));
        String aggression = getText(aggressionET, mCursor.getString(mCursor.getColumnIndex(FishContract.AGGRESSION)));
        String diet = getText(dietET, mCursor.getString(mCursor.getColumnIndex(FishContract.DIET)));
        String water = getText(waterEV, mCursor.getString(mCursor.getColumnIndex(FishContract.WATER_HARDNESS)));
        String difficult = getText(difficultET, mCursor.getString(mCursor.getColumnIndex(FishContract.DIFFICULT)));
        String temperature = getText(temperatureET, mCursor.getString(mCursor.getColumnIndex(FishContract.TEMPERATURE)));
        String image = getText(imageET, mCursor.getString(mCursor.getColumnIndex(FishContract.IMAGE)));
        String breeding = getText(breedingET, mCursor.getString(mCursor.getColumnIndex(FishContract.BREEDING)));
        String overview = getText(overviewET, mCursor.getString(mCursor.getColumnIndex(FishContract.OVERVIEW)));




        Boolean isUpdated = customHelper.updateData(mCursor.getString(mCursor.getColumnIndex(FishContract.DATABASE_ID)),
                 sciName,  commonName, size, ph, breeding, temperature,
                aggression , diet, overview, difficult,  image, water);

        if(isUpdated){
            Toast.makeText(this,"Data Updated",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this,"Error data is not Updated", Toast.LENGTH_LONG).show();
        }

        mCursor.close();
        customHelper.close();

        finish();


    }

        }




