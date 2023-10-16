package com.example.a2doparcial.Dato;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;


import com.example.a2doparcial.Conexion.Conexion;

import java.util.ArrayList;


public class DCategoria extends EliminarTemplate {
    Conexion BaseDatos;
    private SQLiteDatabase db;
    private String nombreTabla;
    public int id;
    public String nombre;

    public DCategoria() {

    }

    public DCategoria(Context context) {
        this.BaseDatos = new Conexion(context);
        this.nombreTabla = "CATEGORIA";
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

    @NonNull
    @Override
    public String toString() {
        return nombre;
    }

    /**
     * Función para agregar una Categoría a la Base de Datos
     */
    public long agregarCategoria(String nombre) {
        long i = 0;
        try {
            db = BaseDatos.getWritableDatabase();

            if (db != null) {
                ContentValues values = new ContentValues();
                values.put("nombre", nombre);

                i = db.insert(nombreTabla, null, values);
                db.close();
            }

        } catch (Exception e) {
            e.toString();
        }

        return i;
    }
    /**
     * Función para Listar las Categorías que se encuentran en la Base de Datos
     */
    public ArrayList<DCategoria> getListaCategorias() {
        db = BaseDatos.getWritableDatabase();
        ArrayList<DCategoria> listaCategoria = new ArrayList<>();
        DCategoria categoria = null;
        Cursor cursorCategoria = null;

        cursorCategoria = db.rawQuery("SELECT * FROM " + nombreTabla, null);

        if (cursorCategoria.moveToFirst()) {
            do {
                categoria = new DCategoria();
                categoria.setId(cursorCategoria.getInt(0));
                categoria.setNombre(cursorCategoria.getString(1));
                listaCategoria.add(categoria);
            } while (cursorCategoria.moveToNext());
        }
        cursorCategoria.close();
        db.close();                    
        return listaCategoria;

    }

    /**
     * Función para traer los datos de una categoría específica
     */
    public DCategoria getCategoria(int id) {
        db = BaseDatos.getWritableDatabase();
        DCategoria categoria = null;
        Cursor cursorCategoria = null;

        cursorCategoria = db.rawQuery("SELECT * FROM " + nombreTabla + " WHERE ID =" + id + " LIMIT 1", null);

        if (cursorCategoria.moveToFirst()) {
            categoria = new DCategoria();
            categoria.setId(cursorCategoria.getInt(0));
            categoria.setNombre(cursorCategoria.getString(1));
        }
        cursorCategoria.close();
        db.close();
        return categoria;

    }

    /**
     * Función para editar una categoría específica
     */
    public boolean editarCategoria(int id, String nombre) {
        boolean b = false;
        db = BaseDatos.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + nombreTabla + " SET NOMBRE = '" + nombre + "' WHERE id='" + id + "' ");
            b = true;
        } catch (Exception e) {
            e.toString();
            b = false;
        } finally {
            db.close();
        }
        return b;
    }

    /**
     * Operaciones para Eliminar la Categoría con el Patrón Template
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