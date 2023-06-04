package com.magom.inotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{
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
                nextActivityNotesStorage();
            }
        });

        notesCount = getIntent().getIntExtra("notesCount", 0);
        if (notesCount>0){
            notesCountView.setText(notesCount+"");
        }

    }


    private void init(){
        notesStorageButton = findViewById(R.id.defaultNotesStorage);

        notesCountView = findViewById(R.id.notesCount);
    }

    public static Intent newIntent(Context context, int notesCount) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("notesCount", notesCount);
        return intent;
    }
    private void nextActivityNotesStorage() {
        Intent intent = NotesStorage.newIntent(this);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}