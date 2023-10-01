package com.magom.inotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class NotesStorage extends AppCompatActivity{
    Button prevPageBtn;
    TextView addNoteBtn;
    Database database = Database.newInstance();
    RecyclerView recyclerView;
    NotesAdapter notesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_storage);

        init();


        recyclerView.setAdapter(notesAdapter);//установка адаптера на recycler view
        notesAdapter.setOnNoteClickListener(new NotesAdapter.onNoteClickListener() {
            @Override
            public void onNoteClick(Note note) {
                Intent intent = EditNote.newIntent(
                        NotesStorage.this,
                        note.getNoteName(),
                        note.getNoteText(),
                        note.getNoteId()
                );
                startActivity(intent);
            }
        });//установка слушателей клика на адаптер

        //слушатели на кнопки
        prevPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        addNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchEditNoteScreen();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        showNotes();
    }

    private void showNotes(){
        notesAdapter.setNotes(database.getNotes());
    }

    private void init(){
        prevPageBtn = findViewById(R.id.prevPageButton);
        addNoteBtn = findViewById(R.id.addNoteButton);
        recyclerView = findViewById(R.id.recyclerView);
        notesAdapter = new NotesAdapter();
    }

    //все связанное с интентом
    public static Intent newIntent(Context context){
        Intent intent = new Intent(context, NotesStorage.class);
        return intent;
    }
    private void launchEditNoteScreen(){
        Intent intent = EditNote.newIntent(this);
        startActivity(intent);
    }


    @Override
    public void onBackPressed() {
        finish();
    }
}