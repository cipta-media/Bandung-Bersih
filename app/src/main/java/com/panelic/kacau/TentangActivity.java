package com.panelic.kacau;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Wahyu on 10/01/2016.
 */
public class TentangActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tentang_layout);

        String url = "file:///android_asset/about.html";  //Pendefinisian URL
        WebView view = (WebView) this.findViewById(R.id.webView);  //sinkronisasi object berdasarkan id
        view.getSettings().setJavaScriptEnabled(true);  //untuk mengaktifkan javascript
        view.loadUrl(url);   //agar URL terload saat dibuka aplikasi
        view.setWebViewClient(new MyBrowser());
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
