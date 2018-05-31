package com.sample.androidinterview;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class FrontPage extends AppCompatActivity implements View.OnClickListener{

    Button btnSimpleQuestion, btnToughQuestion, btnSeeOtherApps, btnRateApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.front_page);

        //code to add titlebar

        LinearLayout frotnpage_titlebar_ll = (LinearLayout) findViewById(R.id.frontpage_title_bar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.frontpage_title_bar);

        btnSimpleQuestion = (Button) findViewById(R.id.bsq);
        btnSimpleQuestion.setOnClickListener(this);
        btnToughQuestion = (Button) findViewById(R.id.btq);
        btnToughQuestion.setOnClickListener(this);
        btnSeeOtherApps = (Button) findViewById(R.id.bseeotherapps);
        btnSeeOtherApps.setOnClickListener(this);
        btnRateApp = (Button) findViewById(R.id.bra);
        btnRateApp.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bsq:
                Intent i = new Intent(getApplicationContext(), Simple_question.class);
                startActivity(i);

                break;
            case R.id.btq:
                Intent j = new Intent(getApplicationContext(), Tough_question.class);
                startActivity(j);

                break;
            case R.id.bseeotherapps:

                try{

                    Uri uri1 = Uri.parse("market://search?q=Sriyank" );
                    Intent goToMarket1 = new Intent(Intent.ACTION_VIEW, uri1);
                    startActivity(goToMarket1);
                }
                catch (ActivityNotFoundException ex){

                    Uri uri1 = Uri.parse("http://play.google.com/store/search?q=Sriyank");
                    Intent goToMarket1 = new Intent(Intent.ACTION_VIEW, uri1);
                    startActivity(goToMarket1);

                }

                break;
            case R.id.bra:

                try{
                //Uri uri1 = Uri.parse("market://details?id=com.sample.androidinterview");
                //an other way to write above line or to get the package name of your app by using builtin function
                Uri uri1 = Uri.parse("market://details?id=" + getPackageName());
                Intent goToMarket1 = new Intent(Intent.ACTION_VIEW, uri1);
                startActivity(goToMarket1);
                }
                catch (ActivityNotFoundException ex){

                    Uri uri1 = Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName());
                    Intent goToMarket1 = new Intent(Intent.ACTION_VIEW, uri1);
                    startActivity(goToMarket1);

                }

                break;
        }
    }
}
