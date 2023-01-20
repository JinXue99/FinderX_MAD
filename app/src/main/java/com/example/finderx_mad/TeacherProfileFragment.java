package com.example.finderx_mad;

import static android.app.Activity.RESULT_OK;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TeacherProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TeacherProfileFragment extends Fragment {
    //private FirebaseAuth student;
    private FirebaseUser teacher;

    // Declare Database
    private FirebaseDatabase database;
    private DatabaseReference teacherRef;

    private static final int GalleryPick = 1;
    private StorageReference teacherProfileImageRef;
    private ProgressDialog loading;
    private Uri ImageUri;
    CircleImageView IVProfilePic;


    String TeacherID;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TeacherProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TeacherProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TeacherProfileFragment newInstance(String param1, String param2) {
        TeacherProfileFragment fragment = new TeacherProfileFragment();
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
        View view = inflater.inflate(R.layout.fragment_teacher_profile,null,false);

        TextView TVTeacherName = (TextView) view.findViewById(R.id.TVTeacherName);
        TextView TVTeacherID = (TextView) view.findViewById(R.id.TVTeacherID);
        TextView TVTeacherMajor = (TextView) view.findViewById(R.id.TVTeacherMajor);
        TextView TVTeacherPhone = (TextView) view.findViewById(R.id.TVTeacherPhone);
        TextView TVTeacherEmail =(TextView)  view.findViewById(R.id.TVTeacherEmail);
        IVProfilePic = (CircleImageView)view.findViewById(R.id.IVProfilePic);

        IVProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GalleryPick);
                //choosePicture();
            }
        });

        // Connect to Firebase
        teacher = FirebaseAuth.getInstance().getCurrentUser();
        TeacherID = teacher.getUid();

        //Firebase Storage
        teacherProfileImageRef = FirebaseStorage.getInstance().getReference().child("Teacher Profile");

        //RealTime Database
        database=FirebaseDatabase.getInstance();
        teacherRef=database.getReference("TeacherList").child(TeacherID);
        teacherRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists() && snapshot.hasChildren()){
                    TVTeacherName.setText(snapshot.child("Name").getValue().toString());
                    TVTeacherID.setText(snapshot.child("TeacherID").getValue().toString());
                    TVTeacherEmail.setText(snapshot.child("Email").getValue().toString());
                    TVTeacherMajor.setText(snapshot.child("Majoring").getValue().toString());
                    TVTeacherPhone.setText(snapshot.child("Phone").getValue().toString());
                    if (snapshot.child("image").exists()) {
                        Picasso.get().load(snapshot.child("image").getValue().toString()).into(IVProfilePic);
                    }else{
                        IVProfilePic.setImageResource(R.drawable.avatar_profile);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GalleryPick && resultCode == RESULT_OK && data != null) ;
        {

            Uri ImageUri = data.getData();

            StorageReference filepath = teacherProfileImageRef.child(TeacherID + ".jpg");

            filepath.putFile(ImageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getContext().getApplicationContext(), "Profile Image is uploaded", Toast.LENGTH_SHORT).show();

                        StorageReference imageRef = teacherProfileImageRef.child(TeacherID + ".jpg");

                        imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                final String downloadUrl = uri.toString();
                                teacherRef.child("image").setValue(downloadUrl).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(getContext().getApplicationContext(), "Image saved in database", Toast.LENGTH_SHORT).show();

                                        }else{
                                            Toast.makeText(getContext().getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                });

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });

                    } else {
                        String errorMessage = task.getException().toString();
                        Toast.makeText(getContext().getApplicationContext(), "Error" + errorMessage, Toast.LENGTH_SHORT).show();
                    }
                }
            });



        }
    }
}