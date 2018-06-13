package com.vedas.weightloss.LoginModule;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Bundle;
import android.text.Html;
import android.text.method.PasswordTransformationMethod;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.Visibility;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dd.CircularProgressButton;
import com.vedas.weightloss.R;
import com.vedas.weightloss.Settings.PersonalInfoActivity;
import com.vedas.weightloss.Transition.BaseDetailActivity;

import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
public class LoginViewActivity extends BaseDetailActivity {
    TextView title;
    RelativeLayout rl_main;
    private int type;
    EditText userNameTextField, passwordTextField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindData();
        ButterKnife.bind(this);
        setupWindowAnimations();
        rl_main=(RelativeLayout)findViewById(R.id.rl_main);

        passwordTextField = (EditText) findViewById(R.id.edittext2);


        title=(TextView)findViewById(R.id.weightloss) ;
        String text = "<font color=#cc0029>Weight</font> <font color=#ffffff>Loss</font>";
        title.setText(Html.fromHtml(text));

        final Button circularButton1 = (Button) findViewById(R.id.button);
        circularButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(LoginViewActivity.this, PersonalInfoActivity.class);
                i.putExtra(EXTRA_TYPE, TYPE_PROGRAMMATICALLY);
                transitionTo(i);

            }
        });

    }
    @OnCheckedChanged(R.id.chk)
    public void onChecked(boolean checked) {
        if (checked) {
            passwordTextField.setTransformationMethod(null);
        } else {
            passwordTextField.setTransformationMethod(new PasswordTransformationMethod());

        }
        // cursor reset his position so we need set position to the end of text
        passwordTextField.setSelection(passwordTextField.getText().length());
    }
    private void bindData() {
        setContentView(R.layout.activity_login_view);
    }
    private void setupWindowAnimations() {
        Transition transition;

        if (type == TYPE_PROGRAMMATICALLY) {
            transition = buildEnterTransition();
        }  else {
            transition = TransitionInflater.from(this).inflateTransition(R.transition.explode);
        }
        getWindow().setEnterTransition(transition);
    }
    private Visibility buildEnterTransition() {
        Fade enterTransition = new Fade();
        enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
        return enterTransition;
    }


    @OnClick({R.id.signup})
     void signupAction(){
        //finish();
       startActivity(new Intent(getApplicationContext(),RegisterViewActivity.class));
    }
    @OnClick({R.id.forgot})
    void forgotAction(){
        Intent i = new Intent(LoginViewActivity.this, ForgotViewActivity.class);
        i.putExtra(EXTRA_TYPE, TYPE_PROGRAMMATICALLY);
        transitionTo(i);

        /*Intent i = new Intent(getApplicationContext(), ForgotViewActivity.class);
        startActivity(i);*/
    }
    private String simulateSuccessProgress(final CircularProgressButton button) {
        ValueAnimator widthAnimation = ValueAnimator.ofInt(1, 100);
        widthAnimation.setDuration(1500);
        //widthAnimation.setRepeatCount(Animation.INFINITE);
        widthAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        widthAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                button.setProgress(value);
                if (value == 100) {
                    Intent i = new Intent(LoginViewActivity.this, PersonalInfoActivity.class);
                    i.putExtra(EXTRA_TYPE, TYPE_PROGRAMMATICALLY);
                    transitionTo(i);
                    button.setProgress(50);

                }
            }
        });
        widthAnimation.start();
        return "complete!";
    }
    private void showingSuccessOrFailure(String msg,CircularProgressButton circularProgressButton){
        if (msg.equals("Success")){
            circularProgressButton.setVisibility(View.GONE);
        }

    }

    private void simulateErrorProgress(final CircularProgressButton button) {
        ValueAnimator widthAnimation = ValueAnimator.ofInt(1, 100);
        widthAnimation.setDuration(1500);
        widthAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        widthAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                button.setProgress(value);
                /*if (value == 100) {
                    Intent i = new Intent(LoginViewActivity.this, PersonalInfoActivity.class);
                    i.putExtra(EXTRA_TYPE, TYPE_PROGRAMMATICALLY);
                    transitionTo(i);
                }*/
            }
        });
        widthAnimation.start();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {    //when click on phone backbutton
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}