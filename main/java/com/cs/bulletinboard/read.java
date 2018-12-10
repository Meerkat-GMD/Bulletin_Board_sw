package com.cs.bulletinboard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class read extends Activity {

    Button writecomment;
    ListView listview;
    TextView Btext, Bdate, Btitle;

    String bnum;
    String result1, result2, result3;
    String btitle;
    String bdate;
    String nc;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read);
        Intent intent = getIntent();
        bnum = intent.getStringExtra("bnum");

        btitle = intent.getStringExtra("btitle");
        bdate = intent.getStringExtra("bdate");
        final List<String> list = new ArrayList<>();

        Btext = (TextView)findViewById(R.id.btext);
        Bdate = (TextView)findViewById(R.id.bdate);
        Btitle = (TextView)findViewById(R.id.btitle);
        writecomment = (Button)findViewById(R.id.writecomment);
        listview = (ListView)findViewById(R.id.ctext);
        final EditText newcomment = (EditText)findViewById(R.id.comment);

        // 게시글 내용 채우기
        try {
            JSONObject getboard = new JSONObject();
            getboard.accumulate("bnum",bnum);
            result1 = new JSONTASK().execute("getboard_for_read", getboard.toString()).get(); // 정보를 받아오기만 함!
            if(result1.equals("0")){
                Toast.makeText(this,"데이터 송수신 실패..",Toast.LENGTH_SHORT).show();
            } else{
                JSONArray obj = new JSONArray(result1);
                JSONObject board = obj.getJSONObject(0);
                Btext.setText(board.getString("btext"));
                Bdate.setText(bdate);
                Btitle.setText(btitle);
            }

            JSONObject getcomment = new JSONObject();
            getcomment.accumulate("bnum",bnum);
            result3 = new JSONTASK().execute("getcomment_for_read",getcomment.toString()).get();
            if(result3.equals("0")){
                Toast.makeText(this,"댓글창 가져오기 실패..",Toast.LENGTH_SHORT).show();
            }else{
                System.out.println(result3);
                JSONArray obj = new JSONArray(result3);
                System.out.println(obj.length());
                list.add("--무플 방지 위원회--");
                for (int i=0; i<obj.length(); i++){
                    System.out.println("*************************************************");
                    JSONObject coms = obj.getJSONObject(i);
                    if(i==0) list.remove(0);
                    System.out.println(coms.getString("ctext"));
                    list.add(coms.getString("ctext"));
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.textfinal,list);
        listview.setAdapter(adapter);
        writecomment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nc = newcomment.getText().toString();
                try{
                    JSONObject commdata = new JSONObject();
                    commdata.accumulate("bnum",bnum);
                    commdata.accumulate("webmail",WEBMAIL.getInstance().getData());
                    commdata.accumulate("ctext",nc);
                    result2 = new JSONTASK().execute("putcomment",commdata.toString()).get();
                    if (result2.equals("1")){
                        list.add(0,nc);
                        newcomment.setText(null);
                        Toast.makeText(read.this,"댓글 입력 완료!",Toast.LENGTH_SHORT).show();
                    } else{
                        Toast.makeText(read.this,"데이터 전송 실패..",Toast.LENGTH_SHORT).show();
                    }

                    adapter.notifyDataSetChanged();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
    public void onBackPressed(){
        super.onBackPressed();
    }
}