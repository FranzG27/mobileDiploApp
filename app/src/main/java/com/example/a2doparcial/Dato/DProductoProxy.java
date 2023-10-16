package com.example.a2doparcial.Dato;

import android.content.Context;

import java.util.ArrayList;

public class DProductoProxy implements IProducto{

    private DProducto dp;

    public DProductoProxy(Context context){
        this.dp=new DProducto(context);
    }

    @Override
    public long agregarProducto(String nombre, double precio, byte[] imagen, int id_categoria) {
        long i=0;
        if(!nombre.isEmpty() && precio >0 && id_categoria!=0) {
            i = dp.agregarProducto(nombre,precio,imagen,id_categoria);
        }
        return i;
    }

    @Override
    public ArrayList<DProducto> getListaProductos() {
        return dp.getListaProductos();
    }

    @Override
    public DProducto getProducto(int id) {
        if(id>0){
            return dp.getProducto(id);
        }else{
            return null;
        }
    }

    @Override
    public boolean editarProducto(int id, String nombre, double precio) {
        boolean b=false;

        if( (id>0) && !nombre.isEmpty() && precio >0) {
            b = dp.editarProducto(id,nombre,precio);
        }
        return b;
    }

    @Override
    public boolean eliminarProducto(int id) {
        boolean b=false;
        if(id>0){
            b=dp.eliminarProducto(id);
        }
        return b;
    }

}
