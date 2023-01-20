package com.example.finderx_mad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import io.reactivex.rxjava3.annotations.NonNull;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.checkerframework.checker.units.qual.Current;


public class myView_GroupAdapter extends RecyclerView.Adapter<myView_GroupAdapter.ViewHolder> {

    LayoutInflater inflater;
    ArrayList<View_GroupUser> list;
    Context context;
//    FirebaseDatabase database = FirebaseDatabase.getInstance();
//    DatabaseReference myRef = database.getReference("string_data"); // to push the accept/decline
    String CurrentState = "nothing_happened";


    public myView_GroupAdapter(Context context, ArrayList<View_GroupUser> list){
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.list = list;

    }

    @NonNull
    @Override
    public myView_GroupAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup container, int viewType) {
        View view = inflater.inflate(R.layout.view_request_for_student, container, false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull myView_GroupAdapter.ViewHolder holder, int position){

        String name = list.get(position).getName();
        String course = list.get(position).getCourseCode();
        String occ = list.get(position).getOcc();

        holder.tvApplyName.setText(name);
        holder.tvCourseCode.setText(course);
        holder.tvOcc.setText(occ);

        // to push the data into database
        holder.ibaccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                myRef.push().setValue("accept");
                CurrentState = "accepted";

            }
        });
        holder.ibdecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                myRef.push().setValue("decline");
                CurrentState = "declined";

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvApplyName, tvCourseCode, tvOcc;
        ImageButton ibaccept, ibdecline;

        public ViewHolder(@androidx.annotation.NonNull View itemView) {
            super(itemView);
            tvApplyName = itemView.findViewById(R.id.tvApplyName);
            tvCourseCode = itemView.findViewById(R.id.tvCourseCode);
            tvOcc = itemView.findViewById(R.id.tvOcc);
            ibaccept = itemView.findViewById((R.id.ibaccept));
            ibdecline = itemView.findViewById(R.id.ibdecline);



        }
    }

}

