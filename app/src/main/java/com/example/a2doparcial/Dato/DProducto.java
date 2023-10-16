package com.example.a2doparcial.Dato;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.a2doparcial.Conexion.Conexion;

import java.util.ArrayList;

public class DProducto implements IProducto{

    Conexion BaseDatos;
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
        this.BaseDatos = new Conexion(context);
        this.nombreTabla = "PRODUCTO";
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



    /**
     * Función para agregar un Producto a la Base de Datos
     */  
    public long agregarProducto(String nombre,double precio,byte[] imagen ,int id_categoria){
        long i=0;
        try{
            
            db = BaseDatos.getWritableDatabase();

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
            
            e.toString();
        }
        return i;
    }
    

    /**
     * Función para Listar los Productos que se encuentran en la Base de Datos
     */
    public ArrayList<DProducto> getListaProductos(){
        db = BaseDatos.getWritableDatabase();
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
        db.close();                     
        return listaProductos;

    }

    /**
     * Función para traer los datos de un Producto específico
     */ 
    public DProducto getProducto(int id){
        db = BaseDatos.getWritableDatabase();
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
    /**
     * Función para editar un Producto específico
     */   
    public boolean editarProducto(int id,String nombre,double precio){
        boolean b=false;
        db = BaseDatos.getWritableDatabase();

        try{
            db.execSQL("UPDATE "+nombreTabla+" SET NOMBRE = '" + nombre +"' WHERE id='"+ id+ "' ");
            db.execSQL("UPDATE "+nombreTabla+" SET PRECIO = '" + precio +"' WHERE id='"+ id+ "' ");
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
     * Función para eliminar un Producto específico de la Base de Datos
     */
    public boolean eliminarProducto(int id){
        boolean b=false;
        db = BaseDatos.getWritableDatabase();

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
   

}
