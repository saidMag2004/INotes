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
import android.widget.Toast;

import java.io.Serializable;

public class EditNote extends AppCompatActivity {
    DataBase dataBase = DataBase.getInstance();
    TextView noteTextView;
    TextView noteNameView;//text-view текстового поля с именем заметки
    Button backToNotesStorageBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);
        init();

        backToNotesStorageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prevToNotesStorage();
            }
        });
    }

    private void init(){
        noteNameView = findViewById(R.id.noteNameView);
        noteTextView = findViewById(R.id.noteTextView);
        backToNotesStorageBtn = findViewById(R.id.prevPageButton);
    }
//--------------INTENTS-------------------
    public static Intent newIntent(Context context){
        Intent intent = new Intent(context, EditNote.class);
        return intent;
    }

    private void prevToNotesStorage(){
        addNote();

        finish();
    }

    @Override
    public void onBackPressed() {
        prevToNotesStorage();
    }
//--------------INTENTS-------------------


    private void addNote(){
        String noteNameText = noteNameView.getText().toString();
        String noteText = noteTextView.getText().toString();
        int id = dataBase.getNotes().size() + 1;

        if(noteNameText.trim().isEmpty()){
            Toast.makeText(
                    EditNote.this,
                    R.string.empty_field_warning,
                    Toast.LENGTH_SHORT
            ).show();
        }else{
            Note note = new Note(noteNameText, noteText, id);
            dataBase.add(note);
        }
    }
}