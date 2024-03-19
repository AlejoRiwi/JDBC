package database;


import java.util.List;

public interface CRUD {
    /*
        Se declaran los metodos unicamente, pero no hay logica en ellos,
        En este caso solo vamos a devolver el objeto Object

        OBJECT = es una clase generica para los objetos donde cualquier objeto de JAVA puede ser
        un OBject
     */
    public Object insert(Object object);

    public boolean update(Object object);

    public boolean delete (Object object);

    public List<Object> findAll();

    public Object findById(int id);
}
