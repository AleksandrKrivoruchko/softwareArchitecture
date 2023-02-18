package main.java.ru.geekbrains.lesson6.databases;

import java.util.Date;

public class NotesRecord {


    private static int counter;
    private int id;
    private String title;
    private String details;
    private Date creationDate;
    private Date editDate;


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDetails() {
        return details;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getEditDate() {
        return editDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }

    {
        id = ++counter;
    }

    public NotesRecord(String title, String details){
        this.title = title;
        this.details = details;
        creationDate = new Date();
    }


}
