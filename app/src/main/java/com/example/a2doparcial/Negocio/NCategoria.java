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
        this.dct=new DCategoria(contexto); 
    }

    public long agregarCategoria(String nombre){
        long i=0;
        if(!nombre.isEmpty()) {
           
            i = dc.agregarCategoria(nombre);
            if(i==0){
                
            }
            
        }
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
       

        return dct.eliminarTupla(id);
    }

}
