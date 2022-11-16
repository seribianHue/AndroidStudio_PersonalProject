package com.example.project_personal;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;


public class SingInPageActivity extends AppCompatActivity {

    private EditText mIDText;
    private EditText mPasswordText;
    private EditText mNameText;
    private EditText mTelNumText;
    private EditText mEmailAddressText;
    private RadioGroup mAgreeGroup;
    boolean Agreement = true;
    boolean LogIn = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_page);

        mIDText = (EditText) findViewById(R.id.IDText);
        mPasswordText = (EditText) findViewById(R.id.PasswordText);
        mNameText = (EditText) findViewById(R.id.NameText);
        mTelNumText = (EditText) findViewById(R.id.TelNumText);
        mEmailAddressText = (EditText) findViewById(R.id.EmailAddressText);
        mAgreeGroup = (RadioGroup) findViewById(R.id.AgreeGroup);

        mAgreeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.Agree:
                        Agreement = true;
                        break;
                    case R.id.Disagree:
                        Agreement = false;
                        break;
                }
            }
        });

        Button SingInButton = (Button) findViewById(R.id.SingInButton);
        SingInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String IDinput = mIDText.getText().toString();
                String PWinput = mPasswordText.getText().toString();
                String name = mNameText.getText().toString();
                String Tel = mTelNumText.getText().toString();
                String Email = mEmailAddressText.getText().toString();

                final String REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{6,16}$";
                final Pattern PASSWORD_PATTERN = Pattern.compile(REGEX);

                SharedPreferences prefs = getSharedPreferences("person_info", 0);
                SharedPreferences idpw = getSharedPreferences("idpw", 0);

                SharedPreferences.Editor editor = prefs.edit();
                SharedPreferences.Editor idpweditor = idpw.edit();



                String ID = prefs.getString("ID", "");

                if (!Agreement) {
                    Toast.makeText(getApplicationContext(),"이용약관 동의 부탁드립니다.", Toast.LENGTH_SHORT).show();
                }

                else if (IDList.checkIDduplicants(IDinput)) {
                    Toast.makeText(getApplicationContext(),"중복된 아이디입니다", Toast.LENGTH_SHORT).show();
                }

                else if (PASSWORD_PATTERN.matcher(PWinput).matches()) {
                    idpweditor.putString(IDinput + PWinput, "ID: " + IDinput + "\nPW: " + PWinput + "\nname: " + name + "\nTel: " + Tel + "\nEmail: " + Email);
                    idpweditor.putString("ID", IDinput);
                    idpweditor.putString("PW", PWinput);
                    idpweditor.putBoolean("LogIn여부", LogIn);
                    idpweditor.apply();

                    IDList.IDList.add(IDinput);

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }

                else {
                    for (String id: IDList.IDList) {
                        if (IDinput.equals(id)) {
                            Toast.makeText(getApplicationContext(),"중복된 아이디입니다", Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }
                    Toast.makeText(getApplicationContext(),"잘못된 비밀번호입니다", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }


}
