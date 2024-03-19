import controller.CoderController;
import database.ConfigDB;

import javax.swing.*;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        System.out.println("Probando la conexion!");


        CoderController objCoderController = new CoderController();
        String option;
        do {
            option = JOptionPane.showInputDialog("1. List coders\n" +
                    "2. Insert coder\n" +
                    "3. Update coder\n" +
                    "4. Delet coder\n" +
                    "5. Get By name\n" +
                    "6. Exit\n" +
                    "Choose an option");

            switch (option){
                case "1":
                    objCoderController.getAll();
                    break;
                case "2":
                    objCoderController.create();
                    break;
            }
        }while (!option.equals("6"));

    }
}