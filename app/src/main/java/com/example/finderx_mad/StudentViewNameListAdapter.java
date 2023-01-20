package com.example.finderx_mad;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class StudentViewNameListAdapter extends ArrayAdapter<StudentViewNameList> {
    private Activity context;
    private int resource;
    private List<StudentViewNameList> listStudent;

    public StudentViewNameListAdapter(@NonNull Activity context, @LayoutRes int resource,@NonNull List<StudentViewNameList> objects){
        super(context,resource,objects);
        this.context = context;
        this.resource = resource;
        listStudent = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView,@NonNull ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View v = inflater.inflate(resource,null);
        TextView TVName = (TextView) v.findViewById(R.id.TVName);
        TextView TVGmail = (TextView) v.findViewById(R.id.TVGmail);
        TextView TVMajoring = (TextView) v.findViewById(R.id.TVMajoring);
        TextView TVDescription = (TextView) v.findViewById(R.id.TVDesciption);
        CircleImageView IVProfileImage = (CircleImageView) v.findViewById(R.id.IVProfileImage);

        TVName.setText("Name: " +listStudent.get(position).getName());
        TVGmail.setText("Email: " + listStudent.get(position).getGmail());
        TVMajoring.setText("Majoring: "+ listStudent.get(position).getMajoring());
        TVDescription.setText("Description: " + listStudent.get(position).getDescription());
        if(listStudent.get(position).getImage().equals("")){
                IVProfileImage.setImageResource(R.drawable.avatar_profile);
            }else {
            Glide.with(context).load(listStudent.get(position).getImage()).into(IVProfileImage);
        }
        return v;
    }

    public void addElement(StudentViewNameList element) {

        listStudent.add(element);
    }

}
