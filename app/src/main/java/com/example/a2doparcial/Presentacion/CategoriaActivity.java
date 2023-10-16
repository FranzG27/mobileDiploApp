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

import com.example.a2doparcial.Dato.DCategoria;
import com.example.a2doparcial.Negocio.NCategoria;
import com.example.a2doparcial.R;
import com.example.a2doparcial.UI_Adaptadores.CategoriaA;

import java.util.ArrayList;

public class CategoriaActivity extends AppCompatActivity {

    EditText txtNombre;
    Button btnGuardar,btnHomeCategoria;
    RecyclerView listaCategoria;
    ArrayList<DCategoria> listaArrayCategoria;

    NCategoria nc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);
        nc=new NCategoria(CategoriaActivity.this);

        txtNombre = findViewById(R.id.txtNombre);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnHomeCategoria=findViewById(R.id.btnHomeCategoria);

        listaCategoria=findViewById(R.id.listaCategoria);
        listaCategoria.setLayoutManager(new LinearLayoutManager(CategoriaActivity.this));



        listaArrayCategoria=new ArrayList<>();

        CategoriaA adapter=new CategoriaA(nc.getListaCategorias());
        listaCategoria.setAdapter(adapter);



        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long i= nc.agregar(txtNombre.getText().toString());
                if (i!=0){
                    txtNombre.setText("");
                    Toast.makeText(CategoriaActivity.this, "Se inserto correctamente", Toast.LENGTH_SHORT).show();
                    actualizar();
                }else{
                    Toast.makeText(CategoriaActivity.this, "Error al insertar", Toast.LENGTH_SHORT).show();
                }

            }

        });

        //=====================boton Home=============================
        btnHomeCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
        //============================================================

    }

    public void actualizar(){
        Intent intent = new Intent(this, CategoriaActivity.class);
        startActivity(intent);
    }
    //
}