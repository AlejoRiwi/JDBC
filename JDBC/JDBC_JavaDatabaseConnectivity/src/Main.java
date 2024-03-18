import database.ConfigDB;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        System.out.println("Probando la conexion!");
        ConfigDB.openConnection()
    }
}