package com.vedas.weightloss.Settings;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
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
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.vedas.weightloss.R;
import com.vedas.weightloss.Transition.BaseDetailActivity;

import java.util.ArrayList;

/**
 * Created by VEDAS on 5/5/2018.
 */
public class ActivityLevelActivity extends BaseDetailActivity {
    int type;
    RecyclerView recyclerView;
    ActivityLevel activityLevel;
    ArrayList<String> levelArray;
    ArrayList<String> messageArray;
    int selectedPosition=0;
    private final static int FADE_DURATION = 1000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitylevel);
        setupToolbar();
        loadArrays();
        setupRecyclerView();
        setupWindowAnimations();

    }

    void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        TextView textView = (TextView) toolbar.findViewById(R.id.title);
        textView.setText("Activity Level");
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
                Intent i = new Intent(ActivityLevelActivity.this, WeekGoalActivity.class);
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
        levelArray = new ArrayList<>();
        messageArray = new ArrayList<>();

        levelArray.add("Not very Active");
        levelArray.add("Lightly Active");
        levelArray.add("Active");
        levelArray.add("Very Active");
 /*["Spend most of the day sitting(e.g.bank teller,desk job)",
                "Spend a good part of the day on your feet(e.g.teacher,salesperson)",
                "Spend a good part of the day doing some physical activity(e.g.food server,postal carrier)",
                "Spend most of the day doing physical activity(bike messenger,caepenter)"]*/
        messageArray.add("Spend most of the day sitting(e.g.bank teller,desk job)");
        messageArray.add("Spend a good part of the day on your feet(e.g.teacher,salesperson)");
        messageArray.add("Spend a good part of the day doing some physical activity(e.g.food server,postal carrier)");
        messageArray.add("Spend most of the day doing physical activity(bike messenger,carpenter)");
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
                    .inflate(R.layout.activitylevel_items, parent, false);
            ViewHolder myViewHolder = new ViewHolder(view, ctx);
            return myViewHolder;
        }
        //step 6:-
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.txt_level.setText(levelArray.get(position));
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
            return levelArray.size();
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
                //setScaleAnimation(itemView);
                //loadAnimatonToRecyclerView();
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {

            }
        }
    }
    private void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }
    private void setScaleAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }
    public void loadAnimatonToRecyclerView(){
        // for adding animatio to recyclerview
        int resId = R.anim.layout_animation_fall_down;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getApplicationContext(), resId);
        recyclerView.setLayoutAnimation(animation);

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
