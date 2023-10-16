package com.example.a2doparcial.Negocio;

import static org.junit.Assert.*;

import org.junit.Test;

public class NClienteTest {
    @Test
    public void agregarValido(){
        String nombre = "Juan Garcia";
        String telefono = "79829182";
        String direccion = "https://maps.app.goo.gl/r96YRiwNB9aTSEHJ9";

        NCliente ncObj = new NCliente();
        assertTrue(ncObj.validacionesAgregar(nombre, telefono, direccion));
    }

    @Test
    public void agregarInvalidoNombreVacio(){
        String nombre = "    ";
        String telefono = "79829182";
        String direccion = "https://maps.app.goo.gl/r96YRiwNB9aTSEHJ9";

        NCliente ncObj = new NCliente();
        assertFalse(ncObj.validacionesAgregar(nombre, telefono, direccion));
    }

    @Test
    public void agregarInvalidoDireccionInvalida(){
        String nombre = "Juan Garcia";
        String telefono = "79829182";
        String direccion = null;

        NCliente ncObj = new NCliente();
        assertFalse(ncObj.validacionesAgregar(nombre, telefono, direccion));
    }

    @Test
    public void agregarInvalidoTelefonoInvalido(){
        String nombre = "Juan Garcia";
        String telefono = "";
        String direccion = "https://maps.app.goo.gl/r96YRiwNB9aTSEHJ9";

        NCliente ncObj = new NCliente();
        assertFalse(ncObj.validacionesAgregar(nombre, telefono, direccion));
    }

    //EDITAR
    @Test
    public void editarValido(){
        String nombre = "Juan Garcia";
        String telefono = "79829182";
        String direccion = "https://maps.app.goo.gl/r96YRiwNB9aTSEHJ9";

        NCliente ncObj = new NCliente();
        assertTrue(ncObj.validacionesEditar(nombre, telefono, direccion));
    }

    @Test
    public void editarInvalidoNombreVacio(){
        String nombre = "    ";
        String telefono = "79829182";
        String direccion = "https://maps.app.goo.gl/r96YRiwNB9aTSEHJ9";

        NCliente ncObj = new NCliente();
        assertFalse(ncObj.validacionesEditar(nombre, telefono, direccion));
    }

    @Test
    public void editarInvalidoDireccionInvalida(){
        String nombre = "Juan Garcia";
        String telefono = "79829182";
        String direccion = null;

        NCliente ncObj = new NCliente();
        assertFalse(ncObj.validacionesEditar(nombre, telefono, direccion));
    }

    @Test
    public void editarInvalidoTelefonoInvalido(){
        String nombre = "Juan Garcia";
        String telefono = "";
        String direccion = "https://maps.app.goo.gl/r96YRiwNB9aTSEHJ9";

        NCliente ncObj = new NCliente();
        assertFalse(ncObj.validacionesEditar(nombre, telefono, direccion));
    }
}