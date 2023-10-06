package com.example.a2doparcial.UI_Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.a2doparcial.Dato.DRepartidor;
import com.example.a2doparcial.Presentacion.EditRepartidorActivity;
import com.example.a2doparcial.R;

import java.util.ArrayList;

public class RepartidorA extends RecyclerView.Adapter<RepartidorA.RepartidorViewHolder> {

    ArrayList<DRepartidor> listaRepartidores;

    public RepartidorA(ArrayList<DRepartidor> listaRepartidores){
        this.listaRepartidores=listaRepartidores;
    }


    @NonNull
    @Override
    public RepartidorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_repartidor,null,false);
        return new RepartidorA.RepartidorViewHolder(view);
        // return new ClienteA.ClienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepartidorViewHolder holder, int position) {
        holder.viewNombreRepartidor.setText(listaRepartidores.get(position).getNombre());
        holder.viewTelefonoRepartidor.setText(listaRepartidores.get(position).getTelefono());
        holder.viewPlacaRepartidor.setText(listaRepartidores.get(position).getPlaca());

    }

    @Override
    public int getItemCount() {
        return listaRepartidores.size();
    }

    public class RepartidorViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombreRepartidor,viewTelefonoRepartidor,viewPlacaRepartidor;

        public RepartidorViewHolder(@NonNull View itemView) {
            super(itemView);
            viewNombreRepartidor=itemView.findViewById(R.id.viewNombreRepartidor);
            viewTelefonoRepartidor=itemView.findViewById(R.id.viewTelefonoRepartidor);
            viewPlacaRepartidor=itemView.findViewById(R.id.viewPlacaRepartidor);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context=view.getContext();
                    Intent intent=new Intent(context, EditRepartidorActivity.class);
                    intent.putExtra("ID",listaRepartidores.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
