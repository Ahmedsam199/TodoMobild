package com.example.notbasictodolist;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Todo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Todo extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Todo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Todo.
     */
    // TODO: Rename and change types and number of parameters
    public static Todo newInstance(String param1, String param2) {
        Todo fragment = new Todo();
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

         List<DataModel> dataModelList;
         MyAdapter adapter;
         DBHandler dbHandler;
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_todo, container, false);

       ListView listView =(ListView) rootView.findViewById(R.id.Listview);
        dataModelList = new ArrayList<>();
        dbHandler = new DBHandler(getActivity());

        // Get all the data from the database
        dataModelList = dbHandler.getAllContacts();

        // Create a custom adapter and set it to the ListView
        adapter = new MyAdapter(getActivity(), dataModelList);
        listView.setAdapter(adapter);
        return rootView;
    }
}