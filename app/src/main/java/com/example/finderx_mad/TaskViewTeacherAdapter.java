package com.example.finderx_mad;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;



public class TaskViewTeacherAdapter extends RecyclerView.Adapter<TaskViewTeacherAdapter.ViewHolder>{

    LayoutInflater inflater;
    ArrayList<TaskModel> list;
    private Context context;
    private OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener clickListener){
        listener = clickListener;
    }

    FirebaseDatabase database;
    DatabaseReference myRef,TaskRef;
    TaskModel teacherViewTask;
    //private FirebaseAuth teacher;
    private FirebaseUser teacher;
    String TeacherID;
    // Connect to Firebase


    TaskViewTeacherAdapter(Context context, ArrayList<TaskModel> list){
        this.inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @NonNull
    @Override
    public TaskViewTeacherAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = inflater.inflate(R.layout.task_view_for_teacher, parent, false);

        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewTeacherAdapter.ViewHolder holder, int position) {


        String title = list.get(position).getTaskTitle();
        String desc = list.get(position).getTaskDetails();
        String date = list.get(position).getTaskDateandTime();

        holder.tvTaskTitle.setText(title);
        holder.tvTaskDesc.setText(desc);
        holder.tvDate.setText(date);
        holder.IVEdit.setImageResource(R.drawable.ic_baseline_edit_24);
        holder.IVDelete.setImageResource(R.drawable.ic_baseline_delete_24);
//        holder.IVDelete.setOnClickListener((view) -> {
//            final Dialog descDialog = new Dialog(getContext());
//                //Add A title in hte custom layout
//                descDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                //The user able to cancel the dialog by clicking anywhere outside the dialog
//                descDialog.setCancelable(true);
//                //Mention the name of the layout of your custom dialog
//                descDialog.setContentView(R.layout.student_description_dialog);
//                descDialog.show();
//
//
//
//        });
    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout constraintlayoutTaskView;
        TextView tvTaskTitle,tvTaskDesc,tvDate;
        ImageView IVEdit, IVDelete;
        public ViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);

            tvTaskTitle = itemView.findViewById(R.id.tvTaskTitle);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvTaskDesc = itemView.findViewById(R.id.tvTaskDesc);
            IVEdit = itemView.findViewById(R.id.IVEdit);
            IVDelete = itemView.findViewById(R.id.IVDelete);
            constraintlayoutTaskView = itemView.findViewById(R.id.constraintlayoutTaskView);
            IVDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(getAdapterPosition());
                }
            });

        }
        //Function to display the custom dialog
        private void showCustomDialog() {
            final Dialog descDialog = new Dialog(itemView.getContext());
            //Add A title in hte custom layout
            descDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            //The user able to cancel the dialog by clicking anywhere outside the dialog
            descDialog.setCancelable(true);
            //Mention the name of the layout of your custom dialog
            descDialog.setContentView(R.layout.student_description_dialog);

            //initializing the views of the dialog
            final EditText ETStudentDescription = descDialog.findViewById(R.id.ETStudentDescription);
//            DatabaseReference dbDesc = database.getReference("Users").child(StudentID);
//            dbDesc.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<DataSnapshot> task) {
//                    DataSnapshot snapshot = task.getResult();
//                    // initial description
//                    ETStudentDescription.setText(snapshot.child("Description").getValue().toString());
//                }
//            });
//
//            //String updatedDescription = String.valueOf(ETStudentDescription.getText());
//
//            Button btnUpdate = descDialog.findViewById(R.id.btnUpdate);
//            btnUpdate.setOnClickListener((v -> {
//                String studentUpdatedDesc = ETStudentDescription.getText().toString();
//                dbDesc.child("Description").setValue(studentUpdatedDesc);
//                // dfDesc.update("Description",studentUpdatedDesc);
//                dbDesc.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
//                    @Override
//                    public void onSuccess(DataSnapshot dataSnapshot) {
//                        Toast.makeText(getContext().getApplicationContext(), "Update Successfully!", Toast.LENGTH_SHORT).show();
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(getContext().getApplicationContext(), "Fail!", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//                descDialog.dismiss();
//            }));

            descDialog.show();
        }

    }




}