package com.magom.inotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EditNote extends AppCompatActivity {
    private TextView noteNameView;
    private TextView noteTextView;
    private Button prevPageBtn;
    private Database database = Database.newInstance();


    String noteName;
    String noteText;
    int noteId;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);

        init();
//Данные уже существующей заметки

        if(noteName!=null && noteText!=null){
            noteNameView.setText(noteName);
            noteTextView.setText(noteText);
        }

        prevPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fieldsCheck(
                        noteNameView.getText().toString(),
                        noteTextView.getText().toString(),
                        noteId
                );
            }
        });
    }

    public static Intent newIntent(Context context){
        Intent intent = new Intent(context, EditNote.class);
        return intent;
    }

    public static Intent newIntent(Context context, String noteName, String noteText, int noteId){
        Intent intent = new Intent(context, EditNote.class);
        intent.putExtra("noteName", noteName);
        intent.putExtra("noteText", noteText);
        intent.putExtra("noteId", noteId);
        return intent;
    }




    private void init(){
        noteNameView = findViewById(R.id.noteNameView);
        noteTextView = findViewById(R.id.noteTextView);
        prevPageBtn = findViewById(R.id.prevPageButton);

        noteName = getIntent().getStringExtra("noteName");
        noteText = getIntent().getStringExtra("noteText");
        noteId = getIntent().getIntExtra("noteId", 0);
    }

//Код создает/меняет зааметку, если хотя бы одно поле заполнено
    private void fieldsCheck(String noteNameStr, String noteTextStr, int noteId){
        noteNameStr = noteNameStr.trim();
        noteTextStr = noteTextStr.trim();

        // заметка новая, ее надо просто добавить
        if(noteId == 0){
            if(noteNameStr.length() != 0){
                database.add(new Note(noteNameStr, noteTextStr, database.getSize()+1));
            }else if(noteTextStr.length() != 0){
                database.add(new Note("Note " + (database.getSize()+1),
                        noteTextStr,
                        database.getSize()+1)
                );
            }
        }
        //выходим из ранее созданной заметки
        else{
            if(noteNameStr.length() != 0){
                database.getNotes().get(noteId-1).setNoteName(noteNameStr);
                database.getNotes().get(noteId-1).setNoteText(noteTextStr);
            }else if(noteTextStr.length() != 0){
                database.getNotes().get(noteId-1).setNoteName("Note "+noteId);
                database.getNotes().get(noteId-1).setNoteText(noteTextStr);
            }else if(noteNameStr.isEmpty() && noteTextStr.isEmpty()){
                database.remove(noteId);
            }

        }

        finish();
    }

    @Override
    public void onBackPressed() {
        fieldsCheck(noteNameView.getText().toString(), noteTextView.getText().toString(), noteId);
    }
}