package com.magom.inotes;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder>{

    private ArrayList<Note> notes = new ArrayList<>();
    public void setNotes(ArrayList<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    private onNoteClickListener onNoteClickListener;
    public void setOnNoteClickListener(NotesAdapter.onNoteClickListener onNoteClickListener) {
        this.onNoteClickListener = onNoteClickListener;
    }
    interface onNoteClickListener{
        void onNoteClick(Note note);
    }



    public class NotesViewHolder extends RecyclerView.ViewHolder{
        TextView noteName;
        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            noteName = itemView.findViewById(R.id.xmlNote);
        }
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View noteView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.note_lay,
                parent,
                false
        );
        return new NotesViewHolder(noteView);
    }
    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.noteName.setText(note.getNoteName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNoteClickListener.onNoteClick(note);
            }
        });
    }
    @Override
    public int getItemCount() {
        return notes.size();
    }
}
