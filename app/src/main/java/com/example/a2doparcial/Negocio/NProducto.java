package com.example.a2doparcial.Negocio;

import android.content.Context;

import com.example.a2doparcial.Dato.DProducto;
import com.example.a2doparcial.Dato.DProductoProxy;
import com.example.a2doparcial.Dato.EliminarTemplate;

import java.util.ArrayList;

public class NProducto {
    

    DProductoProxy productoProxy;

    public  NProducto(Context contexto){
        
        this.productoProxy=new DProductoProxy(contexto);
    }

    //Ejercicio antes
    public long agregarProducto(String nombre,double precio, byte[] imagen,int id_categoria){
        long resultado=productoProxy.agregarProducto(nombre,precio,imagen,id_categoria);
        return resultado;
    }

    //Ejercicio despues
    public long agregarProductoProxy(String nombre,double precio, byte[] imagen,int id_categoria){
        return productoProxy.agregarProducto(nombre, precio, imagen, id_categoria);
    }

    public ArrayList<DProducto> getListaProductos(){
        return productoProxy.getListaProductos();
    }

    public DProducto getProducto(int id){
        return productoProxy.getProducto(id);
    }

    public boolean editarProducto(int id,String nombre,double precio){
        return productoProxy.editarProducto(id, nombre, precio);
    }

    public boolean eliminarProducto(int id){

        return productoProxy.eliminarProducto(id);
    }
}
