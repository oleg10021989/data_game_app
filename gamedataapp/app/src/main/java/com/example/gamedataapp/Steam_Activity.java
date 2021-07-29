package com.example.gamedataapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import static com.example.gamedataapp.DetailActivity.EXTRA_URL_STEAM;

public class Steam_Activity extends AppCompatActivity {
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steam);

        Intent intent = getIntent();
        String steam_url = intent.getStringExtra(EXTRA_URL_STEAM);


        webView = findViewById(R.id.steam_wabView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(steam_url);



    }
}
