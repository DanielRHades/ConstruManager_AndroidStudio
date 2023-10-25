package com.example.construmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WorkerAdapter extends RecyclerView.Adapter<WorkerAdapter.ViewHolder> {

    private Context context;
    private List<Worker> listSet;

    public WorkerAdapter(Context context, List<Worker> listSet) {
        this.context = context;
        this.listSet = listSet;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNameWorker,tvOccupationWorker;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameWorker = itemView.findViewById(R.id.tv_name_worker);
            tvOccupationWorker = itemView.findViewById(R.id.tv_occupation_worker);
        }

        public void link(Worker worker) {
            tvNameWorker.setText(worker.getName());
            tvOccupationWorker.setText(worker.getOccupation());
        }
    }

    @NonNull
    @Override
    public WorkerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rv_worker, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkerAdapter.ViewHolder holder, int position) {
        Worker worker = listSet.get(position);
        holder.tvNameWorker.setText(worker.getName());
        holder.tvOccupationWorker.setText(worker.getOccupation());
    }

    @Override
    public int getItemCount() {
        return listSet.size();
    }
}