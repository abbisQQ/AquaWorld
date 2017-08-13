package com.abbisqq.myapplication.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abbisqq.myapplication.R;
import com.abbisqq.myapplication.data.FishContract;
import com.squareup.picasso.Picasso;



/**
 * Created by chart on 25/6/2017.
 */

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>{


    private Context mContext;

    private LayoutInflater inflater;
    Cursor mCursor;




    private ItemClickCallBack itemClickCallBack;

    public interface ItemClickCallBack {
        void onItemClick(View view, int p);

        void onLongClick(View view, int position);

    }


    public void setItemClickCallBack(final ItemClickCallBack itemClickCallBack) {
        this.itemClickCallBack = itemClickCallBack;
    }


    public FavoriteAdapter(Context context, Cursor cursor) {
        mContext = context;
        this.inflater = LayoutInflater.from(mContext);
        mCursor= cursor;


        }


    public void swapCursor(Cursor newCursor) {
        // Always close the previous mCursor first
        if (mCursor != null) mCursor.close();
        mCursor = newCursor;
        if (newCursor != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }




    @Override
    public FavoriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.favorite_list_row,parent,false);




        return new FavoriteViewHolder(view);

    }

    @Override
    public void onBindViewHolder(FavoriteViewHolder holder, int position) {
        if(!mCursor.moveToPosition(position)){
            return;
        }

        mCursor.moveToPosition(position);
        String commonName = mCursor.getString(mCursor.getColumnIndex(FishContract.COMMONNAME));

        if(commonName.contains("N/A")|| commonName.isEmpty()) {
            String sciName = mCursor.getString(mCursor.getColumnIndex(FishContract.SCINAME));

            holder.favorite_textView.setText(sciName);
        }else
            holder.favorite_textView.setText(commonName);
        holder.itemView.setTag(mCursor.getString(mCursor.getColumnIndex(FishContract.DATABASE_ID)));


        Picasso.with(mContext)
                .load(mCursor.getString(mCursor.getColumnIndex(FishContract.IMAGE)))
                .error(R.drawable.error_image)
                .placeholder(R.drawable.loading_animation)
                .fit().into(holder.favorite_imageView);



    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    class  FavoriteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{


        ImageView favorite_imageView;
        TextView  favorite_textView;
        View container;

        public FavoriteViewHolder(View itemView) {
            super(itemView);
            favorite_imageView = (ImageView)itemView.findViewById(R.id.favorite_image_view);
            favorite_textView = (TextView)itemView.findViewById(R.id.favorite_text_view);
            container = itemView.getRootView();
            container.setOnClickListener(this);
            container.setOnLongClickListener(this);

        }

        @Override
        public void onClick(View view) {

                //gets the position of the item hat was clicked
                itemClickCallBack.onItemClick(view, getAdapterPosition());

        }

        @Override
        public boolean onLongClick(View view) {
                itemClickCallBack.onLongClick(view, getAdapterPosition());
                return true;

        }
    }


}
