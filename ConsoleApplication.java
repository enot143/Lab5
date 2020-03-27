package Lab5;

import java.io.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import javax.xml.bind.*;

public class ConsoleApplication {

    private File xmlFile;
    private Date date;
    boolean start;
    Catalog catalog = new Catalog();
    protected static HashMap<String, String> manual;

    {
        manual = new HashMap<>();
        manual.put("info", "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        manual.put("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        manual.put("insert null {element}", "добавить новый элемент с заданным ключом");
        manual.put("update id {element}", "обновить значение элемента коллекции, id которого равен заданному");
        manual.put("remove_key null", "удалить элемент из коллекции по его ключу");
        manual.put("clear", "очистить коллекцию");
        manual.put("save", "сохранить коллекцию в файл");
        manual.put("execute_script file_name", "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        manual.put("exit", "завершить программу (без сохранения в файл)");
        manual.put("remove_greater {element}", "удалить из коллекции все элементы, превышающие заданный ");
        manual.put("remove_lower {element}", "удалить из коллекции все элементы, меньшие, чем заданный");
        manual.put("remove_lower_key null", "удалить из коллекции все элементы, ключ которых меньше, чем заданный");
        manual.put("average_of_manufacture_cost", "вывести среднее значение поля manufactureCost для всех элементов коллекции");
        manual.put("group_counting_by_part_number", "сгруппировать элементы коллекции по значению поля partNumber, вывести количество элементов в каждой группе");
        manual.put("print_field_descending_unit_of_measure", "вывести значения поля unitOfMeasure всех элементов в порядке убывания");
    }

    /**
     * Конструктор для загрузки коллекции из файла.
     *
     * @param path путь файла
     * @throws IOException если нет аргумента
     */
    public ConsoleApplication(String path) throws IOException {
        try {
            if (path == null) throw new FileNotFoundException();
        } catch (FileNotFoundException e) {
            System.out.println("Путь до файла xml нужно передать через переменную окружения FilePath");
            System.exit(1);
        }
        File file = new File(path);
        try {
            if (file.exists()) this.xmlFile = new File(path);
            else throw new FileNotFoundException();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не существует");
            System.exit(1);
        }
        load();
        date = new Date();
        start = true;
    }


    /**
     * Десериализация коллекции из xml-файла
     */
    public void load() {
        try {
            if (!xmlFile.exists()) throw new FileNotFoundException();
        } catch (FileNotFoundException e) {
            System.out.println("Файла не существует");
            if (!start) System.exit(1);
            else return;
        }
        try {
            if (!xmlFile.canRead() || !xmlFile.canWrite()) throw new SecurityException();
        } catch (SecurityException e) {
            System.out.println("Файл защищён от чтения и/или записи. Для работы программы нужны оба разрешения");
            if (!start) System.exit(1);
            else return;
        }
        try {
            if (xmlFile.length() == 0) throw new JAXBException("");
        } catch (JAXBException e) {
            System.out.println("Файл пуст.");
            if (!start) System.exit(1);
            else return;
        }
        try {
            JAXBContext context = JAXBContext.newInstance(Catalog.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            catalog = (Catalog) unmarshaller.unmarshal(xmlFile);
            System.out.println("Коллекция загружается");

        } catch (JAXBException e) {
            System.out.println("Ошибка синтаксиса xml. Коллекция не может быть загружена.");
            System.exit(1);
        }
        System.out.println("Коллекция успешно загружена. Добавлено " + catalog.getSize() + " элементов.");
        catalog.getProducts().forEach((k, v) -> {
            System.out.println("key: " + k + " value: " + v);
        });
    }

    /**
     * *Сериализация в xml-файл
     */
    public void save() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(catalog, xmlFile);
            System.out.println("Success!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Выводит на экран список доступных пользователю команд.
     */
    public void help() {
        for (Map.Entry<String, String> man : manual.entrySet()) {
            System.out.printf("%-40s %-30s\n", man.getKey(), man.getValue());
        }
    }

    /**
     * Выводит информацию о коллекции.
     */
    public void info() {
        System.out.println("Тип коллекции: " + catalog.getProducts().getClass() +
                "\nДата инициализации: " + date +
                "\nКоличество элементов: " + catalog.getSize());
    }

    /**
     * Выводит все элементы коллекции.
     */
    public void show() {
        if (catalog.getSize() != 0) {
            try {
                for (Map.Entry<Integer, Product> productEntry : catalog.getProducts().entrySet()) {
                    System.out.println("key: " + productEntry.getKey() + " value:" + productEntry.getValue().toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else System.out.println("Коллекция пуста.");
    }

    /**
     * Добавляет новый элемент с заданным ключом
     *
     * @param key ключ элемента
     * @throws IOException если ошибка ввода
     */
    public void insert(String key) throws IOException {
        boolean f = true;
        int i = Integer.parseInt(key);
        for (Map.Entry<Integer, Product> product : catalog.getProducts().entrySet()) {
            if (product.getKey().equals(i)) {
                System.out.println("Элемент с таким ключом уже существует");
                f = false;
            }
        }
        if (f) {
            Product p = new Product();
            p.update();
            catalog.getProducts().put(i, p);
        }
        save();
    }


    /**
     * Обновляет значение элемента коллекции.
     *
     * @param id значение id элемента
     * @throws IOException если ошибка ввода
     */
    public void update(String id) throws IOException {
        int i = Integer.parseInt(id);
        boolean f = false;
        for (Map.Entry<Integer, Product> product : catalog.getProducts().entrySet()) {
            if (product.getValue().getId() == i) {
                product.getValue().update();
                f = true;
            }
        }
        if (!f){
            System.out.println("Элемента с данным id не существует.");
        }
        save();
    }

    /**
     * Удаляет все элементы коллекции.
     */
    public void clear() {
        catalog.getProducts().clear();
        System.out.print("Коллекция очищена.");
    }

    /**
     * Удаляет элемент коллекции по его ключу.
     *
     * @param key ключ элемента коллекции
     */
    public void removeKey(String key) {
        boolean f = true;
        int i = Integer.parseInt(key);
        for (Map.Entry<Integer, Product> product : catalog.getProducts().entrySet()) {
            if (product.getKey().equals(i)) {
                catalog.getProducts().remove(i);
                System.out.println("Элемент удален.");
                f = false;
            }
        }
        if (f) {
            System.out.println("Элемента с данным ключом не существует.");
        }

    }

    /**
     * Удаляет из коллекции все элементы, превышающие заданный.
     */
    public void remove_greater() throws IOException {
        System.out.println("Введите данные продукта. Все продукты, которые больше вашего (исходя из логики сравнения), будут удалены.");
        Product p = new Product();
        p.update();
        catalog.getProducts().entrySet().removeIf(entry -> entry.getValue().compareTo(p) < 0);
    }

    /**
     * Удаляет из коллекции все элементы, меньше, чем заданный.
     *
     * @throws IOException если ошибка ввода аргумента
     */
    public void remove_lower() throws IOException {
        System.out.println("Введите данные продукта. Все продукты, которые меньше вашего (исходя из логики сравнения), будут удалены.");
        Product p = new Product();
        p.update();
        catalog.getProducts().entrySet().removeIf(entry -> entry.getValue().compareTo(p) > 0);
    }

    /**
     * Удаляет из коллекции элементы, ключ которых меньше чем заданный.
     *
     * @param key введенный ключ элемента
     */
    public void remove_lower_key(String key) {
        int i = Integer.parseInt(key);
        catalog.getProducts().entrySet().removeIf(entry -> entry.getKey().compareTo(i) < 0);
        System.out.println("Элементы с ключом менше данного удалены.");
    }

    /**
     * Выводит среднее значение поля manufactureCost для всех элементов коллекции
     */
    public void average_of_manufacture_cost() {
        double sum = 0;
        for (Map.Entry<Integer, Product> product : catalog.getProducts().entrySet()) {
            sum = product.getValue().getManufactureCost() + sum;
        }
        System.out.println("Среденее значение цены производителя = " + sum / catalog.getSize());
    }

    /**
     * Выводит значение поля unitOfMeasure для всех элементов в порядке убывания
     */
    public void print_field_descending_unit_of_measure() {
        Comparator<UnitOfMeasure> unitOfMeasureComparator = new Comparator<UnitOfMeasure>() {
            @Override
            public int compare(UnitOfMeasure o1, UnitOfMeasure o2) {
                return o1.compareTo(o2);
            }
        };
        List<UnitOfMeasure> unitOfMeasures = new ArrayList<>();
        for (Map.Entry<Integer, Product> productEntry : catalog.getProducts().entrySet()) {
            unitOfMeasures.add(productEntry.getValue().getUnitOfMeasure());
        }
        Collections.sort(unitOfMeasures, unitOfMeasureComparator);
        for (UnitOfMeasure u : unitOfMeasures) {
            System.out.println(u);
        }
    }

    /**
     * Группирует элементы коллекции по значению поля partNumber, вывести количество элементов в каждой группе
     */
    public void group_counting_by_part_number() {
        Map<String, List<Product>> productsByPartNumber1 = catalog.getProducts().values().stream().collect(Collectors.groupingBy(Product::getPartNumber));
        Map<String, Long> productsByPartNumber2 = catalog.getProducts().values().stream().collect(Collectors.groupingBy(Product::getPartNumber, Collectors.counting()));
        for (Map.Entry<String, List<Product>> item1 : productsByPartNumber1.entrySet()) {
            for (Map.Entry<String, Long> item2 : productsByPartNumber2.entrySet()) {
                if (item2.getKey() == item1.getKey())
                    System.out.println(item1.getKey() + " - " + item2.getValue() + ":");
            }
            for (Product product : item1.getValue()) {
                System.out.println(product.getName());
            }
            System.out.println();
        }
    }

}
