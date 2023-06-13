package com.magom.inotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{
    private DataBase dataBase = DataBase.getInstance();
    int notesCount;
    LinearLayout notesStorageButton;
    TextView notesCountView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        notesStorageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextPageNotesStorage();
            }
        });
    }


    private void init(){
        notesStorageButton = findViewById(R.id.defaultNotesStorage);
        notesCountView = findViewById(R.id.notesCount);
    }

    private void nextPageNotesStorage(){
        Intent intent = NotesStorage.newIntent(MainActivity.this);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(dataBase.getNotes().size()!=0){
            notesCountView.setText(dataBase.getNotes().size()+"");
        }
    }
}