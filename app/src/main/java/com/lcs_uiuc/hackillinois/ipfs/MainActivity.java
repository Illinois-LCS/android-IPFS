package com.lcs_uiuc.hackillinois.ipfs;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.drm.DrmStore;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        buttonReport.OnButtonFragmentInteractionListener,
        ConnectionsFragment.OnConnectionsFragmentInteractionListener{

    private DrawerLayout mDrawer;
    private Toolbar tb;
    private NavigationView nDrawer;

    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Thread ipfsThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    IPFS ipfs = new IPFS("/ip4/127.0.0.1/tcp/5001");
                }
                catch (Exception e ){
                    e.printStackTrace();
                }
            }
        });
        ipfsThread.start();

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();


        nDrawer = (NavigationView) findViewById(R.id.nvView);

        setupDrawerContent(nDrawer);

        NamedStreamable.FileWrapper file = new NamedStreamable.FileWrapper(new File("hello.txt"));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
           return true;
        }
        if(id == android.R.id.home){
            mDrawer.openDrawer(GravityCompat.START);
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        android.support.v4.app.Fragment fragment = null;
        FragmentManager fm = getFragmentManager();
        Class fragmentClass = null;


            if (id == R.id.nav_home) {
            } else if (id == R.id.nav_connections) {
               fragmentClass = ConnectionsFragment.class;
            } else if (id == R.id.nav_contributions) {

            } else if (id == R.id.nav_report) {
                fragmentClass = buttonReport.class;

            } else if (id == R.id.nav_settings) {
                fragment = null;

            } else if (id == R.id.nav_share) {
                Uri uri = Uri.parse("http://www.github.com/Illinois-LCS/android-IPFS");
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                startActivity( i );

            } else if (id == R.id.nav_send) {
                fragment = new android.support.v4.app.Fragment();

                //FragmentTransaction ft = fm.beginTransaction();
                //ft.replace(R.id.fragment_report11, new Fragment());
                //ft.commit();
            }

            try{
                fragment = (android.support.v4.app.Fragment) fragmentClass.newInstance();
            } catch (Exception e){
                e.printStackTrace();
            }

            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

            item.setChecked(true);
            setTitle(item.getTitle());
            mDrawer.closeDrawers();

    }

    @Override
    public void onButtonFragmentInteraction(View view) {
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void onConnectionFragmentInteraction(int position) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}