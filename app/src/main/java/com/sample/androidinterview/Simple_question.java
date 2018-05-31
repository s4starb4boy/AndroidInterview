package com.sample.androidinterview;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class Simple_question extends AppCompatActivity implements View.OnClickListener{

    TextView tvquestion, tvanswer, tvtotallength_yy, tvpresentindex_xx, tvcategory;
    Button bleft, bshowanswer, bright, bspeak, bstop_mute;

    String[] simple_question, simple_answers;
    int index;
    private static final String default_answer = "Press \"A\" button for the Answer.";
    //variable objects for Text to Speech
    TextToSpeech ttsobject_answer, ttsobject_question;
    int result_answer, result_question;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Code Start From Here
        setContentView(R.layout.questions);

        //code to add titlebar

        LinearLayout question_ll = (LinearLayout) findViewById(R.id.questions_title_bar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.questions_title_bar);


        //TextView tialization.
        tvquestion = (TextView) findViewById(R.id.tvquestion);
        tvanswer = (TextView) findViewById(R.id.tvanswer);
        tvtotallength_yy = (TextView) findViewById(R.id.tvyy);
        tvpresentindex_xx = (TextView) findViewById(R.id.tvxx);
        tvcategory = (TextView) findViewById(R.id.tv_questions_titlebar);


        //Buttons initialization
        bleft = (Button) findViewById(R.id.bleft);
        bshowanswer = (Button) findViewById(R.id.bshowanswer);
        bright = (Button) findViewById(R.id.bright);
        bspeak = (Button) findViewById(R.id.bspeak);
        bstop_mute = (Button) findViewById(R.id.bstop_mute);

        //Importing the string array from xml file in values folder.
        simple_question = getResources().getStringArray(R.array.simple_ques);
        simple_answers = getResources().getStringArray(R.array.simple_ans);

        //onClicklistner for three buttons also speak and mute buttons.
        bleft.setOnClickListener(this);
        bshowanswer.setOnClickListener(this);
        bright.setOnClickListener(this);
        bspeak.setOnClickListener(this);
        bstop_mute.setOnClickListener(this);

        //setting value to our variables and TextView
        index = 0;
        tvquestion.setText(simple_question[index]);
        tvanswer.setText(default_answer);
        tvpresentindex_xx.setText(String.valueOf(index+1));
        tvtotallength_yy.setText("/"+String.valueOf(simple_question.length));
        tvcategory.setText("Simple Questions");

        //text to speech object and lister initialization for answers
        ttsobject_answer = new TextToSpeech(Simple_question.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

                if(status == TextToSpeech.SUCCESS){

                    result_answer = ttsobject_answer.setLanguage(Locale.US);
                }else{

                    Toast.makeText(Simple_question.this, "Feature Does not support in your device", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //text to speech object and lister initialization for questions
        ttsobject_question = new TextToSpeech(Simple_question.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

                if(status == TextToSpeech.SUCCESS){

                    result_question = ttsobject_question.setLanguage(Locale.US);
                }else{

                    Toast.makeText(Simple_question.this, "Feature Does not support in your device", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.bleft:

                //stop the speech object if it is already speaking

                if(ttsobject_question != null && ttsobject_answer != null){

                    ttsobject_question.stop();
                    ttsobject_answer.stop();
                }

                tvanswer.setText(default_answer);
                index--;
                if(index == -1){

                    index = simple_question.length -1;

                    tvquestion.setText(simple_question[index]);
                    tvpresentindex_xx.setText(String.valueOf(index + 1));
                }else
                    {
                    tvquestion.setText(simple_question[index]);
                    tvpresentindex_xx.setText(String.valueOf(index + 1));
                }
                break;

            case R.id.bshowanswer:

                tvanswer.setText(simple_answers[index]);
                break;

            case R.id.bright:

                //stop the speech object if it is already speaking

                if(ttsobject_question != null && ttsobject_answer != null){

                    ttsobject_question.stop();
                    ttsobject_answer.stop();
                }

                tvanswer.setText(default_answer);
                index++;

                if(index == simple_question.length){
                    index = 0;
                    tvquestion.setText(simple_question[index]);
                    tvpresentindex_xx.setText(String.valueOf(index+1));
                }else {
                    tvquestion.setText(simple_question[index]);
                    tvpresentindex_xx.setText(String.valueOf(index + 1));
                }
                break;
            case R.id.bspeak:

                if(result_answer == TextToSpeech.LANG_NOT_SUPPORTED || result_answer == TextToSpeech.LANG_MISSING_DATA){

                    Toast.makeText(Simple_question.this, "Feature Does not support in your device", Toast.LENGTH_SHORT).show();

                }else{

                    if(tvanswer.getText().toString().equals(default_answer)){

                        ttsobject_question.speak(simple_question[index], TextToSpeech.QUEUE_FLUSH,null);

                    }else {

                        ttsobject_answer.speak(simple_answers[index], TextToSpeech.QUEUE_FLUSH, null);
                    }
                }

                break;

            case R.id.bstop_mute:

                if(ttsobject_answer != null && ttsobject_question != null){

                    ttsobject_answer.stop();
                    ttsobject_question.stop();
                }

                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(ttsobject_answer != null){

            ttsobject_answer.stop();

            ttsobject_answer.shutdown();
        }

    }
}
