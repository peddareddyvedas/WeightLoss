package com.vedas.weightloss.Profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.vedas.weightloss.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rise on 13/06/2018.
 */

public class Activity_About extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
       ButterKnife.bind(this);
        init();
    }

    private void init() {

    }
}
