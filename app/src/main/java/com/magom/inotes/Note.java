package com.magom.inotes;

public class Note {
    private String noteName;
    private String noteText;
    private int noteId;



    public Note(String noteName, String noteText, int noteId) {
        this.noteName = noteName;
        this.noteText = noteText;
        this.noteId = noteId;
    }

    public String getNoteName() {
        return noteName;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public int getNoteId() {
        return noteId;
    }

}
