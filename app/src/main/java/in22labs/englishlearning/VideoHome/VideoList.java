package in22labs.englishlearning.VideoHome;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import in22labs.englishlearning.HomeActivity;
import in22labs.englishlearning.HomeTab.PracticeHome;
import in22labs.englishlearning.R;
import in22labs.englishlearning.Utils.ConnectionDetector;

public class VideoList extends AppCompatActivity {
    ListView lt_videolist;
    public int limit;
    String vid, vtitle, vcate, vdiff, vdate, vcoin, vurl, viurl;
    String[] Arrvid, Arrvtitle, Arrvcate, Arrvdiff, Arrvdate, Arrvcoin, Arrvurl, Arrviurl;
    ConnectionDetector cd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videolist);



        Toolbar tb_videohome=(Toolbar)findViewById(R.id.toolbar_videohome);
        tb_videohome.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        tb_videohome.setTitle("Videos");
        tb_videohome.setTitleTextColor(Color.parseColor("#FFFFFF"));

        tb_videohome.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(VideoList.this, HomeActivity.class);
                startActivity(i);
                VideoList.this.finish();
            }
        });
        tb_videohome.inflateMenu(R.menu.abs1);


       // setSupportActionBar(tb_videohome);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        cd = new ConnectionDetector(VideoList.this);

        lt_videolist=(ListView)findViewById(R.id.listView_videolist);
        try {
            JSONObject Jobject = new JSONObject(loadJSONFromAsset());
            JSONArray Jarray = Jobject.getJSONArray("results");
            limit = Jarray.length();
            Arrvid=new String[limit];
            Arrvtitle=new String[limit];
            Arrvcate=new String[limit];
            Arrvdiff=new String[limit];
            Arrvdate=new String[limit];
            Arrvcoin=new String[limit];
            Arrvurl=new String[limit];
            Arrviurl=new String[limit];
            for(int i =0; i<limit;i++){
                JSONObject object = Jarray.getJSONObject(i);
                vid=object.getString("titleid");
                vtitle=object.getString("title");
                vcate=object.getString("category");
                vdiff=object.getString("difficult");
                vdate=object.getString("date");
                vcoin=object.getString("coin");
                vurl=object.getString("url");
                viurl=object.getString("image_url");

                Arrvid[i]=vid;
                Arrvtitle[i]=vtitle;
                Arrvcate[i]=vcate;
                Arrvdiff[i]=vdiff;
                Arrvdate[i]=vdate;
                Arrvcoin[i]=vcoin;
                Arrvurl[i]=vurl;
                Arrviurl[i]=viurl;

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        lt_videolist.setAdapter(new cardVideo(VideoList.this, Arrvid, Arrvtitle, Arrvcate, Arrvdiff, Arrvdate, Arrvcoin, Arrvurl, Arrviurl));

    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("video.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private class cardVideo extends BaseAdapter{
        private LayoutInflater inflater = null;
        Context mContext;
        String[] vid, vtitle, vcate, vdiff, vdate, vcoin, vurl, viurl;

        private cardVideo(Context context, String[] id, String[] title, String[] cate, String[] diff, String[] date, String[] coin, String[] url, String[] iurl){
            mContext=context;
            vid=id;
            vtitle=title;
            vcate=cate;
            vdiff=diff;
            vdate=date;
            vcoin=coin;
            vurl=url;
            viurl=iurl;
            inflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return vid.length;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
        private class Holder{
            TextView txt_titile, txt_cate, txt_diff, txt_date, txt_coin;
            CardView cd_video;
            ImageView im_viurl;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final Holder holder = new Holder();
            final View rowView;

            rowView = inflater.inflate(R.layout.card_videolist, null);
            holder.txt_titile=(TextView)rowView.findViewById(R.id.videolist_title);
            holder.txt_cate=(TextView)rowView.findViewById(R.id.videolist_category);
            holder.txt_diff=(TextView)rowView.findViewById(R.id.videolist_difficult);
            holder.txt_date=(TextView)rowView.findViewById(R.id.videolist_date);
            holder.txt_coin=(TextView)rowView.findViewById(R.id.videolist_coins);
            holder.cd_video=(CardView)rowView.findViewById(R.id.card_videolist);
            holder.im_viurl=(ImageView)rowView.findViewById(R.id.videolist_screen);

            holder.txt_titile.setText(vtitle[position]);
            holder.txt_cate.setText(vcate[position]);
            holder.txt_diff.setText(vdiff[position]);
            holder.txt_date.setText(vdate[position]);
            holder.txt_coin.setText(vcoin[position]);
            if(!cd.isConnectingToInternet()){
//                holder.im_viurl.setImageBitmap(getBitmapFromURL(viurl[position]));


////            try {
////                Bitmap bmp = BitmapFactory.decodeFile(String.valueOf(new URL(viurl[position]).openStream()));
////                holder.im_viurl.setBackground(tImageBitmap(bmp));
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
//
//                new ImageLoadTask(viurl[position], holder.im_viurl);
            }else {
//                BitmapDrawable ob = new BitmapDrawable(getResources(), getBitmapFromURL(viurl[position]));
//                holder.im_viurl.setBackgroundDrawable(ob);
                new ImageLoadTask(viurl[position], holder.im_viurl);
            }


            holder.cd_video.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(VideoList.this, VideosActivity.class);
                    i.putExtra("youtube_url", vurl[position]);
                    i.putExtra("title", vtitle[position]);
                    startActivity(i);
                }
            });

            return rowView;
        }
    }

    public class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {

        private String url;
        private ImageView imageView;

        public ImageLoadTask(String url, ImageView imageView) {
            this.url = url;
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            try {
                URL urlConnection = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) urlConnection
                        .openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);

            BitmapDrawable ob = new BitmapDrawable(getResources(), result);
            imageView.setBackgroundDrawable(ob);
            Toast.makeText(VideoList.this,"hi,",Toast.LENGTH_SHORT).show();
        }

    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.abs1, menu);
//        return super.onCreateOptionsMenu(menu);
//    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            Log.e("src",src);
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            Log.e("Bitmap","returned");
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception",e.getMessage());
            return null;
        }
    }
}
