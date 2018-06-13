package com.vedas.weightloss.Profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.vedas.weightloss.R;

/**
 * Created by Rise on 12/06/2018.
 */

public class Activity_Goals extends AppCompatActivity {
    TextView startingkg,currentweight,goalweight,weeklygoal,activitylevel,caloriesperday;
    Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);

        init();
    }

    private void init() {
        startingkg=(TextView)findViewById(R.id.startingkg);

        currentweight=(TextView)findViewById(R.id.currentweight);

        goalweight=(TextView)findViewById(R.id.goalweight);

        weeklygoal=(TextView)findViewById(R.id.weeklygoal);

        activitylevel=(TextView)findViewById(R.id.activitylevel);

        caloriesperday=(TextView)findViewById(R.id.caloriesperday);
        button=(Button)findViewById(R.id.back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}
