package com.example.prctica_5_listas;

import android.content.Context;
import android.content.om.OverlayIdentifier;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdaptadorLegos extends RecyclerView.Adapter<LegosViewHolder> {

    private Legos[] listaLegos;

    public AdaptadorLegos(Legos[] listaLegos){
        this.listaLegos = listaLegos;
    }

    @NonNull
    @Override
    public LegosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.legos_item, parent, false);

        return new LegosViewHolder(view, parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull LegosViewHolder holder, int position) {
        holder.BindLego(this.listaLegos[position]);
    }

    @Override
    public int getItemCount() {
        return this.listaLegos.length;
    }

}

class LegosViewHolder extends RecyclerView.ViewHolder{

    public TextView nombreView;
    public ImageView imagenView;
    private Context context;

    public LegosViewHolder(View view, Context context){
        super(view);
        this.context = context;

        nombreView = view.findViewById(R.id.nombreView);
        imagenView = view.findViewById(R.id.imagenView);

    }

    public void BindLego(Legos lego){

        nombreView.setText(lego.nombre);
        imagenView.setImageResource(
                context.getResources().getIdentifier(lego.imagen,
                        "drawable",
                        "com.example.prctica_5_listas"));

    }

}