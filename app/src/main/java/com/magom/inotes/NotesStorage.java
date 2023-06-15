package com.magom.inotes;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class NotesStorage extends AppCompatActivity{
    private DataBase dataBase = DataBase.getInstance();

    private Button backToMainBtn;
    public LinearLayout linearNotesGroup;
    private TextView addNoteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_storage);

        init();

        Log.i(TAG, dataBase.getNotes().size()+" is note's size");

        backToMainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prevToMainActivity();
            }
        });

        addNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextToEditNoteActivity();
            }
        });
    }

    public static Intent newIntent(Context context){
        Intent intent = new Intent(context, NotesStorage.class);
        return intent;
    }

    //------------INIT----------------
    private void init(){
        addNoteButton = findViewById(R.id.addNoteButton);
        linearNotesGroup = findViewById(R.id.linearNotesGroup);
        backToMainBtn = findViewById(R.id.prevPageButton);
    }
    //------------INIT----------------

    //----------------INTENTS-----------------

    private void prevToMainActivity(){
        finish();
    }

    private void nextToEditNoteActivity(){
        Intent intent = EditNote.newIntent(this);
        startActivity(intent);

    }



    @Override
    public void onBackPressed() {
        prevToMainActivity();
    }

    //----------------INTENTS-----------------


    @Override
    protected void onResume() {
        super.onResume();
        linearNotesGroup.removeAllViews();
        for(Note note : dataBase.getNotes()){
            View noteView = getLayoutInflater().inflate(
                    R.layout.note_lay,
                    linearNotesGroup,
                    false
            );

            noteView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = EditNote.newIntent(NotesStorage.this, note.getNoteName(), note.getNoteText(), note.getId());
                    startActivity(intent);
                }
            });

            TextView noteNameView = noteView.findViewById(R.id.xmlNote);
            noteNameView.setText(note.getNoteName());

            linearNotesGroup.addView(noteView);
        }
    }
}