package in22labs.englishlearning.ProfileManagement;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import in22labs.englishlearning.R;

public class CityrankActivity extends AppCompatActivity {
    TextView txt_rankcity, city_rankoverall, city_toprank;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cityrank);
        txt_rankcity=(TextView)findViewById(R.id.cityrank_location);
        city_toprank=(TextView)findViewById(R.id.cityrank_toprank);
        city_rankoverall=(TextView)findViewById(R.id.cityrank_outoff);


       }
}
