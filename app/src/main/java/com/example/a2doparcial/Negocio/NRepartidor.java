package com.example.a2doparcial.Negocio;

import android.content.Context;

import com.example.a2doparcial.Dato.DRepartidor;
import com.example.a2doparcial.Dato.EliminarTemplate;

import java.util.ArrayList;

public class NRepartidor {

    DRepartidor dr;
    EliminarTemplate drt;

    public NRepartidor() {
    }

    public NRepartidor(Context contexto) {
        this.dr = new DRepartidor(contexto);
        this.drt = new DRepartidor(contexto);
    }

    public boolean validacionesAgregar(String nombre, String telefono, String placa) {
        boolean resultado = true;

        if (nombre == null || nombre.isEmpty() || nombre.trim().isEmpty()) {
            resultado = false;
            return resultado;
        }

        if (telefono == null || telefono.isEmpty() || telefono.trim().isEmpty()) {
            resultado = false;
            return resultado;
        }

        try {
            int telefonoValido = Integer.parseInt(telefono);
        } catch (Exception e) {
            resultado = false;
            return resultado;
        }

        if (placa == null || placa.isEmpty() || placa.trim().isEmpty()) {
            resultado = false;
            return resultado;
        }

        return resultado;
    }

    public boolean validacionesEditar(String nombre, String telefono, String placa) {
        boolean resultado = true;

        if (nombre == null || nombre.isEmpty() || nombre.trim().isEmpty()) {
            resultado = false;
            return resultado;
        }

        if (telefono == null || telefono.isEmpty() || telefono.trim().isEmpty()) {
            resultado = false;
            return resultado;
        }

        try {
            int telefonoValido = Integer.parseInt(telefono);
        } catch (Exception e) {
            resultado = false;
            return resultado;
        }

        if (placa == null || placa.isEmpty() || placa.trim().isEmpty()) {
            resultado = false;
            return resultado;
        }

        return resultado;
    }

    public long agregar(String nombre, String telefono, String placa) {
        long i = 0;
        if (validacionesAgregar(nombre, telefono, placa)) {
            i = dr.agregar(nombre, telefono, placa);
        }
        return i;
    }

    public ArrayList<DRepartidor> getListaRepartidor() {
        return dr.getListaRepartidores();
    }

    public DRepartidor getRepartidor(int id) {
        return dr.getRepartidor(id);
    }

    public boolean editarRepartidor(int id, String nombre, String telefono, String placa) {
        boolean b = false;
        if (validacionesEditar(nombre, telefono, placa)) {
            return dr.editarRepartidor(id, nombre, telefono, placa);
        }
        return b;
    }

    public boolean eliminarRepartidor(int id) {
        return drt.eliminarTupla(id);
    }
}

