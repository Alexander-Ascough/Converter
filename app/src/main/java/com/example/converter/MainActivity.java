package com.example.converter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.ViewAnimator;

public class MainActivity extends AppCompatActivity {

    // Step #1 Define GUI objects
    private RadioButton radioButtonFromKm;
    private RadioButton radioButtonFromMiles;
    private EditText textValue;
    private EditText textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Step #2 Assign widgets to GUI objects
        // You have to cast to correct class type, because findVieById() returns generic
        radioButtonFromKm = (RadioButton) findViewById(R.id.rbFromKm);
        radioButtonFromMiles = (RadioButton) findViewById(R.id.rbFromMiles);
        textValue = (EditText) findViewById(R.id.value);
        textResult = (EditText) findViewById(R.id.result);
    }

    // Step #3 Handle events

    // After defining a method to handle an event, go to the graphical editor,
    // select a widget, scroll through the options until you find the desired event
    // (e.g. onClick), then select the desired method from the drop down

    // "View" objects are stupidly named. When a widget creates an event,
    // the widget must be passed to the event handler.
    // This passage happens through the View object.

    public void handleClick(View view){
        switch (view.getId()){
            case R.id.buttonConvert:
                String result;
                String value = textValue.getText().toString();
                if (value.length() == 0){
                    Context context = getApplicationContext();
                    CharSequence text = "Error: empty value";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else{
                    if(radioButtonFromMiles.isChecked()){
                        result = milesToKm(value);
                    }else{
                        result = kmToMiles(value);
                    }
                    textResult.setText(result);
                }
                break;
            case R.id.buttonReset:
                radioButtonFromKm.setChecked(true);
                radioButtonFromMiles.setChecked(false);
                textValue.setText("");
                textResult.setText("");
        }
    }

    // Step #4 Methods

    private String milesToKm(String miles){
        double m_value = Double.parseDouble(miles);
        double km_value = m_value * 1.609;
        return String.valueOf(km_value);
    }

    private String kmToMiles(String km){
        double k_value = Double.parseDouble(km);
        double m_value = k_value / 1.609;
        return String.valueOf(m_value);
    }

}