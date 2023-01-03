package com.example.finderx_mad;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StudentProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudentProfileFragment extends Fragment {
    //private FirebaseAuth student;
    private FirebaseUser student;
    private FirebaseFirestore FirebaseStore;
    private DocumentReference df;

   String StudentID;
    //private String StudentName;
    //private String StudentGmail;
   // private String StudentMajor;
    //private String StudentTelephone;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StudentProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StudentProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StudentProfileFragment newInstance(String param1, String param2) {
        StudentProfileFragment fragment = new StudentProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student_profile,null,false);
        RatingBar RBRating = view.findViewById(R.id.RBRating);
        //RBRating.setRating(AvgRating);
        RBRating.setIsIndicator(true);

        //EditText editText = view.findViewById(R.id.TVStudentDescription);
        //ImageView imageView = view.findViewById(R.id.IVEditPen);
       // imageView.setOnClickListener(new View.OnClickListener() {
       //                                  @Override
        //                                 public void onClick(View view) {
                                           //editText.setEnabled(true);
        //                                 }
         //                            });

                TextView TVStudentName =(TextView)  view.findViewById(R.id.TVStudentName);
                TextView TVStudentID = (TextView) view.findViewById(R.id.TVStudentID);
                TextView TVStudentMajor = (TextView) view.findViewById(R.id.TVStudentMajor);
                TextView TVStudentPhone = (TextView) view.findViewById(R.id.TVStudentPhone);
                TextView TVStudentEmail = (TextView) view.findViewById(R.id.TVStudentEmail);
                TextView TVStudentDescription = (TextView) view.findViewById(R.id.TVStudentDescription);
                TVStudentDescription.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showCustomDialog();
                    }
                });

                // Connect to Firebase
                student = FirebaseAuth.getInstance().getCurrentUser();
                StudentID = student.getUid();
                FirebaseStore = FirebaseFirestore.getInstance();
                df = FirebaseStore.collection("User").document(StudentID);
                df.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            TVStudentName.setText(document.getString("Name"));
                            TVStudentID.setText(document.getString("StudentID"));
                            TVStudentEmail.setText(document.getString("Email"));
                            TVStudentMajor.setText(document.getString("Majoring"));
                            TVStudentPhone.setText(document.getString("Phone"));
                            TVStudentDescription.setText(document.getString("Description"));
                            if (document.exists()) {
                                Log.d("TAG", "DocumentSnapshot data: " + document.getData());
                            } else {
                                Log.d("TAG", "No such document");
                            }
                        } else {
                            Log.d("TAG", "get failed with ", task.getException());
                        }
                    }
                });

        return view;
    }

    //Function to display the custom dialog
    private void showCustomDialog() {
        final Dialog descDialog = new Dialog(getContext());
        //Add A title in hte custom layout
        descDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //The user able to cancel the dialog by clicking anywhere outside the dialog
        descDialog.setCancelable(true);
        //Mention the name of the layout of your custom dialog
        descDialog.setContentView(R.layout.student_description_dialog);

        //initializing the views of the dialog
        final EditText ETStudentDescription = descDialog.findViewById(R.id.ETStudentDescription);
        //String updatedDescription = String.valueOf(ETStudentDescription.getText());
        DocumentReference dfDesc = FirebaseStore.collection("User").document(StudentID);

        Button btnUpdate = descDialog.findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener((v -> {
            String studentUpdatedDesc = ETStudentDescription.getText().toString();
            dfDesc.update("Description",studentUpdatedDesc);
            dfDesc.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    Toast.makeText(getContext().getApplicationContext(),"Update Successfully!",Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getContext().getApplicationContext(),"Fail!",Toast.LENGTH_SHORT).show();
                }
            });
            //Toast.makeText(getContext().getApplicationContext(),"Update Successfully!",Toast.LENGTH_SHORT).show();
           // updateStudentDesc(studentUpdatedDesc);
            descDialog.dismiss();
        }));

        descDialog.show();
    }
/*
    private void updateStudentDesc(String studentUpdatedDesc) {

    }*/

    //to calculate the average rating and display them
        /*float sum=0;
        int numRatings = ratings.size();
        for(float rating:ratings){
            sum += rating;
        }
        float AvgRating = sum/numRatings;*/

    public void onViewCreated(View view,@Nullable Bundle savedInstanceState){

    }
}