package com.vedas.weightloss.LoginModule;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;

import com.vedas.weightloss.LoginModule.LoginViewActivity;
import com.vedas.weightloss.R;

import butterknife.ButterKnife;

public class SplashScreenViewActivity extends AppCompatActivity {
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splachscreen);
        ButterKnife.bind(this);
        title=(TextView)findViewById(R.id.weightloss) ;

        String text = "<font color=#e71027>Weight</font> <font color=#ffffff>Loss</font>";
        title.setText(Html.fromHtml(text));

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                // showingSuccessOrFailure("Success",circularButton1);
            startActivity(new Intent(getApplicationContext(),LoginViewActivity.class));
            }
        }, 1000);
    }
}