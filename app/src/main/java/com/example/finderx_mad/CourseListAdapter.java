package com.example.finderx_mad;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class CourseListAdapter extends FirebaseRecyclerAdapter<CourseList,CourseListAdapter.myviewHolder> {

    public CourseListAdapter(@NonNull FirebaseRecyclerOptions<CourseList> options){
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewHolder holder, int position, @NonNull CourseList model) {
        holder.Code.setText(model.getCode());
        holder.Name.setText(model.getName());

        holder.Code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity=(AppCompatActivity)view.getContext();
                //activity.getSupportFragmentManager().beginTransaction().replace( );
            }
        });
    }

    @NonNull
    @Override
    public myviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_item,parent,false);
        return new myviewHolder(view);
    }

    public class myviewHolder extends RecyclerView.ViewHolder {
        TextView Code,Name;
        public myviewHolder(@NonNull View itemView) {
            super(itemView);

            Code=itemView.findViewById(R.id.TVCourseName);
            Name=itemView.findViewById(R.id.TVCourseCode);
        }
    }
}
