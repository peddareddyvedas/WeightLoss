package com.vedas.weightloss.LoginModule;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.transition.Explode;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Gravity;
import android.view.View;

import com.goodiebag.pinview.Pinview;
import com.vedas.weightloss.LoginModule.NewPasswordViewActivity;
import com.vedas.weightloss.R;
import com.vedas.weightloss.Transition.BaseDetailActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
public class VerificationViewActivity extends BaseDetailActivity {
    int type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        type = getIntent().getExtras().getInt(EXTRA_TYPE);
        setupWindowAnimations();
        ButterKnife.bind(this);

        final View contextView = findViewById(R.id.context_view);
        final Context context = contextView.getContext();
        findViewById(R.id.resend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Snackbar.make(contextView, R.string.item_removed_message, Snackbar.LENGTH_LONG)
                        .setActionTextColor(Color.CYAN)
                        .setAction(R.string.undo, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // Respond to the click, such as by undoing the modification that caused
                                // this message to be displayed
                             //   startActivity(new Intent(getApplicationContext(),LoginViewActivity.class));
                            };
                        }).show();
            }
        });
        Pinview pinview = (Pinview) findViewById(R.id.pinview);
        pinview.setPinViewEventListener(new Pinview.PinViewEventListener() {
            @Override
            public void onDataEntered(Pinview pinview, boolean fromUser) {
                //Make api calls here or what not
              //  Toast.makeText(getApplicationContext(), pinview.getValue(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void setupWindowAnimations() {
        Transition transition;

        if (type == TYPE_PROGRAMMATICALLY) {
            transition = buildEnterTransition();
        }  else {
            transition = TransitionInflater.from(this).inflateTransition(R.transition.slide);
        }
        getWindow().setEnterTransition(transition);
    }
    /*private Transition buildEnterTransition() {
        Slide enterTransition = new Slide();
        enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
        enterTransition.setSlideEdge(Gravity.RIGHT);
        return enterTransition;
    }*/
    private Transition buildEnterTransition() {
        Explode enterTransition = new Explode();
        enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_medium));
        return enterTransition;
    }
    @OnClick({R.id.next_btn})
    void newtAction(){
        finish();
        Intent i = new Intent(getApplicationContext(), NewPasswordViewActivity.class);
        startActivity(i);
    }

    @OnClick({R.id.resend})
    void resendActon(){

    }
}