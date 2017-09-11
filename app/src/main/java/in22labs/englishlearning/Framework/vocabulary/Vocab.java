package in22labs.englishlearning.Framework.vocabulary;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import in22labs.englishlearning.R;
import in22labs.englishlearning.Utils.LocalsharedPref;

public class Vocab extends AppCompatActivity {
    ImageView im;
    AnimatorSet set;
    ListView lt_st;
    ArrayList<String> temp, answer;
    Button b1;
    LocalsharedPref localsharedPref;
    Boolean FirstClick;
    int[] d = {R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.a4, R.drawable.a5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocab);
        lt_st = (ListView) findViewById(R.id.list_vocab);
        b1=(Button)findViewById(R.id.vocab_test);
        im = (ImageView) findViewById(R.id.img_flip);
        temp = new ArrayList<>();
        temp.add("GAIETY");
        temp.add("RIVETING");
        temp.add("LAUNCH");
        temp.add("STRIFE");
        temp.add("ABANDON");

        answer= new ArrayList<>();
        answer.add("The state or quality of being light-hearted or cheerful.");
        answer.add("Completely engrossing; compelling.");
        answer.add("Start or set in motion");
        answer.add("Angry or bitter disagreement over fundamental issues; conflict.");
        answer.add("Cease to support or look after");

        localsharedPref = new LocalsharedPref(Vocab.this);
//        im.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                set = (AnimatorSet) AnimatorInflater.loadAnimator(Vocab.this, R.animator.flip_anim);
//                set.setTarget(im);
//                set.start();
//                if (set.isRunning()) {
//                    im.setBackground(getResources().getDrawable(R.drawable.k));
//
//                }
//
//            }
//        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Vocab.this, Vocabquiz.class);
                startActivity(i);
            }
        });

        lt_st.setAdapter( new listImageVocab(Vocab.this, temp, answer, d));
    }

    public class listImageVocab extends BaseAdapter {

        Context mContext;
        ArrayList<String> vocab_text, vocab_mean;

        int[] Drawable;


        private LayoutInflater inflater = null;

        public listImageVocab(Context context, ArrayList<String> vocab_Text, ArrayList<String> vocab_Mean, int[] drawable) {
            mContext = context;
            vocab_text = vocab_Text;
            vocab_mean=vocab_Mean;
            Drawable = drawable;

            inflater = (LayoutInflater) mContext.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }


        @Override
        public int getCount() {
            return vocab_text.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        public class Holder {
            TextView txt_description;
            TextView txt_meaning;
            ImageView im, flip_im;


        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            final Holder holder = new Holder();
            final View rowView;

            rowView = inflater.inflate(R.layout.flash_adapter, null);

            holder.txt_description = (TextView) rowView.findViewById(R.id.txt_flip_flashcard);


            holder.im = (ImageView) rowView.findViewById(R.id.im_flip_flashcard);
            holder.flip_im =(ImageView) rowView.findViewById(R.id.im_afflip_flashcard);
            holder.txt_meaning =(TextView)rowView.findViewById(R.id.txt_flip_text);


            holder.im.setTag(position);
            holder.txt_description.setText(vocab_text.get(position));

            holder.im.setBackground(getDrawable(Drawable[position]));


            final String jobid = vocab_text.get(position);

            holder.im.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final int pos = (Integer) v.getTag();
                    FirstClick=localsharedPref.isFirstClick(position);
                    if(!FirstClick) {
                        set = (AnimatorSet) AnimatorInflater.loadAnimator(Vocab.this, R.animator.flip_anim);
                        set.setTarget(holder.im);
                        set.start();
                        new Handler().postDelayed(new Runnable() {

                            @Override
                            public void run() {

                                holder.flip_im.setVisibility(View.VISIBLE);
                                holder.flip_im.setBackground(getResources().getDrawable(R.drawable.transparent));
                                localsharedPref.SetFirstClick(position, true);
                                holder.txt_meaning.setVisibility(View.VISIBLE);
                                holder.txt_meaning.setText(vocab_mean.get(position));
                                holder.txt_description.setVisibility(View.INVISIBLE);
                                // overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);
                            }
                        }, 1100);
                    }else{
                        set = (AnimatorSet) AnimatorInflater.loadAnimator(Vocab.this, R.animator.flip_anim);
                        set.setTarget(holder.im);
                        set.start();
                        holder.flip_im.setVisibility(View.INVISIBLE);
                        localsharedPref.SetFirstClick(position,false);
                        holder.txt_description.setVisibility(View.VISIBLE);
                        holder.txt_meaning.setVisibility(View.INVISIBLE);
                    }
                }
            });
            return rowView;
        }
    }
}