package com.vedas.weightloss.Settings;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.shawnlin.numberpicker.NumberPicker;
import com.vedas.weightloss.Calender.MainActivity;
import com.vedas.weightloss.R;
import com.vedas.weightloss.Transition.BaseDetailActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by VEDAS on 5/5/2018.
 */
public class PersonalInfoActivity extends BaseDetailActivity {
    int type;
    TextInputEditText textInputgender, textInputdob, textInputloc, textInputzip;
    ArrayList<String> genderArray;
    String selectedGender = "Female";
    SimpleDateFormat df, df_year, weekFormatter;
    String mage;
    ImageView imageview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalinfo);
        setupToolbar();
        textInputdob = (TextInputEditText) findViewById(R.id.dob);
        textInputzip = (TextInputEditText) findViewById(R.id.zip);
        textInputloc = (TextInputEditText) findViewById(R.id.loc);
        df = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        df_year = new SimpleDateFormat("yyyy", Locale.ENGLISH);
        weekFormatter = new SimpleDateFormat("E,dd", Locale.ENGLISH);
       // imageview=(ImageView)findViewById(R.id.imageview);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                textInputdob.setVisibility(View.VISIBLE);
                textInputzip.setVisibility(View.VISIBLE);
                textInputloc.setVisibility(View.VISIBLE);
            }
        }, 500);
        loadtextFeildActions();
        loadGenderSpinner();
        setupWindowAnimations();
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
                Intent i = new Intent(PersonalInfoActivity.this, PersonalInfoNextActivity.class);
                i.putExtra(EXTRA_TYPE, TYPE_PROGRAMMATICALLY);
                i.putExtra("age",mage);
                i.putExtra("gender",selectedGender);
                transitionTo(i);
            }
        });


    }

    private void loadtextFeildActions() {
        textInputdob.setShowSoftInputOnFocus(false);
        textInputdob.
                setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean hasfocus) {
                        if (hasfocus) {
                            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(textInputdob.getWindowToken(), 0);
                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            startActivityForResult(i, 1);
                        }
                    }
                });
        textInputdob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivityForResult(i, 1);
            }
        });
        textInputzip.setShowSoftInputOnFocus(false);

        textInputloc.setShowSoftInputOnFocus(false);
    }

    private void loadGenderSpinner() {
        genderArray = new ArrayList<>();
        genderArray.add("Female");
        genderArray.add("Male");
        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        Spinner spin = (Spinner) findViewById(R.id.spinnergender);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, genderArray);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
        if (selectedGender != null) {
            int spinnerPosition = aa.getPosition(selectedGender);
            spin.setSelection(spinnerPosition);
        }
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedGender = genderArray.get(i);
               // text.setText(selectedGender);
                Log.e("selectedGender", "call" + selectedGender);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String strEditText = data.getStringExtra("editTextValue");
                Date selectedDateOject = null;

                try {
                    selectedDateOject = df.parse(strEditText);
                    textInputdob.setText("" + df.format(selectedDateOject));
                    Log.e("onActivityResult", "call" + df_year.format(selectedDateOject));
                    Log.e("onActivityResult", "call" + weekFormatter.format(selectedDateOject));
                    String birthdate = df.format(selectedDateOject);
                    String[] fullDate = birthdate.split("-");
                    int year = Integer.parseInt(fullDate[0]);
                    int month = Integer.parseInt(fullDate[1]);
                    int day = Integer.parseInt(fullDate[2]);
                     mage = calculatingAge(year, month, day);
                    Log.e("age", "call" + mage);

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Log.e("onActivityResult", "call" + strEditText);

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

    public String calculatingAge(int year, int month, int day) {
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();
        dob.set(year, month, day);
        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }
        Integer ageInt = new Integer(age);
        String ageS = ageInt.toString();
        return ageS;
    }


    @Override
    public void onBackPressed() {    //when click on phone backbutton
        /*Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);*/
        finish();
    }

}
