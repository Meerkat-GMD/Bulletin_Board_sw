package com.cs.bulletinboard;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class read extends Activity {
    static final String[] LIST_MENU = {"댓글1","댓글2","댓글3","댓글4","댓글5","댓글6","댓글7","댓글8","댓글9","댓글10","댓글11","댓글12"};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read);

        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.simpleitem,LIST_MENU);

        ListView listview = (ListView) findViewById(R.id.List_view2) ;
        listview.setAdapter(adapter) ;
    }
}