package com.magom.inotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{
    LinearLayout defaultNotesStorageButton;
    TextView addNoteBtn;
    TextView notesCountView;
    Database database = Database.newInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        defaultNotesStorageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchNotesStorageScreen();
            }
        });
        addNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchEditNoteScreen();
            }
        });

    }

    private void init(){
        defaultNotesStorageButton = findViewById(R.id.defaultNotesStorage);
        addNoteBtn = findViewById(R.id.addNoteView);
        notesCountView = findViewById(R.id.notesCount);
    }

    private void launchNotesStorageScreen(){
        Intent intent = NotesStorage.newIntent(this);
        startActivity(intent);
    }
    private void launchEditNoteScreen(){
        Intent intent = EditNote.newIntent(this);
        startActivity(intent);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if(database.getSize() != 0){
            notesCountView.setText(database.getSize()+"");
        }
    }
}