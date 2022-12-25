package com.example.finderx_mad;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        Spinner spinner = view.findViewById(R.id.sUser);


        ArrayAdapter adapter = new ArrayAdapter<>(
                getContext(),
                R.layout.custom_spinner,
                getResources().getStringArray(R.array.userlist)

        );
        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        spinner.setAdapter(adapter);


        //Set an OnItemSelectedListener
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                String user = adapterView.getItemAtPosition(i).toString();
//                if(user.equals("Student") || user.equals("Teacher")){
//                    //Enable the login button
//                    Button btnLogin = view.findViewById(R.id.btnLogin);
//                    btnLogin.setEnabled(true);
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });

//        //Set and OnClickListener on the login button
//        Button btnLogin = view.findViewById(R.id.btnLogin);
//        View.OnClickListener OCLLogin = new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //Get the selected user from the spinner
//                Spinner spinner = view.findViewById(R.id.sUser);
//                String user = spinner.getSelectedItem().toString();
//                if(user.equals("Student")){
//                    //Navigate to Student Main Page Fragment
//                    Navigation.findNavController(view).navigate(R.id.DestStudentMainPage);
//                }else if(user.equals("Teacher")){

//                }
//
//            }
//        };
//        btnLogin.setOnClickListener(OCLLogin);


        //Set and OnClickListener on the login button
        Button btnLogin = view.findViewById(R.id.btnLogin);
        View.OnClickListener OCLLogin = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Navigate to Student Main Page Fragment
                Navigation.findNavController(view).navigate(R.id.DestStudentMainPage);


            }
        };
        btnLogin.setOnClickListener(OCLLogin);


    }
}