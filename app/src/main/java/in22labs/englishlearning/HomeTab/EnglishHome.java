package in22labs.englishlearning.HomeTab;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;

import in22labs.englishlearning.R;
import in22labs.englishlearning.Utils.SessionManager;


public class EnglishHome extends Fragment {

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 4 ;
    public int temp;
    SessionManager session;

    String Index;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        Toolbar tool=(Toolbar)getActivity().findViewById(R.id.toolbar);
        TextView ab = (TextView) tool.findViewById(R.id.toolbar_title);
        ab.setText("Home");
        session=new SessionManager(getActivity());

        ab.setTextSize(20.0f);


        View x =  inflater.inflate(R.layout.tab_layout,null);
        tabLayout = (TabLayout) x.findViewById(R.id.tabs);
        viewPager = (ViewPager) x.findViewById(R.id.viewpager);



        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });
        return x;
    }

    private class MyAdapter extends FragmentPagerAdapter {

        MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {
            switch (position){
                case 0 :
                    ProfileHome p1 = new ProfileHome();
                    temp=0;
//                    final Bundle b1 = new Bundle();
//                    b1.putString("clgcode", CollegeCode);
//                    p1.setArguments(b1);
                    return p1;
                case 1 :
                    LessonHome p2 = new LessonHome();
                    temp=1;
//                    final Bundle b2 = new Bundle();
//                    b2.putString("clgcode", CollegeCode);
//                    p2.setArguments(b2);
                    return p2;
                case 2 :
                    PracticeHome p3 = new PracticeHome();
                        temp=2;
//                    final Bundle b3 = new Bundle();
//                    b3.putString("clgcode", CollegeCode);
//                    p3.setArguments(b3);
                    return p3;

                case 3 :
                    DictionaryHome p4 = new DictionaryHome();
                    temp=3;
//                    final Bundle b4 = new Bundle();
//                    b4.putString("clgcode", CollegeCode);
//                    p4.setArguments(b4);
                    return p4;
            }
            return null;
        }

        @Override
        public int getCount() {

            return int_items;

        }


        @Override
        public CharSequence getPageTitle(int position) {

            switch (position){
                case 0 :
                    return "Profile";
                case 1 :
                    return "All Lessons";
                case 2 :
                    return "Practice";
                case 3:
                    return "Dictionary";
            }
            return null;
        }
    }
   public void onResume(){
       super.onResume();
       viewPager.setCurrentItem(session.isFragpos());
   }

}
