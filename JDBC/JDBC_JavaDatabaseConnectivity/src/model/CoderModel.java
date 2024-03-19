package model;

import database.CRUD;
import database.ConfigDB;
import entity.Coder;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoderModel implements CRUD {
    @Override
    public Object insert(Object object) {
        // 1. Abrir la conexion
        Connection objConnection = ConfigDB.openConnection();

        //2. Castear al objeto
        Coder objCoder = (Coder) object;

        try {
            // 3. Crear el Sql

            String sql = "INSERT INTO coder(name,age,clan) VALUES(?,?,?);";

            //4. Preparar el Statement
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            //5. Asignar los signos de interrogacion
            objPrepare.setString(1,objCoder.getName());
            objPrepare.setInt(2,objCoder.getAge());
            objPrepare.setString(3,objCoder.getClan());

            //6. Ejecutamos el Query
            objPrepare.execute();

            //7. Obtener el resultado
            ResultSet objResult = objPrepare.getGeneratedKeys();

            while(objResult.next()){
                objCoder.setId(objResult.getInt(1));
            }

            // 8. Cerramos el prepareStatement
            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Coder insertion was successful");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error adding coder " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return objCoder;
    }

    @Override
    public boolean update(Object object) {
        return false;
    }

    @Override
    public boolean delete(Object object) {
        return false;
    }

    @Override
    public List<Object> findAll() {

        // Abrimos la conexion
        Connection objConnection = ConfigDB.openConnection();

        // 2. Iniciar la lista donde se guardan los registros que devuelve la DB
        List<Object> listCoder = new ArrayList<>();

        // Se empieza el manejo de errores
        try {
            // 3. Se escribe la sentencia SQL
            String sql = "SELECT * FROM coder ORDER BY coder.id ASC;";

            // 4. Utilizar PrepareStatement
            PreparedStatement objPrepareStatement = (PreparedStatement) objConnection.prepareStatement(sql);

            // 5 Ejecutar el Query o el prepareStatement
            ResultSet objResult = objPrepareStatement.executeQuery();

            //6. Obtener los resultados

            while (objResult.next()){
                Coder objCoder = new Coder();

                objCoder.setId(objResult.getInt("id"));
                objCoder.setName(objResult.getString("name"));
                objCoder.setAge(objResult.getInt("age"));
                objCoder.setClan(objResult.getString("clan"));

                // Finalmente agregar el coder a la lista
                listCoder.add(objCoder);
            }

        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data acquosoton ERROR");
        }

        // 7. Cerramos la conexion
        ConfigDB.closeConnection();

        return listCoder;

    }

    @Override
    public Object findById(int id) {
        return null;
    }
}