package com.vedas.weightloss.Settings;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.vedas.weightloss.LoginModule.LoginViewActivity;
import com.vedas.weightloss.R;
import com.vedas.weightloss.Transition.BaseDetailActivity;

import java.util.ArrayList;

/**
 * Created by VEDAS on 5/5/2018.
 */
public class WeekGoalActivity extends BaseDetailActivity {
    int type;
    RecyclerView recyclerView;
    ActivityLevel activityLevel;
    ArrayList<String> kgArray;
    ArrayList<String> messageArray;
    int selectedPosition=0;
    TextInputEditText textInputKgs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekgoal);
        setupToolbar();
        setupWindowAnimations();
        loadArrays();
        setupRecyclerView();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                textInputKgs = (TextInputEditText) findViewById(R.id.goal);
                textInputKgs.setVisibility(View.VISIBLE);
            }
        }, 500);
    }

    void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        TextView textView = (TextView) toolbar.findViewById(R.id.title);
        textView.setText("Weekly Goal");
        Button btn_back=(Button)toolbar.findViewById(R.id.back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        findViewById(R.id.centerImage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(WeekGoalActivity.this, WeekGoalNextActivity.class);
                i.putExtra(EXTRA_TYPE, TYPE_PROGRAMMATICALLY);
                transitionTo(i);
            }
        });
    }

    private void setupRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recylerview);
        activityLevel = new ActivityLevel(getApplication());
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        recyclerView.setAdapter(activityLevel);
    }

    private void loadArrays() {
        kgArray = new ArrayList<>();
        messageArray = new ArrayList<>();

        kgArray.add("Lose 0.25 kg for week");
        kgArray.add("Lose 0.5 kg for week");
        kgArray.add("Lose 0.75 kg for week");
        kgArray.add("Lose 1 kg for week");

        messageArray.add("");
        messageArray.add("Recommended");
        messageArray.add("");
        messageArray.add("");

    }

    // Step 1:-
    public class ActivityLevel extends RecyclerView.Adapter<ActivityLevel.ViewHolder> {
        // step 3:-
        Context ctx;
        ImageView button;
        public ActivityLevel(Context ctx) {
            this.ctx = ctx;
        }
        // step 5:-
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.activitylevelnext_items, parent, false);
            ViewHolder myViewHolder = new ViewHolder(view, ctx);
            return myViewHolder;
        }

        //step 6:-
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.txt_level.setText(kgArray.get(position));
            holder.txt_message.setText(messageArray.get(position));
            if (selectedPosition==position){
                Log.e("selectedPosition","call"+selectedPosition);
                holder.radioButton.setChecked(true);
            }else {
                holder.radioButton.setChecked(false);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedPosition = position;
                    Log.e("pos",""+selectedPosition);
                    notifyDataSetChanged();
                }
            });

        }
        // step 4:-
        @Override
        public int getItemCount() {
            return kgArray.size();
        }


        // Step 2:-
        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            TextView txt_level, txt_message;
            Context ctx;
            RadioButton radioButton;

            public ViewHolder(View itemView, Context ctx) {
                super(itemView);
                this.ctx = ctx;
                txt_level = (TextView) itemView.findViewById(R.id.mode);
                txt_message = (TextView) itemView.findViewById(R.id.txt);
                radioButton=(RadioButton)itemView.findViewById(R.id.radio);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {

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

    @Override
    public void onBackPressed() {    //when click on phone backbutton
        finish();
    }

}
