package in22labs.englishlearning.Framework.Testmodule;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import in22labs.englishlearning.R;

public class Startscreen extends Activity {
    ListView lt_audio;
    String[] txtTamil, txtEnglish;
    String Tcontent, Econtent;
    TextToSpeech t1;
    Button btn_next;
    Animation shake;
    CountDownTimer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startscreen);
        lt_audio=(ListView)findViewById(R.id.audiolist);
        btn_next=(Button)findViewById(R.id.btn_next_startscreen);
        Econtent="My Name is Rabi";
        Tcontent ="என்னுடைய பெயர் இருக்கிறது Rabi";
        txtTamil=Tcontent.split(" ");
        txtEnglish=Econtent.split(" ");
        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });
//        t1.setPitch(0.1f);

        shake = AnimationUtils.loadAnimation(this, R.anim.shake_view);
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
               btn_next.startAnimation(shake);
                timer.start();
            }

        }.start();
        String toSpeak = Econtent;
        t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
        lt_audio.setAdapter( new Audioadapter(Startscreen.this, txtTamil, txtEnglish));
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Startscreen.this, Multiplechoice.class);
                startActivity(i);
            }
        });

    }

    private class Audioadapter extends BaseAdapter{
        String[] tcontent, econtent;
        Context mContext;
        private LayoutInflater inflater = null;

        Audioadapter(Context context, String[] Tcontent, String[] Econtent){
            mContext=context;
            tcontent=Tcontent;
            econtent=Econtent;
            inflater = (LayoutInflater) mContext.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }


        @Override
        public int getCount() {
            return tcontent.length;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
        class Holder{
            TextView tamilTxt;
            TextView englishTxt;
            ImageView audioImage;
        }
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final Startscreen.Audioadapter.Holder holder = new Startscreen.Audioadapter.Holder();
            final View rowView;

            rowView= inflater.inflate(R.layout.audioadapter, null);
            holder.tamilTxt=(TextView)rowView.findViewById(R.id.txtTamilaudio);
            holder.englishTxt=(TextView)rowView.findViewById(R.id.txtEnglishaudio);
            holder.audioImage=(ImageView)rowView.findViewById(R.id.audioHear);


            holder.englishTxt.setText(econtent[position]);
            holder.tamilTxt.setText(tcontent[position]);
            holder.audioImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                String toSpeak = econtent[position];
                    t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                }
            });

            return rowView;
        }

    }
    public void onPause(){
        if(t1 !=null){
            t1.stop();
            t1.shutdown();
        }
        super.onPause();
    }
}
