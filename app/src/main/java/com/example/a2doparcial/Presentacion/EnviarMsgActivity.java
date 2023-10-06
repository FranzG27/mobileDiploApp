package com.example.a2doparcial.Presentacion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.a2doparcial.Dato.DCliente;
import com.example.a2doparcial.Dato.DRepartidor;
import com.example.a2doparcial.Negocio.NCliente;
import com.example.a2doparcial.Negocio.NRepartidor;
import com.example.a2doparcial.R;

import android.telephony.SmsManager;
import android.widget.Toast;


public class EnviarMsgActivity extends AppCompatActivity {

    private ContextoEnviarMsg estrategiaMsg;
    private static final int PERMISSION_REQUEST_CODE = 1;

    Button btnHomeEnviarMsg,btnEnviarWpp,btnEnviarSms;
    Spinner spnCliente,spnRepartidor;
    int idCliente,idRepartidor;
    String nombreCliente,direccionCliente,nroRepartidor;
    NCliente nc;
    NRepartidor nr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar_msg);
        nc= new NCliente(EnviarMsgActivity.this);
        nr=new NRepartidor(EnviarMsgActivity.this);
        estrategiaMsg=new ContextoEnviarMsg();

        btnHomeEnviarMsg=findViewById(R.id.btnHomeEnviarMsg);
        btnEnviarWpp=findViewById(R.id.btnEnviarWpp);
        btnEnviarSms=findViewById(R.id.btnEnviarSms);

        spnCliente=findViewById(R.id.spnCliente);
        llenarSpinerCliente();

        spnRepartidor=findViewById(R.id.spnRepartidor);
        llenarSpinerRepartidor();

        //Boton Home
        btnHomeEnviarMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

        //Boton Enviar Whatsaap
        btnEnviarWpp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //estrategiaMsg= new EnviarWhatsaap(EnviarMsgActivity.this);
                estrategiaMsg.setStrategy(new EnviarWhatsaap(EnviarMsgActivity.this));
                DCliente cliente=getDatosCliente(idCliente);
                DRepartidor repartidor=getDatosRepartidor(idRepartidor);
                nombreCliente=cliente.getNombre();
                direccionCliente=cliente.getDireccion();
                nroRepartidor=repartidor.getTelefono();
                estrategiaMsg.enviarMensaje(nombreCliente,direccionCliente,nroRepartidor);
               // estrategiaMsg.enviarMsg(nombreCliente,direccionCliente,nroRepartidor);
               // enviarMsgActual();
            }
        });


        //Boton Enviar SMS
        btnEnviarSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //estrategiaMsg= new EnviarSms(EnviarMsgActivity.this);
                estrategiaMsg.setStrategy(new EnviarSms(EnviarMsgActivity.this));
                if (checkPermission()) {
                    DCliente cliente=getDatosCliente(idCliente);
                    DRepartidor repartidor=getDatosRepartidor(idRepartidor);
                    nombreCliente=cliente.getNombre();
                    direccionCliente=cliente.getDireccion();
                    nroRepartidor=repartidor.getTelefono();
                    estrategiaMsg.enviarMensaje(nombreCliente,direccionCliente,nroRepartidor);
                    //estrategiaMsg.enviarMsg(nombreCliente,direccionCliente,nroRepartidor);
                    //enviarMsgActual();
                } else {
                    requestPermission();
                }
            }
        });
        //===================================================

    }


    private void llenarSpinerCliente(){
        ArrayAdapter<DCliente> adaptador= new ArrayAdapter<DCliente>(EnviarMsgActivity.this,
                android.R.layout.simple_spinner_item, nc.getListaClientes());

        adaptador.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spnCliente.setAdapter(adaptador);

        spnCliente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                idCliente=((DCliente)adapterView.getSelectedItem()).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void llenarSpinerRepartidor(){
        ArrayAdapter<DRepartidor> adaptador= new ArrayAdapter<DRepartidor>(EnviarMsgActivity.this,
                android.R.layout.simple_spinner_item, nr.getListaRepartidor());

        adaptador.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spnRepartidor.setAdapter(adaptador);

        spnRepartidor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                idRepartidor=((DRepartidor)adapterView.getSelectedItem()).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    //==============Traer Datos===================\
        private DCliente getDatosCliente(int id){
            DCliente cliente=nc.getCliente(id);
            return cliente;
        }

        private DRepartidor getDatosRepartidor(int id){
            DRepartidor repartidor=nr.getRepartidor(id);
            return repartidor;
        }

    //============================================


    //=================Enviar SMS VERIFICACIONES==================
    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(EnviarMsgActivity.this, android.Manifest.permission.SEND_SMS);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(EnviarMsgActivity.this, new String[]{android.Manifest.permission.SEND_SMS}, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                DCliente cliente=getDatosCliente(idCliente);
                DRepartidor repartidor=getDatosRepartidor(idRepartidor);
                nombreCliente=cliente.getNombre();
                direccionCliente=cliente.getDireccion();
                nroRepartidor=repartidor.getTelefono();
                estrategiaMsg.enviarMensaje(nombreCliente,direccionCliente,nroRepartidor);
                //estrategiaMsg.enviarMsg(nombreCliente,direccionCliente,nroRepartidor); // Realiza el envío de SMS si se otorga el permiso
            } else {
                Toast.makeText(EnviarMsgActivity.this, "Permiso de SMS denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void actualizar(){
        Intent intent = new Intent(this, EnviarMsgActivity.class);
        startActivity(intent);
    }
}