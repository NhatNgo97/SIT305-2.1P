package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Button lengthButton;
    Button weightButton;
    Button temperatureButton;
    TextView measurement1;
    TextView measurement2;
    TextView measurement3;
    TextView measurementValue1;
    TextView measurementValue2;
    TextView measurementValue3;

    EditText myEditText;
    Spinner mySpinner;


    String[] converter = {"Metre", "Celsius", "Kilograms"};
    String[] lengthMeasurements = {"Centimetre", "Foot", "Inch"};
    String[] weightMeasurements = {"Gram", "Ounce(Oz)", "Pound(lb)"};
    String[] tempMeasurements = {"Fahrenheit", "Kelvin", null};
    String[] listMeasurements;
    DecimalFormat df = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //assign buttons
        lengthButton = findViewById(R.id.lengthButton);
        weightButton = findViewById(R.id.weightButton);
        temperatureButton = findViewById(R.id.temperatureButton);

        //assign textviews
        measurement1 = findViewById(R.id.measurement1);
        measurement2 = findViewById(R.id.measurement2);
        measurement3 = findViewById(R.id.measurement3);
        measurementValue1 = findViewById(R.id.measurementValue1);
        measurementValue2 = findViewById(R.id.measurementValue2);
        measurementValue3 = findViewById(R.id.measurementValue3);

        myEditText = findViewById(R.id.myEditText);
        myEditText.setInputType(InputType.TYPE_CLASS_NUMBER);

        mySpinner = findViewById(R.id.mySpinner);
        mySpinner.setOnItemSelectedListener(this);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,converter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(adapter);



        setListenerButtons();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        if(mySpinner.getSelectedItem().toString() == "Metre"){
            listMeasurements = lengthMeasurements;
        } else if(mySpinner.getSelectedItem().toString() == "Celsius"){
            listMeasurements = tempMeasurements;
        } else {
            listMeasurements = weightMeasurements;
        }
        measurement1.setText(listMeasurements[0]);
        measurement2.setText(listMeasurements[1]);
        measurement3.setText(listMeasurements[2]);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void setListenerButtons(){
        lengthButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(myEditText.getText().toString().trim().equals("") ){
                    Toast.makeText(MainActivity.this, "Please enter your value", Toast.LENGTH_SHORT).show();

                } else if (mySpinner.getSelectedItem().toString() == "Metre") {
                    double number = Double.parseDouble(myEditText.getText().toString());
                    double centimeter = number * 100;
                    double foot = number * 3.2808;
                    double inch = number * 39.370079;
                    measurementValue1.setText(df.format(centimeter));
                    measurementValue2.setText(df.format(foot));
                    measurementValue3.setText(df.format(inch));
                }
                else {
                    Toast.makeText(MainActivity.this, "Please select the correct conversion icon.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        weightButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(myEditText.getText().toString().trim().equals("")){
                    Toast.makeText(MainActivity.this, "Please enter your value", Toast.LENGTH_SHORT).show();

                } else if (mySpinner.getSelectedItem().toString() == "Kilograms") {
                    Toast.makeText(MainActivity.this, "Please enter your value", Toast.LENGTH_SHORT).show();
                    double number = Double.parseDouble(myEditText.getText().toString());
                    double gram = number * 1000;
                    double ounce = number * 35.274;
                    double pound = number * 2.20462;
                    measurementValue1.setText(df.format(gram));
                    measurementValue2.setText(df.format(ounce));
                    measurementValue3.setText(df.format(pound));
                }
                else {
                    Toast.makeText(MainActivity.this, "Please select the correct conversion icon.", Toast.LENGTH_SHORT).show();
                }

            }
        });
        temperatureButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(myEditText.getText().toString().trim().equals("")){
                    Toast.makeText(MainActivity.this, "Please enter your value", Toast.LENGTH_SHORT).show();

                } else if (mySpinner.getSelectedItem().toString() == "Celsius") {
                    double number = Double.parseDouble(myEditText.getText().toString());
                    double fahrenheit  = number * 1.8 + 32;
                    double kelvin = number * 273.15;
                    measurementValue1.setText(df.format(fahrenheit));
                    measurementValue2.setText(df.format(kelvin));
                    measurementValue3.setText(null);
                }
                else {
                    Toast.makeText(MainActivity.this, "Please select the correct conversion icon.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}