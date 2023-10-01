package com.magom.inotes;

import java.util.ArrayList;

public class Database {
    private ArrayList<Note> notes = new ArrayList<>();

    //-----------Singleton--------//
    private static Database instance = null;
    public static Database newInstance(){
        if(instance == null){
            instance = new Database();
        }
        return instance;
    }
    //-----------Singleton--------//

    private Database(){

    }
    public int getSize(){
        return notes.size();
    }
    public void add(Note note){
        notes.add(note);
    }

    public void remove(int noteId){
        for(Note note : notes){
            if(note.getNoteId() == noteId){
                notes.remove(note);
            }
        }
    }
    public ArrayList<Note> getNotes() {
        return new ArrayList<>(notes);
    }
}
