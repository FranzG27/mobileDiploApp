package com.example.a2doparcial.Presentacion;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class EnviarWhatsaap implements StrategyEnviarMsg {

    private Context context;

    public EnviarWhatsaap(Context context){
        this.context=context;
    }

    @Override
    public void enviarMsg(String nombreCliente, String direccionCliente, String nroRepartidor ){
        String msj = "Cliente: " + nombreCliente + ", \nUbicacion: " + direccionCliente + ", \nTotal a Pagar: Bs.- n" ;
        String numeroTel = nroRepartidor;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String uri = "whatsapp://send?phone=" + numeroTel + "&text=" + msj;
        intent.setData(Uri.parse(uri));
        context.startActivity(intent);
    }
}
