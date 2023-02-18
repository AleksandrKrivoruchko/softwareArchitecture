package main.java.ru.geekbrains.lesson6.notes.infrastructure.persistance;

public abstract class DbContext {

    protected final Database database;

    public DbContext(Database database){
        this.database = database;
    }

    public boolean saveChanges(){
        //TODO: Сохранение изменений в базе данных
        return true;
    }

    protected abstract void onModelCreating(ModelBuilder builder);

}

class ModelBuilder{

    public ModelBuilder applyConfiguration(ModelConfiguration configuration){
        //TODO: добавление конфигурации маппинга объекта некоторого типа к структуре таблицы базы данных ...
        return this;
    }

}