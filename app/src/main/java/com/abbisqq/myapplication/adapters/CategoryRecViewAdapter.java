package com.abbisqq.myapplication.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abbisqq.myapplication.R;
import com.abbisqq.myapplication.data.ListItem;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by chart on 17/6/2017.
 */

public class CategoryRecViewAdapter extends RecyclerView.Adapter<CategoryRecViewAdapter.CategoryViewHolder>{


    private List<ListItem> listData;
    private LayoutInflater inflater;
    private Context mContext;


    private ItemClickCallBack itemClickCallBack;

    public  interface ItemClickCallBack {
        void onItemClick(int p);
    }


    public void setItemClickCallBack(final ItemClickCallBack itemClickCallBack) {
        this.itemClickCallBack = itemClickCallBack;
    }


    public CategoryRecViewAdapter(List<ListItem> listData, Context c) {
        this.listData = listData;
        this.inflater = LayoutInflater.from(c);
        mContext = c;
    }


    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.fish_category_list_row,parent,false);

        return new CategoryViewHolder(view);

    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {

        ListItem item = listData.get(position);
        holder.caregory_textView.setText(item.getTitle());
        Picasso.with(mContext).load(item.getImageRes()).fit().into(holder.caregory_imageView);

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView caregory_imageView;
        TextView caregory_textView;
        View container;

        public CategoryViewHolder(View itemView) {
            super(itemView);

            caregory_imageView = (ImageView)itemView.findViewById(R.id.category_image_view);
            caregory_textView = (TextView)itemView.findViewById(R.id.category_text_view);

            container = itemView.findViewById(R.id.fish_category_container);
            container.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.fish_category_container) {
                //gets the position of the item hat was clicked
                itemClickCallBack.onItemClick(getAdapterPosition());
            }
        }

    }


}
