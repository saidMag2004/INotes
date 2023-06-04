package com.magom.inotes;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;

public class EditNote extends AppCompatActivity {
    TextView noteTextView;
    TextView noteNameView;//text-view текстового поля с именем заметки
    String noteNameText;//Строка с названием заметки

    Button prevPageButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);
        init();

        prevPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noteNameText = noteNameView.getText().toString();
                if (noteNameText.trim().isEmpty()){

                }else{
                    String noteText = noteTextView.getText().toString();
                    Note note = new Note(noteNameText, noteText);
                    NotesStorage.notes.add(note);
                }
                prevPageNotesStorage();
            }
        });

    }

    public static Intent newIntent(Context context){
        Intent intent = new Intent(context, EditNote.class);
        return intent;
    }

    private void prevPageNotesStorage(){
        Intent intent = NotesStorage.newIntent(this);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        prevPageNotesStorage();
    }

    private void init(){
        noteNameView = findViewById(R.id.noteNameView);
        noteTextView = findViewById(R.id.noteTextView);
        prevPageButton = findViewById(R.id.prevPageButton);
    }


}