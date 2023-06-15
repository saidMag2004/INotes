package com.magom.inotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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


        String noteName = getIntent().getStringExtra("noteName");
        String noteText = getIntent().getStringExtra("noteText");
        int noteId = getIntent().getIntExtra("noteId", 0);

        setNotesAttr(noteName, noteText);

        backToNotesStorageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String noteName = noteNameView.getText().toString();
                String noteText = noteTextView.getText().toString();

                if(noteId == 0){
                    prevToNotesStorage();
                }else{
                    if(isFieldFilled(noteName) == 0){
                        Toast.makeText(
                                EditNote.this,
                                R.string.empty_field_warning,
                                Toast.LENGTH_SHORT
                        ).show();
                    }else {
                        dataBase.getNote(noteId).setNoteName(noteName);
                        dataBase.getNote(noteId).setNoteText(noteText);
                    }
                    finish();

                }
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

    public static Intent newIntent(Context context, String noteName, String noteText, int id){
        Intent intent = new Intent(context, EditNote.class);
        intent.putExtra("noteName", noteName);
        intent.putExtra("noteText", noteText);
        intent.putExtra("noteId", id);
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

        if(isFieldFilled(noteNameText) == 0){
            Toast.makeText(
                    EditNote.this,
                    R.string.empty_field_warning,
                    Toast.LENGTH_SHORT
            ).show();
        }else {
            Note note = new Note(noteNameText, noteText, id);
            dataBase.add(note);
        }
    }

    private int isFieldFilled(String string){
        if(string.trim().isEmpty()){
            return 0;
        }else{
            return 1;
        }
    }

//Для элементов
    private void setNotesAttr(String noteName, String noteText){
        noteNameView.setText(noteName);
        noteTextView.setText(noteText);
    }
}