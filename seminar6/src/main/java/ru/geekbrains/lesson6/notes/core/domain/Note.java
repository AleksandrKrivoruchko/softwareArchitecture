package main.java.ru.geekbrains.lesson6.notes.core.domain;

import java.util.Date;

public class Note {

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

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }

    public Note(int id, String title, String details, Date creationDate, Date editDate) {
        this.id = id;
        this.title = title;
        this.details = details;
        this.creationDate = creationDate;
        this.editDate = editDate;
    }

    @Override
    public String toString() {
        return String.format("[%d] - [%s] - [%s]", id, title, details);
    }
}















