package in22labs.englishlearning.HomeTab;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import in22labs.englishlearning.Framework.Testmodule.Testmodule;
import in22labs.englishlearning.R;
import in22labs.englishlearning.Utils.SessionManager;

/**
 * Created by Ratan on 7/29/2015.
 */
public class LessonHome extends Fragment {
    ListView lt_lesson;
    SessionManager session;
    ArrayList<String> listTitle, listSubject, listPoints, listBackground;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_lesson,null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lt_lesson=(ListView)getActivity().findViewById(R.id.listView_lesson);
        listTitle=new ArrayList<>();
        listSubject=new ArrayList<>();
        listPoints=new ArrayList<>();
        listBackground=new ArrayList<>();
        session=new SessionManager(getActivity());
        listTitle.add("Lesson 1");
        listSubject.add("பெயர் கேட்க மற்றும் பெயரை சொல்ல கற்று கொள்ளுங்கள்");
        listPoints.add("15 Points");
        listBackground.add("#4dd0e1");

        listTitle.add("Lesson 2");
        listSubject.add("ஹலோ, நீங்கள் எப்படி இருக்கிறரீகள்?, நான் நன்றாக இருக்கிறேன்");
        listPoints.add("30 Points");
        listBackground.add("#dce775");

        listTitle.add("Lesson 3");
        listSubject.add("நீங்கள் எங்கிருந்து வருகிறீர்கள்? கேட்க கற்று கொள்ளுங்கள்");
        listPoints.add("45 Points");
        listBackground.add("#4dd0e1");

        listTitle.add("Lesson 4");
        listSubject.add("Simple Present Tense : to-be verb தேசியம் சொல்ல கற்றுக்கொள்ளுங்கள்");
        listPoints.add("20 Points");
        listBackground.add("#dce775");

        listTitle.add("Lesson 5");
        listSubject.add("Simple Present Negative - is, am, are உடைய எதிர்மறை வாக்கியம் அமைக்க கற்று கொள்ளுங்கள்");
        listPoints.add("15 Points");
        listBackground.add("#4dd0e1");

        listTitle.add("Lesson 6");
        listSubject.add("Simple Present Interrogative: சாதாரண நிகழ்காலத்தில் கேள்விகள் கேட்க கற்று கொள்ளுங்கள்");
        listPoints.add("10 Points");
        listBackground.add("#dce775");

        lt_lesson.setAdapter(new listHomeAdapter(getActivity(), listTitle, listSubject, listPoints, listBackground));
    }

    class listHomeAdapter extends BaseAdapter{
        Context mContext;
        ArrayList<String> ListTitle, ListSubject, ListPoints, ListBackground;

        private LayoutInflater inflater = null;

        public listHomeAdapter(Context context, ArrayList<String> listtitle, ArrayList<String> listsubject, ArrayList<String> listpoints, ArrayList<String> listbackground){
            mContext=context;
            ListTitle=listtitle;
            ListSubject=listsubject;
            ListPoints=listpoints;
            ListBackground=listbackground;
            inflater = (LayoutInflater) mContext.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return ListTitle.size();
        }
        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
        public class Holder{
            TextView textTitle;
            TextView textSubject;
            TextView textPoints;
            LinearLayout lessonall;
        }
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final Holder holder = new Holder();
            final View rowView;

            rowView = inflater.inflate(R.layout.card_layout, null);
            holder.textTitle=(TextView)rowView.findViewById(R.id.lesson_title);
            holder.textSubject=(TextView)rowView.findViewById(R.id.lesson_message);
            holder.textPoints=(TextView)rowView.findViewById(R.id.lesson_points);
            holder.lessonall=(LinearLayout)rowView.findViewById(R.id.lesson_all);

            holder.textTitle.setText(ListTitle.get(position));
            holder.textSubject.setText(ListSubject.get(position));
            holder.textPoints.setText(ListPoints.get(position));
            holder.lessonall.setBackgroundColor(Color.parseColor(ListBackground.get(position)));
//            if(position==0 || position%2==0){
//                holder.lessonall.setBackgroundColor(Color.parseColor("4dd0e1"));
//            }else{
//                holder.lessonall.setBackgroundColor(Color.parseColor("#dce775"));
//            }
            holder.lessonall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(position==0){
                        Intent i = new Intent(mContext, Testmodule.class);
                        startActivity(i);
                    }
                    session.setFragpo(1);
                }
            });


            return rowView;
        }
    }

}
