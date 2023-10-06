package com.example.a2doparcial.Negocio;

import android.content.Context;

import com.example.a2doparcial.Dato.DRepartidor;
import com.example.a2doparcial.Dato.EliminarTemplate;

import java.util.ArrayList;

public class NRepartidor {

    DRepartidor dr;
    EliminarTemplate drt;

    public  NRepartidor(Context contexto){
        this.dr=new DRepartidor(contexto);
        this.drt=new DRepartidor(contexto);
    }

    public long agregar(String nombre,String telefono, String placa){
        long i=0;
        if(!nombre.isEmpty() && !telefono.isEmpty() && !placa.isEmpty()) {
            i = dr.agregar(nombre,telefono,placa);
        }
        return i;
    }

    public ArrayList<DRepartidor> getListaRepartidor(){
        return dr.getListaRepartidores();
    }

    public DRepartidor getRepartidor(int id){
        return dr.getRepartidor(id);
    }

    public boolean editarRepartidor(int id,String nombre,String telefono,String placa){
        boolean b=false;
        if(!nombre.isEmpty() && !telefono.isEmpty() && !placa.isEmpty()){
            return dr.editarRepartidor(id,nombre,telefono,placa);
        }
        return b;
    }

    public boolean eliminarRepartidor(int id){
        return drt.eliminarTupla(id);
    }
}

