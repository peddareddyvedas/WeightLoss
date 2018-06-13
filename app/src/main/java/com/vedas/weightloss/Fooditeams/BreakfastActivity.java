package com.vedas.weightloss.Fooditeams;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.vedas.weightloss.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

import static com.vedas.weightloss.Fooditeams.BreakfastAdapter.selectedLanguage;
import static com.vedas.weightloss.R.id.spinner;

public class BreakfastActivity extends AppCompatActivity {

    ArrayList<String> name = new ArrayList<>();
    ArrayList<String> units = new ArrayList<>();

    RecyclerView addRecyclerView;
    View view;
    static int selectedPosition = -1;
    DeviceSearch adapter;
    Toolbar toolbar;
    ImageView  home,btn_back;


    private FloatingActionButton fab;
    EditText searchBox;
    ImageView cancel;
    String selectedLanguage, caloriesedit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_breakfast);
        ButterKnife.bind(this);
        init();
        setToolbar();
    }


    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btn_back = (ImageView) toolbar.findViewById(R.id.back);
        home = (ImageView) toolbar.findViewById(R.id.toolbar_icon);


        btn_back.setBackgroundResource(R.drawable.ic_back);


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        TextView toolbartext = (TextView) toolbar.findViewById(R.id.toolbar_text);
        toolbartext.append("Breakfast");
    }

    private void init() {
        name.add("Device Name");
        name.add("upma");
        name.add(" Name");
        name.add("Device Name");
        name.add("pasaratu");
        name.add(" chatni");
        name.add(" Name");
        name.add("samosa");
        name.add(" kichidi");
        name.add(" Name");
        name.add("panipur");
        name.add(" roti");
        name.add("dosa Name");
        name.add("pongal");
        name.add(" rihte");
        name.add("rice Name");
        name.add("upit");
        name.add(" curry");
        addRecyclerView = (RecyclerView) findViewById(R.id.recyclerviewbeakfast);
        //  resultsTableViewCell = new HomeActivityViewController.RecentResultsTableViewCell(time, getApplication());
        adapter = new DeviceSearch(name, getApplicationContext());
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        addRecyclerView.setLayoutManager(horizontalLayoutManager);
        addRecyclerView.setAdapter(adapter);

        fab = (FloatingActionButton) findViewById(R.id.fab);

      /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Breakfast.this, CreateFood.class);
                startActivity(intent);
            }
        });*/
        searchBox = (EditText) findViewById(R.id.searchBox);
        cancel = (ImageView) findViewById(R.id.img_cancel);


        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString().toLowerCase());
                cancel.setVisibility(View.VISIBLE);
                if (searchBox.getText().toString().isEmpty()) {
                    cancel.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString().toLowerCase());
                cancel.setVisibility(View.VISIBLE);
                if (searchBox.getText().toString().isEmpty()) {
                    cancel.setVisibility(View.GONE);
                }

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (searchBox.getText().toString().length() > 0) {
                    searchBox.setText("");
                }
            }
        });
    }

    private void filter(String text) {
        Log.e("filtertext", "call" + text);
        //new array list that will hold the filtered data
        ArrayList<String> breakfastList = new ArrayList<>();
        for (int i = 0; i < name.size(); i++) {
            String itemName = name.get(i).toLowerCase();
            Log.e("nameFilter", "" + name);

            if (itemName.contains(text)) {
                breakfastList.add(name.get(i));
            }
            Log.e("filtername", "" + itemName + "  " + text);
        }

        adapter.getFilter(breakfastList);
        adapter.notifyDataSetChanged();


    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("onResume", "call");
        adapter.notifyDataSetChanged();

    }


    // Step 1:-
    public class DeviceSearch extends RecyclerView.Adapter<DeviceSearch.ViewHolder> {

        // step 3:-
        ArrayList<String> arrayList = new ArrayList<>();
        Context ctx;

        public DeviceSearch(ArrayList<String> arrayList, Context ctx) {
            this.ctx = ctx;
            this.arrayList = arrayList;
        }


        public void getFilter(ArrayList<String> filterList) {
            name = new ArrayList<>();
            name.addAll(filterList);
            adapter.notifyDataSetChanged();
        }

        // step 5:-
        @Override
        public DeviceSearch.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_breakfast, parent, false);


            DeviceSearch.ViewHolder myViewHolder = new DeviceSearch.ViewHolder(view, ctx, arrayList);
            return myViewHolder;


        }

        //step 6:-
        @Override
        public void onBindViewHolder(final DeviceSearch.ViewHolder holder, final int position) {

            holder.userName.setText(name.get(position));

            if (selectedPosition == position) {
                Log.e("if", "called");
            } else {
                Log.e("else", "called");

            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedPosition = position;
                    Log.e("pos", "" + selectedPosition);

                    selectedLanguage = holder.userName.getText().toString();
                    Log.e("text", "" + selectedLanguage);

                    loadWeightMeasuresSpinner();

                    //notifyDataSetChanged();

                }
            });


        }

        // step 4:-

        @Override
        public int getItemCount() {
            return name.size();
        }


        // Step 2:-
        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


            TextView userName, calories;
            ArrayList<String> arrayList = new ArrayList<String>();
            Context ctx;

            public ViewHolder(View itemView, Context ctx, final ArrayList<String> arrayList) {
                super(itemView);

                this.arrayList = arrayList;
                this.ctx = ctx;
                userName = (TextView) itemView.findViewById(R.id.itemName);
                calories = (TextView) itemView.findViewById(R.id.calories);
                calories.setText(""+caloriesedit);
                Log.e("calaor",""+calories);
                itemView.setOnClickListener(this);

            }

            @Override
            public void onClick(View v) {

            }
        }
    }

    public void loadWeightMeasuresSpinner() {

        final Dialog mod = new Dialog(BreakfastActivity.this);
        mod.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mod.setContentView(R.layout.activity_servingsize);
        mod.setCanceledOnTouchOutside(false);
        mod.setCancelable(false);
        mod.show();
        mod.getWindow().setBackgroundDrawableResource(R.drawable.layout_cornerbg);
        RelativeLayout rl_measure = (RelativeLayout) mod.findViewById(R.id.measure);
        final TextView selected_measure = (TextView) mod.findViewById(R.id.selected_type);

        final TextView iteam = (TextView) mod.findViewById(R.id.iteam);
        iteam.setText("" + selectedLanguage);

        final TextView txt_set = (TextView) mod.findViewById(R.id.set);

        final EditText caloriestext = (EditText) mod.findViewById(R.id.caloriesedittext);

        caloriesedit=caloriestext.getText().toString();

        //Log.e("text", "" + calories);

        txt_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                mod.dismiss();


            }
        });

        Spinner spin = (Spinner) mod.findViewById(R.id.spinnersize);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("0");
        categories.add("1");
        categories.add("2");
        categories.add("3");
        categories.add("4");
        categories.add("5");
        categories.add("6");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spin.setAdapter(dataAdapter);
        //spinnerunits

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();

                // Showing selected spinner item
                Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        Spinner spin1 = (Spinner) mod.findViewById(R.id.spinnerunits);

        // Spinner Drop down elements
        List<String> categories1 = new ArrayList<String>();
        categories1.add("Units");
        categories1.add("1");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories1);

        // Drop down layout style - list view with radio button
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spin1.setAdapter(dataAdapter1);
        //spinnerunits

        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();

                // Showing selected spinner item
                Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        Spinner spin2 = (Spinner) mod.findViewById(R.id.spinnerpercontainer);

        // Spinner Drop down elements
        List<String> categories2 = new ArrayList<String>();
        categories2.add("0");
        categories2.add("1");
        categories2.add("2");

        categories2.add("3");

        categories2.add("4");

        categories2.add("5");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);

        // Drop down layout style - list view with radio button
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spin2.setAdapter(dataAdapter2);

        //spinnerunits
        spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();

                // Showing selected spinner item
                Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    @Override
    public void onBackPressed() {
        // stop = true;
        //startActivity(new Intent(getApplicationContext(), MyDeviceViewController.class));
        finish();
        super.onBackPressed();
    }


}


