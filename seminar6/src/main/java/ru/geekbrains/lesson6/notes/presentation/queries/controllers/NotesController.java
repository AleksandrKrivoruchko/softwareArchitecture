package main.java.ru.geekbrains.lesson6.notes.presentation.queries.controllers;


import main.java.ru.geekbrains.lesson6.notes.core.application.interfaces.NoteEditor;
import main.java.ru.geekbrains.lesson6.notes.core.domain.Note;

public class NotesController extends Controller{
    private final NoteEditor notesEditor;

    public NotesController(NoteEditor notesEditor){
        this.notesEditor = notesEditor;
    }


    public void routeAddNote(Note note){
        notesEditor.add(note);
    }

    public void routeRemoveNote(Note note){
        notesEditor.remove(note);
    }

    public void printAll(){
        notesEditor.printAllAndName();
    }

}
