package com.ase.team22.ihealthcare;
        import android.content.Intent;
        import android.graphics.Bitmap;
        import android.os.AsyncTask;
        import android.support.v4.app.FragmentActivity;
        import android.os.Bundle;

        import com.ase.team22.ihealthcare.helpers.ImageRequestHelper;
        import com.ase.team22.ihealthcare.jsonmodel.DoctorsData;
        import com.ase.team22.ihealthcare.jsonmodel.ResponseJSONBetterDoctor;
        import com.google.android.gms.maps.CameraUpdateFactory;
        import com.google.android.gms.maps.GoogleMap;
        import com.google.android.gms.maps.OnMapReadyCallback;
        import com.google.android.gms.maps.model.BitmapDescriptorFactory;
        import com.google.android.gms.maps.SupportMapFragment;
        import com.google.android.gms.maps.model.LatLng;
        import com.google.android.gms.maps.model.MarkerOptions;
        import java.net.URL;
        import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ArrayList<DoctorsData> doctorList ;
    private ArrayList<Doctorinfo> doctorinfoArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Intent intent = getIntent();
        ResponseJSONBetterDoctor responseJSONBetterDoctor = (ResponseJSONBetterDoctor) intent.getSerializableExtra("response_data");
        if (responseJSONBetterDoctor.getData().size() > 0) {
            doctorList = (ArrayList<DoctorsData>) responseJSONBetterDoctor.getData();
            for (DoctorsData doc : doctorList) {
                if (doc.getDoctors() != null) {
                    if (doc.getDoctors().size() > 0) {
                        Doctorinfo doctorinfo = new Doctorinfo();
                        doctorinfo.setLat(doc.getDoctors().get(0).getLat());
                        doctorinfo.setLng(doc.getDoctors().get(0).getLon());
                        doctorinfo.setIconUrl(doc.getProfile().getImageUrl());
                        doctorinfo.setName(doc.getProfile().getFirstName() + " " + doc.getProfile().getLastName());
                        doctorinfo.setSnip(doc.getProfile().getBio().substring(0, 250));
                        doctorinfoArrayList.add(doctorinfo);
                        //Log.i(this.getClass().getName(),doctorinfo.getName()+" "+doctorinfo.getIconUrl());
                    }
                }
            }
        }
        new BitmapImageLoader().execute();
    }

    public void renderMap() {
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        for(Doctorinfo doctorinfo: doctorinfoArrayList) {
            if(doctorinfo.getIcon() != null){
                LatLng latLng = new LatLng(doctorinfo.getLat(),doctorinfo.getLng());
                mMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title(doctorinfo.getName())
                        .icon(BitmapDescriptorFactory.fromBitmap(doctorinfo.getIcon()))
                        .snippet(doctorinfo.getSnip())
                );
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            }
        }

    }

    private class BitmapImageLoader extends AsyncTask<URL, Integer, Long> {
        protected Long doInBackground(URL... urls) {
            for(Doctorinfo doc : doctorinfoArrayList){
                //get images in background and save in icon variable of doctor info
                doc.setIcon(ImageRequestHelper.getBitmapImage(doc.getIconUrl()));
            }
            return null;
        }

        @Override
        protected void onPostExecute(Long aLong) {
            renderMap();
        }
    }



        class Doctorinfo {

            double lat;
            double lng;
            String name;
            Bitmap icon;
            String snip;
            String iconUrl;

            public String getIconUrl() {
                return iconUrl;
            }

            public void setIconUrl(String iconUrl) {
                this.iconUrl = iconUrl;
            }

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }

            public double getLng() {
                return lng;
            }

            public void setLng(double lng) {
                this.lng = lng;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Bitmap getIcon() {
                return icon;
            }

            public void setIcon(Bitmap icon) {
                this.icon = icon;
            }

            public String getSnip() {
                return snip;
            }

            public void setSnip(String snip) {
                this.snip = snip;
            }
        }
    }
