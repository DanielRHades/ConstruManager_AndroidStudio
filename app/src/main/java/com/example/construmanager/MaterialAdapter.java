package com.example.construmanager;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class MaterialAdapter extends FirebaseRecyclerAdapter<Material,MaterialAdapter.myViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MaterialAdapter(@NonNull FirebaseRecyclerOptions<Material> options) {
        super(options);
    }
    // Le pone el nombre de Material correspondiente a cada item
    @Override
    protected void onBindViewHolder(@NonNull MaterialAdapter.myViewHolder holder, int position, @NonNull Material model) {
        holder.projectId = model.getProjectId();
        holder.materialId = model.getMaterialId();
        holder.name = model.getName();
        holder.price = model.getPrice();
        holder.tvNameMaterial.setText(model.getName());
        holder.tvPrice.setText("Precio: " + String.valueOf(model.getPrice()));
        holder.tvAvailable.setText("Disponibles: " + model.getAmountAvailable());
        holder.tvMissing.setText("Faltantes: " + model.getAmountMissing());
        holder.tvPayed.setText("Pagados: " + model.getAmountPayed());
        holder.tvOwed.setText("Debidos: " + model.getAmountOwed());
    }
    // Carga el layout de los items
    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_material,parent,false);
        return new myViewHolder(view);
    }
    class myViewHolder extends RecyclerView.ViewHolder{
        String materialId;
        String projectId;
        String name;
        int price;
        TextView tvNameMaterial, tvPrice, tvAvailable, tvMissing,tvPayed, tvOwed;
        ImageView ivCollapse;
        Button btnEditar;
        boolean isCollapsed;
        LinearLayout llInfoProject;
        FragmentManager fragmentManager;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            llInfoProject = itemView.findViewById(R.id.ll_info_project);
            tvNameMaterial = itemView.findViewById(R.id.tv_name_material);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvAvailable = itemView.findViewById(R.id.tv_available);
            tvMissing = itemView.findViewById(R.id.tv_missing);
            tvPayed = itemView.findViewById(R.id.tv_payed);
            tvOwed = itemView.findViewById(R.id.tv_owed);
            ivCollapse = itemView.findViewById(R.id.iv_collapse);
            btnEditar = itemView.findViewById(R.id.btn_editar);
            llInfoProject.setVisibility(View.GONE);
            isCollapsed = true;

            ivCollapse.setOnClickListener(v -> {
                // Muestra el botón de editar si está oculto
                if(isCollapsed){
                    ivCollapse.setImageResource(R.drawable.upward_arrow);
                    llInfoProject.setVisibility(View.VISIBLE);
                    isCollapsed = false;
                }else{
                    ivCollapse.setImageResource(R.drawable.downward_arrow);
                    llInfoProject.setVisibility(View.GONE);
                    isCollapsed = true;
                }
            });
             btnEditar.setOnClickListener(v -> {
            // Manda al usuario a la vista de regitro al interactuar con el texto resaltado
            Intent myIntent = new Intent(btnEditar.getContext(), EditMaterialActivity.class);
            myIntent.putExtra("projectId", projectId);
            myIntent.putExtra("materialId", materialId);
            myIntent.putExtra("name", name);
            myIntent.putExtra("price", price);
            btnEditar.getContext().startActivity(myIntent);
        });
        }
    }
}