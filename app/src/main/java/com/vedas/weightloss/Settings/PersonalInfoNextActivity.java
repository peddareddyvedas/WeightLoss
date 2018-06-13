package com.vedas.weightloss.Settings;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.vedas.weightloss.R;
import com.vedas.weightloss.Transition.BaseDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VEDAS on 5/5/2018.
 */
public class PersonalInfoNextActivity extends BaseDetailActivity {
    int type;
    TextInputEditText textInputHeight, textInputWeight, textInputBMI;
    ArrayList<String> weightMeasures = new ArrayList<>();
    String selectedMeasure = "Kg";
    String selectedWeigthValue = "29";
    String selectedWeight;
    List<String> kgArray;
    List<String> lbsArray;
    /*For height*/
    ArrayList<String> heightMeasures = new ArrayList<>();
    String selectedHeightMeasure = "Feets & Inches";
    List<String> feetArray;
    List<String> inchArray;
    List<String> cmArray;
    String selectedFeetVal = "3", selectedInchValue = "0", selectedCmValue = "93";
    String mAge, mGender;
    PieChart mChart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalinfo_next);
        loadWeightValuesArray();
        setupToolbar();
        setupWindowAnimations();
        loadHeightValuesArray();
        textInputHeight = (TextInputEditText) findViewById(R.id.height);
        textInputWeight = (TextInputEditText) findViewById(R.id.dob);
        textInputBMI = (TextInputEditText) findViewById(R.id.loc);
        mChart = (PieChart) findViewById(R.id.piechart);


        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if (bd != null) {
            mAge = (String) bd.get("age");
            mGender = (String) bd.get("gender");
            Log.e("agegender", "call" + mAge + "" + mGender);
        }

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                textInputHeight.setVisibility(View.VISIBLE);
                textInputWeight.setVisibility(View.VISIBLE);
                textInputBMI.setVisibility(View.VISIBLE);
                mChart.setVisibility(View.VISIBLE);
            }
        }, 500);
        loadTextfeildActions();
        loadPieChart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        TextView textView = (TextView) toolbar.findViewById(R.id.title);
        textView.setText("You");
        Button btn_back = (Button) toolbar.findViewById(R.id.back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        findViewById(R.id.centerImage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculatingBMI();
                Intent i = new Intent(PersonalInfoNextActivity.this, ActivityLevelActivity.class);
                i.putExtra(EXTRA_TYPE, TYPE_PROGRAMMATICALLY);
                transitionTo(i);
            }
        });
    }

    private void loadTextfeildActions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textInputHeight.setShowSoftInputOnFocus(false);
        }
        textInputHeight.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasfocus) {
                if (hasfocus) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(textInputHeight.getWindowToken(), 0);
                    loadHeightMeasureSpinner();
                }

            }
        });
        textInputHeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(textInputHeight.getWindowToken(), 0);
                loadHeightMeasureSpinner();
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textInputWeight.setShowSoftInputOnFocus(false);
        }
        textInputWeight.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasfocus) {
                if (hasfocus) {
                    loadWeightMeasuresSpinner();
                }
            }
        });
        textInputWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadWeightMeasuresSpinner();
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textInputBMI.setShowSoftInputOnFocus(false);
        }

    }

    private void loadPieChart() {
        mChart = (PieChart) findViewById(R.id.piechart);
        mChart.setUsePercentValues(true);

        mChart.setBackgroundColor(Color.rgb(255,255,255));
//        moveOffScreen();
        mChart.setUsePercentValues(false);
        //mChart.getDescription().setEnabled(false);

        mChart.setDrawCenterText(true);
        mChart.setCenterTextTypeface(Typeface.DEFAULT_BOLD);

        mChart.setCenterTextColor(Color.BLACK);
        mChart.setCenterTextSize(18f);
        mChart.setCenterText("BMI:0.0");

        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColor(Color.rgb(255,255,255));

        mChart.setTransparentCircleColor(Color.rgb(255,255,255));
        mChart.setTransparentCircleAlpha(110);

        mChart.setHoleRadius(65f);
        mChart.setTransparentCircleRadius(68f);
        mChart.setDrawSliceText(false);
        mChart.setDrawSlicesUnderHole(true);


        mChart.setRotationEnabled(false);
        mChart.setHighlightPerTapEnabled(false);

        mChart.setMaxAngle(270f); // HALF CHART
        mChart.setRotationAngle(135f);




        mChart.getLegend().setEnabled(false);
        mChart.invalidate();


        ArrayList<PieEntry> yvalues = new ArrayList<PieEntry>();
        yvalues.add(new PieEntry(8f, 0));
        yvalues.add(new PieEntry(15f, 1));
        yvalues.add(new PieEntry(12f, 2));
        yvalues.add(new PieEntry(25f, 3));
        yvalues.add(new PieEntry(23f, 4));
        yvalues.add(new PieEntry(17f, 5));

        PieDataSet dataSet = new PieDataSet(yvalues,"sbabsansb");
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        //set X values
        ArrayList<String> xVals = new ArrayList<String>();

        xVals.add("January");
        xVals.add("February");
        xVals.add("March");
        xVals.add("April");
        xVals.add("May");
        xVals.add("June");

        PieData data = new PieData(dataSet);

        //set value formatter for in percentage term
        data.setValueFormatter(new PercentFormatter());
        data.setDrawValues(false);


        // Default value
        //data.setValueFormatter(new DefaultValueFormatter(0));
        mChart.setData(data);
        mChart.invalidate();

    }

    private void loadHeightMeasureSpinner() {
        heightMeasures = new ArrayList<>();
        heightMeasures.add("Feets & Inches");
        heightMeasures.add("Cm");
        final Dialog mod = new Dialog(PersonalInfoNextActivity.this);
        mod.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mod.setContentView(R.layout.activity_height_picker);
        mod.setCanceledOnTouchOutside(false);
        mod.setCancelable(false);
        mod.show();
        mod.getWindow().setBackgroundDrawableResource(R.drawable.layout_cornerbg);
        final RelativeLayout rl_feetinch = (RelativeLayout) mod.findViewById(R.id.rl_feetinch);
        final RelativeLayout rl_cm = (RelativeLayout) mod.findViewById(R.id.rl_cm);
        final TextView txt_set = (TextView) mod.findViewById(R.id.set);
        txt_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mod.dismiss();
                if (selectedHeightMeasure.equals("Cm")) {
                    textInputHeight.setText(selectedCmValue + " cm");
                } else {
                    textInputHeight.setText(selectedFeetVal + " feet " + selectedInchValue + " inch");
                }

            }
        });
        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        Spinner spin = (Spinner) mod.findViewById(R.id.spinnerHeight);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, heightMeasures);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
        if (selectedHeightMeasure != null) {
            int spinnerPosition = aa.getPosition(selectedHeightMeasure);
            spin.setSelection(spinnerPosition);
        }
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedHeightMeasure = heightMeasures.get(i);
                Log.e("selectedHeightMeasure", "call" + selectedHeightMeasure);
                if (selectedHeightMeasure.equals("Cm")) {
                    rl_feetinch.setVisibility(View.GONE);
                    rl_cm.setVisibility(View.VISIBLE);
                    loadCmSpinnerValues(mod);
                } else {
                    rl_feetinch.setVisibility(View.VISIBLE);
                    rl_cm.setVisibility(View.GONE);
                    loadFeetAndInchSpinnerValues(mod);
                    Log.e("selectedFeetVal", "call" + selectedFeetVal + "call" + selectedInchValue);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void loadFeetAndInchSpinnerValues(final Dialog mod) {
        //feet spinner
        Spinner spin = (Spinner) mod.findViewById(R.id.feetspinner);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, feetArray);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
        Log.e("selectedFeetVal", "call" + selectedFeetVal);

        if (selectedFeetVal != null) {
            Log.e("tempselectedFeetVal", "call" + selectedFeetVal);
            int spinnerPosition = aa.getPosition(selectedFeetVal);
            Log.e("indextempselectedFe", "call" + spinnerPosition);
            spin.setSelection(spinnerPosition);
        }
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedFeetVal = feetArray.get(i);
                String selectedValue = convertFeetToCm(selectedFeetVal + " " + selectedInchValue);
                Log.e("convertCmValue", "call" + selectedValue);
                int index = cmArray.indexOf(selectedValue);
                selectedCmValue = cmArray.get(index);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //inch spinner
        Spinner inchspin = (Spinner) mod.findViewById(R.id.inchspinner);
        ArrayAdapter inchspinaa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, inchArray);
        inchspinaa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inchspin.setAdapter(inchspinaa);
        Log.e("selectedInchValue", "call" + selectedInchValue);
        if (selectedInchValue != null) {
            Log.e("tempselectedInchValue", "call" + selectedInchValue);
            int spinnerPos = inchspinaa.getPosition(selectedInchValue);
            Log.e("indextempselectedin", "call" + spinnerPos);
            inchspin.setSelection(spinnerPos);
        }
        inchspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedInchValue = inchArray.get(i);
                String selectedValue = convertFeetToCm(selectedFeetVal + " " + selectedInchValue);
                Log.e("convertCmValue", "call" + selectedValue);
                int index = cmArray.indexOf(selectedValue);
                selectedCmValue = cmArray.get(index);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void loadCmSpinnerValues(final Dialog mod) {
        //inch spinner
        Spinner inchspin = (Spinner) mod.findViewById(R.id.spinnercm);
        ArrayAdapter inchspinaa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, cmArray);
        inchspinaa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inchspin.setAdapter(inchspinaa);
        if (selectedCmValue != null) {
            int spinnerPosition = inchspinaa.getPosition(selectedCmValue);
            inchspin.setSelection(spinnerPosition);
        }
        inchspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedCmValue = cmArray.get(i);
                String selectedfeeinch = convertCmToFeet(selectedCmValue);
                Log.e("selectedCmValue", "call" + selectedfeeinch);
                String feetinch[] = selectedfeeinch.split(" ");
                int index = feetArray.indexOf(feetinch[0]);
                int index1 = inchArray.indexOf(feetinch[1]);
                selectedFeetVal = feetArray.get(index);
                selectedInchValue = inchArray.get(index1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void loadHeightValuesArray() {
        feetArray = new ArrayList<String>();
        inchArray = new ArrayList<String>();
        cmArray = new ArrayList<String>();
        for (int i = 3; i <= 8; i++) {
            feetArray.add(String.valueOf(i));
        }
        for (int i = 93; i <= 243; i++) {
            cmArray.add(String.valueOf(i));
        }
        for (int i = 0; i <= 11; i++) {
            inchArray.add(String.valueOf(i));
        }
    }

    public String convertCmToFeet(String cmValue) {

        String[] cmValueArray = cmValue.split(" ");

        Double cmVal = Double.parseDouble(cmValueArray[0]);
        Log.e("cm", "call" + cmVal);
        double inchesValue = cmVal * 0.39370;
        int feetValue = (int) (inchesValue / 12);

        int remainInchesValue = (int) (inchesValue % 12);

        Log.e("requiredFeetString", feetValue + "inch" + remainInchesValue);
        return feetValue + " " + remainInchesValue;

    }

    public String convertFeetToCm(String feetValue) {
        Log.e("convertFeetToCm", "" + feetValue);

        String[] feetStrArray = feetValue.split(" ");

        double totalInches = 0.0;
        double feetInches = Double.parseDouble(feetStrArray[0]);
        double inches = Double.parseDouble(feetStrArray[1]);

        Log.e("feetInches", "" + feetInches + "" + inches);
        totalInches = feetInches * 12 + inches;

        Double feetDouble = new Double(totalInches * 2.54);

        int feetint = (int) Math.round(feetDouble);

        if (feetint < 93) {
            feetint = 93;
        }
        if (feetint > 243) {

            feetint = 243;
        }
        Log.e("feetint", "call" + feetint);
        String strValue = String.valueOf(feetint);

        return strValue;
    }

    public void loadWeightMeasuresSpinner() {
        weightMeasures = new ArrayList<>();
        weightMeasures.add("Kg");
        weightMeasures.add("lbs");
        final Dialog mod = new Dialog(PersonalInfoNextActivity.this);
        mod.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mod.setContentView(R.layout.activity_weight_picker);
        mod.setCanceledOnTouchOutside(false);
        mod.setCancelable(false);
        mod.show();
        mod.getWindow().setBackgroundDrawableResource(R.drawable.layout_cornerbg);
        RelativeLayout rl_measure = (RelativeLayout) mod.findViewById(R.id.measure);
        final TextView selected_measure = (TextView) mod.findViewById(R.id.selected_type);
        final TextView txt_set = (TextView) mod.findViewById(R.id.set);
        txt_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mod.dismiss();
                if (selectedWeight != null && selectedWeight.contains("Kg")) {
                    String array[] = selectedWeight.split(" ");
                    selectedWeigthValue = array[0];
                    textInputWeight.setText("" + selectedWeigthValue + " Kg");
                } else {
                    String array[] = selectedWeight.split(" ");
                    selectedWeigthValue = array[0];
                    textInputWeight.setText("" + selectedWeigthValue + " Lbs");

                }

            }
        });
        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        Spinner spin = (Spinner) mod.findViewById(R.id.spinner1);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, weightMeasures);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
        if (selectedMeasure != null) {
            int spinnerPosition = aa.getPosition(selectedMeasure);
            spin.setSelection(spinnerPosition);
        }
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedMeasure = weightMeasures.get(i);
                Log.e("selectedMeasure", "call" + selectedMeasure);
                selected_measure.setText(selectedMeasure);
                loadWeigthSpinnerValues(mod);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        loadWeigthSpinnerValues(mod);
    }

    private void loadWeigthSpinnerValues(final Dialog mod) {
        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        Spinner spin = (Spinner) mod.findViewById(R.id.spinner);
        if (selectedMeasure.equals("Kg")) {
            ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, kgArray);
            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spin.setAdapter(aa);
            if (textInputWeight.getText().toString() != null && selectedWeight != null && selectedWeight.contains("Kg")) {
                selectedWeigthValue = textInputWeight.getText().toString();
            }
            /*int spinnerPosition = aa.getPosition(selectedWeigthValue);
            spin.setSelection(spinnerPosition);*/
        } else {
            ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, lbsArray);
            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spin.setAdapter(aa);
            if (textInputWeight.getText().toString() != null && selectedWeight != null && selectedWeight.contains("Lbs")) {
                selectedWeigthValue = textInputWeight.getText().toString();
            }
            /*int spinnerPosition = aa.getPosition(selectedWeigthValue);
            spin.setSelection(spinnerPosition);*/
        }

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (selectedMeasure.equals("Kg")) {
                    String selectedValue = kgArray.get(i);
                    selectedWeight = selectedValue + " " + "Kg";
                    Log.e("selectedWeigthValue", "call" + selectedWeight);
                    double lbsValue = new Double(kgArray.get(i)) * 2.2046;
                    String selectedValue1 = String.valueOf(Math.round(lbsValue));
                    int index = lbsArray.indexOf(selectedValue1);
                    selectedWeigthValue = lbsArray.get(index);
                    Log.e("selectedWeigth", "call" + selectedWeigthValue);
                } else {
                    String selectedValue = lbsArray.get(i);
                    selectedWeight = selectedValue + " " + "Lbs";
                    double kgValue = new Double(lbsArray.get(i)) * 0.453592;
                    String selectedValue1 = String.valueOf(Math.round(kgValue));
                    int index = kgArray.indexOf(selectedValue1);
                    selectedWeigthValue = kgArray.get(index);
                    Log.e("selectedWeigth", "call" + selectedWeigthValue);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void loadWeightValuesArray() {

        kgArray = new ArrayList<String>();
        lbsArray = new ArrayList<String>();

        Log.e("kg", "call");
        for (int i = 29; i <= 350; i++) {
            kgArray.add(String.valueOf(i));
        }

        for (int j = 63; j <= 772; j++) {
            lbsArray.add(String.valueOf(j));
        }
    }

    private void calculatingBMI() {
        double KgValue, cmValue;
    /*formula
    For men:	BMR = 10 × weight(kg) + 6.25 × height(cm) - 5 × age  + 5
    For women:	BMR = 10 × weight(kg) + 6.25 × height(cm) - 5 × age  - 161*/
        if (mGender != null && mAge != null && textInputHeight.getText().toString() != null && textInputWeight.getText().toString() != null) {
            if (textInputWeight.getText().toString().contains("Lbs")) {
                String a[] = textInputWeight.getText().toString().split(" ");
                double kgValue = new Double(a[0]) * 0.453592;
                String selectedValue1 = String.valueOf(Math.round(kgValue));
                KgValue = Double.parseDouble(selectedValue1);
                Log.e("bmiWeigth", "call" + KgValue);
            } else {
                String a[] = textInputWeight.getText().toString().split(" ");
                KgValue = Double.parseDouble(a[0]);
                Log.e("bmiWeigth1", "call" + KgValue);

            }

            if (textInputHeight.getText().toString().contains("feet")) {
                String feetVal[] = textInputHeight.getText().toString().split(" ");
                String selectedValue = convertFeetToCm(feetVal[0] + " " + feetVal[2]);
                cmValue = Double.parseDouble(selectedValue);
                Log.e("bmiheigth", "call" + cmValue);

            } else {
                String feetVal[] = textInputHeight.getText().toString().split(" ");
                cmValue = Double.parseDouble(feetVal[0]);
                Log.e("bmiHeigth1", "call" + cmValue);
            }
            ///
            double a = 0.0;
            double age = Double.parseDouble(mAge);
            Log.e("callage", "call" + age);

            if (mGender.equals("Female")) {
                a = 10 * KgValue + 6.25 * cmValue - 5 * age - 161;
                Log.e("Femaledbmi", "call" + a);

            } else {
                a = 10 * KgValue + 6.25 * cmValue - 5 * age + 5;
                Log.e("Maledbmi", "call" + a);
            }

        }

    }

    private void setupWindowAnimations() {
        Transition transition;

        if (type == TYPE_PROGRAMMATICALLY) {
            transition = buildEnterTransition();
        } else {
            transition = TransitionInflater.from(this).inflateTransition(R.transition.explode);
        }
        getWindow().setEnterTransition(transition);
    }

    /*private Transition buildEnterTransition() {
        Slide enterTransition = new Slide();
        enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
        enterTransition.setSlideEdge(Gravity.LEFT);
        return enterTransition;
    }*/
    private Transition buildEnterTransition() {
        Explode enterTransition = new Explode();
        enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
        return enterTransition;
    }
}
