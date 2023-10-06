package com.example.a2doparcial.Presentacion;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.telephony.SmsManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.a2doparcial.Dato.DCliente;
import com.example.a2doparcial.Dato.DRepartidor;

public class EnviarSms implements StrategyEnviarMsg{

    private Context context;
    public EnviarSms(Context context){
        this.context=context;
    }

    @Override
    public void enviarMsg(String nombreCliente, String direccionCliente, String nroRepartidor ){
        String msj = "Cliente: " + nombreCliente + ", \nUbicacion: " + direccionCliente + ", \nTotal a Pagar: Bs.- n" ;
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(nroRepartidor, null, msj, null, null);
            Toast.makeText(context.getApplicationContext(), "SMS enviado", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(context.getApplicationContext(), "Error al enviar SMS", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}
