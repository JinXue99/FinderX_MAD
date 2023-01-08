package com.example.finderx_mad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SGroupListPSAdapter extends RecyclerView.Adapter<SGroupListPSAdapter.myviewHolder> {

    LayoutInflater inflater;
    ArrayList<SGroupListPS> list;

    SGroupListPSAdapter(Context context, ArrayList<SGroupListPS> list){
        this.inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @NonNull
    @Override
    public SGroupListPSAdapter.myviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.group_view_for_student,parent,false);
        return new SGroupListPSAdapter.myviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SGroupListPSAdapter.myviewHolder holder, int position) {

        String TName = list.get(position).getTName();
        String TM1 = list.get(position).getTM1();
        String TM2 = list.get(position).getTM2();
        String TM3 = list.get(position).getTM3();
        String TM4 = list.get(position).getTM4();
        String TM5 = list.get(position).getTM5();
        holder.TName.setText(TName);
        holder.TM1.setText(TM1);
        holder.TM2.setText(TM2);
        holder.TM3.setText(TM3);
        holder.TM4.setText(TM4);
        holder.TM5.setText(TM5);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void filterList(ArrayList<SGroupListPS> filteredList){
        list = filteredList;
        notifyDataSetChanged();
    }

    public class myviewHolder extends RecyclerView.ViewHolder {
        TextView TName,TM1,TM2,TM3,TM4,TM5;

        public myviewHolder(@NonNull View itemView) {
            super(itemView);

            TName=itemView.findViewById(R.id.tvTeamName);
            TM1=itemView.findViewById(R.id.tvTM1);
            TM2=itemView.findViewById(R.id.tvTM2);
            TM3=itemView.findViewById(R.id.tvTM3);
            TM4=itemView.findViewById(R.id.tvTM4);
            TM5=itemView.findViewById(R.id.tvTM5);

        }
    }
}
