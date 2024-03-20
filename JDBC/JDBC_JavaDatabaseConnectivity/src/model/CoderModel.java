package model;

import database.CRUD;
import database.ConfigDB;
import entity.Coder;

import javax.swing.*;
import java.awt.*;
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
        // 1. Convertir el objeto a la entidad
        Coder objCoder = (Coder) object;

        //2. Variable boolean para medir estado de la eliminacion
        boolean idDeleted = false;

        //3. Abrir la conexion

        Connection objConnection = ConfigDB.openConnection();

        try {
            // 4. Escribir sentencia SQL
            String sql = "DELETE FROM coder WHERE id = ?;";

            //5. Preparamos el Statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //6. Asognamos el valor ?
            objPrepare.setInt(1, objCoder.getId());

            //7. Total de filas afectadas
            int filasAfectadas = objPrepare.executeUpdate();

            if (filasAfectadas > 0 ){
                idDeleted = true;
                JOptionPane.showMessageDialog(null, "Se elimino coprrectamente ");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();
        return idDeleted;
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
        //1. Abrir conexion
        Connection objConnection = ConfigDB.openConnection();
        Coder objCoder = null;

        try{
            //2. Sentencia SQL
            String sql = "SELECT * FROM coder WHERE id = ?;";

            //3. Preparo el Statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // 4. Damos valor al ?
            objPrepare.setInt(1,id);

            //5. Ejecutamos
            ResultSet objResult = objPrepare.executeQuery();

            //6. mientras haya un registro
            while(objResult.next()){
                objCoder = new Coder();
                objCoder.setId(objResult.getInt("id"));
                objCoder.setName(objResult.getString("name"));
                objCoder.setClan(objResult.getString("clan"));
                objCoder.setAge(objResult.getInt("age"));
            }




        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();

        return objCoder;
    }

    public Object findByName(Object object){
        //1. Abrir conexion
        Connection objConnection = ConfigDB.openConnection();
        Coder objCoder = null;

        try{
            //2. Sentencia SQL
            String sql = "SELECT * FROM coder WHERE coder.name = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1,"name");

            ResultSet objResult = objPrepare.executeQuery();

            while(objResult.next()){
                objCoder = new Coder();
                objCoder.setId(objResult.getInt("id"));
                objCoder.setName(objResult.getString("name"));
                objCoder.setAge(objResult.getInt("age"));
                objCoder.setClan(objResult.getString("Clan"));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return objCoder;
    }
}
