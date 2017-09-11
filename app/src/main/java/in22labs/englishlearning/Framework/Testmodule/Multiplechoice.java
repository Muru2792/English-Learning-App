package in22labs.englishlearning.Framework.Testmodule;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import in22labs.englishlearning.R;

public class Multiplechoice extends AppCompatActivity  implements TextToSpeech.OnInitListener {
    LinearLayout lt_ch1, lt_ch2, lt_ch3, lt_ch4;
    ImageView btn_ch1, btn_ch2, btn_ch3, btn_ch4;
    TextView txt_Question, txt_ch1, txt_ch2, txt_ch3, txt_ch4;
    ArrayList<String> listQuestion, listCh1, listCh2, listCh3, listCh4, listAns;
    String st_ques1, stques2, st_ques3;
    Button btn_next;
    int score=0;
    Animation shake1, shake;
    CountDownTimer timer=null;
    private int MY_DATA_CHECK_CODE = 0;
    TextToSpeech t1;
    int tQ=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplechoice);

        t1=new TextToSpeech(this, this);

        txt_Question=(TextView)findViewById(R.id.MCQ_question);

        txt_ch1=(TextView)findViewById(R.id.MCQ_ch1_txt);
        btn_ch1=(ImageView)findViewById(R.id.MCQ_ch1_btn);
        lt_ch1=(LinearLayout)findViewById(R.id.MCQ_ch1_lt);


        txt_ch2=(TextView)findViewById(R.id.MCQ_ch2_txt);
        btn_ch2=(ImageView)findViewById(R.id.MCQ_ch2_btn);
        lt_ch2=(LinearLayout)findViewById(R.id.MCQ_ch2_lt);

        txt_ch3=(TextView)findViewById(R.id.MCQ_ch3_txt);
        btn_ch3=(ImageView)findViewById(R.id.MCQ_ch3_btn);
        lt_ch3=(LinearLayout)findViewById(R.id.MCQ_ch3_lt);

        txt_ch4=(TextView)findViewById(R.id.MCQ_ch4_txt);
        btn_ch4=(ImageView)findViewById(R.id.MCQ_ch4_btn);
        lt_ch4=(LinearLayout)findViewById(R.id.MCQ_ch4_lt);

        btn_next=(Button) findViewById(R.id.btn_teststart_mqc);

        st_ques1= "“என் பெயர் Murugan.” இதன் சரியான ஆங்கில மொழிபெயர்ப்பை தேர்வு செய்யுங்கள்";
        stques2 = "பொருத்தமான வார்த்தையாய் தேர்ந்தெடுத்து வெற்றிடத்தை நிரப்பவும் “My Name ______ Murugan”";
        st_ques3 = "“நான் Murugan.” என்பதன் ஆங்கிலச் மொழிபெயர்ப்பைத் தேர்வு செய்யவும்";

        listQuestion=new ArrayList<>();
        listQuestion.add(st_ques1);
        listQuestion.add(stques2);
        listQuestion.add(st_ques3);
        listQuestion.add("“ஹலோ / வணக்கம்” இதன் சரியான ஆங்கிலச் மொழிபெயர்ப்பை தேர்வு செய்யுங்கள்");
        listQuestion.add("“வணக்கம்! நான் Murugan.”");


        listCh1= new ArrayList<>();
        listCh2= new ArrayList<>();
        listCh3=new ArrayList<>();
        listCh4= new ArrayList<>();


        listCh1.add("My Name are Murugan");
        listCh2.add("Myself Murugan");
        listCh3.add("My name is Murugan");
        listCh4.add("Mine name is Murugan");


        listCh1.add("has");
        listCh2.add("am");
        listCh3.add("is");
        listCh4.add("is the");

        listCh1.add("Myself Murugan.");
        listCh2.add("I am the Murugan.");
        listCh3.add("I am Murugan.");
        listCh4.add("I am a Murugan.");


        listCh1.add("Hi");
        listCh2.add("Good Morning");
        listCh3.add("Good");
        listCh4.add("Hello");


        listCh1.add("Hello! I am Murugan");
        listCh2.add("Hello! Myself Murugan");
        listCh3.add("Hello! I is Murugan");
        listCh4.add("Hello! I am a Murugan.");

        listAns=new ArrayList<>();
        listAns.add("My name is Murugan");
        listAns.add("is");
        listAns.add("I am Murugan.");
        listAns.add("Hello");
        listAns.add("Hello! I am Murugan");

        shake1 = AnimationUtils.loadAnimation(this, R.anim.shake_view1);
        shake = AnimationUtils.loadAnimation(this, R.anim.shake_view);

        txt_Question.setText(listQuestion.get(tQ));
        txt_ch1.setText(listCh1.get(tQ));
        txt_ch2.setText(listCh2.get(tQ));
        txt_ch3.setText(listCh3.get(tQ));
        txt_ch4.setText(listCh4.get(tQ));

        timer= new CountDownTimer(3000, 1000) {

            @SuppressLint("DefaultLocale")
            public void onTick(long millisUntilFinished) {
                long mils = millisUntilFinished;

                String hms=String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(mils),
                        TimeUnit.MILLISECONDS.toMinutes(mils)-TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(mils)),
                        TimeUnit.MILLISECONDS.toSeconds(mils)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(mils)) );
                System.out.println(hms);

            }

            public void onFinish() {
                lt_ch1.startAnimation(shake);
                lt_ch2.startAnimation(shake1);
                lt_ch3.startAnimation(shake);
                lt_ch4.startAnimation(shake1);
                timer.start();
            }

        }.start();




        lt_ch1.performClick();
        lt_ch2.performClick();
        lt_ch3.performClick();
        lt_ch4.performClick();
        lt_ch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSpeak = listCh1.get(tQ);

                t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                btn_ch1.setImageDrawable(getDrawable(R.drawable.ic_radio_button_checked_black_24dp));
                lt_ch1.setBackground(getDrawable(R.drawable.buttonbackground));
                lt_ch2.setClickable(false);
                lt_ch3.setClickable(false);
                lt_ch4.setClickable(false);
                timer.cancel();
                if(listCh1.get(tQ).equals(listAns.get(tQ))){
                    score+=2;
                    //Toast.makeText(Multiplechoice.this,"Answer is Correct", Toast.LENGTH_SHORT).show();
                    showAlertDialog(Multiplechoice.this," Answer is Correct","", R.drawable.thumbs);
                }else {
                    //Toast.makeText(Multiplechoice.this,"Answer is Incorrect", Toast.LENGTH_SHORT).show();
                    showAlertDialog(Multiplechoice.this," Answer is Incorrect","", R.drawable.down);
                }

                btn_next.setVisibility(View.VISIBLE);
            }
        });

        lt_ch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSpeak = listCh2.get(tQ);
                t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                btn_ch2.setImageDrawable(getDrawable(R.drawable.ic_radio_button_checked_black_24dp));
                lt_ch2.setBackground(getDrawable(R.drawable.buttonbackground));
                lt_ch1.setClickable(false);
                lt_ch3.setClickable(false);
                lt_ch4.setClickable(false);
                timer.cancel();
                if(listCh2.get(tQ).equals(listAns.get(tQ))){
                    score+=2;
                    //Toast.makeText(Multiplechoice.this,"Answer is Correct", Toast.LENGTH_SHORT).show();
                    showAlertDialog(Multiplechoice.this," Answer is Correct","", R.drawable.thumbs);
                }else {
                    //Toast.makeText(Multiplechoice.this,"Answer is Incorrect", Toast.LENGTH_SHORT).show();
                    showAlertDialog(Multiplechoice.this," Answer is Incorrect","", R.drawable.down);
                }
                btn_next.setVisibility(View.VISIBLE);
            }
        });

        lt_ch3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSpeak = listCh3.get(tQ);
                t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                btn_ch3.setImageDrawable(getDrawable(R.drawable.ic_radio_button_checked_black_24dp));
                lt_ch3.setBackground(getDrawable(R.drawable.buttonbackground));
                lt_ch2.setClickable(false);
                lt_ch1.setClickable(false);
                lt_ch4.setClickable(false);
                timer.cancel();
                if(listCh3.get(tQ).equals(listAns.get(tQ))){
                    score+=2;
                    //Toast.makeText(Multiplechoice.this,"Answer is Correct", Toast.LENGTH_SHORT).show();
                    showAlertDialog(Multiplechoice.this," Answer is Correct","", R.drawable.thumbs);
                }else {
                    //Toast.makeText(Multiplechoice.this,"Answer is Incorrect", Toast.LENGTH_SHORT).show();
                    showAlertDialog(Multiplechoice.this," Answer is Incorrect","", R.drawable.down);
                }
                btn_next.setVisibility(View.VISIBLE);
            }
        });

        lt_ch4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSpeak = listCh4.get(tQ);
                t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);

                btn_ch4.setImageDrawable(getDrawable(R.drawable.ic_radio_button_checked_black_24dp));
                lt_ch4.setBackground(getDrawable(R.drawable.buttonbackground));
                lt_ch2.setClickable(false);
                lt_ch3.setClickable(false);
                lt_ch1.setClickable(false);
                timer.cancel();
                if(listCh4.get(tQ).equals(listAns.get(tQ))){
                    score+=2;
                    //Toast.makeText(Multiplechoice.this,"Answer is Correct", Toast.LENGTH_SHORT).show();
                    showAlertDialog(Multiplechoice.this," Answer is Correct","", R.drawable.thumbs);
                }else {
                    //Toast.makeText(Multiplechoice.this,"Answer is Incorrect", Toast.LENGTH_SHORT).show();
                    showAlertDialog(Multiplechoice.this," Answer is Incorrect","", R.drawable.down);
                }
                btn_next.setVisibility(View.VISIBLE);
            }
        });

        btn_next.setAnimation(shake);
        if(tQ<listQuestion.size()){
            btn_next.setText("Next");
        }

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            tQ++;
                if(tQ<listQuestion.size()){

                    txt_Question.setText(listQuestion.get(tQ));
                    txt_ch1.setText(listCh1.get(tQ));
                    txt_ch2.setText(listCh2.get(tQ));
                    txt_ch3.setText(listCh3.get(tQ));
                    txt_ch4.setText(listCh4.get(tQ));

                    lt_ch1.setClickable(true);
                    lt_ch2.setClickable(true);
                    lt_ch3.setClickable(true);
                    lt_ch4.setClickable(true);

                    btn_ch1.setImageDrawable(getDrawable(R.drawable.ic_radio_button_unchecked_black_24dp));
                    btn_ch2.setImageDrawable(getDrawable(R.drawable.ic_radio_button_unchecked_black_24dp));
                    btn_ch3.setImageDrawable(getDrawable(R.drawable.ic_radio_button_unchecked_black_24dp));
                    btn_ch4.setImageDrawable(getDrawable(R.drawable.ic_radio_button_unchecked_black_24dp));

                    lt_ch1.setBackground(getDrawable(R.drawable.textbackground));
                    lt_ch2.setBackground(getDrawable(R.drawable.textbackground));
                    lt_ch3.setBackground(getDrawable(R.drawable.textbackground));
                    lt_ch4.setBackground(getDrawable(R.drawable.textbackground));

                    btn_next.setVisibility(View.INVISIBLE);
                }else {

                    lt_ch1.setClickable(false);
                    lt_ch2.setClickable(false);
                    lt_ch3.setClickable(false);
                    lt_ch4.setClickable(false);
                    btn_next.setText("Done");
                    Intent i = new Intent(Multiplechoice.this, Dragactivity.class);
                    i.putExtra("Testscore", score);
                    startActivity(i);
                }

            }
        });
    }

    public void onPause(){
        if(t1 !=null){
            t1.stop();
            t1.shutdown();
        }
        super.onPause();
    }


    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            // Setting speech language
            int result = t1.setLanguage(Locale.UK);
            // If your device doesn't support language you set above
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                // Cook simple toast message with message
                Toast.makeText(getApplicationContext(), "Language not supported",
                        Toast.LENGTH_LONG).show();
                Log.e("TTS", "Language is not supported");
            }
            // Enable the button - It was disabled in main.xml (Go back and
            // Check it)
            else {
               // btnSpeak.setEnabled(true);
            }
            // TTS is not initialized properly
        } else {
            Toast.makeText(this, "TTS Initilization Failed", Toast.LENGTH_LONG)
                    .show();
            Log.e("TTS", "Initilization Failed");
        }
    }

    private void showAlertDialog(Context mContext, String mTitle, String mBody, int mImage){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.CustomDialog);
        builder.setCancelable(true);
        builder.setIcon(mImage);
        if(mTitle.length()>0)
            builder.setTitle(mTitle);
        if(mBody.length()>0)
            builder.setTitle(mBody);

        builder.setPositiveButton("OK",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }
}
