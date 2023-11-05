package com.example.construmanager;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class ProjectAdapter extends FirebaseRecyclerAdapter<Project,ProjectAdapter.myViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ProjectAdapter(@NonNull FirebaseRecyclerOptions<Project> options) {
        super(options);
    }
    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Project model) {
        holder.id = model.getId();
        holder.tvNameProject.setText(model.getName());
        holder.tvCompany.setText(model.getCompany());
        holder.tvAffiliates.setText(model.getAffiliates());
        holder.tvAddress.setText(model.getAddress());
    }
    // Carga el layout de los items
    @NonNull
    @Override
    public ProjectAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_project,parent,false);
        return new ProjectAdapter.myViewHolder(view);
    }
    class myViewHolder extends RecyclerView.ViewHolder{
        String id;
        LinearLayout llInfoProject;
        TextView tvNameProject,tvCompany,tvAffiliates,tvAddress;
        ImageView ivCollapse;
        Button btnGoInfoProject;
        boolean isCollapsed;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            llInfoProject = itemView.findViewById(R.id.ll_info_project);
            tvNameProject = itemView.findViewById(R.id.tv_name_project);
            tvCompany = itemView.findViewById(R.id.tv_company);
            tvAffiliates = itemView.findViewById(R.id.tv_affiliates);
            tvAddress = itemView.findViewById(R.id.tv_address);
            ivCollapse = itemView.findViewById(R.id.iv_collapse);
            btnGoInfoProject = itemView.findViewById(R.id.btn_go_info_project);
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
            btnGoInfoProject.setOnClickListener(v -> {
                Intent myIntent = new Intent(btnGoInfoProject.getContext(), MaterialActivity.class);
                myIntent.putExtra("id", id);
                btnGoInfoProject.getContext().startActivity(myIntent);
            });
        }
    }

}