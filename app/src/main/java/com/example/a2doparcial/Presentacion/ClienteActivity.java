package com.example.a2doparcial.Presentacion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.a2doparcial.Dato.DCliente;
import com.example.a2doparcial.Negocio.NCliente;
import com.example.a2doparcial.R;
import com.example.a2doparcial.UI_Adaptadores.ClienteA;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.ArrayList;

public class ClienteActivity extends AppCompatActivity {

    EditText txtNombreCliente,txtTelefonoCliente,txtDireccionCliente;
    Button btnGuardarCliente,btnHomeCliente;
    RecyclerView listaCliente;
    NCliente nc;

    ArrayList<DCliente> listaArrayCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        nc=new NCliente(ClienteActivity.this);

        txtNombreCliente=findViewById(R.id.txtNombreCliente);
        txtTelefonoCliente=findViewById(R.id.txtTelefonoCliente);
        txtDireccionCliente=findViewById(R.id.txtDireccionCliente);

        btnGuardarCliente=findViewById(R.id.btnGuardarCliente);
        btnHomeCliente=findViewById(R.id.btnHomeCliente);

        listaCliente=findViewById(R.id.listaCliente);
        listaCliente.setLayoutManager(new LinearLayoutManager(ClienteActivity.this));

        listaArrayCliente=new ArrayList<>();

        //================MOSTRAR CLIENTES=================
        ClienteA adapter=new ClienteA(nc.getListaClientes());
        listaCliente.setAdapter(adapter);
        //=================================================


        //CLICK EN GUARDAR
        btnGuardarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long i=0;
                i= nc.agregar(txtNombreCliente.getText().toString(),
                        txtTelefonoCliente.getText().toString(),
                        txtDireccionCliente.getText().toString());

                if (i!=0){
                    txtNombreCliente.setText("");
                    txtTelefonoCliente.setText("");
                    txtDireccionCliente.setText("");
                    Toast.makeText(ClienteActivity.this, "Se inserto un Cliente correctamente", Toast.LENGTH_SHORT).show();
                    actualizar();
                }else{
                    Toast.makeText(ClienteActivity.this, "Error al insertar Cliente", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //=====================boton Home=============================
        btnHomeCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
        //============================================================

    }

    public void actualizar(){
        Intent intent = new Intent(this, ClienteActivity.class);
        startActivity(intent);
    }
}