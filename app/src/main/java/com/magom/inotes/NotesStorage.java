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

    private Button backToMainBtn;
    public LinearLayout linearNotesGroup;
    private TextView addNoteButton;
    public static ArrayList<Note> notes = new ArrayList<Note>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_storage);

        init();

        addNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivityEditNote();
            }
        });

        backToMainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prevToMainActivity();
            }
        });

        addNote();

    }

    private void init(){
        addNoteButton = findViewById(R.id.addNoteButton);
        linearNotesGroup = findViewById(R.id.linearNotesGroup);
        backToMainBtn = findViewById(R.id.prevPageButton);
    }
    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, NotesStorage.class);
        return intent;
    }

    private void nextActivityEditNote() {
        Intent intent = EditNote.newIntent(this);
        startActivity(intent);

    }

    private void prevToMainActivity(){
        Intent intent = MainActivity.newIntent(this, notes.size());
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        prevToMainActivity();
    }


    private void addNote(){
        for (Note note:notes){
            View noteView = getLayoutInflater().inflate(
                    R.layout.note_lay,
                    linearNotesGroup,
                    false
            );
            TextView noteViewNameView = noteView.findViewById(R.id.xmlNote);
            noteViewNameView.setText(note.getNoteName());
            TextView noteViewTextView = noteView.findViewById(R.id.xmlNote);

            linearNotesGroup.addView(noteView);
        }

    }

}