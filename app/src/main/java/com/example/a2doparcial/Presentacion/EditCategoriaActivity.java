package com.example.a2doparcial.Presentacion;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a2doparcial.Dato.DCategoria;
import com.example.a2doparcial.Negocio.NCategoria;
import com.example.a2doparcial.R;

public class EditCategoriaActivity extends AppCompatActivity {
    EditText txtNombreEdit;
    Button btnEditar,btnEliminar;
    NCategoria nc;
    DCategoria categoria; 
    int id=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_categoria);
        nc=new NCategoria(EditCategoriaActivity.this);
        categoria=new DCategoria();

        txtNombreEdit=findViewById(R.id.txtNombreEdit);
        btnEditar=findViewById(R.id.btnEditar);
        btnEliminar=findViewById(R.id.btnEliminar);


          if(savedInstanceState ==null){
            Bundle extras=getIntent().getExtras();
            if(extras==null){
                id=Integer.parseInt(null);
            }else{
                id=extras.getInt("ID");
            }
        }else{
            id=(int) savedInstanceState.getSerializable("ID");
        }

        categoria=getCategoria(id);
        if(categoria!=null){
            txtNombreEdit.setText(categoria.getNombre());
        }


         btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean b=nc.editarCategoria(id,txtNombreEdit.getText().toString());
                if(b){
                    Toast.makeText(EditCategoriaActivity.this,"Categoria Modifica", Toast.LENGTH_SHORT).show();
                    verCategorias();
                }else {
                    Toast.makeText(EditCategoriaActivity.this, "No se Pudo Modificar Categoria", Toast.LENGTH_SHORT).show();
                }
            }
        });

         btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder buider=new AlertDialog.Builder(EditCategoriaActivity.this);
                buider.setMessage("Seguro que Desea eliminar esta categotia?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(nc.eliminarCategoria(id)){
                                    Toast.makeText(EditCategoriaActivity.this,"Categoria Eliminada",Toast.LENGTH_SHORT).show();
                                    verCategorias();
                                }
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        });


        
    }
    public DCategoria getCategoria(int id){
        return nc.getCategoria(id);
    }

    private void verCategorias(){
        Intent intent=new Intent(this, CategoriaActivity.class);
        intent.putExtra("ID",id);
        startActivity(intent);

    }


}