package main.java.ru.geekbrains.lesson6.notes.infrastructure.persistance;



import main.java.ru.geekbrains.lesson6.databases.NotesDatabase;
import main.java.ru.geekbrains.lesson6.databases.NotesRecord;
import main.java.ru.geekbrains.lesson6.notes.core.application.interfaces.NotesDatabaseContext;
import main.java.ru.geekbrains.lesson6.notes.core.domain.Note;

import java.util.ArrayList;
import java.util.Collection;

public class DatabaseContext extends DbContext implements NotesDatabaseContext {


    public DatabaseContext(Database database) {
        super(database);
    }

    @Override
    protected void onModelCreating(ModelBuilder builder) {

        builder.applyConfiguration(new NoteConfiguration());

    }

    @Override
    public Collection<Note> getAll() {
        Collection<Note> notesList = new ArrayList<>();
        //TODO: Этого кастинга быть не должно, тут должен работать внутренний механизм фреймворка
        for (NotesRecord record : ((NotesDatabase)database).getNotesTable().getRecords()){
            notesList.add(new Note(
                    record.getId(),
                    record.getTitle(),
                    record.getDetails(),
                    record.getCreationDate(),
                    record.getEditDate()
            ));
        }
        return notesList;
    }

    @Override
    public boolean saveChanges() {
        return false;
    }
}
