package ru.geekbrains.lesson5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.Scanner;

public class Sample01 {
    //TODO: Домашняя работа
    // Сформировать формальную UML-диаграмму по текущей задаче.
    /**
     * Необходимо разделить на горизонтальные уровни "Редактор 3D графики".
     * Один пользователь. Программа работает на одном компьютере без выхода в сеть.
     *
     * Что видит пользователь, как взаимодействует? (Панель загрузки, блок редактирования, блок просмотра).
     * Какие задачи можно делать – функции системы? (Загрузить 3D модель, рассмотреть 3D модель, создать новую,
     * редактировать вершины, текстуры, сделать рендер, сохранить рендер).
     * Какие и где хранятся данные? (файлы 3D моделей, рендеры, анимация .., в файловой системе компьютера).
     *
     * Предложить варианты связывания всех уровней – сценарии использования. 3-4 сценария.
     * Сквозная функция – создать новую 3D модель, сделать рендер для печати на принтере…
     */
    public static void main(String[] args) {

        /*EditorDatabase editorDatabase = new EditorDatabase(new ProjectFile("D:/database.db"));
        ArrayList<Entity> entities = (ArrayList<Entity>)editorDatabase.getAll();
        for (Entity entity: entities) {
            System.out.println(entity);
        }*/
        Editor3D editor3D = new Editor3D();
        Scanner scanner = new Scanner(System.in);
        boolean f = true;
        while (f){
            System.out.println("*** МОЙ 3D РЕДАКТОР ***");
            System.out.println("=======================");
            System.out.println("1. Открыть проект");
            System.out.println("2. Сохранить проект");
            System.out.println("3. Отобразить параметры проекта");
            System.out.println("4. Отобразить все модели проекта");
            System.out.println("5. Отобразить все текстуры проекта");
            System.out.println("6. Выполнить рендер всех моделей");
            System.out.println("7. Выполнить рендер модели");
            System.out.println("0. ЗАВЕРШЕНИЕ РАБОТЫ ПРИЛОЖЕНИЯ");
            System.out.print("Пожалуйста, выберите пункт меню: ");
            if (scanner.hasNextInt()){
                int no = scanner.nextInt();
                scanner.nextLine();
                try{
                    switch (no){
                        case 0:
                            System.out.println("Завершение работы приложения");
                            f = false;
                            break;
                        case 1:
                            System.out.print("Укажите наименование файла проекта: ");
                            String fileName = scanner.nextLine();
                            editor3D.openProject(fileName);
                            System.out.println("Проект успешно открыт.");
                            break;
                        case 3:
                            editor3D.showProjectSettings();
                            break;
                        case 4:
                            editor3D.printAllModels();
                            break;
                        case 5:
                            editor3D.printAllTextures();
                            break;
                        case 6:
                            editor3D.renderAll();
                            break;
                        case 7:
                            System.out.print("Укажите номер модели: ");
                            if (scanner.hasNextInt()){
                                int modelNo = scanner.nextInt();
                                scanner.nextLine();
                                editor3D.renderModel(modelNo);
                            }
                            else {
                                System.out.println("Номер модели указан некорректно.");
                            }
                            break;
                        default:
                            System.out.println("Укажите корректный пункт меню.");
                    }
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            else{
                System.out.println("Укажите корректный пункт меню.");
                scanner.nextLine();
            }
        }


    }

}

class Editor3D implements UILayer{

    private ProjectFile projectFile;
    private BusinessLogicalLayer businessLogicalLayer;
    private DatabaseAccess databaseAccess;
    private Database database;


    @Override
    public void openProject(String fileName) {
        projectFile = new ProjectFile(fileName);

        database = new EditorDatabase(projectFile);
        databaseAccess = new EditorDatabaseAccess(database);
        businessLogicalLayer = new EditorBusinessLogicalLayer(databaseAccess);
    }

    @Override
    public void showProjectSettings() {

        checkProjectFile();


        System.out.println("*** Project v1 ***");
        System.out.println("******************");
        System.out.printf("fileName: %s\n", projectFile.getFileName());
        System.out.printf("setting1: %d\n", projectFile.getSetting1());
        System.out.printf("setting2: %s\n", projectFile.getSetting2());
        System.out.printf("setting3: %s\n", projectFile.getSetting3());
        System.out.println("******************");
    }

    @Override
    public void printAllModels() {
        // Предусловие
        checkProjectFile();

        ArrayList<Model3D> models = (ArrayList<Model3D>)businessLogicalLayer.getAllModels();
        for (int i = 0; i < models.size(); i++){
            System.out.printf("===%d===\n", i);
            System.out.println(models.get(i));
            for (Texture texture: models.get(i).getTextures()) {
                System.out.printf("\t%s\n", texture);
            }
        }
    }

    @Override
    public void printAllTextures() {
        // Предусловие
        checkProjectFile();

        ArrayList<Texture> textures = (ArrayList<Texture>)businessLogicalLayer.getAllTextures();
        for (int i = 0; i < textures.size(); i++){
            System.out.printf("===%d===\n", i);
            System.out.println(textures.get(i));
        }
    }

    @Override
    public void renderAll() {
        // Предусловие
        checkProjectFile();
        System.out.println("Подождите ...");
        long startTime = System.currentTimeMillis();
        businessLogicalLayer.renderAllModels();
        long endTime = (System.currentTimeMillis() - startTime);
        System.out.printf("Операция выполнена за %d мс.\n", endTime);
    }

    @Override
    public void renderModel(int i) {
        // Предусловие
        checkProjectFile();

        ArrayList<Model3D> models = (ArrayList<Model3D>)businessLogicalLayer.getAllModels();
        if (i < 0 || i > models.size() - 1)
            throw new RuntimeException("Номер модели указан некорректною.");
        System.out.println("Подождите ...");
        long startTime = System.currentTimeMillis();
        businessLogicalLayer.renderModel(models.get(i));
        long endTime = (System.currentTimeMillis() - startTime);
        System.out.printf("Операция выполнена за %d мс.\n", endTime);
    }

    private void checkProjectFile(){
        if (projectFile == null)
            throw new RuntimeException("Файл проекта не определен.");
    }
}

/**
 * Интерфейс User Interface Layer
 */
interface UILayer{
    /**
     * Пользователь может открыть мой проект
     * @param fileName
     */
    void openProject(String fileName);

    /**
     * Пользователь может просмотреть настройки проекта
     */
    void showProjectSettings();

    /**
     * Пользователь может распечатать список всех моделей
     */
    void printAllModels();

    /**
     * Пользователь может распечатать список всех текстур
     */
    void printAllTextures();

    /**
     * Выполнить рендер всех моделей
     */
    void renderAll();

    /**
     * Выполнить рендер одной модели
     * @param i
     */
    void renderModel(int i);
}


/**
 * Business Logical Layer
 * Описываем реализацию конкртеных функций комплекса
 */
class EditorBusinessLogicalLayer implements BusinessLogicalLayer{

    private final DatabaseAccess databaseAccess;

    public EditorBusinessLogicalLayer(DatabaseAccess databaseAccess){
        this.databaseAccess = databaseAccess;
    }

    @Override
    public Collection<Model3D> getAllModels() {
        return databaseAccess.getAllModels();
    }

    @Override
    public Collection<Texture> getAllTextures() {
        return databaseAccess.getAllTextures();
    }

    @Override
    public void renderModel(Model3D model) {
        processRender(model);
    }

    @Override
    public void renderAllModels() {
        for (Model3D model: getAllModels()) {
            processRender(model);
        }
    }

    private Random random = new Random();
    private void processRender(Model3D model){
        try {
            Thread.sleep(2500 - random.nextInt(2000));
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

/**
 * Интерфейс Business Logical Layer
 */
interface BusinessLogicalLayer{

    Collection<Model3D> getAllModels();
    Collection<Texture> getAllTextures();

    void renderModel(Model3D model);
    void renderAllModels();


}

/**
 * Data Access Layer - подготовка данных к использованию
 */
class EditorDatabaseAccess implements DatabaseAccess {

    private final Database editorDatabase;

    public EditorDatabaseAccess(Database editorDatabase){
        this.editorDatabase = editorDatabase;
    }

    /**
     * Добавить сущность в проект
     * @param entity
     */
    @Override
    public void addEntity(Entity entity) {
        editorDatabase.getAll().add(entity);
    }

    /**
     * Удалить сущность из проекта
     * @param entity
     */
    @Override
    public void removeEntity(Entity entity) {
        editorDatabase.getAll().remove(entity);
    }

    /**
     * Получить список всех текстур
     * @return
     */
    @Override
    public Collection<Texture> getAllTextures() {
        Collection<Texture> textures = new ArrayList<>();
        for (Entity entity: editorDatabase.getAll()) {
            if (entity instanceof Texture)
            {
                textures.add((Texture)entity);
            }
        }
        return textures;
    }

    /**
     * Получить список всех моделей
     * @return
     */
    @Override
    public Collection<Model3D> getAllModels() {
        Collection<Model3D> models = new ArrayList<>();
        for (Entity entity: editorDatabase.getAll()) {
            if (entity instanceof Model3D)
            {
                models.add((Model3D)entity);
            }
        }
        return models;
    }
}

/**
 * Интерфейс Data Access Layer
 */
interface DatabaseAccess{

    void addEntity(Entity entity);
    void removeEntity(Entity entity);
    Collection<Texture> getAllTextures();
    Collection<Model3D> getAllModels();
}

class EditorDatabase implements Database{

    private Random random = new Random();
    private Collection<Entity> entityCollection;

    private final ProjectFile projectFile;

    public EditorDatabase(ProjectFile projectFile){
        this.projectFile = projectFile;
        load();
    }

    @Override
    public void load() {
        //TODO: Загрузка всех сущностей проекта (модели и текстуры ...)
        // entityCollection.add(...)
    }

    @Override
    public void save() {
        //TODO: Сохранение всех сущностей проекта (модели и текстуры ...)
    }

    @Override
    public Collection<Entity> getAll() {

        if (entityCollection == null) {
            entityCollection = new ArrayList<>();
            int modelsCount = 10 - random.nextInt(6);
            for (int i = 0; i < modelsCount; i++)
                generateModelAndTextures();
        }
        return entityCollection;
    }

    private void generateModelAndTextures(){
        Model3D model = new Model3D();
        int textureCount = random.nextInt(3);
        for (int i = 0; i < textureCount; i++){
            Texture texture = generateTexture();
            model.getTextures().add(texture);
            entityCollection.add(texture);
        }
        entityCollection.add(model);
    }

    private Texture generateTexture(){
        return new Texture();
    }
}


/**
 * Интерфейс БД
 */
interface Database{

    void load();
    void save();
    Collection<Entity> getAll();
}


/**
 * Файл проекта
 */
class ProjectFile{

    private String fileName;
    private int setting1;
    public String setting2;
    private boolean setting3;

    public String getFileName() {
        return fileName;
    }

    public int getSetting1() {
        return setting1;
    }

    public String getSetting2() {
        return setting2;
    }

    public boolean getSetting3() {
        return setting3;
    }

    public ProjectFile(String fileName){
        this.fileName = fileName;
        //TODO: Считываем данные из файла-проекта, заполняем поля-настройки
        setting1 = 100;
        setting2 = "main-setting";
        setting3 = false;
    }

}

/**
 * 3D Модель
 */
class Model3D implements Entity{
    private static int counter = 10000;
    private int id;
    private Collection<Texture> textures = new ArrayList<>();

    {
        id = ++counter;
    }

    public int getId() {
        return id;
    }

    public Collection<Texture> getTextures() {
        return textures;
    }

    public Model3D(){}

    public Model3D(Collection<Texture> textures){
        this.textures = textures;
    }

    @Override
    public String toString() {
        return String.format("Model #%s", id);
    }
}

/**
 * Текстура
 */
class Texture implements Entity{

    private static int counter = 50000;
    private int id;
    {
        id = ++counter;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("Texture #%s", id);
    }
}

/**
 * Интерфейс описывает главную сущность элемента проекта
 */
interface Entity{

    int getId();

}
