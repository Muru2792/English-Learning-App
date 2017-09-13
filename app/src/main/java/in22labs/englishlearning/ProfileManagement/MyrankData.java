package in22labs.englishlearning.ProfileManagement;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//import com.google.android.gms.ads.AdListener;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.InterstitialAd;
//import com.in22labs.tneaapp.Utils.ConnectionDetector;
//import com.in22labs.tneaapp.Utils.DatabaseHelper;
//import com.in22labs.tneaapp.R;


import com.google.android.gms.location.LocationListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import in22labs.englishlearning.R;
import in22labs.englishlearning.Utils.GPSTracker;
import in22labs.englishlearning.Utils.SessionManager;

import static com.facebook.FacebookSdk.getApplicationContext;


public class MyrankData extends Fragment implements View.OnClickListener{

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
    ImageView im_profilepic;
    GPSTracker gps;
    Bitmap bm;
    Boolean ju=true;
    private static final int PICK_FROM_GALLERY = 2;
    //    InterstitialAd interAd;
//    ConnectionDetector cd;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mContext = getActivity();
        View x = inflater.inflate(R.layout.tab_layout1, null);
        Toolbar tool = (Toolbar) getActivity().findViewById(R.id.toolbar);
        TextView ab = (TextView) tool.findViewById(R.id.toolbar_title);
        ab.setText("College Profile");
        txt_username = (TextView) getActivity().findViewById(R.id.myrank_name);
        session = new SessionManager(getActivity());
        ab.setTextSize(20.0f);
        im_profilepic=(ImageView)x.findViewById(R.id.profileupdate);
//        cd=new ConnectionDetector(getActivity());



        tabLayout = (TabLayout) x.findViewById(R.id.tabs1);
        viewPager = (ViewPager) x.findViewById(R.id.viewpager1);

        getPhotoRouund();





        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        im_profilepic.setOnClickListener(this);
        return x;
    }

    private void getPhotoRouund() {

        File file = new File(Environment.getExternalStorageDirectory()+ File.separator + "English App/Photo/Photo.jpg");

        if(file.exists()){

            bm = BitmapFactory.decodeFile(file.getAbsolutePath());
        }else{
            bm = BitmapFactory.decodeResource(getResources(), R.drawable.avatar);
        }

        im_profilepic.setImageBitmap(getCircleBitmap(bm));

    }

    private Bitmap getCircleBitmap(Bitmap bitmap) {
        final Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(output);

        final int color = Color.RED;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);


        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);

        canvas.drawOval(rectF, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        bitmap.recycle();


        return output;
    }

    private void SaveImage(Bitmap finalBitmap) {

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/English App/Photo");
        myDir.mkdirs();


        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        //String fname = "Image-" + n + ".jpg";
        String fname1 = "Photo.jpg";

        File file = new File(myDir, fname1);
        if (file.exists()) file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
//        Toast.makeText(MainActivity.this, "Write function  "+ file.getAbsolutePath().toString(), Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == PICK_FROM_GALLERY) {
            if (data != null) {

                Bundle extras2 = data.getExtras();
                if (extras2 != null) {
                    Bitmap photo = extras2.getParcelable("data");

                    SaveImage(photo);
                    getPhotoRouund();

                }
            }
        }
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

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.profileupdate){
            ju=false;
            Intent intent = new Intent();
            // call android default gallery
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            // ******** code for crop image
            intent.putExtra("crop", "true");
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            intent.putExtra("outputX", 300);
            intent.putExtra("outputY", 300);
            intent.putExtra("return-data", true);

            try {
               startActivityForResult(Intent.createChooser(intent, "Complete action using"), PICK_FROM_GALLERY);
            } catch (ActivityNotFoundException e) {
                // Do nothing for now
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
