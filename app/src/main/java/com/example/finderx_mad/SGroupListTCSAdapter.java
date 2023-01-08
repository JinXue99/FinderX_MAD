package com.example.finderx_mad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SGroupListTCSAdapter extends RecyclerView.Adapter<SGroupListTCSAdapter.myviewHolder> {

    LayoutInflater inflater;
    ArrayList<SGroupListTCS> list;

    public SGroupListTCSAdapter(Context context, ArrayList<SGroupListTCS> list) {
        this.inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @NonNull
    @Override
    public myviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.group_view_for_student, parent, false);
        return new SGroupListTCSAdapter.myviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewHolder holder, int position) {

        String TName = list.get(position).getTName();
        String Name = list.get(position).getName();

        holder.TName.setText(TName);
        holder.Name.setText(Name);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void filterList(ArrayList<SGroupListTCS> filteredList){
        list = filteredList;
        notifyDataSetChanged();
    }


    public class myviewHolder extends RecyclerView.ViewHolder {
        TextView TName,Name;

        public myviewHolder(@NonNull View itemView) {
            super(itemView);
            TName=itemView.findViewById(R.id.tvTeamName);
            Name=itemView.findViewById(R.id.tvTM1);
        }
    }
}
