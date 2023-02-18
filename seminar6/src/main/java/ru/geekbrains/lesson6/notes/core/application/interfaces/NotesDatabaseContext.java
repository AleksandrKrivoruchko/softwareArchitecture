package main.java.ru.geekbrains.lesson6.notes.core.application.interfaces;

import main.java.ru.geekbrains.lesson6.notes.core.domain.Note;

import java.util.Collection;

public interface NotesDatabaseContext {

    Collection<Note> getAll();
    boolean saveChanges();
}
