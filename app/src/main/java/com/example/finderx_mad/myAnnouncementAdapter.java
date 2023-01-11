package com.example.finderx_mad;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;



import java.util.ArrayList;
import java.util.List;

public class  myAnnouncementAdapter extends RecyclerView.Adapter<myAnnouncementAdapter.ViewHolder> {

    LayoutInflater inflater;
    ArrayList<AnnouncementUser> list;
    Context context;

    public myAnnouncementAdapter(Context context, ArrayList<AnnouncementUser> list) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @NonNull
    @Override
    public myAnnouncementAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup container, int viewType) {
        View view = inflater.inflate(R.layout.task_view_for_student, container, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myAnnouncementAdapter.ViewHolder holder, int position) {


        String course = list.get(position).getCourseCode();
        String name = list.get(position).getName();
        String title = list.get(position).getTaskTitle();
        String deadline = list.get(position).getTaskDeadline();
        String details = list.get(position).getTaskDetails();


        holder.tvCourseCodeAnnouncement.setText(course);
        holder.tvTaskTitle.setText(title);
        holder.tvDate.setText(deadline);
        holder.tvTaskDesc.setText(details);
        holder.tvLecturer.setText("Dr Ong Sim Ying");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCourseCodeAnnouncement, tvTaskTitle, tvDate, tvTaskDesc, tvLecturer;
//        ConstraintLayout maintaskforstudent;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCourseCodeAnnouncement = itemView.findViewById(R.id.tvCourseCodeAnnouncement);
            tvTaskTitle = itemView.findViewById(R.id.tvTaskTitle);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvTaskDesc = itemView.findViewById(R.id.tvTaskDesc);
            tvLecturer = itemView.findViewById(R.id.tvLecturer);

                }
            }

        }

