package com.example.a2doparcial.UI_Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.a2doparcial.Dato.DCategoria;
import com.example.a2doparcial.Presentacion.EditCategoriaActivity;
import com.example.a2doparcial.R;

import java.util.ArrayList;

public class CategoriaA extends RecyclerView.Adapter<CategoriaA.CategoriaViewHolder> {

    ArrayList<DCategoria> listaCategorias;

    public CategoriaA(ArrayList<DCategoria> listaCategorias){
        this.listaCategorias=listaCategorias;
    }

    @NonNull
    @Override
    public CategoriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_categoria,null,false);

        return new CategoriaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriaViewHolder holder, int position) {
          holder.viewNombre.setText(listaCategorias.get(position).getNombre());
    }

    @Override
    public int getItemCount() {
        return listaCategorias.size();
    }

    public class CategoriaViewHolder extends RecyclerView.ViewHolder {
        TextView viewNombre;
        public CategoriaViewHolder(@NonNull View itemView) {
            super(itemView);
            viewNombre=itemView.findViewById(R.id.viewNombreC);

            itemView.setOnClickListener(new  View.OnClickListener() {


                @Override
                public void onClick(View view) {
                    Context context=view.getContext();
                    Intent intent=new Intent(context, EditCategoriaActivity.class);
                    intent.putExtra("ID",listaCategorias.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}