package com.example.a2doparcial.Presentacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.a2doparcial.Conexion.Conexion;
import com.example.a2doparcial.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Conexion BaseDatos=new Conexion(MainActivity.this);//aqui paso el contexto
        SQLiteDatabase db=BaseDatos.getWritableDatabase();

        if(db!=null){
            Toast.makeText(this, "SE CREO LA BD", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "ERROR DE DB", Toast.LENGTH_SHORT).show();
        }

        ImageView imgCategoria=findViewById(R.id.image1);
        ImageView imgProducto=findViewById(R.id.image2);
        ImageView imgCliente=findViewById(R.id.image3);
        ImageView imgRepartidor=findViewById(R.id.image4);
        ImageView imgCotizacion=findViewById(R.id.image5);
        ImageView imgEnviarMsg=findViewById(R.id.image6);

        imgCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(), CategoriaActivity.class);
                startActivity(i);
            }
        });


        imgProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(), ProductoActivity.class);
                startActivity(i);
            }
        });


        imgCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(), ClienteActivity.class);
                startActivity(i);
            }
        });
    }
}
