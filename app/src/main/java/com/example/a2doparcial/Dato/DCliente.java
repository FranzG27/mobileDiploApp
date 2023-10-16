package com.example.a2doparcial.Dato;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import androidx.annotation.NonNull;

import com.example.a2doparcial.Conexion.Conexion;

import java.util.ArrayList;

public class DCliente extends EliminarTemplate{
    Conexion BaseDatos;
    private SQLiteDatabase db;
    private String nombreTabla;

    public int id;
    public String nombre;
    public String telefono;
    public String direccion;


    public DCliente() {

    }

    public DCliente(Context context) {
        this.BaseDatos = new Conexion(context);
        this.nombreTabla = "CLIENTE";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion =direccion ;
    }

    /**
     * Función que sirve para llenar el Spinner
     */
    @NonNull
    @Override
    public String toString() {
        return nombre;
    }
  


    /**
     * Función para agregar un Cliente a la Base de Datos
     */
  
    public long agregarCliente(String nombre,String telefono, String direccion){
        long i=0;
        try{
            db = BaseDatos.getWritableDatabase();

            if (db != null) {
                ContentValues values = new ContentValues();
                values.put("nombre", nombre);//copy
                values.put("telefono", telefono);
                values.put("direccion", direccion);

                i= db.insert(nombreTabla, null, values);
                db.close();
            }

        }catch (Exception e){
            e.toString();
        }

        return i;
    }

    /**
     * Función para Listar los Clientes que se encuentran en la Base de Datos
     */
    public ArrayList<DCliente> getListaClientes(){
        db = BaseDatos.getWritableDatabase();
        ArrayList<DCliente> listaCliente=new ArrayList<>();
        DCliente cliente=null;
        Cursor cursorCliente=null;

        cursorCliente=db.rawQuery("SELECT * FROM "+nombreTabla,null);

        if (cursorCliente.moveToFirst()){
            do {
                cliente=new DCliente();
                cliente.setId(cursorCliente.getInt(0));
                cliente.setNombre(cursorCliente.getString(1));
                cliente.setTelefono(cursorCliente.getString(2));
                cliente.setDireccion(cursorCliente.getString(3));
                listaCliente.add(cliente);

            }while (cursorCliente.moveToNext());
        }
        cursorCliente.close();
        db.close();                     
        return listaCliente;

    }
    /**
     * Función para traer los datos de un Cliente específico
     */
    public DCliente getCliente(int id){
        db = BaseDatos.getWritableDatabase();
        DCliente cliente=null;
        Cursor cursorCliente=null;

        cursorCliente=db.rawQuery("SELECT * FROM "+nombreTabla + " WHERE ID =" + id + " LIMIT 1",null);

        if (cursorCliente.moveToFirst()){
            cliente=new DCliente();
            cliente.setId(cursorCliente.getInt(0));
            cliente.setNombre(cursorCliente.getString(1));
            cliente.setTelefono(cursorCliente.getString(2));
            cliente.setDireccion(cursorCliente.getString(3));
        }
        cursorCliente.close();
        db.close();
        return cliente;

    }


 
    /**
     * Función para editar un Cliente específico
     */
    public boolean editarCliente(int id,String nombre,String telefono,String direccion){
        boolean b=false;
        db = BaseDatos.getWritableDatabase();

        try{
            db.execSQL("UPDATE "+nombreTabla+" SET NOMBRE = '" + nombre +"' WHERE id='"+ id+ "' ");
            db.execSQL("UPDATE "+nombreTabla+" SET TELEFONO = '" + telefono +"' WHERE id='"+ id+ "' ");
            db.execSQL("UPDATE "+nombreTabla+" SET DIRECCION = '" + direccion +"' WHERE id='"+ id+ "' ");
            b=true;
        }catch (Exception e){
            e.toString();
            b=false;
        }finally {
            db.close();
        }
        return b;
    }
 
    /**
     * Operaciones para eliminar el cliente con el Patrón Template
     */
    @Override
    public String getNombreTabla(){
        return nombreTabla;
    }

    @Override
    public Conexion getBaseDatos(){
        return this.BaseDatos;
    }
    
}
