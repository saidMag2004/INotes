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
    private static final String  NOTE_NAME_EXTRA = "noteName";
    private static final String  NOTE_TEXT_EXTRA = "noteText";
    private static final String  NOTE_ID_EXTRA = "noteId";


    DataBase dataBase = DataBase.getInstance();
    TextView noteTextView;
    TextView noteNameView;//text-view текстового поля с именем заметки
    Button backToNotesStorageBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);
        init();

//--------Прием данных с существующей заметки---------
        String noteName = getIntent().getStringExtra(NOTE_NAME_EXTRA);
        String noteText = getIntent().getStringExtra(NOTE_TEXT_EXTRA);
        int noteId = getIntent().getIntExtra(NOTE_ID_EXTRA, 0);
//--------Прием данных с существующей заметки---------

//--------Установка этих значений на данном экране, если что то передано вообще
        setNotesAttr(noteName, noteText);

        backToNotesStorageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//Тут регулируется поведение NotesStorage в зависимости                 
                String noteName = noteNameView.getText().toString();
                String noteText = noteTextView.getText().toString();

                if(noteId == 0){
                    prevToNotesStorage();
                }//если заметка новая
                else{
                    if(isStringFilled(noteName) == 0 && isStringFilled(noteText) == 0){
                        Toast.makeText(
                                EditNote.this,
                                R.string.empty_note_warning,
                                Toast.LENGTH_SHORT
                        ).show();
                        dataBase.remove(noteId);
                    }else if(isStringFilled(noteName) == 0){
                        noteName = "Note";
                        dataBase.getNote(noteId).setNoteName(noteName);
                        dataBase.getNote(noteId).setNoteText(noteText);
                    }else{
                        dataBase.getNote(noteId).setNoteName(noteName);
                        dataBase.getNote(noteId).setNoteText(noteText);
                    }

                    finish();
                }//если заметка существующая
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
        intent.putExtra(NOTE_NAME_EXTRA, noteName);
        intent.putExtra(NOTE_TEXT_EXTRA, noteText);
        intent.putExtra(NOTE_ID_EXTRA, id);
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
        int noteId = dataBase.getNotes().size() + 1;


        if(isStringFilled(noteNameText) == 0 && isStringFilled(noteText) == 0){
            Toast.makeText(
                    EditNote.this,
                    R.string.empty_note_warning,
                    Toast.LENGTH_SHORT
            ).show();

        }else if(isStringFilled(noteNameText) == 0){
            noteNameText = "Note";
            Note note = new Note(noteNameText, noteText, noteId);
            dataBase.add(note);
        }else{
            Note note = new Note(noteNameText, noteText, noteId);
            dataBase.add(note);
        }
    }

    private int isStringFilled(String string){
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