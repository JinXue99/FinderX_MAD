package com.example.finderx_mad;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class SGroupListAdapter2 extends RecyclerView.Adapter<SGroupListAdapter2.myviewHolder> {

    LayoutInflater inflater;
    ArrayList<SGroupListDB> list;

    DatabaseReference CountRef;
    TextView MemberCount;
    int countMember;

    SGroupListAdapter2(Context context, ArrayList<SGroupListDB> list){
        this.inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @NonNull
    @Override
    public SGroupListAdapter2.myviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.view_member_for_student, parent, false);
        return new SGroupListAdapter2.myviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SGroupListAdapter2.myviewHolder holder, @SuppressLint("RecyclerView") int position) {

        String TM1 = list.get(position).getTM1();
        String TM2 = list.get(position).getTM2();
        String TM3 = list.get(position).getTM3();
        String TM4 = list.get(position).getTM4();
        String TM5 = list.get(position).getTM5();

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

    public void filterList(ArrayList<SGroupListDB> filteredList){
        list = filteredList;
        notifyDataSetChanged();
    }

    public class myviewHolder extends RecyclerView.ViewHolder {
        TextView TM1,TM2,TM3,TM4,TM5;
        ImageView arrow;

        public myviewHolder(@NonNull View itemView) {
            super(itemView);


            TM1=itemView.findViewById(R.id.tvTM1);
            TM2=itemView.findViewById(R.id.tvTM2);
            TM3=itemView.findViewById(R.id.tvTM3);
            TM4=itemView.findViewById(R.id.tvTM4);
            TM5=itemView.findViewById(R.id.tvTM5);
            arrow=itemView.findViewById(R.id.ivArrow);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(itemView.getContext(), "View Group", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
