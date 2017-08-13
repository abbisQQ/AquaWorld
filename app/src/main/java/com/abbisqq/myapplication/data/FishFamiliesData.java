package com.abbisqq.myapplication.data;

import com.abbisqq.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chart on 17/6/2017.
 */

public class FishFamiliesData {

    private static final String[] titles = {"Favorite/User made","Cichlid", "Tetras",
            "Catfish", "Gourami","Barbs","Loaches","Discus","Angelfish", "Fancy Goldfish","Mollies","Swordtails"};

    private static final int[] icons = {R.drawable.favorite,R.drawable.cichlid, R.drawable.tetras,
            R.drawable.corydoras,
            R.drawable.gourami,
            R.drawable.bard,
    R.drawable.loach,R.drawable.discus,R.drawable.angelfish,R.drawable.goldfish
    ,R.drawable.mollies,R.drawable.swordtail};


    public static List<ListItem> getListData() {
        List<ListItem> data = new ArrayList<>();


            for (int j = 0; j < titles.length && j < icons.length; j++) {
                ListItem item = new ListItem();
                item.setImageRes(icons[j]);
                item.setTitle(titles[j]);
                data.add(item);
            }

        return data;
    }
}