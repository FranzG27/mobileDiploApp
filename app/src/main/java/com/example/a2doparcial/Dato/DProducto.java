package com.example.a2doparcial.Dato;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.a2doparcial.Conexion.Conexion;

import java.util.ArrayList;

public class DProducto implements IProducto{

    Conexion baseDatos;//=new conexion(contexto);
    private SQLiteDatabase db;
    private String nombreTabla;

    public int id;
    public String nombre;
    public double precio;
    public byte[] imagen;
    public int id_categoria;


    public DProducto() {

    }

    public DProducto(Context context) {
        this.baseDatos = new Conexion(context);
        this.nombreTabla = "PRODUCTO";
    }

    //getters y setters
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria =id_categoria;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }



    //=================Metodo para agregar productos a la BD=========================
    //Despues de aplicar comentarios
    public long agregar(String nombre,double precio,byte[] imagen ,int id_categoria){
        long i=0;
        try{
            //funcion para conseguir la coneccion a la bd
            db = baseDatos.getWritableDatabase();

            if (db != null) {
                ContentValues values = new ContentValues();
                values.put("nombre", nombre);
                values.put("precio", precio);
                values.put("imagen", imagen);
                values.put("id_categoria", id_categoria);

                i= db.insert(nombreTabla, null, values);
                db.close();
            }

        }catch (Exception e){
            //funcion que nos devolvera un error por si ocurre alguno
            e.toString();
        }
        return i;
    }
    //=================================================================

    //=====================MOSTRAR PRODUCTOS===========================
    public ArrayList<DProducto> getListaProductos(){
        db = baseDatos.getWritableDatabase();
        ArrayList<DProducto> listaProductos=new ArrayList<>();
        DProducto producto=null;
        Cursor cursorProducto=null;

        cursorProducto=db.rawQuery("SELECT * FROM "+nombreTabla,null);

        if (cursorProducto.moveToFirst()){
            do {
                producto=new DProducto();
                producto.setId(cursorProducto.getInt(0));
                producto.setNombre(cursorProducto.getString(1));
                producto.setPrecio(cursorProducto.getDouble(2));
                producto.setImagen(cursorProducto.getBlob(3));
                producto.setId_categoria(cursorProducto.getInt(4));
                listaProductos.add(producto);

            }while (cursorProducto.moveToNext());
        }
        cursorProducto.close();
        db.close();                     //aqui puede ser el error
        return listaProductos;

    }

    //================================================================

    //========================Ver 1 Producto===========================
    public DProducto getProducto(int id){
        db = baseDatos.getWritableDatabase();
        DProducto producto=null;
        Cursor cursorProducto=null;

        cursorProducto=db.rawQuery("SELECT * FROM "+nombreTabla + " WHERE ID =" + id + " LIMIT 1",null);

        if (cursorProducto.moveToFirst()){
            producto=new DProducto();
            producto.setId(cursorProducto.getInt(0));
            producto.setNombre(cursorProducto.getString(1));
            producto.setPrecio(cursorProducto.getDouble(2));
            producto.setImagen(cursorProducto.getBlob(3));
            producto.setId_categoria(cursorProducto.getInt(4));
        }
        cursorProducto.close();
        db.close();
        return producto;

    }
    //================================================================

    //===========================EDITAR===============================

    public boolean editarProducto(int id,String nombre,double precio){
        boolean b=false;
        db = baseDatos.getWritableDatabase();

        try{
            db.execSQL("UPDATE "+nombreTabla+" SET NOMBRE = '" + nombre +"' WHERE id='"+ id+ "' ");
            db.execSQL("UPDATE "+nombreTabla+" SET PRECIO = '" + precio +"' WHERE id='"+ id+ "' ");
            //db.execSQL("UPDATE "+nombreTabla+" SET ID_CATEGORIA = '" + id_categoria +"' WHERE id='"+ id+ "' ");
            b=true;
        }catch (Exception e){
            e.toString();
            b=false;
        }finally {
            db.close();
        }
        return b;
    }
    //================================================================


    //======================ELIMINAR==================================
    public boolean eliminarProducto(int id){
        boolean b=false;
        db = baseDatos.getWritableDatabase();

        try{
            db.execSQL("DELETE FROM " + nombreTabla + " WHERE ID = '" + id + "'");
            b=true;
        }catch (Exception e){
            e.toString();
            b=false;
        }finally {
            db.close();
        }
        return b;
    }
    //================================================================

}
