package com.example.abdulmuqeethmohammed.activitiesandintents;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class InputNumberActivity extends Activity {
    private EditText numberField;
    private String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_number);
        numberField = (EditText) findViewById(R.id.phoneNumberWidget);
        //setting up listener for EditText widget
        numberField.setOnEditorActionListener(numberListener);
    }

    //Listener for EditText widget
    public TextView.OnEditorActionListener numberListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                captureNumber();
                return true;
            } else {
                return false;
            }
        }
    };

    //Method to retrieve the number and perform regex comparison
    private void captureNumber() {
        Intent i = getIntent();
        number = numberField.getText().toString();

        if (number.matches("\\s*\\(\\d{3}\\)\\s\\d{3}\\-\\d{4}\\s*")) {
            Log.i("Regex Match", "True");
            setResult(RESULT_OK, i);
            i.putExtra("number", number.trim());
            //finish the activity
            finish();
        } else {
            Log.i("Regex Match", "False");
            setResult(RESULT_CANCELED, i);
            i.putExtra("number", number.trim());
            //finish the activity
            finish();
        }
    }

}
