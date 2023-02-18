package main.java.ru.geekbrains.lesson6;


import main.java.ru.geekbrains.lesson6.databases.NotesDatabase;
import main.java.ru.geekbrains.lesson6.notes.core.application.ConcreteNoteEditor;
import main.java.ru.geekbrains.lesson6.notes.presentation.queries.controllers.NotesController;
import main.java.ru.geekbrains.lesson6.notes.presentation.queries.views.NotesConsolePresenter;
import main.java.ru.geekbrains.lesson6.notes.infrastructure.persistance.DatabaseContext;

public class Sample01 {

    /**
     *      *     * TODO: ДОМАШНЯЯ РАБОТА
     *      *      *  разработать UML-диаграмму классов для текущего приложения
     * Приложение "редактор заметок"
     * @param args
     */
    public static void main(String[] args) {


        NotesController notesController =
                new NotesController(new ConcreteNoteEditor
                        (new DatabaseContext(new NotesDatabase()), new NotesConsolePresenter()));
        notesController.printAll();

         /*for(NotesRecord record :  new NotesDatabase().getNotesTable().getRecords()){
             System.out.println(record);
         }*/

    }

}
