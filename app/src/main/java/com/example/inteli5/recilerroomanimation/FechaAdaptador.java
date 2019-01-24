package com.example.inteli5.recilerroomanimation;

import android.arch.lifecycle.HolderFragment;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FechaAdaptador extends RecyclerView.Adapter<FechaAdaptador.FechaValorViewHolder> {
    List<FechaValor> fechaValors = new ArrayList<>();
    Context context;
    private OnItemClickListener listener;


    public FechaAdaptador( Context context) {
        this.fechaValors = fechaValors;
        this.context = context;


    }


    @NonNull
    @Override
    public FechaValorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_fechavalor, viewGroup, false);
        return new FechaValorViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FechaValorViewHolder holder, int i) {
        FechaValor fechacurren = fechaValors.get(i);
        Animation animation = AnimationUtils.loadAnimation(context,R.anim.mover);
        holder.valor.setText(String.valueOf(fechacurren.getValor()));
        holder.fecha.setText(String.valueOf(fechacurren.getFecha()));

        holder.relativeLayout.setAnimation(animation);

    }

    @Override
    public int getItemCount() {
        return fechaValors.size();
    }

    public void setFechaValors(List<FechaValor> fechaValors) {
        this.fechaValors = fechaValors;
        notifyDataSetChanged();
    }

    class FechaValorViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        TextView fecha, valor;
        Button btn_cobrar;
        RelativeLayout relativeLayout;

        public FechaValorViewHolder(@NonNull View itemView) {
            super(itemView);

            fecha = itemView.findViewById(R.id.txt_fec);
            valor = itemView.findViewById(R.id.txt_valor);
            btn_cobrar = itemView.findViewById(R.id.btn_cobrar);
            relativeLayout = itemView.findViewById(R.id.relative_diseño);

            itemView.setOnCreateContextMenuListener(this);
            btn_cobrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(fechaValors.get(position));
                    }
                }
            });
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {


            if (item.getTitle()=="Delete") {

                Toast.makeText(context, "si se puede", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context, "haber", Toast.LENGTH_SHORT).show();
            }
            return true;
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MenuItem menuItem = menu.add("Delete");
            menuItem.setOnMenuItemClickListener(this);
            MenuItem menuItem1 = menu.add("ola");
            menuItem1.setOnMenuItemClickListener(this);


        }
    }

    public interface OnItemClickListener {
        void onItemClick(FechaValor fechaValor);
    }

    public void setOnIteMClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
