package com.ase.team22.ihealthcare;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ase.team22.ihealthcare.othermodel.UserDetails;
import com.bumptech.glide.Glide;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;


public class Home extends AppCompatActivity {

    private AccountHeader headerResult;
    private Drawer result;

    LineGraphSeries<DataPoint> series;


    private TextView tvIntro;
    private UserDetails user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent i = getIntent();
        if(i.getExtras() != null){
            if(i.getExtras().get("facebook").equals("facebook")){
                FacebookSdk.sdkInitialize(getApplicationContext());
            }
        }
        user = UserDetails.getActiveUserInstance();
        tvIntro = (TextView) findViewById(R.id.tv_intro);
        tvIntro.setText("Welcome "+user.getFirstName()+" "+user.getLastName());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /*CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(getString(R.string.drawer_item_collapsing_toolbar_drawer));*/


        double y,x;
        x = -5.0;
        GraphView graph = (GraphView)findViewById(R.id.graph);
        series = new LineGraphSeries<DataPoint>();
        for(int j = 0;j< 500; j++)
        {
            x= x+0.1;
            y = Math.sin(x);
            series.appendData(new DataPoint(x,y),true,500);
        }
        graph.addSeries(series);

        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withCompactStyle(false)
                .withHeaderBackground(R.drawable.header)
                .withSavedInstance(savedInstanceState)
                .build();

        result = new DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(headerResult)
                .withToolbar(toolbar)
                .withFullscreen(true)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.drawer_item_home).withIcon(FontAwesome.Icon.faw_home).withIdentifier(1),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_previous_reports).withIcon(FontAwesome.Icon.faw_line_chart).withIdentifier(2),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_near_by_doctors).withIcon(FontAwesome.Icon.faw_stethoscope).withIdentifier(3),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_book_appointment).withIcon(FontAwesome.Icon.faw_ambulance).withIdentifier(4),
                        new SectionDrawerItem().withName(R.string.drawer_item_section_header),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_settings).withIcon(FontAwesome.Icon.faw_cog).withIdentifier(5),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_help).withIcon(FontAwesome.Icon.faw_question).withEnabled(false),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_contact).withIcon(FontAwesome.Icon.faw_bullhorn).withIdentifier(6),
                        new SectionDrawerItem().withName(""),
        new SecondaryDrawerItem().withName(R.string.drawer_item_logout).withIcon(FontAwesome.Icon.faw_sign_out).withIdentifier(7)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        //check if the drawerItem is set.
                        //there are different reasons for the drawerItem to be null
                        //--> click on the header
                        //--> click on the footer
                        //those items don't contain a drawerItem

                        if (drawerItem != null) {
                            Intent intent = null;
                            if (drawerItem.getIdentifier() == 7) {
                               // Log.i(this.getClass().getName(),"login behaviour of facebook in logout method: ");
                                LoginManager.getInstance().logOut();
                                FirebaseAuth.getInstance().signOut();
                                intent = new Intent(Home.this, FirstpageActivity.class);
                            } /*else if (drawerItem.getIdentifier() == 2) {
                                intent = new Intent(Home.this, ActionBarActivity.class);
                            } else if (drawerItem.getIdentifier() == 3) {
                                intent = new Intent(Home.this, MultiHome.class);*/
                            if (intent != null) {
                                Home.this.startActivity(intent);
                            }
                        }

                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .withShowDrawerOnFirstLaunch(true)
                .build();

        fillFab();
        //loadBackdrop();
    }


   /* private void loadBackdrop() {
        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
        Glide.with(this).load("https://unsplash.it/600/300/?random").centerCrop().into(imageView);
    }*/

    private void fillFab() {
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_add_diagnosis);
        fab.setImageDrawable(new IconicsDrawable(this, GoogleMaterial.Icon.gmd_plus_circle).actionBar().color(Color.WHITE));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the drawer to the bundle
        outState = result.saveInstanceState(outState);
        //add the values which need to be saved from the accountHeader to the bundle
        outState = headerResult.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }
    public void newDiagnosis(View view){
        if(view.getId() == R.id.fab_add_diagnosis){
            Intent intent = new Intent(this,NewDiagnosis.class);
            startActivity(intent);
        }
    }
}