package com.example.warungfikri

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import android.webkit.WebView
import kotlinx.android.synthetic.main.activity_ppkd.*

class Ppkd : AppCompatActivity() {
  private lateinit var webView: WebView
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_ppkd)

    webView = findViewById(R.id.webView)
    // WebViewClient allows you to handle
    // onPageFinished and override Url loading.
    webView.webViewClient = WebViewClient()

    // this will load the url of the website
    webView.loadUrl("https://www.detik.com/")

    // this will enable the javascript settings, it can also allow xss vulnerabilities
    webView.settings.javaScriptEnabled = true

    // if you want to enable zoom feature
    webView.settings.setSupportZoom(true)
  }

  // if you press Back button this code will work
  override fun onBackPressed() {
    // if your webview can go back it will go back
    if (webView.canGoBack())
      webView.goBack()
    // if your webview cannot go back
    // it will exit the application
    else
      super.onBackPressed()
  }
}