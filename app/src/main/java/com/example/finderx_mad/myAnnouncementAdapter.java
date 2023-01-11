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

    FragmentManager fragmentManager;


    public myAnnouncementAdapter(Context context, ArrayList<AnnouncementUser> list) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @NonNull
    @Override
    public myAnnouncementAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup container, int viewType) {
        View view = inflater.inflate(R.layout.task_view_student_backup, container, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myAnnouncementAdapter.ViewHolder holder, int position) {


        String course = list.get(position).getCourseCode();
        String title = list.get(position).getTaskTitle();
        String deadline = list.get(position).getTaskDeadline();
        String details = list.get(position).getTaskDetails();


        holder.tvCourseCode.setText(course);
        holder.tvTaskTitle.setText(title);
        holder.tvDeadline.setText(deadline);
        holder.tvTaskDetails.setText(details);
//        holder.tvTaskDetails.setText(details);


//        holder.maintaskforstudent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Navigation.findNavController(view).navigate(R.id.DestannouncementContent);
//                AnnouncementContent fragment = new AnnouncementContent();
//                Bundle bundle = new Bundle();
//
//                bundle.putString("course",course);
//                bundle.putString("title",title);
//                bundle.putString("deadline",deadline);
//                bundle.putString("details",details);
//
//                if(fragment.isAdded()) {
//                    fragment.setArguments(bundle);
//                    fragment.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.DestStudentAnnouncement,fragment).commit();
////                    FragmentTransaction = fragment.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.DestStudentAnnouncement, fragment).commitNow();
//                }
////                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.DestStudentAnnouncement,fragment).commit();
//            }
//        });


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
        TextView tvCourseCode, tvTaskTitle, tvDeadline, tvTaskDetails;
//        ConstraintLayout maintaskforstudent;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCourseCode = itemView.findViewById(R.id.tvCourseCodebc);
            tvTaskTitle = itemView.findViewById(R.id.tvTaskTitlebc);
            tvDeadline = itemView.findViewById(R.id.tvDeadlinebc);
            tvTaskDetails = itemView.findViewById(R.id.tvDetailsbc);
//            maintaskforstudent = itemView.findViewById(R.id.maintaskforstudent);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(itemView.getContext(), "item Clicked", Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(view).navigate(R.id.DestAnnouncementContent);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(itemView.getContext(), "item Clicked", Toast.LENGTH_SHORT).show();
//                    Navigation.findNavController(view).navigate(R.id.DestannouncementContent);
//                }
//            });

                }
            });

        }
    }
}