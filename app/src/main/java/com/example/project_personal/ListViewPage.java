package com.example.project_personal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListViewPage extends AppCompatActivity {

    ArrayList<SampleData> itemDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_list_page);

        this.InitializeItemData();

        ListView listView = (ListView)findViewById(R.id.listview);
        final MyAdapter myAdapter = new MyAdapter(this, itemDataList);

        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){
                Toast.makeText(getApplicationContext(),
                        myAdapter.getItem(position).getItemName(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        Button InfoButton = (Button) findViewById(R.id.InfoButton);
        InfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView personalinfotext = (TextView) findViewById(R.id.personalinfo);

                SharedPreferences idpw = getSharedPreferences("idpw", 0);

                Boolean LogIn = idpw.getBoolean("LogIn여부", true);
                if (LogIn) {
                    String ID = idpw.getString("ID", "");
                    String PW = idpw.getString("PW", "");

                    String msg = idpw.getString(ID + PW, "");

                    personalinfotext.setText(msg);
                }
                else {
                    Button YesButton = (Button) findViewById(R.id.Yes);
                    Button NoButton = (Button) findViewById(R.id.No);
                    YesButton.setVisibility(View.VISIBLE);
                    NoButton.setVisibility(View.VISIBLE);

                    personalinfotext.setText("회원가입을 하시겠습니까?");

                    YesButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getApplicationContext(), SingInPageActivity.class);
                            startActivity(intent);
                        }
                    });


                    NoButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getApplicationContext(), ListViewPage.class);
                            startActivity(intent);
                        }
                    });

                }
            }
        });
    }

    public void InitializeItemData()
    {
        itemDataList = new ArrayList<SampleData>();

        itemDataList.add(new SampleData(R.drawable.item_chocolate, "초콜릿","6000원"));
        itemDataList.add(new SampleData(R.drawable.item_fan, "레트로 선풍기","16000원"));
        itemDataList.add(new SampleData(R.drawable.item_headphone, "게이밍 헤드폰","25000원"));
        itemDataList.add(new SampleData(R.drawable.item_keyboard, "애플 키보드","200000원"));
        itemDataList.add(new SampleData(R.drawable.item_nintendoswitch, "닌텐도 스위치 마리오 에디션 ","370000원"));
    }
}