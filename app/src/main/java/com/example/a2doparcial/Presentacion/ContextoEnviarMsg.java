package com.example.a2doparcial.Presentacion;

public class ContextoEnviarMsg {
    private StrategyEnviarMsg estrategia;


    public ContextoEnviarMsg() {
        estrategia = null;
    }


    public ContextoEnviarMsg(StrategyEnviarMsg estrategy) {
        this.estrategia = estrategy;
    }


    public void setStrategy(StrategyEnviarMsg estrategy){
        this.estrategia = estrategy;
    }



    public void enviarMensaje(String nombreCliente,String direccionCliente,String nroRepartidor){
        this.estrategia.enviarMsg(nombreCliente,direccionCliente,nroRepartidor);
    }
}
