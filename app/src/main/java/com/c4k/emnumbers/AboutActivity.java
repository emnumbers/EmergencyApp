package com.c4k.emnumbers;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;


public class AboutActivity extends AppCompatActivity {
    Toolbar v7Toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // Support toolbar for backward devices
        v7Toolbar = findViewById(R.id.aboutToolBar);
        setSupportActionBar(v7Toolbar);
        // Show back arrow button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            // when back arrow pressed
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void SendAnEmail() {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", "emnumbers-iraq@codeforiraq.org", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "رأيي بالتطبيق");
        startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }

    void LoadFragment(String title,String text){
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();

        AboutFragment aboutFragment=new AboutFragment();
        Bundle bundle=new Bundle();
        bundle.putString("title",title);
        bundle.putString("text",text);
        aboutFragment.setArguments(bundle);
        aboutFragment.show(fragmentTransaction,"aboutFragment");
    }

    public void AboutCode(View view) {
        LoadFragment("مبادرة البرمجة من اجل العراق",getString(R.string.about_codeforiraq));
    }

    public void AboutTeam(View view) {
        StringBuilder aboutTeam = new StringBuilder();
        String[]team=getResources().getStringArray(R.array.about_team);
        for (String item:team){
            aboutTeam.append(item).append("\n");
        }
        LoadFragment("فريق العمل", aboutTeam.toString());
    }

    public void AboutApp(View view) {
        LoadFragment("حول التطبيق",getString(R.string.about_app));
    }

    public void MailUs(View view) {
        SendAnEmail();
    }
}