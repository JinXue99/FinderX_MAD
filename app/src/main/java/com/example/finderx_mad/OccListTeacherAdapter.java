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

//OccList2001TeacherAdapter
public class OccListTeacherAdapter extends RecyclerView.Adapter<OccListTeacherAdapter.myviewHolder>{

    LayoutInflater inflater;
    ArrayList<OccListTeacher> list;

    OccListTeacherAdapter(Context context, ArrayList<OccListTeacher> list){
        this.inflater = LayoutInflater.from(context);
        this.list = list;
    }
    @NonNull
    @Override
    public OccListTeacherAdapter.myviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.occ_view_for_teacher, parent, false);
        return new OccListTeacherAdapter.myviewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull OccListTeacherAdapter.myviewHolder holder, @SuppressLint("RecyclerView") int position) {
        String occ = list.get(position).getOcc();
        holder.Occ.setText(occ);

        holder.CVTeacherOcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(position){
                    case 0:
                        Toast.makeText(view.getContext().getApplicationContext(),occ + " is selected ",Toast.LENGTH_SHORT).show();
                        Navigation.findNavController(view).navigate(R.id.DestTeacherChoice);
                        break;


                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void filterList(ArrayList<OccListTeacher> filteredList){
        list = filteredList;
        notifyDataSetChanged();
    }


    public class myviewHolder extends RecyclerView.ViewHolder {

        TextView Occ;
        CardView CVTeacherOcc;

        public myviewHolder(@NonNull View itemView) {
            super(itemView);

            Occ=itemView.findViewById(R.id.tvOcc);
            CVTeacherOcc=itemView.findViewById(R.id.CVTeacherOcc);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(itemView.getContext(), "Occ Selected", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}

