package com.example.finderx_mad;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class  myAnnouncementAdapter extends RecyclerView.Adapter<myAnnouncementAdapter.ViewHolder> {

    LayoutInflater inflater;

    ArrayList<AnnouncementUser> list;




    public myAnnouncementAdapter(Context context, ArrayList<AnnouncementUser> list) {
        this.inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @NonNull
    @Override
    public myAnnouncementAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.task_view_for_student,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myAnnouncementAdapter.ViewHolder holder, int position) {


        String course = list.get(position).getCourseCode();
        String title = list.get(position).getTaskTitle();
        String deadline = list.get(position).getTaskDeadline();


        holder.tvCourseCode.setText(course);
        holder.tvTaskTitle.setText(title);
        holder.tvDeadline.setText(deadline);


//        AnnouncementUser user = list.get(position);
//        holder.CourseCode.setText(user.getcourseCode());
//        holder.taskTitle.setText(user.getTaskTitle());
//        holder.taskDeadline.setText(user.getDeadline());
//        holder.content.setText(user.getDescription());

        //        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick (View view){
        //                Intent intent = new Intent(context, announcement_content.class);
        //                intent.putExtra("task", user.getcourseCode());
        //                intent.putExtra("content", user.getDescription());
        //                context.startActivity(intent);
        //
        //            }
        //        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCourseCode,tvTaskTitle, tvDeadline;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCourseCode = itemView.findViewById(R.id.tvCourseCode);
            tvTaskTitle = itemView.findViewById(R.id.tvTaskTitle);
            tvDeadline = itemView.findViewById(R.id.tvDeadline);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(itemView.getContext(), "item Clicked", Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(view).navigate(R.id.DestannouncementContent);

                }
            });

        }
    }
}
