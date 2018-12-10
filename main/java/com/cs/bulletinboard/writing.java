package com.cs.bulletinboard;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;


public class writing extends Activity {
    long mNow;
    Date mDate;
    String btitles;
    String webmail;
    String btexts;
    String bdate;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.writing);
        final EditText Ebtitle = (EditText)findViewById(R.id.btitle);
        final EditText Ebtext = (EditText)findViewById(R.id.btext);
        Button button1=(Button)findViewById(R.id.btn);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btitles = Ebtitle.getText().toString();
                btexts = Ebtext.getText().toString();
                webmail = WEBMAIL.getInstance().getData();
                bdate = getTime();
                try{
                    String result = "실패";
                    JSONObject boarddata = new JSONObject();
                    boarddata.accumulate("bdate", bdate);
                    boarddata.accumulate("webmail",webmail);
                    boarddata.accumulate("btitle", btitles);
                    boarddata.accumulate("btext",btexts);
                    result = new JSONTASK().execute("putboard",boarddata.toString()).get();
                    if (result.equals("1")){
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(writing.this,"데이터 전송 실패",Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
    private String getTime(){
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }
    public void onBackPressed(){
        super.onBackPressed();
    }
}
