package com.android.benben.meterialdesign;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Time      2017/3/16 17:53 .
 * Author   : LiYuanXiong.
 * Content  :
 */

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.MyViewHolder> {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<Picture> mData;

    public PictureAdapter(Context context, List<Picture> pictureList) {
        this.mContext = context;
        this.mData = pictureList;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(mInflater.inflate(R.layout.item_picture, parent, false));
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        if (mData != null) {
            final Picture item = mData.get(position);
            holder.mName.setText(item.getPictureNmae());
            /*由于图片可能导致报oom所以通过Glide进行处理*/
//            holder.mPicture.setImageResource(mData.get(position).getPictureId());
            Glide.with(mContext).load(item.getPictureId()).into(holder.mPicture);

            holder.mPicture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, HomeActivity.class);
                    intent.putExtra(HomeActivity.PICTURE_NAME, item.getPictureNmae());
                    intent.putExtra(HomeActivity.PICTURE_ID, item.getPictureId());
                    mContext.startActivity(intent);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mName;
        private ImageView mPicture;
        public MyViewHolder(View itemView) {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.item_text);
            mPicture = (ImageView) itemView.findViewById(R.id.item_image);
        }
    }
}
