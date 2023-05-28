package com.example.newsapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.tabs.TabItem;

public class webView extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar mtoolbar;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        mtoolbar=findViewById(R.id.toolbar);
        webView=findViewById(R.id.webview);
        setSupportActionBar(mtoolbar);

        Intent intent=getIntent();
        String url=intent.getStringExtra("url");
        //Toast.makeText(getApplicationContext(),url,Toast.LENGTH_SHORT).show();
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
     //   webView.getSettings().setLoadsImagesAutomatically(true);
      //  webView.getSettings().setJavaScriptEnabled(true);
     //   webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
      //  webView.loadUrl(url);





    }


}