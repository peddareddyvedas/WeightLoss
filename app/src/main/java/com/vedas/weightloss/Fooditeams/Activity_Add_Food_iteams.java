package com.vedas.weightloss.Fooditeams;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vedas.weightloss.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Rise on 24/05/2018.
 */

public class Activity_Add_Food_iteams extends AppCompatActivity {
    Toolbar toolbar;
    ImageView  home,btn_back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfooditems);
        ButterKnife.bind(this);
        setToolbar();
    }



    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btn_back = (ImageView) toolbar.findViewById(R.id.back);
        home = (ImageView) toolbar.findViewById(R.id.toolbar_icon);


            btn_back.setBackgroundResource(R.drawable.ic_back);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        TextView toolbartext = (TextView) toolbar.findViewById(R.id.toolbar_text);
        toolbartext.append("Day1");
    }
    @OnClick({R.id.relative1})
    void relative1() {
    }
    @OnClick({R.id.relative2})
    void relative2(){

        startActivity(new Intent (getApplicationContext(),BreakfastActivity.class));
    }

    @OnClick({R.id.relative3})
    void relative3() {
        startActivity(new Intent (getApplicationContext(),CreateFood.class));

    }
    @OnClick({R.id.relative4})
    void relative4() {
    }
    @OnClick({R.id.relative5})
    void relative5() {
    }
    @OnClick({R.id.relative6})
    void relative6() {
    }
    @OnClick({R.id.relative7})
    void relative7() {
    }
    /*@OnClick({R.id.relative8})
    void relative1() {
    }*/



}
