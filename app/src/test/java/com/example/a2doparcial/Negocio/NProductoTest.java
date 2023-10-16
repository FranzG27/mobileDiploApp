package com.example.a2doparcial.Negocio;

import static org.junit.Assert.*;

import org.junit.Test;

public class NProductoTest {
    @Test
    public void crearValido(){
        String nombre = "Club social clasica";
        String precio = "2.5";

        NProducto npObj = new NProducto();
        assertTrue(npObj.validacionesAgregar(nombre, precio));
    }

    @Test
    public void crearInvalidoNombreVacio(){
        String nombre = "";
        String precio = "2.5";

        NProducto npObj = new NProducto();
        assertFalse(npObj.validacionesAgregar(nombre, precio));
    }

    @Test
    public void crearInvalidoNombreNulo(){
        String nombre = null;
        String precio = "2.5";

        NProducto npObj = new NProducto();
        assertFalse(npObj.validacionesAgregar(nombre, precio));
    }

    @Test
    public void crearInvalidoPrecioVacio(){
        String nombre = "Club social clasica";
        String precio = "";

        NProducto npObj = new NProducto();
        assertFalse(npObj.validacionesAgregar(nombre, precio));
    }

    @Test
    public void crearInvalidoPrecioNulo(){
        String nombre = "Club social clasica";
        String precio = null;

        NProducto npObj = new NProducto();
        assertFalse(npObj.validacionesAgregar(nombre, precio));
    }

    @Test
    public void crearInvalidoPrecioInvalido(){
        String nombre = "Club social clasica";
        String precio = "16.66a0";

        NProducto npObj = new NProducto();
        assertFalse(npObj.validacionesAgregar(nombre, precio));
    }

    @Test
    public void editarValido(){
        String nombre = "Club social clasica";
        String precio = "3";

        NProducto npObj = new NProducto();
        assertTrue(npObj.validacionesEditar(nombre, precio));
    }

    @Test
    public void editarInValidoNombreVacio(){
        String nombre = "";
        String precio = "3";

        NProducto npObj = new NProducto();
        assertFalse(npObj.validacionesEditar(nombre, precio));
    }

    @Test
    public void editarInValidoNombreNulo(){
        String nombre = null;
        String precio = "3";

        NProducto npObj = new NProducto();
        assertFalse(npObj.validacionesEditar(nombre, precio));
    }

    @Test
    public void editarInValidoPrecioInvalido(){
        String nombre = "Chocosoda";
        String precio = "";

        NProducto npObj = new NProducto();
        assertFalse(npObj.validacionesEditar(nombre, precio));
    }

    @Test
    public void editarInValidoPrecioNulo(){
        String nombre = "Chocosoda";
        String precio = null;

        NProducto npObj = new NProducto();
        assertFalse(npObj.validacionesEditar(nombre, precio));
    }
}