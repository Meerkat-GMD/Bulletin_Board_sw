package com.cs.bulletinboard;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.content.Intent;


public class writing extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.writing);

        Button button1=(Button)findViewById(R.id.make);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(writing.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
