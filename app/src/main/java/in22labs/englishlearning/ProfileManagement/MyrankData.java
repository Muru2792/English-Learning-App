package in22labs.englishlearning.ProfileManagement;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

//import com.google.android.gms.ads.AdListener;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.InterstitialAd;
//import com.in22labs.tneaapp.Utils.ConnectionDetector;
//import com.in22labs.tneaapp.Utils.DatabaseHelper;
//import com.in22labs.tneaapp.R;


import com.google.android.gms.location.LocationListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import in22labs.englishlearning.R;
import in22labs.englishlearning.Utils.GPSTracker;
import in22labs.englishlearning.Utils.SessionManager;

import static com.facebook.FacebookSdk.getApplicationContext;


public class MyrankData extends Fragment {

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 2;
    public String CollegeName = null;
    public String CollegeCode = null;
    //    DatabaseHelper db;
    TextView txt_username;
    SessionManager session;
    String Index;
    private LocationManager locationManager;
    private String provider;
    Context mContext;
    GPSTracker gps;
    //    InterstitialAd interAd;
//    ConnectionDetector cd;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mContext = getActivity();
        Toolbar tool = (Toolbar) getActivity().findViewById(R.id.toolbar);
        TextView ab = (TextView) tool.findViewById(R.id.toolbar_title);
        ab.setText("College Profile");
        txt_username = (TextView) getActivity().findViewById(R.id.myrank_name);
        session = new SessionManager(getActivity());
        ab.setTextSize(20.0f);
//        cd=new ConnectionDetector(getActivity());


        View x = inflater.inflate(R.layout.tab_layout1, null);
        tabLayout = (TabLayout) x.findViewById(R.id.tabs1);
        viewPager = (ViewPager) x.findViewById(R.id.viewpager1);
//        try {
//            db = new DatabaseHelper(getActivity());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        Index = getArguments().getString("Index");
//        if(Index.equals("1")){
//            CollegeCode= getArguments().getString("clgcode");
//
//            if (  Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD_MR1) {
//                if (!cd.isConnectingToInternet()) {
//                    // Internet Connection is not present
//
//                } else {
//                    //fullBanner();
//                }
//            }
//
//
//        }else if(Index.equals("2")) {
//            CollegeName = getArguments().getString("clgname");
//            CollegeCode= db.getCollegeCode(CollegeName);
//        }

//        // Get the location manager
//        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
//        // Define the criteria how to select the locatioin provider -> use
//        // default
//        Criteria criteria = new Criteria();
//        provider = locationManager.getBestProvider(criteria, false);
//        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return null;
//        }
//        Location location = locationManager.getLastKnownLocation(provider);
//
//        // Initialize the location fields
//        if (location != null) {
//            System.out.println("Provider " + provider + " has been selected.");
//            Toast.makeText(getActivity(), "hi", Toast.LENGTH_SHORT).show();
//            onLocationChanged(location);
//        } else {
//            Toast.makeText(getActivity(), "hihhh", Toast.LENGTH_SHORT).show();
//        }


        gps = new GPSTracker(mContext);

        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        } else {
            Toast.makeText(mContext,"You need have granted permission",Toast.LENGTH_SHORT).show();
            gps = new GPSTracker(mContext);

            // Check if GPS enabled
//            if (gps.canGetLocation()) {

                double latitude = gps.getLatitude();
                double longitude = gps.getLongitude();

                // \n is for new line
                Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
//            } else {
                // Can't get location.
                // GPS or network is not enabled.
                // Ask user to enable GPS/network in settings.
                gps.showSettingsAlert();
//            }
        }
        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });
        return x;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the

                    // contacts-related task you need to do.

                    gps = new GPSTracker(mContext);

                    // Check if GPS enabled
//                    if (gps.canGetLocation()) {

                        double latitude = gps.getLatitude();
                        double longitude = gps.getLongitude();

                        // \n is for new line
                        Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
//                    } else {
                        // Can't get location.
                        // GPS or network is not enabled.
                        // Ask user to enable GPS/network in settings.
                        gps.showSettingsAlert();
//                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                    Toast.makeText(mContext, "You need to grant permission", Toast.LENGTH_SHORT).show();
                }
                return;
            }

    }
}

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {
            switch (position){
                case 0 :
                    CityrankFragment p2 = new CityrankFragment();

                    return p2;
                case 1 :
                    Globalrank p4 = new Globalrank();
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
                    return "Local Rank";
                case 1 :
                    return "Global Rank";

            }
            return null;
        }
    }


//    @Override
//    public void onLocationChanged(Location location) {
//        int lat = (int) (location.getLatitude());
//        int lng = (int) (location.getLongitude());
//
//        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
//
//        List<Address> addresses  = null;
//        try {
//            addresses = geocoder.getFromLocation(lat,lng, 1);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        String city = addresses.get(0).getLocality();
//        txt_username.setText(city);
//        Toast.makeText(getActivity(), lat+" "+lng, Toast.LENGTH_SHORT).show();
//
//    }
//
//    public void onStatusChanged(String provider, int status, Bundle extras) {
//
//
//    }
//
//    public void onProviderEnabled(String provider) {
//        Toast.makeText(getActivity(), "Enabled new provider " + provider,
//                Toast.LENGTH_SHORT).show();
//
//    }
//
//    public void onProviderDisabled(String provider) {
//        Toast.makeText(getActivity(), "Disabled provider " + provider,
//                Toast.LENGTH_SHORT).show();
//    }
}
