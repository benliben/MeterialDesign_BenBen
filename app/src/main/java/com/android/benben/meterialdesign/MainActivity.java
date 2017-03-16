package com.android.benben.meterialdesign;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private SwipeRefreshLayout swipeRefresh;

    private Picture[] pictures = {
            new Picture(R.drawable.ic_1, "1"), new Picture(R.drawable.ic_2, "2"),
            new Picture(R.drawable.ic_3, "3"), new Picture(R.drawable.ic_4, "4"),
            new Picture(R.drawable.ic_5, "5"), new Picture(R.drawable.ic_6, "6"),
            new Picture(R.drawable.ic_7, "7"), new Picture(R.drawable.ic_8, "8"),
            new Picture(R.drawable.ic_9, "9"), new Picture(R.drawable.ic_10, "10"),
            new Picture(R.drawable.ic_11, "11"), new Picture(R.drawable.ic_12, "12"),
            new Picture(R.drawable.ic_13, "13"), new Picture(R.drawable.ic_14, "14"),
            new Picture(R.drawable.ic_15, "15"), new Picture(R.drawable.ic_16, "16"),
            new Picture(R.drawable.ic_17, "17"), new Picture(R.drawable.ic_18, "18"),
            new Picture(R.drawable.ic_19, "19"), new Picture(R.drawable.ic_20, "20"),
            new Picture(R.drawable.ic_21, "21"), new Picture(R.drawable.ic_22, "22"),
            new Picture(R.drawable.ic_23, "23"), new Picture(R.drawable.ic_24, "24"),
            new Picture(R.drawable.ic_25, "25"), new Picture(R.drawable.ic_26, "26"),
            new Picture(R.drawable.ic_27, "27"), new Picture(R.drawable.ic_28, "28"),
            new Picture(R.drawable.ic_29, "29"), new Picture(R.drawable.ic_30, "30"),
            new Picture(R.drawable.ic_31, "31"), new Picture(R.drawable.ic_32, "32"),
            new Picture(R.drawable.ic_33, "33"), new Picture(R.drawable.ic_34, "34"),
            new Picture(R.drawable.ic_35, "35"), new Picture(R.drawable.ic_36, "36"),
            new Picture(R.drawable.ic_37, "37"), new Picture(R.drawable.ic_38, "38"),
            new Picture(R.drawable.ic_39, "39"), new Picture(R.drawable.ic_40, "40"),
            new Picture(R.drawable.ic_41, "41")

    };

    private List<Picture> pictureList = new ArrayList<>();
    private PictureAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_menu);
        }
        mDrawerLayout = (DrawerLayout) findViewById(R.id.activity_main);

        /*找到左抽屉控件*/
        NavigationView navView = (NavigationView) findViewById(R.id.main_vav_view);
        /*默认选中一个item*/
        navView.setCheckedItem(R.id.nav_events);
        /*设置点击事件*/
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                /*没有做筛选，点击任何一个item都可以使左抽屉关闭吊*/
                mDrawerLayout.closeDrawers();
                return true;
            }
        });

        /*FloatingActionButton和SnackBar*/
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.main_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*SnackBar*/
                Snackbar.make(v, "哈哈，这是要提示的内容", Snackbar.LENGTH_LONG).setAction("点击", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "内部点击", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }).show();
                Toast.makeText(MainActivity.this, "外层点击", Toast.LENGTH_SHORT).show();
            }
        });

        setData();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.main_recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        adapter = new PictureAdapter(this, pictureList);
        recyclerView.setAdapter(adapter);


        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.main_swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorGreen, R.color.colorAccent, R.color.colorPrimaryDark);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFruits();//刷新操作
            }
        });
    }

    private void refreshFruits() {
        /*因为没有联网，所以通过延时来模拟联网*/
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000 * 3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setData();
                        adapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private void setData() {
        pictureList.clear();//将集合清空
        for (int i = 0; i < 100; i++) {
            /*产生一个随机数*/
            Random random = new Random();
            int index = random.nextInt(pictures.length);//产生一个0-belles.length的随机数
            pictureList.add(pictures[index]);//将这个随机数所对应对象添加到集合里面
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.pen:
                Toast.makeText(this, "你点击了笔", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ink:
                Toast.makeText(this, "你点击了墨", Toast.LENGTH_SHORT).show();
                break;
            case R.id.book:
                Toast.makeText(this, "你点击了纸", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ink_slab:
                Toast.makeText(this, "你点击了砚", Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }
}
