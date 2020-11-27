package com.example.testinggg.ui.home;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.testinggg.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private TextView addToDo;
    private TextView textHome;
    private Button add;
    private Button delete;
    private Button clear;
    com.example.testinggg.DatabaseHelper mDatabaseHelper;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        //Set up the items
        textHome = root.findViewById(R.id.text_home);
        addToDo = root.findViewById(R.id.addToDo);
        add = root.findViewById(R.id.add);
        delete = root.findViewById(R.id.delete);
        clear = root.findViewById(R.id.clear);
        mDatabaseHelper = new com.example.testinggg.DatabaseHelper(getActivity());

        //Make the list scrollable
        textHome.setMovementMethod(new ScrollingMovementMethod());

        //Have the do to list displayed on creation
        Cursor data = mDatabaseHelper.getData();
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext()){
            listData.add(data.getString(1));
            textHome.append(data.getString(1));
            textHome.append("\n");
        }

        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        //Use add function of database
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text = addToDo.getText().toString();

                //Check the entry is not empty
                if(!(text.equals(""))) {

                    textHome.setText("");

                    //Add database entry
                    mDatabaseHelper.addData(text);

                    //Add items to array list and then append to textHome
                    Cursor data = mDatabaseHelper.getData();
                    ArrayList<String> listData = new ArrayList<>();
                    while (data.moveToNext()) {
                        listData.add(data.getString(1));
                        textHome.append(data.getString(1));
                        textHome.append("\n");
                    }

                    //Clear the value of addToDo
                    addToDo.setText("");

                }//end if

            }
        });

        //Use delete function of database
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text = addToDo.getText().toString();

                //Check the entry is not empty
                if(!(text.equals(""))) {

                    textHome.setText("");

                    //Delete database entry
                    mDatabaseHelper.deleteData(text);

                    //Add items to array list and then append to textHome
                    Cursor data = mDatabaseHelper.getData();
                    ArrayList<String> listData = new ArrayList<>();
                    while (data.moveToNext()) {
                        listData.add(data.getString(1));
                        textHome.append(data.getString(1));
                        textHome.append("\n");
                    }

                    //Clear the value of addToDo
                    addToDo.setText("");

                }//end if

            }
        });

        //Use clear function of database
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text = addToDo.getText().toString();

                    textHome.setText("");

                    //Clear all database entries
                    mDatabaseHelper.clearData();

                    //Add items to array list and then append to textHome
                    Cursor data = mDatabaseHelper.getData();
                    ArrayList<String> listData = new ArrayList<>();
                    while (data.moveToNext()) {
                        listData.add(data.getString(1));
                        textHome.append(data.getString(1));
                        textHome.append("\n");
                    }

                    //Clear the value of addToDo
                    addToDo.setText("");

            }
        });

        return root;

    }
}