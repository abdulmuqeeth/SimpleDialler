package com.example.abdulmuqeethmohammed.activitiesandintents;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    private Button input;
    private Button dial;
    private final int REQUEST_CODE =100;
    private String uri = "tel:";
    private String number;
    private Boolean correctNumberFlag=false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Binding the interface buttons to corresponding fields
        input=(Button) findViewById(R.id.inputButton);
        dial =(Button) findViewById(R.id.dialButton);

        //Setting up listeners
        input.setOnClickListener(inputButtonListener);
        dial.setOnClickListener(dialButtonListener);

        if(number==null){
            dial.setEnabled(false);
        }

        Log.i("MainActivity", "Completed onCreate Method");
    }


    //Listener for input button
    private View.OnClickListener inputButtonListener= new View.OnClickListener() {

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
            dial();
        }
    };

    // Method to start InputNumber Activity
    private void switchToInputNumber(){
         Intent i = new Intent(MainActivity.this, InputNumberActivity.class);
         MainActivity.this.startActivityForResult(i,REQUEST_CODE);
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data ){
        super.onActivityResult(requestCode,resultCode,data);
        Log.i("OnActivityResult", "True");

        if(requestCode==REQUEST_CODE){
            Log.i("Request Code", "Good");
            if (resultCode==RESULT_OK){
                Log.i("Result Code", "OK");
                correctNumberFlag =true;
                try{
                    number = data.getStringExtra("number");
                    dial.setEnabled(true);
                }catch (NullPointerException e){
                    Log.i("Exception","Number is null");
                }
            }
            else if(resultCode==RESULT_CANCELED){
                Log.i("Result Code", "Cancelled");
                correctNumberFlag =false;
                try{
                    number = data.getStringExtra("number");
                    dial.setEnabled(true);
                }catch (NullPointerException e){
                    Log.i("Exception","Number is null");
                }

            }
            else{
                Log.i("Result Code", "incorrect");
            }

        }
        else{
            Log.i("Request Code", "Bad");
        }
    }

    private void dial(){
        if(correctNumberFlag==true){
            Intent i = new Intent(Intent.ACTION_DIAL);
            i.setData(Uri.parse(uri.concat(number)));
            MainActivity.this.startActivity(i);
        }
        else{
            Toast.makeText(this, "Invalid Number:"+number , Toast.LENGTH_SHORT).show();
        }
    }
}
