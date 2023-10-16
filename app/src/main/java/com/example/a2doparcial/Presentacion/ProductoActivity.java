package com.example.a2doparcial.Presentacion;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.a2doparcial.Dato.DProducto;

import com.example.a2doparcial.Negocio.NProducto;
import com.example.a2doparcial.R;
import com.example.a2doparcial.UI_Adaptadores.ProductoA;

import java.util.ArrayList;

public class ProductoActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_WRITE_EXTERNAL_STORAGE_PERMISSION =1;
    Button btnHomeProducto,btnCrearProducto,btnGenerarCatalogo;
    RecyclerView listaProductos;
    ArrayList<DProducto> listaArrayProducto;
    NProducto np;
    CatalogoFacade catalagoFacade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);
        np=new NProducto(ProductoActivity.this);

        btnHomeProducto=findViewById(R.id.btnHomeProducto);
        btnCrearProducto=findViewById(R.id.btnCrearProducto);
        btnGenerarCatalogo=findViewById(R.id.btnGenerarCatalogo);

        listaProductos=findViewById(R.id.listaProducto);
        listaProductos.setLayoutManager(new LinearLayoutManager(ProductoActivity.this));


        //=====================Mostrar productos=============================
        ProductoA adapter=new ProductoA(np.getListaProductos());
        listaProductos.setAdapter(adapter);
        //===================================================================

        //=====================boton Home=============================
        btnHomeProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
        //============================================================

        btnCrearProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(), CrearProductoActivity.class);
                startActivity(i);
            }
        });

        btnGenerarCatalogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                catalagoFacade=new CatalogoFacade(getApplicationContext(), ProductoActivity.this);
                catalagoFacade.compartirPDF();
            }
        });
    }

}