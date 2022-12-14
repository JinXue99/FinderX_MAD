package com.example.finderx_mad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;



public class TaskViewTeacherAdapter extends RecyclerView.Adapter<TaskViewTeacherAdapter.ViewHolder>{

    LayoutInflater inflater;
//    List<TaskModel> taskModels;
    ArrayList<TaskModel> list;

    TaskViewTeacherAdapter(Context context, ArrayList<TaskModel> list){
        this.inflater = LayoutInflater.from(context);
        this.list = list;
    }


    @NonNull
    @Override
    public TaskViewTeacherAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.task_view_for_teacher, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewTeacherAdapter.ViewHolder holder, int position) {

        String title = list.get(position).getTaskTitle();
        String desc = list.get(position).getTaskDetails();
        String date = list.get(position).getTaskDateandTime();


        holder.tvTaskTitle.setText(title);
        holder.tvTaskDesc.setText(desc);
        holder.tvDate.setText(date);

////        int x[] = {R.color.matColor1,R.color.matColor2,R.color.matColor3,R.color.matColor5,R.color.matColor4,R.color.matColor6,R.color.matColor7,R.color.matColor8,R.color.matColor9,R.color.matColor10};
//        int x[] = {R.color.white,R.color.purple_500};
//        int i=0;
//        for(int j=0;j<getItemCount();j++){
//            if(i==2)
//                i=0;
//            if(position==j){
//                holder.constraintlayoutTaskView.setBackgroundColor(x[i]);
//
//            }
//            i++;
//        }



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

//    public void filterList(ArrayList<TaskModel> filteredList){
//        list = filteredList;
//        notifyDataSetChanged();
//    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout constraintlayoutTaskView;
        TextView tvTaskTitle,tvTaskDesc,tvDate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTaskTitle = itemView.findViewById(R.id.tvTaskTitle);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvTaskDesc = itemView.findViewById(R.id.tvTaskDesc);
            constraintlayoutTaskView = itemView.findViewById(R.id.constraintlayoutTaskView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(itemView.getContext(), "item Clicked", Toast.LENGTH_SHORT).show();
                }
            });


        }
    }
}