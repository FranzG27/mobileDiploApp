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

import com.example.a2doparcial.Dato.DRepartidor;
import com.example.a2doparcial.Negocio.NRepartidor;
import com.example.a2doparcial.R;


public class EditRepartidorActivity extends AppCompatActivity {

    EditText txtNombreRepartidorEdit,txtTelefonoRepartidorEdit,txtPlacaRepartidorEdit;
    Button btnEditarRepartidor,btnEliminarRepartidor;
    NRepartidor nr;
    DRepartidor repartidor; //no es un instancia si no un tipo de dato(OBJETO)
    int id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_repartidor);
        nr=new NRepartidor(EditRepartidorActivity.this);
        repartidor=new DRepartidor();

        txtNombreRepartidorEdit=findViewById(R.id.txtNombreRepartidorEdit);
        txtTelefonoRepartidorEdit=findViewById(R.id.txtTelefonoRepartidorEdit);
        txtPlacaRepartidorEdit=findViewById(R.id.txtPlacaRepartidorEdit);

        btnEditarRepartidor=findViewById(R.id.btnEditarRepartidor);
        btnEliminarRepartidor=findViewById(R.id.btnEliminarRepartidor);

        //-----------Proceso para ver el item seleccionado----------
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

        repartidor=getRepartidor(id);
        if(repartidor!=null){
            txtNombreRepartidorEdit.setText(repartidor.getNombre());
            txtTelefonoRepartidorEdit.setText(repartidor.getTelefono());
            txtPlacaRepartidorEdit.setText(repartidor.getPlaca());
        }

        //---------------------------------------------------------------

        //-----------------click boton editar---------------------------
        btnEditarRepartidor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean b=nr.editarRepartidor(id,txtNombreRepartidorEdit.getText().toString(),
                        txtTelefonoRepartidorEdit.getText().toString(),
                        txtPlacaRepartidorEdit.getText().toString());
                if(b){
                    Toast.makeText(EditRepartidorActivity.this,"Repartidor Modificado", Toast.LENGTH_SHORT).show();
                    verRepartidores();
                }else {
                    Toast.makeText(EditRepartidorActivity.this, "No se Pudo Modificar Repartidor", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //------------------------------------------------------------


        //-----------------click Eliminar---------------------------
        btnEliminarRepartidor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder buider=new AlertDialog.Builder(EditRepartidorActivity.this);
                buider.setMessage("Seguro que Desea eliminar este repartidor?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(nr.eliminarRepartidor(id)){
                                    Toast.makeText(EditRepartidorActivity.this,"Repartidor Eliminado",Toast.LENGTH_SHORT).show();
                                    verRepartidores();
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


        //----------------------------------------------------------
    }

    public DRepartidor getRepartidor(int id){
        return nr.getRepartidor(id);
    }

    private void verRepartidores(){
        Intent intent=new Intent(this, RepartidorActivity.class);
        intent.putExtra("ID",id);
        startActivity(intent);
    }
}