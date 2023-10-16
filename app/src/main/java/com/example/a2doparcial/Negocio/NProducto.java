package com.example.a2doparcial.Negocio;

import android.content.Context;

import com.example.a2doparcial.Dato.DProducto;
import com.example.a2doparcial.Dato.DProductoProxy;
import com.example.a2doparcial.Dato.EliminarTemplate;

import java.util.ArrayList;

public class NProducto {
    //DProducto dp;

    DProductoProxy productoProxy;

    public NProducto() {
    }

    public NProducto(Context contexto) {
        //this.dp=new DProducto(contexto);
        this.productoProxy = new DProductoProxy(contexto);
    }

    public boolean validacionesAgregar(String nombre, String precio) {
        boolean resultado = true;

        if (nombre == null || nombre.isEmpty()) {
            resultado = false;
            return resultado;
        }

        if (precio == null || precio.isEmpty()) {
            resultado = false;
            return resultado;
        }

        try {
            double precioValido = Double.parseDouble(precio);
        } catch (Exception e) {
            resultado = false;
            return resultado;
        }

        return resultado;
    }

    //Ejercicio antes
    public long agregar(String nombre, String precio, byte[] imagen, int id_categoria) {
        long resultado = 0;
        if (validacionesAgregar(nombre, precio)) {
            resultado = productoProxy.agregar(nombre, Double.parseDouble(precio), imagen, id_categoria);
        }
        return resultado;
    }

    //Ejercicio despues
    public long agregar2(String nombre, double precio, byte[] imagen, int id_categoria) {
        return productoProxy.agregar(nombre, precio, imagen, id_categoria);
    }

    public ArrayList<DProducto> getListaProductos() {
        return productoProxy.getListaProductos();
    }

    public DProducto getProducto(int id) {
        return productoProxy.getProducto(id);
    }

    public boolean validacionesEditar(String nombre, String precio) {
        boolean resultado = true;

        if (nombre == null || nombre.isEmpty()) {
            resultado = false;
            return resultado;
        }

        if (precio == null || precio.isEmpty()) {
            resultado = false;
            return resultado;
        }

        try {
            double precioValido = Double.parseDouble(precio);
        } catch (Exception e) {
            resultado = false;
            return resultado;
        }

        return resultado;
    }

    public boolean editarProducto(int id, String nombre, String precio) {
        boolean resultado = false;
        if (validacionesEditar(nombre, precio)) {
            resultado = productoProxy.editarProducto(id, nombre, Double.parseDouble(precio));
        }

        return resultado;
    }

    public boolean eliminarProducto(int id) {

        return productoProxy.eliminarProducto(id);
    }
}
