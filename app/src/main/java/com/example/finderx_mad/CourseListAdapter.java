package com.example.finderx_mad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CourseListAdapter extends RecyclerView.Adapter<CourseListAdapter.myviewHolder> {


    LayoutInflater inflater;
    ArrayList<CourseList>list;

    CourseListAdapter(Context context, ArrayList<CourseList> list){
        this.inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @NonNull
    @Override
    public CourseListAdapter.myviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.course_item, parent, false);
        return new CourseListAdapter.myviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewHolder holder, int position) {
        String code = list.get(position).getCode();
        String name = list.get(position).getName();

        holder.Code.setText(code);
        holder.Name.setText(name);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void filterList(ArrayList<CourseList> filteredList){
        list = filteredList;
        notifyDataSetChanged();
    }

    public class myviewHolder extends RecyclerView.ViewHolder {

        TextView Code,Name;
        public myviewHolder(@NonNull View itemView) {
            super(itemView);

            Code=itemView.findViewById(R.id.TVCourseCodeTitle);
            Name=itemView.findViewById(R.id.TVCourseNameStudent);
        }
    }
}
