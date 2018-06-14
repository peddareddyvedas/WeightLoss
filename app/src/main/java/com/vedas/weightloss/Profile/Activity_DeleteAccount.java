package com.vedas.weightloss.Profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.vedas.weightloss.R;

import butterknife.ButterKnife;

/**
 * Created by Rise on 13/06/2018.
 */

public class Activity_DeleteAccount extends AppCompatActivity {
    TextView text, text1, text2;
    Button delete, back;
    EditText password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deletaccount);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        text = (TextView) findViewById(R.id.text);
        text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);

      /*  text.setText(Html.fromHtml("<h2><br><p> I Understand that this was permantly delete my Weightloss account,that my information could not be recover,and that this action could't be undone</p>"));
        text1.setText(Html.fromHtml("<h2><br><p> I understand that this was permenently lose access to allof data associated with my profile including food.entries,workout, weight entries and  news feed.  </p>"));
        text2.setText(Html.fromHtml("<h2><br><p> I Understand that this was prementely  delet my Weightloss account,my unused  giftcard can't be recovered. </p>"));
    */
        password = (EditText) findViewById(R.id.password);

        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        delete = (Button) findViewById(R.id.deleteaccount);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
