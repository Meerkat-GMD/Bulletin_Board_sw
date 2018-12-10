package com.cs.bulletinboard;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.content.res.TypedArray;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends Activity{

    private ListView m_oListView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button write = (Button)findViewById(R.id.write);
        final ArrayList<ItemData> oData = new ArrayList<>();
        String result = "실패";
        try {
            result = new JSONTASK().execute("getboard_for_list", "trash").get(); // 정보를 받아오기만 함!
            JSONArray obj = new JSONArray(result);

            for (int i=0; i<obj.length(); i++) {
                JSONObject board = obj.getJSONObject(i);
                ItemData oItem = new ItemData();
                oItem.bNum = board.getString("bnum");   // 화면에 출력되지는 않지만, 글 읽고, 글 수정할때 사용할 용도
                oItem.bTitle = board.getString("btitle");
                oItem.bDate = board.getString("bdate");
                oData.add(oItem);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        // ListView, Adapter 생성 및 연결 ------------------------
        m_oListView = (ListView)findViewById(R.id.listView);
        ListAdapter oAdapter = new ListAdapter(oData);
        m_oListView.setAdapter(oAdapter);
        m_oListView.setClickable(true);
        m_oListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Intent intent = new Intent(getApplicationContext(), read.class);
                intent.putExtra("bnum", oData.get(position).bNum);
                intent.putExtra("btitle",oData.get(position).bTitle);
                intent.putExtra("bdate",oData.get(position).bDate);
                startActivity(intent);
                finish();
            }
        });

        write.setOnClickListener(new View.OnClickListener() { // 글 작성 버튼
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), writing.class);
                startActivity(intent);
                finish();
            }
        });
    }
}