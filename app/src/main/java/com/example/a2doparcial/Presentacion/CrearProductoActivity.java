package com.example.a2doparcial.Presentacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.a2doparcial.Dato.DCategoria;
import com.example.a2doparcial.Negocio.NCategoria;
import com.example.a2doparcial.Negocio.NProducto;
import com.example.a2doparcial.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CrearProductoActivity extends AppCompatActivity {

    Button btnSeleccionarImagen,btnInsertarProducto;
    EditText txtNombreProducto,txtPrecioProducto;
    Spinner spinnerCategoria;
    ImageView imagenProductoInsertar;
    int id_categoria;
    NProducto np;
    NCategoria nc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_producto);

        np=new NProducto(CrearProductoActivity.this);
        nc=new NCategoria(CrearProductoActivity.this);// para traer las categorias

        btnInsertarProducto=findViewById(R.id.btnEliminarProducto);
        btnSeleccionarImagen=findViewById(R.id.btnEditarProducto);

        txtNombreProducto=findViewById(R.id.txtEditNombreProducto);
        txtPrecioProducto=findViewById(R.id.txtEditPrecioProducto);

        imagenProductoInsertar=findViewById(R.id.imageProductoEdit);

        spinnerCategoria=findViewById(R.id.spinnerCategoria);
        llenarSpiner();


        //================SELECCIONAR IMAGEN==============
        btnSeleccionarImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eligiriImagenProducto();
            }
        });

        //=================================================

        btnInsertarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long i=0;
                i= np.agregar(txtNombreProducto.getText().toString(),
                        Double.parseDouble(txtPrecioProducto.getText().toString()),
                        imagenByte(imagenProductoInsertar),
                        id_categoria);

                if (i!=0){
                    txtNombreProducto.setText("");
                    txtPrecioProducto.setText("");
                    Toast.makeText(CrearProductoActivity.this, "Se inserto un Producto correctamente", Toast.LENGTH_SHORT).show();
                    actualizar();
                }else{
                    Toast.makeText(CrearProductoActivity.this, "Error al insertar Producto", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void llenarSpiner(){
        ArrayAdapter<DCategoria> adaptador= new ArrayAdapter<DCategoria>(CrearProductoActivity.this,
                android.R.layout.simple_spinner_item, nc.getListaCategorias());

        adaptador.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinnerCategoria.setAdapter(adaptador);

        spinnerCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                id_categoria=((DCategoria)adapterView.getSelectedItem()).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 99 && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imagenProductoInsertar.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public static byte[] imagenByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Bitmap redimensionada=Bitmap.createScaledBitmap(bitmap,300,400,true);
        redimensionada.compress(Bitmap.CompressFormat.PNG, 70, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    public void eligiriImagenProducto() {
        try {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, 99);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public List<DCategoria> getCategorias(){
        List<DCategoria> categorias= new ArrayList<>();
        categorias=nc.getListaCategorias();
        return categorias;
    }

    public void actualizar(){
        Intent intent = new Intent(this, ProductoActivity.class);
        startActivity(intent);
    }
}