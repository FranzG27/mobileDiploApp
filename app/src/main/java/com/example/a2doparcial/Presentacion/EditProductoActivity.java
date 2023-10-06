package com.example.a2doparcial.Presentacion;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.a2doparcial.Dato.DProducto;
import com.example.a2doparcial.Negocio.NProducto;
import com.example.a2doparcial.R;

public class EditProductoActivity extends AppCompatActivity {
    EditText txtNombreProductoEdit,txtPrecioProductoEdit;
    Button btnEditarProducto,btnEliminarProducto;
    ImageView imageProducto;
    NProducto np;
    DProducto producto; //es un tipo de dato(OBJETO)
    int id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_producto);

        np=new NProducto(EditProductoActivity.this);
        producto=new DProducto();

        txtNombreProductoEdit=findViewById(R.id.txtEditNombreProducto);
        txtPrecioProductoEdit=findViewById(R.id.txtEditPrecioProducto);
        imageProducto=findViewById(R.id.imageProductoEdit);


        btnEditarProducto=findViewById(R.id.btnEditarProducto);
        btnEliminarProducto=findViewById(R.id.btnEliminarProducto);


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

        producto=getProducto(id);
        if(producto!=null){
            txtNombreProductoEdit.setText(producto.getNombre());
            txtPrecioProductoEdit.setText(Double.toString(producto.getPrecio()));

            byte[] foodImage = producto.getImagen();
            Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
            imageProducto.setImageBitmap(bitmap);
        }
        //---------------------------------------------------------------

        //-----------------click boton editar---------------------------
        btnEditarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean b=np.editarProducto(id,txtNombreProductoEdit.getText().toString(),
                        Double.parseDouble(txtPrecioProductoEdit.getText().toString()));
                if(b){
                    Toast.makeText(EditProductoActivity.this,"Producto Modificado", Toast.LENGTH_SHORT).show();
                    verProductos();
                }else {
                    Toast.makeText(EditProductoActivity.this, "No se Pudo Modificar Producto", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //------------------------------------------------------------

        //-----------------click Eliminar---------------------------
        btnEliminarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder buider=new AlertDialog.Builder(EditProductoActivity.this);
                buider.setMessage("Seguro que Desea eliminar este Producto?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(np.eliminarProducto(id)){
                                    Toast.makeText(EditProductoActivity.this,"Producto Eliminado",Toast.LENGTH_SHORT).show();
                                    verProductos();
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

    public DProducto getProducto(int id){
        return np.getProducto(id);
    }

    private void verProductos(){
        Intent intent=new Intent(this, ProductoActivity.class);
        intent.putExtra("ID",id);
        startActivity(intent);
    }
}