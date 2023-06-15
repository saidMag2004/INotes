package com.magom.inotes;

public class Note {
    private String noteName;
    private String noteText;
    private int id;

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public Note(String noteName, String noteText, int id) {
        this.noteName = noteName;
        this.noteText = noteText;
        this.id = id;
    }

    public String getNoteName() {
        return noteName;
    }

    public String getNoteText() {
        return noteText;
    }

    public int getId() {
        return id;
    }
}
