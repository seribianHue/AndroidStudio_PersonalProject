package com.example.project_personal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private EditText mIDText;
    private EditText mPasswordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIDText = (EditText) findViewById(R.id.IDText);
        mPasswordText = (EditText) findViewById(R.id.PasswordText);

        SharedPreferences idpw = getSharedPreferences("idpw", 0);

        String ID = idpw.getString("ID", "");
        String PW = idpw.getString("PW", "");


        if (savedInstanceState == null) {
            mIDText.setText(ID);
            mPasswordText.setText(PW);
        }

        Button LongInButton = (Button) findViewById(R.id.LongInButton);
        LongInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String IDinput = mIDText.getText().toString();
                String PWinput = mPasswordText.getText().toString();

                String ID = idpw.getString("ID", "");
                String PW = idpw.getString("PW", "");


                SharedPreferences idpw = getSharedPreferences("idpw", 0);
                SharedPreferences.Editor idpweditor = idpw.edit();

                if (idpw.contains(IDinput + PWinput)) {
                    idpweditor.putBoolean("LogIn여부", true);
                    idpweditor.putString("ID", IDinput);
                    idpweditor.putString("PW", PWinput);
                    idpweditor.apply();
                    Intent intent = new Intent(getApplicationContext(), ListViewPage.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(),"아이디와 비밀번호가 잘못되었습니다", Toast.LENGTH_SHORT).show();
                }

            }
        });
        Button SingInButton = (Button) findViewById(R.id.SingInButton);
        SingInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SingInPageActivity.class);
                startActivity(intent);
            }
        });
        Button ListPageButton = (Button) findViewById(R.id.ListPageButton);
        ListPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences idpw = getSharedPreferences("idpw", 0);
                SharedPreferences.Editor idpweditor = idpw.edit();
                idpweditor.putBoolean("LogIn여부", false);
                idpweditor.apply();
                Intent intent = new Intent(getApplicationContext(), ListViewPage.class);
                startActivity(intent);
            }
        });
    }
}