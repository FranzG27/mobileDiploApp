package com.example.a2doparcial.Negocio;

import android.content.Context;

import com.example.a2doparcial.Dato.DCliente;
import com.example.a2doparcial.Dato.EliminarTemplate;

import java.util.ArrayList;

public class NCliente {
    DCliente dc;
    EliminarTemplate dct;

    public  NCliente(Context contexto){
        this.dc=new DCliente(contexto);
        this.dct=new DCliente(contexto);
    }

    public long agregar(String nombre,String telefono, String direccion){
        long i=0;
        if(!nombre.isEmpty() && !telefono.isEmpty() && !direccion.isEmpty()) {
            i = dc.agregar(nombre,telefono,direccion);
        }
        return i;
    }

    public ArrayList<DCliente> getListaClientes(){
        return dc.getListaClientes();
    }

    public DCliente getCliente(int id){
        return dc.getCliente(id);
    }

    public boolean editarClente(int id,String nombre,String telefono,String direccion){
        boolean b=false;
        if(!nombre.isEmpty() && !telefono.isEmpty() && !direccion.isEmpty()){
            return dc.editarCliente(id,nombre,telefono,direccion);
        }
        return b;
    }

    public boolean eliminarCliente(int id){

        return dct.eliminarTupla(id);
    }



}

