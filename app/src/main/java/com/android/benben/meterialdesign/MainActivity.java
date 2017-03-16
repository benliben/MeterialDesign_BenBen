package com.android.benben.meterialdesign;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

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
                Snackbar.make(v,"哈哈，这是要提示的内容",Snackbar.LENGTH_LONG).setAction("点击", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this,"内部点击",Toast.LENGTH_SHORT).show();
                        return;
                    }
                }).show();
                Toast.makeText(MainActivity.this,"外层点击",Toast.LENGTH_SHORT).show();
            }
        });
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
