package com.example.a2doparcial.Dato;


import android.database.sqlite.SQLiteDatabase;

import com.example.a2doparcial.Conexion.Conexion;

public abstract class EliminarTemplate {
    Conexion BaseDatos;//=new conexion(contexto);
    private SQLiteDatabase db;
    // Metodo Template
    public boolean eliminarTupla(int id){
        BaseDatos=getBaseDatos();
        boolean b=false;
        db = BaseDatos.getWritableDatabase();

        try{
            db.execSQL("DELETE FROM " + getNombreTabla() + " WHERE ID = '" + id + "'");
            b=true;
        }catch (Exception e){
            e.toString();
            b=false;
        }finally {
            db.close();
        }
        return b;
    }
    // Primitive Operations
    public abstract String getNombreTabla();
    public abstract Conexion getBaseDatos();
}
