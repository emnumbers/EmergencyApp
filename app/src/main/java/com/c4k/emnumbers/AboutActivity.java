package com.c4k.emnumbers;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class AboutActivity extends AppCompatActivity {
    WebView webView;
    SwipeRefreshLayout swipe;
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
        swipe = findViewById(R.id.swipe);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                WebAction();
            }
        });
        WebAction();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.email_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            // when back arrow pressed
            case android.R.id.home:
                finish();
                return true;
            case R.id.sendItem:
                SendAnEmail();
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

    public void WebAction() {

        webView = findViewById(R.id.WebV);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.loadUrl("file:///android_asset/about_us.html");
        swipe.setRefreshing(true);
        webView.setWebViewClient(new WebViewClient() {

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

            }

            public void onPageFinished(WebView view, String url) {
                // do your stuff here
                swipe.setRefreshing(false);
            }

        });

    }

    @Override
    public void onBackPressed() {

        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            finish();
        }
    }
}