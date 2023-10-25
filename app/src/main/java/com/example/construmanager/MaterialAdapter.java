package com.example.construmanager;


import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MaterialAdapter extends RecyclerView.Adapter<MaterialAdapter.ViewHolder> {

    private List<Material> listSet;

    public MaterialAdapter(List<Material> listSet) {
        this.listSet = listSet;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNameMaterial;
        private ImageButton ibtnCollapse;
        private Button btnEditar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameMaterial = itemView.findViewById(R.id.tv_name_material);
            ibtnCollapse = itemView.findViewById(R.id.ibtn_collapse);
            btnEditar = itemView.findViewById(R.id.btn_editar);
        }

        public void link(Material material) {
            tvNameMaterial.setText(material.getName());
        }
    }

    @NonNull
    @Override
    public MaterialAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rv_material, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MaterialAdapter.ViewHolder holder, int position) {
        holder.link(listSet.get(position));
    }

    @Override
    public int getItemCount() {
        return listSet.size();
    }
}