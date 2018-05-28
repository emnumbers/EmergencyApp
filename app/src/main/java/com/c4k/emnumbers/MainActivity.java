package com.c4k.emnumbers;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {

    SearchView mySearchView;
    ListView eNumbersListView;
    ENumbersAdapter eNumbersAdapter;
    DBHelper dbHelper;
    List<ENumbersTable> eNumbersTableList = new ArrayList<>();
    Toolbar v7Toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        v7Toolbar = findViewById(R.id.mainToolBar);
        setSupportActionBar(v7Toolbar);
        eNumbersListView = findViewById(R.id.numbersListView);

        dbHelper = new DBHelper(getApplicationContext());

        eNumbersTableList = dbHelper.GetAll();
        eNumbersAdapter = new ENumbersAdapter(this, R.layout.listitem, eNumbersTableList);
        eNumbersListView.setAdapter(eNumbersAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.searchItem);
        mySearchView = (SearchView) searchItem.getActionView();

        mySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(final String newText) {
                ArrayList<ENumbersTable> newList = new ArrayList<>();
                for (ENumbersTable item : eNumbersTableList) {
                    if (item.getDescription().contains(newText)) {
                        newList.add(item);
                    }
                }
                eNumbersAdapter = new ENumbersAdapter(MainActivity.this, R.layout.listitem, newList);
                eNumbersListView.setAdapter(eNumbersAdapter);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.rateItem:
                RateClass.RateApp(getApplicationContext());
                return true;
            case R.id.aboutItem:
                Intent aboutIntent = new Intent(this, AboutActivity.class);
                startActivity(aboutIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == eNumbersAdapter.callPhoneClass.REQUEST_PHONE_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                eNumbersAdapter.callPhoneClass.MakePhoneCall(eNumbersAdapter.phoneNumber);
            }
        }
    }
}