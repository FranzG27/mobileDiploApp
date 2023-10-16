package com.example.a2doparcial.Negocio;

import static org.junit.Assert.*;

import org.junit.Test;

public class NCategoriaTest {
    @Test
    public void agregarValido(){
        String nombre = "SALADITO";

        NCategoria ncObj = new NCategoria();
        assertTrue(ncObj.agregarValidaciones(nombre));
    }

    @Test
    public void agregarInvalidoVacio(){
        String nombre = "";

        NCategoria ncObj = new NCategoria();
        assertFalse(ncObj.agregarValidaciones(nombre));
    }

    @Test
    public void editarValido(){
        String nombre = "GALLETAS CHOCOLATE";

        NCategoria ncObj = new NCategoria();
        assertTrue(ncObj.editarValidaciones(nombre));
    }

    @Test
    public void editarValidoInvalidoVacio(){
        String nombre = "";

        NCategoria ncObj = new NCategoria();
        assertFalse(ncObj.editarValidaciones(nombre));
    }

    @Test
    public void editarValidoInvalidoNulo(){
        String nombre = null;

        NCategoria ncObj = new NCategoria();
        assertFalse(ncObj.editarValidaciones(nombre));
    }
}