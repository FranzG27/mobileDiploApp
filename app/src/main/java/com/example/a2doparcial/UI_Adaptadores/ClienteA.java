package com.example.a2doparcial.UI_Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.a2doparcial.Dato.DCliente;
import com.example.a2doparcial.Presentacion.EditClienteActivity;
import com.example.a2doparcial.R;

import java.util.ArrayList;

public class ClienteA extends RecyclerView.Adapter<ClienteA.ClienteViewHolder>{

    ArrayList<DCliente> listaClientes;

    public ClienteA(ArrayList<DCliente> listaClientes){
        this.listaClientes=listaClientes;
    }

    @NonNull
    @Override
    public ClienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_cliente,null,false);
        return new ClienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClienteViewHolder holder, int position) {
        holder.viewNombreCliente.setText(listaClientes.get(position).getNombre());
        holder.viewTelefonoCliente.setText(listaClientes.get(position).getTelefono());
        holder.viewDireccionCliente.setText(listaClientes.get(position).getDireccion());
    }

    @Override
    public int getItemCount() {
        return listaClientes.size();
    }

    public class ClienteViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombreCliente,viewTelefonoCliente,viewDireccionCliente;

        public ClienteViewHolder(@NonNull View itemView) {
            super(itemView);
            viewNombreCliente=itemView.findViewById(R.id.viewNombreCliente);
            viewTelefonoCliente=itemView.findViewById(R.id.viewTelefonoCliente);
            viewDireccionCliente=itemView.findViewById(R.id.viewDireccionCliente);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context=view.getContext();
                    Intent intent=new Intent(context, EditClienteActivity.class);
                    intent.putExtra("ID",listaClientes.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });

        }
    }
}
