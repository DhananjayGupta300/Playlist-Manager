package com.example.dhananjaygupta.dhananjaygupta_project2;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
public class WebPage extends AppCompatActivity {

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_page);
        setTitle(this.getIntent().getExtras().getString("title"));
        webView = (WebView)findViewById(R.id.webview);
        webView.setWebViewClient(new MyWebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(this.getIntent().getExtras().getString("url"));
    }
    class MyWebViewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
