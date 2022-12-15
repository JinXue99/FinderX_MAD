package com.example.finderx_mad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    LayoutInflater inflater;
    List<TaskModel> taskModels;

    Adapter(Context context, List<TaskModel> taskModels){
        this.inflater = LayoutInflater.from(context);
        this.taskModels = taskModels;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.task_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        String title = taskModels.get(position).getTaskTitle();
        String date = taskModels.get(position).getTaskDate();
        String time = taskModels.get(position).getTaskTime();

        holder.nTitle.setText(title);
        holder.nDate.setText(date);
        holder.nTime.setText(time);

    }

    @Override
    public int getItemCount() {
        return taskModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nTitle, nDate, nTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nTitle = itemView.findViewById(R.id.nTitle);
            nTime = itemView.findViewById(R.id.nTime);
            nDate = itemView.findViewById(R.id.nDate);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(itemView.getContext(), "item Clicked", Toast.LENGTH_SHORT).show();
                }
            });


        }
    }
}
