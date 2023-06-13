package com.magom.inotes;

import java.util.ArrayList;

public class DataBase {
    private ArrayList<Note> notes = new ArrayList<>();

    private static DataBase database = null;

    public static DataBase getInstance(){
        if(database==null){
            database = new DataBase();
        }
        return database;
    }

    public void add(Note note){
        notes.add(note);
    }

    public void remove(int id){
        for (Note note : notes){
            if(note.getId() == id){
                notes.remove(note);
            }
        }
    }

    public ArrayList<Note> getNotes(){
        return new ArrayList<Note>(notes);
    }
}
