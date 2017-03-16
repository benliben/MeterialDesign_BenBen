package com.android.benben.meterialdesign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Time      2017/3/16 18:38 .
 * Author   : LiYuanXiong.
 * Content  :
 */
public class HomeActivity extends AppCompatActivity {

    public static final String PICTURE_NAME = "picture_name";
    public static final String PICTURE_ID = "picture_id";
    @InjectView(R.id.home_image_view)
    ImageView mImageView;
    @InjectView(R.id.home_toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.home_collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbar;
    @InjectView(R.id.homeContent_text)
    TextView mContentText;
    private String pictureName;
    private int pictureId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.inject(this);

        pictureName = getIntent().getStringExtra(PICTURE_NAME);
        pictureId = getIntent().getIntExtra(PICTURE_ID, -1);
        initView();

    }

    private void initView() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        mCollapsingToolbar.setTitle(pictureName);
        setData();

    }

    private void setData() {
        Glide.with(this).load(pictureId).into(mImageView);
        String fruitContent = generatePictureContent(pictureName);
        mContentText.setText(fruitContent);
    }

    private String generatePictureContent(String pictureName) {
        StringBuilder fruitContent = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            fruitContent.append(pictureName);
        }
        return fruitContent.toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
