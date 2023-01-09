package com.example.finderx_mad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CourseListTeacherAdapter extends RecyclerView.Adapter<CourseListTeacherAdapter.myviewHolder>{

    LayoutInflater inflater;
    ArrayList<CourseListTeacher> list;

    CourseListTeacherAdapter(Context context, ArrayList<CourseListTeacher> list){
        this.inflater = LayoutInflater.from(context);
        this.list = list;
    }
    @NonNull
    @Override
    public CourseListTeacherAdapter.myviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.course_view_for_teacher, parent, false);
        return new CourseListTeacherAdapter.myviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseListTeacherAdapter.myviewHolder holder, int position) {
        String code = list.get(position).getCode();
        String name = list.get(position).getName();

        holder.Code.setText(code);
        holder.Name.setText(name);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void filterList(ArrayList<CourseListTeacher> filteredList){
        list = filteredList;
        notifyDataSetChanged();
    }


    public class myviewHolder extends RecyclerView.ViewHolder {

        TextView Code,Name;
        CardView CVTeacher;

        public myviewHolder(@NonNull View itemView) {
            super(itemView);

            Code=itemView.findViewById(R.id.tvCourseCodeTeacher);
            Name=itemView.findViewById(R.id.tvCourseNameTeacher);
//            CVTeacher=itemView.findViewById(R.id.CVTeacher);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(itemView.getContext(), "Course Selected", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
