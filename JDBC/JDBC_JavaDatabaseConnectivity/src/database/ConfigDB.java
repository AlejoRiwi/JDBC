package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {
    static Connection objConnection = null;

    // Metodo para abrir la conexion entre JAVA  y la base de datos
    public static Connection openConnection(){
        try {

            // class.forname Permite implementar el Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Se crean las variables para las credenciales a la DB
            String url = "jdbc:mysql://localhost:3306/_01_jdbc";
            String user = "root";
            String password = "Rlwl2023.";

            // para establecer la coneccion
            objConnection = (Connection) DriverManager.getConnection(url,user, password);
            System.out.println("Conexion establedida sin errores !!!! ");

        }catch (ClassNotFoundException e){
            System.out.println("Error, Driver no instalado");
        }catch (SQLException e) {
            System.out.println("Error >> No se pudo establecer coneccion con la base de datos");
        }

        return objConnection;
    }

    public static void closeConnection (){
        try {

            //Si hay una coneccion activa la cerramos
            if (objConnection != null) objConnection.close();
        } catch (SQLException e){
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
