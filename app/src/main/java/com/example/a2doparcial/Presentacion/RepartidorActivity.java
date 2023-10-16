package com.example.a2doparcial.Presentacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.a2doparcial.Dato.DRepartidor;
import com.example.a2doparcial.Negocio.NRepartidor;
import com.example.a2doparcial.R;
import com.example.a2doparcial.UI_Adaptadores.RepartidorA;

import java.util.ArrayList;

public class RepartidorActivity extends AppCompatActivity {

    EditText txtNombreRepartidor, txtTelefonoRepartidor, txtPlacaRepartidor;
    Button btnGuardarRepartidor, btnHomeRepartidor;
    RecyclerView listaRepartidor;
    NRepartidor nr;

    ArrayList<DRepartidor> listaArrayRepartidor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repartidor);

        nr = new NRepartidor(RepartidorActivity.this);

        txtNombreRepartidor = findViewById(R.id.txtNombreRepartidor);
        txtTelefonoRepartidor = findViewById(R.id.txtTelefonoRepartidor);
        txtPlacaRepartidor = findViewById(R.id.txtPlacaRepartidor);

        btnGuardarRepartidor = findViewById(R.id.btnGuardarRepartidor);
        btnHomeRepartidor = findViewById(R.id.btnHomeRepartidor);

        listaRepartidor = findViewById(R.id.listaRepartidor);
        listaRepartidor.setLayoutManager(new LinearLayoutManager(RepartidorActivity.this));

        listaArrayRepartidor = new ArrayList<>();


        //================MOSTRAR CLIENTES=================
        RepartidorA adapter = new RepartidorA(nr.getListaRepartidor());
        listaRepartidor.setAdapter(adapter);
        //=================================================

        //CLICK EN GUARDAR
        btnGuardarRepartidor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long i = 0;
                i = nr.agregar(txtNombreRepartidor.getText().toString(),
                        txtTelefonoRepartidor.getText().toString(),
                        txtPlacaRepartidor.getText().toString());

                if (i != 0) {
                    txtNombreRepartidor.setText("");
                    txtTelefonoRepartidor.setText("");
                    txtPlacaRepartidor.setText("");
                    Toast.makeText(RepartidorActivity.this, "Se inserto un Repartidor correctamente", Toast.LENGTH_SHORT).show();
                    actualizar();
                } else {
                    Toast.makeText(RepartidorActivity.this, "Error al insertar Repartidor", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //=====================boton Home=============================
        btnHomeRepartidor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
        //============================================================
    }

    public void actualizar() {
        Intent intent = new Intent(this, RepartidorActivity.class);
        startActivity(intent);
    }
}