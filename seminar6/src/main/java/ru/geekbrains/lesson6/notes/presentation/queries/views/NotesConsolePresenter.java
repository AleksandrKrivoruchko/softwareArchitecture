package main.java.ru.geekbrains.lesson6.notes.presentation.queries.views;


import main.java.ru.geekbrains.lesson6.notes.core.application.interfaces.NotesPresenter;
import main.java.ru.geekbrains.lesson6.notes.core.domain.Note;

import java.util.Collection;

public class NotesConsolePresenter implements Presenter, NotesPresenter {
    @Override
    public void printAll(Collection<Note> notes) {
        for (Note note: notes) {
            System.out.println(note);
        }
    }

    @Override
    public void printAll(String name, Collection<Note> notes) {

        System.out.println(name);
        System.out.println("==================");

        for (Note note: notes) {
            System.out.println(note);
        }
    }

    @Override
    public void printFirst(Note note) {

    }
}
