package com.owenandroid.keepeye.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.owenandroid.keepeye.R;

/**
 * Created by Administrator on 2017-06-03.
 */

public class ArticleContentActivity extends BaseActivity {
    private WebView mWebView;
    private ProgressBar progressBar;
    private String url;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        String title = intent.getStringExtra("title");
        getSupportActionBar().setTitle(title);
        initView();
    }

    private void initView() {
        progressBar = (ProgressBar) findViewById(R.id.content_progressbar);
        mWebView = (WebView) findViewById(R.id.content_webview);
        //设置支持js
        mWebView.getSettings().setJavaScriptEnabled(true);
        //支持缩放
        mWebView.getSettings().setSupportZoom(true);
        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100){
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl(url);
    }
}
