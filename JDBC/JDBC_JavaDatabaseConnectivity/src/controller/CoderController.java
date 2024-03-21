package controller;

import database.ConfigDB;
import entity.Coder;
import model.CoderModel;

import javax.swing.*;
import java.sql.Connection;
import java.util.List;

public class CoderController {

    CoderModel objCoderModel;
    public CoderController(){
        // Se crea la instancia del Model
        this.objCoderModel = new CoderModel();
    }

    // Metodo para listar todos los coder
    public void getAll(){
        String list = this.getAll(this.objCoderModel.findAll());

        JOptionPane.showMessageDialog(null, list);
    }

    // Se sobre escribe el metodo GETALL para hacer imprimir los Objetos
    public String getAll (List<Object> listObject){
        String list = "--- List coders ---\n";
        for (Object obj : listObject){

            // Convertimos o casteamos el objeto tipo Object a un coder
            Coder objCoder = (Coder) obj;

            // Conectamos la informacion
            list += objCoder.toString() + "\n";
        }
        return list;
    }

    public void delete () {
        String listCoderString = this.getAll(this.objCoderModel.findAll());

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
        String listCoder = " --- --- --- CODER LIST --- --- --- \n";

        String isName = JOptionPane.showInputDialog(null, "Ingresa el nombre que quieres buscar");

        String list  = this.getAll(this.objCoderModel.findByName(isName));

        JOptionPane.showMessageDialog(null,list);
    }

    public void update(Object objeto) {
        getAll();
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

    public void update(){
        // Listamos
        String listCoders = this.getAll(this.objCoderModel.findAll());

        // Pedimos el ID y verificar
        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingresa el Id del usuario"));

        Coder objCoder = (Coder) this.objCoderModel.findById(idUpdate);

        if(objCoder == null){
            JOptionPane.showMessageDialog(null,"El usuario no existe");
        } else {
            String newName = JOptionPane.showInputDialog(null, "Ingresa el nombre actualizado",objCoder.getName());
            int newAge = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa la edad actualizada",String.valueOf(objCoder.getAge())));
            String newClan = JOptionPane.showInputDialog(null, "Ingresa el clan actualizado", objCoder.getClan());
            objCoder.setName(newName);
            objCoder.setAge(newAge);
            objCoder.setClan(newClan);
            this.objCoderModel.update(objCoder);
        }
    }
}
