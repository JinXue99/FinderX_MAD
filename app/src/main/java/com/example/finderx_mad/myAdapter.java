package com.example.finderx_mad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class myAdapter extends RecyclerView.Adapter<myAdapter.myViewHolder> {

    String data1[], data2[];
    Context context;

    public myAdapter (Context ct,String s1[],String s2[]){
        context = ct;
        data1 = s1;
        data2 = s2;

    }
    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_announcement_row,parent,false);

        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.lectureName.setText(data1[position]);
        holder.task.setText(data2[position]);
    }

    @Override
    public int getItemCount() {
        return data2.length;
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView lectureName,task;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            lectureName = itemView.findViewById(R.id.lectureName);
            task = itemView.findViewById(R.id.task);

        }
    }
}
