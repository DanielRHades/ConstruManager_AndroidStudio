package com.example.construmanager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WorkerAdapter extends RecyclerView.Adapter<WorkerAdapter.ViewHolder> {

    private List<Worker> listSet;

    public WorkerAdapter(List<Worker> listSet) {
        this.listSet = listSet;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNameWorker,tvOccupationWorker,tvRankWorker;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameWorker = itemView.findViewById(R.id.tv_name_worker);
            tvOccupationWorker = itemView.findViewById(R.id.tv_occupation_worker);
            tvRankWorker = itemView.findViewById(R.id.tv_rank_worker);
        }

        public void link(Worker worker) {
            tvNameWorker.setText(worker.getName());
            tvOccupationWorker.setText(worker.getOccupation());
            tvRankWorker.setText(worker.getRank());
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
        holder.link(listSet.get(position));
    }

    @Override
    public int getItemCount() {
        return listSet.size();
    }
}