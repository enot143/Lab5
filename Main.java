package Lab5;

import java.io.*;
import java.util.Scanner;

/**
 * @author Stenina Arina
 */

public class Main {
    public static void main(String[] args) throws IOException {
        String userCommand = "";
        Commander commander = new Commander(new ConsoleApplication(System.getenv("FilePath")));
        try(Scanner commandReader = new Scanner(System.in);){
            while (true){
                commander.executeCommand(userCommand);
               userCommand = commandReader.nextLine();
            }

        }
    }
}