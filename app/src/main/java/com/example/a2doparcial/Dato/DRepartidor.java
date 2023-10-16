package com.example.a2doparcial.Dato;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import com.example.a2doparcial.Conexion.Conexion;

import java.util.ArrayList;

public class DRepartidor extends EliminarTemplate{
    Conexion BaseDatos;
    private SQLiteDatabase db;
    private String nombreTabla;

    public int id;
    public String nombre;
    public String telefono;
    public String placa;


    public DRepartidor() {

    }

    public DRepartidor(Context context) {
        this.BaseDatos = new Conexion(context);
        this.nombreTabla = "REPARTIDOR";
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
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa =placa ;
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
     * Función que sirve para agregar un Repartidor a la Base de Datos
     */
    public long agregarRepartidor(String nombre,String telefono, String placa){
        long i=0;
        try{
            db = BaseDatos.getWritableDatabase();

            if (db != null) {
                ContentValues values = new ContentValues();
                values.put("nombre", nombre);
                values.put("telefono", telefono);
                values.put("placa", placa);

                i= db.insert(nombreTabla, null, values);
                db.close();
            }

        }catch (Exception e){
            e.toString();
        }

        return i;
    }
    /**
     * Función que sirve para listar los Repartidores que se encuentran dentro de la Base de Datos
     */
    public ArrayList<DRepartidor> getListaRepartidores(){
        db = BaseDatos.getWritableDatabase();
        ArrayList<DRepartidor> listaRepartidor=new ArrayList<>();
        DRepartidor repartidor=null;
        Cursor cursorRepartidor=null;

        cursorRepartidor=db.rawQuery("SELECT * FROM "+nombreTabla,null);

        if (cursorRepartidor.moveToFirst()){
            do {
                repartidor=new DRepartidor();
                repartidor.setId(cursorRepartidor.getInt(0));
                repartidor.setNombre(cursorRepartidor.getString(1));
                repartidor.setTelefono(cursorRepartidor.getString(2));
                repartidor.setPlaca(cursorRepartidor.getString(3));
                listaRepartidor.add(repartidor);

            }while (cursorRepartidor.moveToNext());
        }
        cursorRepartidor.close();
        db.close();                   
        return listaRepartidor;

    }
    /**
     * Función que sirve para devolver los datos de un Repartidor en específico
     */
      public DRepartidor getRepartidor(int id){
        db = BaseDatos.getWritableDatabase();
        DRepartidor repartidor=null;
        Cursor cursorRepartidor=null;

        cursorRepartidor=db.rawQuery("SELECT * FROM "+nombreTabla + " WHERE ID =" + id + " LIMIT 1",null);

        if (cursorRepartidor.moveToFirst()){
            repartidor=new DRepartidor();
            repartidor.setId(cursorRepartidor.getInt(0));
            repartidor.setNombre(cursorRepartidor.getString(1));
            repartidor.setTelefono(cursorRepartidor.getString(2));
            repartidor.setPlaca(cursorRepartidor.getString(3));
        }
        cursorRepartidor.close();
        db.close();
        return repartidor;

    }
    /**
     * Función que sirve para Editar los datos de un Repartidor específico
     */
    public boolean editarRepartidor(int id,String nombre,String telefono,String placa){
        boolean b=false;
        db = BaseDatos.getWritableDatabase();

        try{
            db.execSQL("UPDATE "+nombreTabla+" SET NOMBRE = '" + nombre +"' WHERE id='"+ id+ "' ");
            db.execSQL("UPDATE "+nombreTabla+" SET TELEFONO = '" + telefono +"' WHERE id='"+ id+ "' ");
            db.execSQL("UPDATE "+nombreTabla+" SET PLACA = '" + placa +"' WHERE id='"+ id+ "' ");
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
         * Operaciones para eliminar el Repartidor con el Patron Template
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
