package in22labs.englishlearning.ProfileManagement;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;


import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;

import in22labs.englishlearning.R;
import in22labs.englishlearning.Utils.AppLocationService;
import in22labs.englishlearning.Utils.LocationAddress;
import in22labs.englishlearning.Utils.Utils;

import static com.facebook.FacebookSdk.getApplicationContext;


public class CityrankFragment extends Fragment implements ConnectionCallbacks,
        OnConnectionFailedListener, com.google.android.gms.location.LocationListener {

    TextView txt_rankcity, city_rankoverall, city_toprank;
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;

    private Location mLastLocation;

    // Google client to interact with Google API
    private GoogleApiClient mGoogleApiClient;

    // boolean flag to toggle periodic location updates
    private boolean mRequestingLocationUpdates = false;

    private LocationRequest mLocationRequest;

    // Location updates intervals in sec
    private static int UPDATE_INTERVAL = 10000; // 10 sec
    private static int FATEST_INTERVAL = 5000; // 5 sec
    private static int DISPLACEMENT = 10; // 10 meters

    double slati = 0.0, slongi = 0.0;
    String Alati, Alongi;
    String strlocationAddress;
    private Location location1;
    private final int REQUEST_LOCATION = 200;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_cityrank, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        txt_rankcity = (TextView) getActivity().findViewById(R.id.cityrank_location);
        city_toprank = (TextView) getActivity().findViewById(R.id.cityrank_toprank);
        city_rankoverall = (TextView) getActivity().findViewById(R.id.cityrank_outoff);


        if (checkPlayServices()) {
            buildGoogleApiClient();
            createLocationRequest();
        } else {
            Utils.ShowToast(getActivity(), "The Device Mot Supported!");
            getActivity().finish();
        }
    }

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FATEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setSmallestDisplacement(DISPLACEMENT);
    }


    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
    }


    @SuppressLint("HandlerLeak")
    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {

            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    strlocationAddress = bundle.getString("address");
                    slati = bundle.getDouble("lat");
                    slongi = bundle.getDouble("lon");
                    break;
                default:
                    strlocationAddress = "No Address found on your location";
            }
            if(strlocationAddress.equals("Address not found"))
            {
                runNetworkGPS(slati, slongi);
            }
            txt_rankcity.setText("MY RANK IN "+removeDuplicates(strlocationAddress));
            city_toprank.setText("TOP RANKER IN "+strlocationAddress);
            city_rankoverall.setText("Your "+strlocationAddress+" rank, out of 640 learners is 300");
            //  ChangesMap(slati,slongi,strlocationAddress);
            Utils.ShowToast(getActivity(), slati+" "+slongi);
        }
    }

    private void runNetworkGPS(double lat, double lang) {
        AppLocationService appLocationService = new AppLocationService(getActivity());
        Location nwLocation = appLocationService
                .getLocation(LocationManager.NETWORK_PROVIDER);

        if (nwLocation != null) {
            double latitude = nwLocation.getLatitude();
            double longitude = nwLocation.getLongitude();
            Toast.makeText(
                    getApplicationContext(),
                    "Mobile Location (NW): \nLatitude: " + latitude
                            + "\nLongitude: " + longitude,
                    Toast.LENGTH_LONG).show();

        } else {
            Location lLocation = appLocationService.getLocation(LocationManager.GPS_PROVIDER);
            if (lLocation != null){
                double latitude = lLocation.getLatitude();
                double longitude = lLocation.getLongitude();
                Toast.makeText(
                        getApplicationContext(),
                        "Mobile Location (NW): \nLatitude: " + latitude
                                + "\nLongitude: " + longitude,
                        Toast.LENGTH_LONG).show();
            }
        }
//        LocationAddress locationAddress = new LocationAddress();
//        locationAddress.getAddressFromLocation(lat, lang,
//                getActivity(), new GeocoderHandler());
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        checkPlayServices();

        if (Utils.isNetworkAvailable(getActivity().getApplicationContext())) {
            // Resuming the periodic location updates
//            if (mGoogleApiClient.isConnected() && mRequestingLocationUpdates) {
//                startLocationUpdates();
//            }

        } else {
            Utils.AlertInternet(getActivity());
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(getActivity());
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, getActivity(),
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Toast.makeText(getActivity().getApplicationContext(),
                        "This device is not supported.", Toast.LENGTH_LONG)
                        .show();
                getActivity().finish();
            }
            return false;
        }
        return true;
    }

    /**
     * Starting the location updates
     * */
    protected void startLocationUpdates() {

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            LocationServices.FusedLocationApi.requestLocationUpdates(
                    mGoogleApiClient, mLocationRequest, this);
        }


    }

    /**
     * Stopping location updates
     */
    protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(
                mGoogleApiClient, this);
    }


    private void displayLocation() {

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi
                .getLastLocation(mGoogleApiClient);

        if (mLastLocation != null) {
            double latitude = mLastLocation.getLatitude();
            double longitude = mLastLocation.getLongitude();

            slati=latitude;
            slongi=longitude;
            // Utils.ShowToast(getActivity(), slati+" "+slongi);

            LocationAddress locationAddress = new LocationAddress();
            locationAddress.getAddressFromLocation(slati, slongi,
                    getActivity(), new GeocoderHandler());
            double lat1=0.0, lng1=0.0;
            String s="1";




//            try {
//                lat1 = new Double(session.getKeyTrlocationlat());
//                lng1 = new Double(session.getKeyTrlocationlong());
//                //Toast.makeText(getActivity(), " " + lat1 + " " + lng1, Toast.LENGTH_SHORT).show();
//            }catch (NumberFormatException e){
//                e.printStackTrace();
//            }
//            if (lat1==0.0)
//                lat1=slati;
//            if (lng1==0.0)
//                lng1=slongi;
//            if (distance(lat1, lng1, slati, slongi) < 0.2) { // if distance < 0.1 miles we take locations as equal
//                //do what you want to do...
//                //String mprovider="WIth in a center";
//                String mprov=String.valueOf(distance(lat1, lng1, slati, slongi));
//                mProve="Withincircle";
//                devicelocation="Matching";
////                Utils.ShowToast(getActivity(),mProve);
//            }else {
//
//                mProve="Notnearby";
//                devicelocation="Not Matching";
////                Utils.ShowToast(getActivity(), mProve);
//            }
        }
    }
    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;

        Toast.makeText(getActivity().getApplicationContext(), "Location changed!",
                Toast.LENGTH_SHORT).show();

        // Displaying the new location on UI
        displayLocation();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_LOCATION) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            }
        }
    }
    private void showGPSDisabledAlertToUser() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setMessage("GPS is disabled in your device. Would you like to enable it?")
                .setCancelable(false)
                .setPositiveButton("Goto Settings Page To Enable GPS", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent callGPSSettingIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(callGPSSettingIntent);
                    }
                });
        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        displayLocation();

        if (mRequestingLocationUpdates) {
            startLocationUpdates();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i("Map", "Connection failed: ConnectionResult.getErrorCode() = "
                + connectionResult.getErrorCode());
    }

    private class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location loc) {
//            editLocation.setText("");
//            pb.setVisibility(View.INVISIBLE);
            Toast.makeText(
                    getActivity().getBaseContext(),
                    "Location changed: Lat: " + loc.getLatitude() + " Lng: "
                            + loc.getLongitude(), Toast.LENGTH_SHORT).show();
            String longitude = "Longitude: " + loc.getLongitude();
            // Log.v(TAG, longitude);
            String latitude = "Latitude: " + loc.getLatitude();
            //  Log.v(TAG, latitude);

        /*------- To get city name from coordinates -------- */
            String cityName = null;
            Geocoder gcd = new Geocoder(getActivity().getBaseContext(), Locale.getDefault());
            List<Address> addresses;
            try {
                addresses = gcd.getFromLocation(loc.getLatitude(),
                        loc.getLongitude(), 1);
                if (addresses.size() > 0) {
                    System.out.println(addresses.get(0).getLocality());
                    cityName = addresses.get(0).getLocality();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            String s = longitude + "\n" + latitude + "\n\nMy Current City is: "
                    + cityName;

            txt_rankcity.setText("MY RANK IN "+s);
            city_toprank.setText("TOP RANKER IN "+s);
            city_rankoverall.setText("Your "+s+" rank, out of 640 learners is 300");
            //  editLocation.setText(s);
            Toast.makeText(getActivity(), "Hi to all", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onProviderDisabled(String provider) {}

        @Override
        public void onProviderEnabled(String provider) {}

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {}
    }

    String removeDuplicates(String str)
    {
        String result="";
        LinkedHashSet<Character> lhs = new LinkedHashSet<>();
        for(int i=0;i<str.length();i++)
            lhs.add(str.charAt(i));

        // print string after deleting duplicate elements
        for(Character ch : lhs)
           result+=ch;


        return result;
    }
}
