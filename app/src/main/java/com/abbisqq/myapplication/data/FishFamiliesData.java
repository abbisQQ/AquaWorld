package com.abbisqq.myapplication.data;

import com.abbisqq.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chart on 17/6/2017.
 */

public class FishFamiliesData {

    private static final String[] titles = {"Cichlid", "Tetras",
            "Catfish", "Darters", "Cyprinis",
            "GoldFish", "Catfish",
            "Cichids", "Darters", "Cyprinis", "GoldFish", "Catfish",
            "Cichids", "Darters", "Cyprinis"};

    private static final int[] icons = {R.drawable.cichlid, R.drawable.tetras,
            R.drawable.corydoras,
            android.R.drawable.arrow_down_float,
            android.R.drawable.alert_dark_frame,
            android.R.drawable.arrow_up_float,
            android.R.drawable.bottom_bar,
            android.R.drawable.btn_default,
            android.R.drawable.btn_default_small,
            android.R.drawable.btn_dialog,
            android.R.drawable.ic_popup_reminder,
            android.R.drawable.btn_dropdown,
            android.R.drawable.btn_star_big_off, android.R.drawable.btn_minus,
            android.R.drawable.ic_input_delete, android.R.drawable.ic_menu_add};


    public static List<ListItem> getListData() {
        List<ListItem> data = new ArrayList<>();


        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < titles.length && j < icons.length; j++) {
                ListItem item = new ListItem();
                item.setImageRes(icons[j]);
                item.setTitle(titles[j]);
                data.add(item);
            }
        }
        return data;
    }
}