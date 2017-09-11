package in22labs.englishlearning.ProfileManagement;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import in22labs.englishlearning.PracticeHome.AudiosActivity;
import in22labs.englishlearning.PracticeHome.BooksActivity;
import in22labs.englishlearning.PracticeHome.ConversationActivity;
import in22labs.englishlearning.PracticeHome.NewsActivity;
import in22labs.englishlearning.R;
import in22labs.englishlearning.VideoHome.VideoList;

public class TestHome extends Fragment {
    ArrayList<String> Title;
    int[] d = {R.drawable.p1, R.drawable.p2, R.drawable.p3};
    ListView lt_allpractice;
    LinearLayout lt_news, lt_conservation, lt_article, lt_games, lt_audios, lt_videos, lt_books;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_cityrank,null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//
//        lt_allpractice=(ListView)getActivity().findViewById(R.id.listView_practice);
//
//        lt_news=(LinearLayout)getActivity().findViewById(R.id.practice_news);
//        lt_conservation=(LinearLayout)getActivity().findViewById(R.id.practice_conversation);
//        lt_article=(LinearLayout)getActivity().findViewById(R.id.practice_articles);
//        lt_games=(LinearLayout)getActivity().findViewById(R.id.practice_games);
//        lt_audios=(LinearLayout)getActivity().findViewById(R.id.practice_audios);
//        lt_videos=(LinearLayout)getActivity().findViewById(R.id.practice_videos);
//        lt_books=(LinearLayout)getActivity().findViewById(R.id.practice_book);
//
//        Title=new ArrayList<>();
//        Title.add("Trump says he has new reasons to hope for Middle East peace");
//        Title.add("Windows 7 hardest hit by WannaCry worm");
//        Title.add("Honey trap");
//
//        lt_allpractice.setAdapter(new cardPractice(getActivity(), Title, d));
//
//        lt_news.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getActivity(), NewsActivity.class);
//                startActivity(i);
//            }
//        });
//
//        lt_conservation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getActivity(), ConversationActivity.class);
//                startActivity(i);
//            }
//        });
//
//        lt_article.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getActivity(), NewsActivity.class);
//                startActivity(i);
//            }
//        });
//
//        lt_videos.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getActivity(), VideoList.class);
//                startActivity(i);
//            }
//        });
//
//        lt_audios.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getActivity(), AudiosActivity.class);
//                startActivity(i);
//            }
//        });
//
//        lt_books.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getActivity(), BooksActivity.class);
//                startActivity(i);
//            }
//        });

    }

    private class cardPractice extends BaseAdapter{
        Context mContext;
        ArrayList<String> Title;
        int[] Drawableint;
        private LayoutInflater inflater = null;

        private cardPractice(Context context, ArrayList<String> title, int[] d){
            mContext=context;
            Title=title;
            Drawableint=d;
            inflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        @Override
        public int getCount() {
            return Title.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
        private class Holder {
            TextView txt_title;
            ImageView im_content;

        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final Holder holder = new Holder();
            final View rowView;

            rowView = inflater.inflate(R.layout.card_praactice, null);
            holder.txt_title=(TextView)rowView.findViewById(R.id.txt_practice_title);
            holder.im_content=(ImageView)rowView.findViewById(R.id.id_img_practice);

            holder.im_content.setTag(position);
            holder.im_content.setBackground(getResources().getDrawable(Drawableint[position]));
            holder.txt_title.setText(Title.get(position));
            return rowView;
        }
    }
}
