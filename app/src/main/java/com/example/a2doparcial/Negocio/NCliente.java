package com.example.a2doparcial.Negocio;

import android.content.Context;

import com.example.a2doparcial.Dato.DCliente;
import com.example.a2doparcial.Dato.EliminarTemplate;

import java.util.ArrayList;

public class NCliente {
    DCliente dc;
    EliminarTemplate dct;

    public NCliente(){}

    public  NCliente(Context contexto){
        this.dc=new DCliente(contexto);
        this.dct=new DCliente(contexto);
    }

    public boolean validacionesAgregar(String nombre, String telefono, String direccion){
        boolean resultado = true;

        if(nombre == null || nombre.isEmpty() || nombre.trim().isEmpty()){
            resultado = false;
            return resultado;
        }

        if(direccion == null || direccion.isEmpty() || direccion.trim().isEmpty()){
            resultado = false;
            return resultado;
        }

        if(telefono == null || telefono.isEmpty() || telefono.trim().isEmpty()){
            resultado = false;
            return resultado;
        }

        return resultado;
    }

    public boolean validacionesEditar(String nombre, String telefono, String direccion){
        boolean resultado = true;

        if(nombre == null || nombre.isEmpty() || nombre.trim().isEmpty()){
            resultado = false;
            return resultado;
        }

        if(direccion == null || direccion.isEmpty() || direccion.trim().isEmpty()){
            resultado = false;
            return resultado;
        }

        if(telefono == null || telefono.isEmpty() || telefono.trim().isEmpty()){
            resultado = false;
            return resultado;
        }

        return resultado;
    }

    public long agregar(String nombre,String telefono, String direccion){
        long i=0;
        if(validacionesAgregar(nombre, telefono, direccion)) {
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
        if(validacionesEditar(nombre, telefono, direccion)){
            return dc.editarCliente(id,nombre,telefono,direccion);
        }
        return b;
    }

    public boolean eliminarCliente(int id){

        return dct.eliminarTupla(id);
    }



}

