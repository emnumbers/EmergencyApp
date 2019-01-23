package com.c4k.emnumbers;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener
,ActivityCompat.OnRequestPermissionsResultCallback {

    Fragment fragment;
    Toolbar v7Toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        v7Toolbar = findViewById(R.id.mainToolbar);
        setSupportActionBar(v7Toolbar);

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar_layout);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        setTitle("ارقام الطوارئ");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        fragment = new NumbersFragment();
        Bundle bundle=new Bundle();
        bundle.putString("gover_name","default");
        fragment.setArguments(bundle);
         LoadFragment(fragment);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bottomNavigationView.getLayoutParams();
        layoutParams.setBehavior(new ScrollBehavior());
    }

    boolean LoadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment).commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.emergencyItem:
                fragment = new NumbersFragment();
                Bundle bundle = new Bundle();
                bundle.putString("gover_name", "default");
                fragment.setArguments(bundle);
                return LoadFragment(fragment);
            case R.id.goverItem:
                fragment = new GoverFragment();
                return LoadFragment(fragment);
            default:
                return true;
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.rateItem:
                RateClass.RateApp(this);
                return true;
            case R.id.aboutItem:
                Intent aboutIntent = new Intent(this, AboutActivity.class);
                startActivity(aboutIntent);
                return true;
            case android.R.id.home:
                LoadFragment(new GoverFragment());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 9867) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                ((NumbersFragment)fragment).recyclerAdapter
                        .callPhoneClass.MakePhoneCall(((NumbersFragment)fragment).recyclerAdapter.phoneNumber);
            }
        }
    }
}