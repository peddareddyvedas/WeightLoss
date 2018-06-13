package com.vedas.weightloss.Profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.vedas.weightloss.R;

import java.util.ArrayList;

/**
 * Created by Rise on 12/06/2018.
 */

public class Activity_Units extends AppCompatActivity {
    ArrayList<String> kilogramArray;
    String kilogram = "Kilogram";
    ArrayList<String> feetArray;
    String feet = "Feet";
    ArrayList<String> kilometerArray;
    String kilometer = "Kilometer";
    ArrayList<String> literArray;
    String liter = "ml";



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_units);

        init();
        loadkilogramSpinner();
        loadfeetSpinner();
        loadkilometerSpinner();
        loadliterSpinner();

    }

    private void init() {


    }


     /////// load Kilogram Spimnner//////////
    private void loadkilogramSpinner() {
        kilogramArray = new ArrayList<>();
        kilogramArray.add("Kilogram");
        kilogramArray.add("Grams");
        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        Spinner spin = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter aa = new ArrayAdapter(this,R.layout.spinner_item, kilogramArray);
        aa.setDropDownViewResource(R.layout.spinner_item);
        spin.setAdapter(aa);
        if (kilogram != null) {
            int spinnerPosition = aa.getPosition(kilogram);
            spin.setSelection(spinnerPosition);
        }
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                kilogram = kilogramArray.get(i);
                // text.setText(selectedGender);
                Log.e("selectedkg", "call" + kilogram);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
       //////////// load feet spinner////
    private void loadfeetSpinner() {
        feetArray = new ArrayList<>();
        feetArray.add("Feet");
        feetArray.add("Inc");
        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        Spinner spin = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter aa = new ArrayAdapter(this,R.layout.spinner_item, feetArray);
        aa.setDropDownViewResource(R.layout.spinner_item);
        spin.setAdapter(aa);
        if (feet != null) {
            int spinnerPosition = aa.getPosition(feet);
            spin.setSelection(spinnerPosition);
        }
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                feet = feetArray.get(i);
                // text.setText(selectedGender);
                Log.e("selectefeet", "call" + feet);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
////////////// load kilometer spinner/////////////
    private void loadkilometerSpinner() {
        kilometerArray = new ArrayList<>();
        kilometerArray.add("Kilometer");
        kilometerArray.add("Meter");
        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        Spinner spin = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter aa = new ArrayAdapter(this,R.layout.spinner_item, kilometerArray);
        aa.setDropDownViewResource(R.layout.spinner_item);
        spin.setAdapter(aa);
        if (kilometer != null) {
            int spinnerPosition = aa.getPosition(kilometer);
            spin.setSelection(spinnerPosition);
        }
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                kilometer = kilometerArray.get(i);
                // text.setText(selectedGender);
                Log.e("selectekilometer", "call" + kilometer);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    private void loadliterSpinner() {
        literArray = new ArrayList<>();
        literArray.add("ml");
        literArray.add("liters");
        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        Spinner spin = (Spinner) findViewById(R.id.spinner3);
        ArrayAdapter aa = new ArrayAdapter(this,R.layout.spinner_item, literArray);
        aa.setDropDownViewResource(R.layout.spinner_item);
        spin.setAdapter(aa);
        if (liter != null) {
            int spinnerPosition = aa.getPosition(liter);
            spin.setSelection(spinnerPosition);
        }
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                liter = literArray.get(i);
                // text.setText(selectedGender);
                Log.e("selecteliter", "call" + liter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
