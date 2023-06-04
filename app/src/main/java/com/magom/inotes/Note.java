package com.magom.inotes;

import java.io.Serializable;

public class Note implements Serializable {
    private String noteName;
    private String noteText;

    public Note(String noteName, String noteText) {
        this.noteName = noteName;
        this.noteText = noteText;
    }

    public String getNoteName() {
        return noteName;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }
}
