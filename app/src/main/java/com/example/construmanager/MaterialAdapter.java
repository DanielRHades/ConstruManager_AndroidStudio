package com.example.construmanager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
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
        holder.tvNameMaterial.setText(model.getName());
    }
    // Carga el layout de los items
    @NonNull
    @Override
    public MaterialAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_material,parent,false);
        return new MaterialAdapter.myViewHolder(view);
    }
    class myViewHolder extends RecyclerView.ViewHolder{
        TextView tvNameMaterial;
        ImageButton ibtnCollapse;
        Button btnEditar;
        boolean isCollapsed;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameMaterial = itemView.findViewById(R.id.tv_name_material);
            ibtnCollapse = itemView.findViewById(R.id.ibtn_collapse);
            btnEditar = itemView.findViewById(R.id.btn_editar);
            isCollapsed = true;
            ibtnCollapse.setOnClickListener(v -> {
                // Muestra el botón de editar si está oculto
                if(isCollapsed){
                    ibtnCollapse.setImageResource(R.drawable.upward_arrow);
                    btnEditar.setVisibility(View.VISIBLE);
                }else{
                    ibtnCollapse.setImageResource(R.drawable.downward_arrow);
                    btnEditar.setVisibility(View.GONE);
                }
            });
        }
    }

}