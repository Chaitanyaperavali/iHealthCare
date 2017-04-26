package com.ase.team22.ihealthcare;
        import android.content.Intent;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.support.v4.app.FragmentActivity;
        import android.os.Bundle;
        import com.ase.team22.ihealthcare.jsonmodel.Doctor;
        import com.ase.team22.ihealthcare.jsonmodel.DoctorsData;
        import com.ase.team22.ihealthcare.jsonmodel.ResponseJSONBetterDoctor;
        import com.google.android.gms.ads.formats.NativeAd;
        import com.google.android.gms.maps.CameraUpdateFactory;
        import com.google.android.gms.maps.GoogleMap;
        import com.google.android.gms.maps.OnMapReadyCallback;
        import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
        import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
        import com.google.android.gms.maps.GoogleMap.OnInfoWindowCloseListener;
        import com.google.android.gms.maps.GoogleMap.OnInfoWindowLongClickListener;
        import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
        import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
        import com.google.android.gms.maps.model.BitmapDescriptor;
        import com.google.android.gms.maps.model.BitmapDescriptorFactory;
        import com.google.android.gms.maps.SupportMapFragment;
        import com.google.android.gms.maps.model.LatLng;
        import com.google.android.gms.maps.model.MarkerOptions;
        import android.util.Log;
        import android.view.View;
        import android.view.animation.BounceInterpolator;
        import android.view.animation.Interpolator;
        import android.widget.CheckBox;
        import android.widget.ImageView;
        import android.widget.RadioGroup;
        import android.widget.RadioGroup.OnCheckedChangeListener;
        import android.widget.SeekBar;
        import android.widget.SeekBar.OnSeekBarChangeListener;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.util.ArrayList;
        import java.util.Iterator;
        import java.util.List;
        import java.util.Random;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Intent intent = getIntent();
        ResponseJSONBetterDoctor responseJSONBetterDoctor = (ResponseJSONBetterDoctor) intent.getSerializableExtra("response_data");



       /* if (responseJSONBetterDoctor != null) {
            doctorList = (ArrayList<DoctorsData>) responseJSONBetterDoctor.getData();
            Log.i("dummy", doctorList.size() + "");
        }*/
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
        int i=0;

        class Doctorinfo{
            double lat;
            double lng;
            String name;
            Bitmap icon;
            String snip;


            public Doctorinfo(double v, double v1, String s,Bitmap icon, String snip) {
                this.lat = v;
                this.lng = v1;
                this.name = s ;
                this.icon = icon;
                this.snip = snip;
            }
        }
        Doctorinfo D1 = new Doctorinfo(39.14956,-94.5516,"Sarah Hon",BitmapFactory.decodeResource(getResources(), R.drawable.sarah),"Sarah Hon is experienced in the use of botulinum toxin (BOTOX®) for treatment of migraine headache, as well as other neurological disorders including cervical dystonia, blepharospasm and limb spasticity.");
        Doctorinfo D2 = new Doctorinfo(39.30729,-94.91843,"Norman Waitley",BitmapFactory.decodeResource(getResources(), R.drawable.noimage),"Norman's practice is open to all areas of pain management with a special interest in chronic migraine headaches.");
        Doctorinfo D3 = new Doctorinfo(39.0101228,-94.6275319,"Dr. Iftekhar Ahmed",BitmapFactory.decodeResource(getResources(), R.drawable.doc),"Specializes in managing acute and chronic pain");
        ArrayList <Doctorinfo> dl = new ArrayList<Doctorinfo>();
        dl.add(D1);
        dl.add(D2);
        dl.add(D3);
       // Iterator itr = D1.iterator();

        // Add a marker in Sydney and move the camera
       // if (doctorList.size() > 0) {
            for (i=0;i<3;i++) {
               // LatLng sydney = new LatLng(doc.getLat(), doc.getLon());
                LatLng docPos = new LatLng(dl.get(i).lat, dl.get(i).lng);
                mMap.addMarker(new MarkerOptions()
                        .position(docPos)
                        .title(dl.get(i).name)
                        .icon(BitmapDescriptorFactory.fromBitmap(dl.get(i).icon))
                        .snippet(dl.get(i).snip)
                );
                //mMap.addMarker(new MarkerOptions().position(sydney).title("marker in sydney"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(docPos));
            }

        //}
       // else {
        //    Toast.makeText(getApplicationContext(), "Doctors not available", Toast.LENGTH_LONG).show();
       // }

        if (mMap == null) {
            Toast.makeText(getApplicationContext(), "Sorry! Unable to create map", Toast.LENGTH_LONG).show();
        }
    }


}
