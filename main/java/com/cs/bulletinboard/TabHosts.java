package com.cs.bulletinboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class TabHosts extends AppCompatActivity {

    private TabHost tabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabhost);

        tabHost = (TabHost)findViewById(R.id.th);
        tabHost.setup();

        tabHost.addTab(tabHost.newTabSpec("Tab_RESIZE").setIndicator("List").setContent(new Intent(this, MainActivity.class)));
        tabHost.addTab(tabHost.newTabSpec("Tab_ROTATE").setIndicator("Rotate").setContent(new Intent(this, read.class)));
        tabHost.addTab(tabHost.newTabSpec("Tab_WATERMARK").setIndicator("Watermark").setContent(new Intent(this, writing.class)));

        tabHost.setCurrentTab(0);
    }
}