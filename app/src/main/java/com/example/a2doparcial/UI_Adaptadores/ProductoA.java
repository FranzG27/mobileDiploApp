package com.example.a2doparcial.UI_Adaptadores;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.a2doparcial.Dato.DProducto;
import com.example.a2doparcial.Presentacion.EditProductoActivity;
import com.example.a2doparcial.R;

import java.util.ArrayList;

public class ProductoA extends RecyclerView.Adapter<ProductoA.ProductoViewHolder> {

    ArrayList<DProducto> listaProductos;

    public ProductoA(ArrayList<DProducto> listaProductos){
        this.listaProductos=listaProductos;
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_producto, null,false);
        return new ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        holder.viewNombreProducto.setText(listaProductos.get(position).getNombre());
          holder.viewPrecioProducto.setText(Double.toString(listaProductos.get(position).getPrecio()));
        byte[] foodImage = listaProductos.get(position).getImagen();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
        holder.imageView.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public class ProductoViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombreProducto,viewPrecioProducto,viewCategoriaProducto;
        ImageView imageView;
        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            viewNombreProducto=itemView.findViewById(R.id.viewNombreProducto);
            viewPrecioProducto=itemView.findViewById(R.id.viewPrecioProducto);
            imageView=itemView.findViewById(R.id.imgProducto);
         
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context=view.getContext();
                    Intent intent=new Intent(context, EditProductoActivity.class);
                    intent.putExtra("ID",listaProductos.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
