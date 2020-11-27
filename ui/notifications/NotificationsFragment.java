package com.example.testinggg.ui.notifications;

import android.os.Bundle;
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

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    public String number1;
    public boolean pressed1 = false;
    public String number2;
    public boolean pressed2 = false;
    public float total;

    //1 => Add
    //2 => Subtract
    //3 => Multiply
    //4 => Divide
    public int symbol;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        //Set up the variables
        Button one = root.findViewById(R.id.one);
        Button two = root.findViewById(R.id.two);
        Button three = root.findViewById(R.id.three);
        Button four = root.findViewById(R.id.four);
        Button five = root.findViewById(R.id.five);
        Button six = root.findViewById(R.id.six);
        Button seven = root.findViewById(R.id.seven);
        Button eight = root.findViewById(R.id.eight);
        Button nine = root.findViewById(R.id.nine);
        Button zero = root.findViewById(R.id.zero);
        Button clear = root.findViewById(R.id.clear);
        Button add = root.findViewById(R.id.add_function);
        Button subtract = root.findViewById(R.id.subtract_function);
        Button multiply = root.findViewById(R.id.multiply_function);
        Button divide = root.findViewById(R.id.divide_function);
        Button equals = root.findViewById(R.id.equals);

        final TextView screen = root.findViewById(R.id.screen);

        //Display the values when the variables are clicked
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                screen.append("1");
            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                screen.append("2");
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                screen.append("3");
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                screen.append("4");
            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                screen.append("5");
            }
        });

        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                screen.append("6");
            }
        });

        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                screen.append("7");
            }
        });

        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                screen.append("8");
            }
        });

        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                screen.append("9");
            }
        });

        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                screen.append("0");
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                screen.setText("0");
                total=0;
            }
        });

        //When the symbols are clicked then set sybmol
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number1 = (String) screen.getText().toString();
                screen.setText("");
                symbol = 1;
            }
        });

        subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number1 = (String) screen.getText().toString();
                screen.setText("");
                symbol = 2;
            }
        });

        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number1 = (String) screen.getText().toString();
                screen.setText("");
                symbol = 3;
            }
        });

        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number1 = (String) screen.getText().toString();
                screen.setText("");
                symbol = 4;
            }
        });

        //When equals is clicked carry out calculation
        equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number2 = (String) screen.getText().toString();
                screen.setText("");
                if(symbol == 1){
                    total = Float.parseFloat(number1) + Float.parseFloat(number2);
                }
                else if(symbol == 2){
                    total = Float.parseFloat(number1) - Float.parseFloat(number2);
                }
                else if(symbol == 3){
                    total = Float.parseFloat(number1) * Float.parseFloat(number2);
                }
                else if(symbol == 4) {
                    total = Float.parseFloat(number1) / Float.parseFloat(number2);
                }
                screen.setText(total + "");
                symbol=0;
            }
        });


        return root;
    }



}