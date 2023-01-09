package com.example.finderx_mad;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
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
    public void onBindViewHolder(@NonNull myviewHolder holder, @SuppressLint("RecyclerView") int position) {
        String code = list.get(position).getCode();
        String name = list.get(position).getName();

        holder.Code.setText(code);
        holder.Name.setText(name);

        holder.CV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(position){
                    case 0:
                        Toast.makeText(view.getContext().getApplicationContext(),"Moving to Database Group",Toast.LENGTH_SHORT).show();
                        Navigation.findNavController(view).navigate(R.id.DestStudentGrouplist);
                        break;
                    case 1:
                        Toast.makeText(view.getContext().getApplicationContext(),"Moving to P&S Group",Toast.LENGTH_SHORT).show();
                        Navigation.findNavController(view).navigate(R.id.DestGroup2003);
                        break;
                    case 2:
                        Toast.makeText(view.getContext().getApplicationContext(),"Moving to SAD Group",Toast.LENGTH_SHORT).show();
                        Navigation.findNavController(view).navigate(R.id.DestGroup2006);
                        break;
                    case 3:
                        Toast.makeText(view.getContext().getApplicationContext(),"Moving to MAD Group",Toast.LENGTH_SHORT).show();
                        Navigation.findNavController(view).navigate(R.id.DestGroup2007);
                        break;
                    case 4:
                        Toast.makeText(view.getContext().getApplicationContext(),"Moving to TCS Group",Toast.LENGTH_SHORT).show();
                        Navigation.findNavController(view).navigate(R.id.DestGroup2001);
                        break;
                    case 5:
                        Toast.makeText(view.getContext().getApplicationContext(),"Moving to PM Group",Toast.LENGTH_SHORT).show();
                        Navigation.findNavController(view).navigate(R.id.DestGroup2002);
                        break;
                }
            }
        });
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
        CardView CV;

        public myviewHolder(@NonNull View itemView) {
            super(itemView);

            Code=itemView.findViewById(R.id.TVCourseCodeTitle);
            Name=itemView.findViewById(R.id.TVCourseNameStudent);
            CV=itemView.findViewById(R.id.CV);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(itemView.getContext(), "Course Selected", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
