package com.abbisqq.myapplication.fragments;


import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.abbisqq.myapplication.R;
import com.abbisqq.myapplication.data.FishContract;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class FishDetailsFragment extends Fragment implements View.OnClickListener{

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SCINAME = "sci_name";
    private static final String ARG_COMMONNAME = "common_name";
    private static final String ARG_SIZE = "size";
    private static final String ARG_PH = "ph";
    private static final String ARG_AGGRESSION = "aggression";
    private static final String ARG_DIET = "diet";
    private static final String ARG_DIFFICULT = "difficult";
    private static final String ARG_TEMPERATURE = "temperature";
    private static final String ARG_IMAGE = "image";
    private static final String ARG_BREEDING = "breeding";
    private static final String ARG_OVERVIEW = "overview";
    private static final String ARG_WATER = "water";
    private static int cursorPosition;
    private static Cursor cursorStatic;

    private String degreesF;
    private String degreesC;
    private String mSci;
    private String mCom;
    private String mSize;
    private String mPh;
    private String mAggr;
    private String mDiet;
    private String mDifficult;
    private String mTemperature;
    private String mImage;
    private String mBreed;
    private String mOver;
    private String mWater;



    private ImageButton measureButton,phButton;
    private ImageView fishImage;
    private TextView sciNameTV,commonNameTV,overviewTV,difficultTV;



    private ImageButton dietButton;
    private ImageButton waterButton;
    private ImageButton breedingButton;
    private ImageButton temperatureButton;
    private ImageButton aggressionButton;
    private TextView phTextView;
    private TextView dietTextView;
    private TextView waterTextView;
    private TextView breedingTextView;
    private TextView temperaturTextView;
    private TextView temperTextView;
    private TextView sizeTextView;




    public FishDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment FishDetailsFragment.
     */

    public static FishDetailsFragment newInstance(Cursor cursor, int p) {
        FishDetailsFragment fragment = new FishDetailsFragment();
        cursorPosition = p;
        cursorStatic = cursor;


        cursor.moveToPosition(p);
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




        Bundle args = new Bundle();
        args.putString(ARG_SCINAME, sciName);
        args.putString(ARG_COMMONNAME, commonName);
        args.putString(ARG_SIZE, size);
        args.putString(ARG_PH, ph);
        args.putString(ARG_AGGRESSION, aggression);
        args.putString(ARG_DIET, diet);
        args.putString(ARG_WATER,water);
        args.putString(ARG_DIFFICULT, difficult);
        args.putString(ARG_TEMPERATURE, temperature);
        args.putString(ARG_IMAGE, image);
        args.putString(ARG_BREEDING, breeding);
        args.putString(ARG_OVERVIEW, overview);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSci = getArguments().getString(ARG_SCINAME);
            mCom = getArguments().getString(ARG_COMMONNAME);
            mSize = getArguments().getString(ARG_SIZE);
            mPh = getArguments().getString(ARG_PH);
            mAggr = getArguments().getString(ARG_AGGRESSION);
            mDiet = getArguments().getString(ARG_DIET);
            mDifficult = getArguments().getString(ARG_DIFFICULT);
            mTemperature = getArguments().getString(ARG_TEMPERATURE);
            mImage = getArguments().getString(ARG_IMAGE);
            mBreed = getArguments().getString(ARG_BREEDING);
            mOver = getArguments().getString(ARG_OVERVIEW);
            mWater = getArguments().getString(ARG_WATER);



        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fish_details, container, false);

        sizeTextView = (TextView)view.findViewById(R.id.size_textView);

        // code for celcius and Far
        degreesF = " \u2109";
        degreesC = " \u2103";

        fishImage = (ImageView)view.findViewById(R.id.fish_main_image);

        measureButton = (ImageButton)view.findViewById(R.id.measure_button);
        measureButton.setOnClickListener(this);

        Picasso.with(getContext()).load(R.drawable.measure).fit().into(measureButton);

        Picasso.with(getContext()).load(mImage).fit().placeholder(R.drawable.loading_animation).into(fishImage);

        sciNameTV = (TextView)view.findViewById(R.id.sciName_tv);
        sciNameTV.setText(getString(R.string.scientific_name)+mSci);

        commonNameTV = (TextView)view.findViewById(R.id.common_name_tv);

        if(mCom==null||mCom.isEmpty()||mCom.equals("N/A"))
            mCom = "    N/A    ";
        commonNameTV.setText(getString(R.string.common_name) + mCom);


        phButton = (ImageButton)view.findViewById(R.id.ph_button);
        phButton.setOnClickListener(this);
        phTextView = (TextView)view.findViewById(R.id.ph_textView);

        dietButton = (ImageButton)view.findViewById(R.id.diet_button);
        dietButton.setOnClickListener(this);
        dietTextView = (TextView)view.findViewById(R.id.feeding_textView);

        waterButton = (ImageButton)view.findViewById(R.id.water_button);
        waterButton.setOnClickListener(this);
        waterTextView = (TextView)view.findViewById(R.id.water_textView);

        breedingButton = (ImageButton)view.findViewById(R.id.breeding_button);
        breedingButton.setOnClickListener(this);
        breedingTextView = (TextView)view.findViewById(R.id.breeding_textView);

        temperatureButton = (ImageButton)view.findViewById(R.id.temperature_button);
        temperatureButton.setOnClickListener(this);
        temperaturTextView = (TextView)view.findViewById(R.id.temperature_textView);

        aggressionButton= (ImageButton)view.findViewById(R.id.aggression_button);
        aggressionButton.setOnClickListener(this);
        temperTextView = (TextView)view.findViewById(R.id.temper_textView);

        phTextView.setText("The water PH must be: " + checkerMethod(mPh));
        temperTextView.setText("Behavior: " + checkerMethod(mAggr));
        dietTextView.setText("This is fish is: "+checkerMethod(mDiet));
        waterTextView.setText("Water must be: "+ checkerMethod(mWater));
        breedingTextView.setText("Breeding type: " + checkerMethod(mBreed));
        temperaturTextView.setText("Temperature: " +checkerMethod(mTemperature)+degreesC);
        sizeTextView.setText("<------"+mSize+" cm"+"------>");


        overviewTV = (TextView)view.findViewById(R.id.overview_tv);
        overviewTV.setText(mOver);
        difficultTV = (TextView)view.findViewById(R.id.difficult_tv);
        switch (mDifficult){
            case "1":
                difficultTV.setText("Current fish difficulty: Very Easy");
                break;
            case "2":
                difficultTV.setText("Current fish difficulty: Easy");
                break;
            case "3":
                difficultTV.setText("Current fish difficulty: Normal");
                break;
            case "4":
                difficultTV.setText("Current fish difficulty: Hard");
                break;
            case "5":
                difficultTV.setText("Current fish difficulty: Very Hard");
                break;
            default:
                difficultTV.setText("Current fish difficulty: Unknown");
        }






        return view;
    }

    @Override
    public void onClick(View v) {
        if ( v == measureButton ) {
            // Handle clicks for measureButton
            // cm to inch
            if (sizeTextView.getText().toString().contains("cm")) {
                sizeTextView.setText("<------"+String.valueOf(Math.round((Float.valueOf(mSize)*0.393701)*100.0)/100.0)+" inch------>");
            }else {
                sizeTextView.setText("<------"+mSize+" cm"+"------>");
            }
        } else if ( v == phButton ) {
            // Handle clicks for phButton

        } else if ( v == dietButton ) {
            // Handle clicks for dietButton

        } else if ( v == waterButton ) {
            // Handle clicks for waterButton

        } else if ( v == breedingButton ) {
            // Handle clicks for breedingButton

        } else if ( v == temperatureButton ) {
            // Handle clicks for temperatureButton
            // celcius to F
            if(temperaturTextView.getText().toString().endsWith(degreesC)) {
                temperaturTextView.setText("Temperature: " + checkerMethod(String.valueOf(Math.round((Float.valueOf(mTemperature) * 9 / 5)*100.0)/100.0 + 32) + degreesF));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    temperatureButton.setElevation(0);
                }
            }else {
                temperaturTextView.setText("Temperature: " +checkerMethod(mTemperature)+degreesC);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    temperatureButton.setElevation(20);
                }
            }
        } else if ( v == aggressionButton ) {
            // Handle clicks for aggressionButton


        }
    }
    //safety when the strings are null or empty
    private String checkerMethod(String arg){
        if(arg==null||arg.isEmpty())
            arg="  N/A  ";

        return arg;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
