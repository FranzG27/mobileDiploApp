package com.example.a2doparcial.Dato;

import java.util.ArrayList;

public interface IProducto {

    long agregar(String nombre,double precio, byte[] imagen,int id_categoria);
    ArrayList<DProducto> getListaProductos();
    DProducto getProducto(int id);
    boolean editarProducto(int id,String nombre,double precio);
    boolean eliminarProducto(int id);

}
