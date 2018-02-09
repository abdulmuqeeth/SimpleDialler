package com.example.abdulmuqeethmohammed.activitiesandintents;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    private Button input;
    private Button dial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Binding the interface buttons to corresponding fields
        input=(Button) findViewById(R.id.inputButton);
        dial =(Button) findViewById(R.id.dialButton);

        //Setting up listeners
        input.setOnClickListener(inputButtonListener);
        dial.setOnClickListener(dialButtonListener);

        Log.i("MainActivity", "Completed onCreate Method");
    }


    //Listener for input button
    public View.OnClickListener inputButtonListener= new View.OnClickListener() {

        //Called when input button is pressed
        @Override
        public void onClick(View v) {

            switchToInputNumber();

        }
    };

    //Listener for dial button
    public View.OnClickListener dialButtonListener = new View.OnClickListener() {
        //Called when dial button is pressed
        @Override
        public void onClick(View v) {

        }
    };

    // Method to start InputNumber Activity
    private void switchToInputNumber(){

    }
}
