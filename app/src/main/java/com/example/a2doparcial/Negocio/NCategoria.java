package com.example.a2doparcial.Negocio;

import android.content.Context;


import com.example.a2doparcial.Dato.DCategoria;
import com.example.a2doparcial.Dato.EliminarTemplate;

import java.util.ArrayList;

public class NCategoria {
    DCategoria dc;
    EliminarTemplate dct;

    public  NCategoria(Context contexto){
        this.dc=new DCategoria(contexto);
        this.dct=new DCategoria(contexto); // este es el objeto que reerencia al conexto
    }

    public long agregar(String nombre){
        long i=0;
        if(!nombre.isEmpty()) {
            //dc = new DCategoria(this.contexto);
            i = dc.agregar(nombre);
            if(i==0){
                // Toast.makeText(contexto, "aqui no pasa en NCategoria", Toast.LENGTH_SHORT).show();
            }
            //return i;
        }
        // return i=0;
        return i;
    }

    public ArrayList<DCategoria> getListaCategorias(){
        return dc.getListaCategorias();
    }

    public DCategoria getCategoria(int id){
        return dc.getCategoria(id);
    }

    public boolean editarCategoria(int id,String nombre){
        boolean b=false;
        if(!nombre.isEmpty()){
            return dc.editarCategoria(id,nombre);
        }
        return b;
    }

    public boolean eliminarCategoria(int id){
        //EliminarTemplate dct;
        // dct=new DCategoria();

        return dct.eliminarTupla(id);
    }

}
