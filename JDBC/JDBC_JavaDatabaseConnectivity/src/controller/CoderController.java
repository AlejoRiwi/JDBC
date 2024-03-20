package controller;

import database.ConfigDB;
import entity.Coder;
import model.CoderModel;

import javax.swing.*;
import java.sql.Connection;

public class CoderController {

    CoderModel objCoderModel;
    public CoderController(){
        // Se crea la instancia del Model
        this.objCoderModel = new CoderModel();
    }

    // Metodo para listar todos los coder
    public void getAll(){
        String list = "--- List coders ---\n";
        // 1. Se crea la instancia de modelo

        // iteramos sobre la lista que devuelve el metodo findAll()
        for (Object obj : this.objCoderModel.findAll()){

            // Convertimos o casteamos el objeto tipo Object a un coder
            Coder objCoder = (Coder) obj;

            // Conectamos la informacion
            list += objCoder.toString() + "\n";

        }
        JOptionPane.showMessageDialog(null, list);
    }


    public void delete () {
        String listCoderString = "--- CODER LIST ---\n";
        for (Object obj : this.objCoderModel.findAll()){
            Coder objCoder = (Coder) obj;

            listCoderString += objCoder.toString() + "\n";
        }

        int confirm = 1;

        int isDelete = Integer.parseInt(JOptionPane.showInputDialog(listCoderString + "Ingresa el id del coder a eliminar"));

        Coder objCoder = (Coder) this.objCoderModel.findById(isDelete);

        if(objCoder == null){
            JOptionPane.showMessageDialog(null, "Coder no encontrado");
        } else {
            confirm = JOptionPane.showConfirmDialog(null, "Esta seguro que quieres eliminar el coder?\n" +
                    objCoder.toString());
            if (confirm == 0) {
                this.objCoderModel.delete(objCoder);
            }
        }
    }

    public void findByName() {
        String listCoder = " --- --- --- --- --- --- --- --- --- \n" +
                " --- --- --- CODER LIST --- --- --- \n" +
                " --- --- --- --- --- --- --- --- --- \n";

        String isName = JOptionPane.showInputDialog(null, "Ingresa el nombre que quieres buscar");

        Coder objCoder = (Coder) this.objCoderModel.findByName(isName);
        JOptionPane.showMessageDialog(null, listCoder + objCoder.toString());

    }

    public void create (){
        Coder objCoder = new Coder();
        String name = JOptionPane.showInputDialog("Insert name ");
        int age = Integer.parseInt(JOptionPane.showInputDialog("Insert age "));
        String clan = JOptionPane.showInputDialog("Insert clan ");

        objCoder.setName(name);
        objCoder.setAge(age);
        objCoder.setClan(clan);

        objCoder = (Coder) this.objCoderModel.insert(objCoder);

        JOptionPane.showMessageDialog(null, objCoder.toString());
    }
}
