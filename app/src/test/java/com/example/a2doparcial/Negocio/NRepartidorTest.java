package com.example.a2doparcial.Negocio;

import static org.junit.Assert.*;

import org.junit.Test;

public class NRepartidorTest {
    @Test
    public  void agregarValido(){
        String nombre = "Julio Cesar";
        String telefono = "77192789";
        String placa = "AXC-201";

        NRepartidor nrObj = new NRepartidor();
        assertTrue(nrObj.validacionesAgregar(nombre, telefono, placa));
    }

    @Test
    public  void agregarInvalidoNombreInvalido(){
        String nombre = "";
        String telefono = "70929092";
        String placa = "AXC-201";

        NRepartidor nrObj = new NRepartidor();
        assertFalse(nrObj.validacionesAgregar(nombre, telefono, placa));
    }

    @Test
    public  void agregarInvalidoTelefonoInvalidoVacio(){
        String nombre = "Julio Cesar";
        String telefono = "   ";
        String placa = "AXC-201";

        NRepartidor nrObj = new NRepartidor();
        assertFalse(nrObj.validacionesAgregar(nombre, telefono, placa));
    }

    @Test
    public  void agregarInvalidoTelefonoInvalidoNoNumerico(){
        String nombre = "Julio Cesar";
        String telefono = "7012mm21";
        String placa = "AXC-201";

        NRepartidor nrObj = new NRepartidor();
        assertFalse(nrObj.validacionesAgregar(nombre, telefono, placa));
    }
}