package com.vedas.weightloss.Dashboard;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vedas.weightloss.Fooditeams.Activity_Add_Food_iteams;
import com.vedas.weightloss.R;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.vedas.weightloss.R.id.view;

public class DashBordFragment extends Fragment {
    ArrayList<String> name = new ArrayList<>();
    ArrayList<String> name1 = new ArrayList<>();
    ArrayList<String> name2 = new ArrayList<>();

    Random myRandom;
    int startRange = 10, endRange = 99;
    RecyclerView addRecyclerView;
    static int selectedPosition = -1;
    DeviceSearch adapter;
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dashbord_fragment, container, false);

        ButterKnife.bind(this, view);
        init();
        return view;

    }

    private void init() {

        name.add("500cal");
        name.add("500cal");
        name.add("500cal");
        name.add("500cal");
        name.add("500cal");
        name.add("500cal");
        name.add("500cal");
        name.add("500cal");
        name.add("500cal");
        name.add("500cal");
        name.add("500cal");
        name.add("500cal");
        name.add("500cal");
        name.add("500cal");
        name.add("500cal");
        name.add("500cal");
        name.add("500cal");
        name.add("500cal");
        name.add("500cal");

        name1.add("200cal");
        name1.add("200cal");
        name1.add("200cal");
        name1.add("200cal");
        name1.add("200cal");
        name1.add("200cal");
        name1.add("200cal");
        name1.add("200cal");
        name1.add("200cal");
        name1.add("200cal");
        name1.add("200cal");
        name1.add("200cal");
        name1.add("200cal");
        name1.add("200cal");
        name1.add("200cal");
        name1.add("200cal");
        name1.add("200cal");
        name1.add("200cal");
        name1.add("200cal");

        name2.add("200calremaining");
        name2.add("200calremaining");
        name2.add("200calremaining");
        name2.add("200calremaining");
        name2.add("200calremaining");
        name2.add("200calremaining");
        name2.add("200calremaining");
        name2.add("200calremaining");
        name2.add("200calremaining");
        name2.add("200calremaining");
        name2.add("200calremaining");
        name2.add("200calremaining");
        name2.add("200calremaining");
        name2.add("200calremaining");
        name2.add("200calremaining");
        name2.add("200calremaining");
        name2.add("200calremaining");
        name2.add("200calremaining");
        name2.add("200calremaining");


        myRandom = new Random();

        addRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerviewcalories);
        adapter = new DeviceSearch(name, getActivity());
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        addRecyclerView.setLayoutManager(horizontalLayoutManager);
        addRecyclerView.setAdapter(adapter);
        addRecyclerView.setHasFixedSize(true);
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

        // step 5:-
        @Override
        public DeviceSearch.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.devicecardlayout, parent, false);


            DeviceSearch.ViewHolder myViewHolder = new DeviceSearch.ViewHolder(view, ctx, arrayList);
            return myViewHolder;


        }

        //step 6:-
        @Override
        public void onBindViewHolder(final DeviceSearch.ViewHolder holder, final int position) {

            holder.userName.setText(name.get(position));
            holder.calories.setText(name1.get(position));
            holder.relative.setBackgroundColor(getRandomColor());
            holder.textViewnumber.setText(String.format("%02d", position + 1));
            holder.caloriesremaining.setText(name2.get(position));

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

                    if(position == 7){
                        holder.image.setVisibility(View.VISIBLE);
                        holder.image.setBackgroundResource(R.drawable.ic_logo);
                        Log.e("text", "" + position);

                    }
                    Intent i = new Intent(getActivity(), Activity_Add_Food_iteams.class);
                    startActivity(i);

                    /*selectedLanguage = holder.userName.getText().toString();
                        Log.e("text", "" + selectedLanguage);

						loadWeightMeasuresSpinner();
*/
                    //notifyDataSetChanged();

                }
            });


        }

        // step 4:-
        @Override
        public int getItemCount() {

            Log.e("listarray", "" + arrayList.size());
            return arrayList.size();
        }


        // Step 2:-
        class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


            TextView userName, calories, textView, textViewnumber, caloriesremaining;
            RelativeLayout relative;
            ImageView image;
            ArrayList<String> arrayList = new ArrayList<String>();
            Context ctx;

            public ViewHolder(View itemView, Context ctx, final ArrayList<String> arrayList) {
                super(itemView);

                this.arrayList = arrayList;
                this.ctx = ctx;
                userName = (TextView) itemView.findViewById(R.id.itemName);
                calories = (TextView) itemView.findViewById(R.id.calories);
                textView = (TextView) itemView.findViewById(R.id.textView);
                relative = (RelativeLayout) itemView.findViewById(R.id.relative);
                textViewnumber = (TextView) itemView.findViewById(R.id.textView);
                caloriesremaining = (TextView) itemView.findViewById(R.id.caloriesremaining);
                image=(ImageView) itemView.findViewById(R.id.image);

                itemView.setOnClickListener(this);

            }

            @Override
            public void onClick(View v) {

            }
        }
    }

    private int getRandomColor() {
        SecureRandom rgen = new SecureRandom();
        return Color.HSVToColor(150, new float[]{
                rgen.nextInt(359), 1, 1
        });
    }

}



