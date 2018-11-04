package com.saiyandapalli.vroom;

import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.directions.route.AbstractRouting;
import com.directions.route.Route;
import com.directions.route.RouteException;
import com.directions.route.Routing;
import com.directions.route.RoutingListener;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, RoutingListener {

    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private boolean mLocationPermissionGranted;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private LatLng currLoc;
    private static final int DEFAULT_ZOOM = 15;
    private final LatLng mDefaultLocation = new LatLng(-33.8523341, 151.2106085);

    private List<Polyline> polylines;
    private static final int[] COLORS = new int[]{R.color.primary_dark_material_light};

    //Replace these with the corresponding arrays:
    int[] GROUP_ICON = {R.drawable.snack, R.drawable.snack, R.drawable.snack, R.drawable.snack, R.drawable.snack, R.drawable.snack, R.drawable.snack, R.drawable.snack, R.drawable.snack};
    String[] GROUP_NAMES = {"Azaad", "NeuroTech", "Anova", "MDB", "AX Captains", "Zahanat", "Cal Bhangra", "Roomies", "All"};
    String[] friendsLocations = {"19811 Portal Plaza, Cupertino, CA"};
    String[] friendsNames = {"Cupertino"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        polylines = new ArrayList<>();

        // Construct a FusedLocationProviderClient.
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ListView nav_view = (ListView) findViewById(R.id.nav_view);
        CustomAdapter customAdapter = new CustomAdapter();
        nav_view.setAdapter(customAdapter);

        nav_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), GROUP_NAMES[i], Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        getLocationPermission();
        if (mLocationPermissionGranted) {
            getDeviceLocation();
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mMap.setMyLocationEnabled(true);

            Geocoder geocoder = new Geocoder(this);
            double minDistance = Double.MAX_VALUE;
            Address[] friendAddresses = new Address[friendsLocations.length];
            int minAddIndex = 0;
            for (int i = 0; i < friendsLocations.length; i++) {
                try {
                    friendAddresses[i] = geocoder.getFromLocationName(friendsLocations[i], 1).get(0);

//                    while (currLoc == null) {
//                        getDeviceLocation();
//                    }
//
//                    float results[] = new float[10];
//                    Location.distanceBetween(currLoc.latitude, currLoc.longitude, friendAddresses[i].getLatitude(), friendAddresses[i].getLongitude(), results);
//                    float dist = results[0];
//                    if (minDistance > dist) {
//                        minAddIndex = i;
//                        minDistance = dist;
//                    }

                    Toast.makeText(this, friendsLocations[i], Toast.LENGTH_SHORT).show();
                    moveCamera(new LatLng(friendAddresses[i].getLatitude(), friendAddresses[i].getLongitude()), DEFAULT_ZOOM, friendsNames[i]);
                } catch (IOException e) {

                }
            }

            //getRouteToMarker(new LatLng(friendAddresses[minAddIndex].getLatitude(), friendAddresses[minAddIndex].getLongitude()));
        }

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);

//        mMap.addMarker(new MarkerOptions()
//                .position(curr_loc)
//                .title("Your Location"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(curr_loc));
    }

    private void getRouteToMarker(LatLng endLatLng) {
        Routing routing = new Routing.Builder()
                .travelMode(AbstractRouting.TravelMode.DRIVING)
                .withListener(this)
                .alternativeRoutes(false)
                //.waypoints(currLoc, endLatLng)
                .waypoints(new LatLng(endLatLng.latitude +  100, endLatLng.longitude + 100), endLatLng)
                .build();
        routing.execute();
    }

    private void getDeviceLocation() {
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        try {
            final Task location = mFusedLocationProviderClient.getLastLocation();
            location.addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if (task.isSuccessful()) {
                        Location currLoc = (Location) task.getResult();
                        moveCamera(new LatLng(currLoc.getLatitude(), currLoc.getLongitude()), DEFAULT_ZOOM, "My Location");
                    } else {
                        Toast.makeText(MapsActivity.this, "unable to get the location", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } catch (SecurityException e) {

        }
    }

    private void moveCamera(LatLng latLng, float zoom, String title) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));

        if (!title.equals("My Location")) {
            MarkerOptions options = new MarkerOptions().position(latLng).title(title);
            mMap.addMarker(options);
        } else {
            currLoc = latLng;
        }
    }

    @Override
    public void onRoutingFailure(RouteException e) {
        if(e != null) {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this, "Something went wrong, Try again", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRoutingStart() {

    }

    @Override
    public void onRoutingSuccess(ArrayList<Route> route, int shortestRouteIndex) {
        if(polylines.size()>0) {
            for (Polyline poly : polylines) {
                poly.remove();
            }
        }

        polylines = new ArrayList<>();
        //add route(s) to the map.
        for (int i = 0; i <route.size(); i++) {

            //In case of more than 5 alternative routes
            int colorIndex = i % COLORS.length;

            PolylineOptions polyOptions = new PolylineOptions();
            polyOptions.color(getResources().getColor(COLORS[colorIndex]));
            polyOptions.width(10 + i * 3);
            polyOptions.addAll(route.get(i).getPoints());
            Polyline polyline = mMap.addPolyline(polyOptions);
            polylines.add(polyline);

            Toast.makeText(getApplicationContext(),"Route "+ (i+1) +": distance - "+ route.get(i).getDistanceValue()+": duration - "+ route.get(i).getDurationValue(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRoutingCancelled() {

    }

    private void erasePolylines() {
        for (Polyline line: polylines) {
            line.remove();
        }
        polylines.clear();
    }


    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return GROUP_NAMES.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.groups_layout, null);

            final ImageView imgView = (ImageView) view.findViewById(R.id.imageView);
            final TextView txtView = (TextView) view.findViewById(R.id.textView_name);

            imgView.setImageResource(GROUP_ICON[i]);
            txtView.setText(GROUP_NAMES[i]);

            return view;
        }
    }
}
