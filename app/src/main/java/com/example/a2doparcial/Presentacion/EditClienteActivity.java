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

import com.example.a2doparcial.Dato.DCliente;
import com.example.a2doparcial.Negocio.NCliente;
import com.example.a2doparcial.R;


public class EditClienteActivity extends AppCompatActivity {
    EditText txtNombreClienteEdit,txtTelefonoClienteEdit,txtDireccionClienteEdit;
    Button btnEditarCliente,btnEliminarCliente;
    NCliente nc;
    DCliente cliente; 
    int id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_cliente);
        nc=new NCliente(EditClienteActivity.this);
        cliente=new DCliente();

        txtNombreClienteEdit=findViewById(R.id.txtNombreClienteEdit);
        txtTelefonoClienteEdit=findViewById(R.id.txtTelefonoClienteEdit);
        txtDireccionClienteEdit=findViewById(R.id.txtDireccionClienteEdit);

        btnEditarCliente=findViewById(R.id.btnEditarCliente);
        btnEliminarCliente=findViewById(R.id.btnEliminarCliente);


       
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

        cliente=getCliente(id);
        if(cliente!=null){
            txtNombreClienteEdit.setText(cliente.getNombre());
            txtTelefonoClienteEdit.setText(cliente.getTelefono());
            txtDireccionClienteEdit.setText(cliente.getDireccion());
        }


        btnEditarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean b=nc.editarClente(id,txtNombreClienteEdit.getText().toString(),
                        txtTelefonoClienteEdit.getText().toString(),
                        txtDireccionClienteEdit.getText().toString());
                if(b){
                    Toast.makeText(EditClienteActivity.this,"Cliente Modificado", Toast.LENGTH_SHORT).show();
                    verClientes();
                }else {
                    Toast.makeText(EditClienteActivity.this, "No se Pudo Modificar Cliente", Toast.LENGTH_SHORT).show();
                }
            }
        });

          btnEliminarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder buider=new AlertDialog.Builder(EditClienteActivity.this);
                buider.setMessage("Seguro que Desea eliminar esta cliente?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(nc.eliminarCliente(id)){
                                    Toast.makeText(EditClienteActivity.this,"Cliente Eliminado",Toast.LENGTH_SHORT).show();
                                    verClientes();
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

    public DCliente getCliente(int id){
        return nc.getCliente(id);
    }

    private void verClientes(){
        Intent intent=new Intent(this, ClienteActivity.class);
        intent.putExtra("ID",id);
        startActivity(intent);
    }
}