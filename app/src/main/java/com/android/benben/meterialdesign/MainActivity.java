package com.android.benben.meterialdesign;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
        }
        return true;
    }
}
