package Lab5;

import java.io.*;

public class Commander {

    private ConsoleApplication manager;
    private String[] lastCommand;


    public Commander(ConsoleApplication manager) {
        this.manager = manager;
    }

    public void executeCommand(String s) throws IOException {
        if (!s.equals("exit")) {
            lastCommand = s.trim().split(" ", 2);
            try {
                switch (lastCommand[0]) {
                    case "":
                        break;
                    case "info":
                        manager.info();
                        break;
                    case "show":
                        manager.show();
                        break;
                    case "insert":
                        manager.insert(lastCommand[1]);
                        break;
                    case "update":
                        manager.update(lastCommand[1]);
                        break;
                    case "remove_key":
                        manager.removeKey(lastCommand[1]);
                        break;
                    case "clear":
                        manager.clear();
                        break;
                    case "save":
                        manager.save();
                        break;
                    case "execute_script":
                        execute_script(lastCommand[1]);
                        break;
                    case "remove_greater":
                        manager.remove_greater();
                        break;
                    case "remove_lower":
                        manager.remove_lower();
                        break;
                    case "remove_lower_key":
                        manager.remove_lower_key(lastCommand[1]);
                        break;
                    case "average_of_manufacture_cost":
                        manager.average_of_manufacture_cost();
                        break;
                    case "group_counting_by_part_number":
                        manager.group_counting_by_part_number();
                        break;
                    case "help":
                        manager.help();
                        break;
                    case "print_field_descending_unit_of_measure":
                        manager.print_field_descending_unit_of_measure();
                        break;
                    default:
                        System.out.println("Неопознанная команда. Наберите 'help' для справки.");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Отсутствует аргумент.");
            }
            catch(NumberFormatException e){
                System.out.println("Данные введены некорректно. Повторите ввод.");
            }
        } else System.exit(0);
    }

    /**
     * Выполняет скрипт.
     * @param path расположение скрипта
     */
    public void execute_script(String path) throws IOException {
        BufferedReader reader = null;
        try {
            FileReader fileReader = new FileReader(path);
            reader = new BufferedReader(fileReader);
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        }
        String task;
        try {
            while ((task = reader.readLine()) != null) {
                if (!task.equals("execute_script " + path)) {
                    System.out.println(task);
                    executeCommand(task);
                } else {
                    System.out.println("Вы вызвали скрипт внутри самого скрипта.");
                    System.exit(1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}