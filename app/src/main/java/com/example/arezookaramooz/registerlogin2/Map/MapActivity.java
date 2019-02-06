package com.example.arezookaramooz.registerlogin2.Map;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.arezookaramooz.registerlogin2.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap googleMap;
    private static final int REQUEST_CODE = 101;
    private LocationManager locationManager;
    private Marker userMarker, destinationMarker;
    private Geocoder geocoder;
    private LatLng userLatLng, destinationLatLng;
    BufferedReader br;
    InputStream iStream;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        setupLocationManager();
    }

    private void setupLocationManager() {
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (locationManager != null) {
            if (ActivityCompat.checkSelfPermission(MapActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(MapActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION
                            , Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_CODE);
                }
                return;
            }

            if (locationManager.isProviderEnabled(locationManager.GPS_PROVIDER)) {
                Location location = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);
                onLocationChanged(location);
                locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 2000, 3, this);
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                if (destinationMarker != null) {
                    destinationMarker.remove();
                }
                destinationMarker = googleMap.addMarker(new MarkerOptions().position(latLng).title("your destination"));
                showDestinationAddress(latLng);
                destinationLatLng = latLng;
//                String url = getDirectionsUrl(userLatLng, destinationLatLng);
//                DownloadTask downloadTask = new DownloadTask();
//                downloadTask.execute(url);
            }
        });
        setupLocationManager();
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null && googleMap != null) {
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            if (userMarker != null) {
                userMarker.remove();
            }
            userMarker = googleMap.addMarker(new MarkerOptions().position(latLng).title("your location"));
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 15);
            googleMap.animateCamera(cameraUpdate);
            showUserAddress(location);
            userLatLng = latLng;
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("MapActivity", "request code is: " + grantResults[0]);
                setupLocationManager();
            }
        }
    }

    private void showUserAddress(Location location) {
        List<Address> addresses;
        geocoder = new Geocoder(this, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String knownName = addresses.get(0).getFeatureName();
            TextView textView = findViewById(R.id.text_view_user_address);
            if (state != null && city != null && knownName != null) {
                textView.setText("your current location: " + state + ", " + city + ", " + knownName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showDestinationAddress(LatLng latLng) {
        List<Address> addresses;
        geocoder = new Geocoder(this, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String knownName = addresses.get(0).getFeatureName();
            TextView textView = findViewById(R.id.text_view_destination_address);
            textView.setVisibility(View.VISIBLE);
            if (state != null && city != null && knownName != null) {
                textView.setText("your destination: " + state + ", " + city + ", " + knownName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//
//    private class DownloadTask extends AsyncTask<String, String, String> {
//
//        @Override
//        protected String doInBackground(String... url) {
//
//            String data = "";
//
//            try {
//                data = downloadUrl(url[0]);
//            } catch (Exception e) {
//                Log.d("Background Task", e.toString());
//            }
//            return data;
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            super.onPostExecute(result);
//
//            ParserTask parserTask = new ParserTask();
//
//
//            parserTask.execute(result);
//
//        }
//    }
//
//    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap>>> {
//
//        @Override
//        protected List<List<HashMap>> doInBackground(String... jsonData) {
//
//            JSONObject jObject;
//            List<List<HashMap>> routes = null;
//
//            try {
//                jObject = new JSONObject(jsonData[0]);
//                JSONParser parser = new JSONParser();
//                routes = (List<List<HashMap>>) parser.parse(String.valueOf(jObject));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return routes;
//        }
//
//        @Override
//        protected void onPostExecute(List<List<HashMap>> result) {
//            ArrayList points = null;
//            PolylineOptions lineOptions = null;
//            MarkerOptions markerOptions = new MarkerOptions();
//
//            for (int i = 0; i < result.size(); i++) {
//                points = new ArrayList();
//                lineOptions = new PolylineOptions();
//
//                List<HashMap> path = result.get(i);
//
//                for (int j = 0; j < path.size(); j++) {
//                    HashMap point = path.get(j);
//
//                    double lat = Double.parseDouble(point.get("lat").toString());
//                    double lng = Double.parseDouble(point.get("lng").toString());
//                    LatLng position = new LatLng(lat, lng);
//
//                    points.add(position);
//                }
//
//                lineOptions.addAll(points);
//                lineOptions.width(12);
//                lineOptions.color(Color.RED);
//                lineOptions.geodesic(true);
//
//            }
//
//// Drawing polyline in the Google Map for the i-th route
//            googleMap.addPolyline(lineOptions);
//        }
//    }
//
//    private String getDirectionsUrl(LatLng userLatLng, LatLng destinationLatLng) {
//
//        // Origin of route
//        String str_origin = "origin=" + userLatLng.latitude + "," + destinationLatLng.longitude;
//        // Destination of route
//        String str_dest = "destination=" +        // Sensor enabled
//                userLatLng.latitude + "," + destinationLatLng.longitude;
//
//        String sensor = "sensor=false";
//        String mode = "mode=driving";
//
//        // Building the parameters to the web service
//
//        br = new BufferedReader(new InputStreamReader(iStream));
//
//        String output = "json";
//
//        String parameters = str_origin + "&" + str_dest + "&" + sensor + "&" + mode;
//
//        // Output format
//
//        // Building the url to the web service
//        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;
//
//
//        return url;
//    }
//
//    private String downloadUrl(String strUrl) throws IOException {
//        String data = "";
//        iStream = null;
//        HttpURLConnection urlConnection = null;
//        try {
//            URL url = new URL(strUrl);
//
//            urlConnection = (HttpURLConnection) url.openConnection();
//
//            urlConnection.connect();
//
//            iStream = urlConnection.getInputStream();
//            StringBuffer sb = new StringBuffer();
//
//            String line = "";
//            while ((line = br.readLine()) != null) {
//                sb.append(line);
//            }
//
//            data = sb.toString();
//
//            br.close();
//
//        } catch (Exception e) {
//            Log.d("Exception", e.toString());
//        } finally {
//            iStream.close();
//            urlConnection.disconnect();
//        }
//        return data;
//    }
}