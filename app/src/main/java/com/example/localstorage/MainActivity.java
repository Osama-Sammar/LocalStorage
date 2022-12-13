package com.example.localstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText edtName, edtTeam;
    private Button btnSave;
    private boolean save = false;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    public static final String NAME = "NAME";
    public static final String TEAM = "TEAM";
    private Boolean FLAG = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViews();
        setupSharedPrefs();
        checkData();

    }

    private void checkData() {
        boolean f = prefs.getBoolean("FLAG", false);
        if(f){
            String name = prefs.getString(NAME, "");
            String team = prefs.getString(TEAM, "");
            edtName.setText(name);
            edtTeam.setText(team);
        }


    }

    // To show this method Click ---> ( ctrl + char 'O' )
    @Override
    protected void onStop() {
        super.onStop();

        if(!save){
            String name = edtName.getText().toString().trim();
            String team = edtTeam.getText().toString().trim();

            editor.putString(NAME, name);
            editor.putString(TEAM, team);
            editor.putBoolean("FLAG", FLAG);

            editor.commit();

        }
    }

    private void setupSharedPrefs() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }

    private void setupViews(){
        edtName = findViewById(R.id.edtName);
        edtTeam = findViewById(R.id.edtTeam);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // assume save operation on block
                save = true;
            }
        });
    }






}