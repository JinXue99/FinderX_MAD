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
        View view = inflater.inflate(R.layout.task_view_for_teacher, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        String title = taskModels.get(position).getTaskTitle();
        String date = taskModels.get(position).getTaskDate();
        String time = taskModels.get(position).getTaskTime();

        holder.tvTaskTitle.setText(title);
        holder.tvDate.setText(date);
        holder.tvTime.setText(time);

    }

    @Override
    public int getItemCount() {
        return taskModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTaskTitle, tvDate, tvTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTaskTitle = itemView.findViewById(R.id.tvTaskTitle);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvTime = itemView.findViewById(R.id.tvTime);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(itemView.getContext(), "item Clicked", Toast.LENGTH_SHORT).show();
                }
            });


        }
    }
}
