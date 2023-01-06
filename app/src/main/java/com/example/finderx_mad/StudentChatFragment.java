package com.example.finderx_mad;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StudentChatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudentChatFragment extends Fragment {

    View chatView;
    private Toolbar myToolbar;
    private ImageButton BtnSendMessage;
    private EditText userMessageInput;
    private ScrollView myScrollView;
    private TextView displayTextMessage;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StudentChatFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StudentChatFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StudentChatFragment newInstance(String param1, String param2) {
        StudentChatFragment fragment = new StudentChatFragment();
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
        chatView = inflater.inflate(R.layout.fragment_student_chat, container, false);

        InitializeField();

        return chatView;

    }



    private void InitializeField() {
        myToolbar = (Toolbar) chatView.findViewById(R.id.group_chat_bar_layout);
        ((AppCompatActivity)getActivity()).setSupportActionBar(myToolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Group Name: ");
        //setSupportActionBar(myToolbar);
        //getSupportActionBar().setTitle("Group Name:");

        BtnSendMessage = (ImageButton) chatView.findViewById(R.id.IBSendMessage);
        userMessageInput = (EditText) chatView.findViewById(R.id.input_group_message);
        displayTextMessage = (TextView) chatView.findViewById(R.id.group_chat_text_display);
        myScrollView = (ScrollView) chatView.findViewById(R.id.my_scroll_view);

    }
}