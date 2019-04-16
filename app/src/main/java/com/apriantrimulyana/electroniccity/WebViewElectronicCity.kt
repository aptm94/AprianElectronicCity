package com.apriantrimulyana.electroniccity

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.DownloadListener
import com.apriantrimulyana.electroniccity.util.TouchyWebView
import kotlinx.android.synthetic.main.activity_web_view_electronic_city.*
import android.annotation.SuppressLint
import android.graphics.Bitmap
import com.apriantrimulyana.electroniccity.util.OtherUtil.hideAlertDialog
import com.apriantrimulyana.electroniccity.util.OtherUtil.showAlertDialogLoading
import android.webkit.WebView
import android.content.DialogInterface
import android.widget.Toast
import android.webkit.WebViewClient


class WebViewElectronicCity : AppCompatActivity() {
    private var awvElCty: TouchyWebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view_electronic_city)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }

        awvElCty = awv_electronic_city as TouchyWebView?


        awvElCty!!.setDesktopMode(true)
        awvElCty!!.getSettings().setAllowFileAccess(true)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            awvElCty!!.getSettings().setAllowFileAccessFromFileURLs(true)
            awvElCty!!.getSettings().setAllowUniversalAccessFromFileURLs(true)
        }
        awvElCty!!.getSettings().setAppCacheEnabled(true)
        awvElCty!!.getSettings().setDomStorageEnabled(true)
        awvElCty!!.getSettings().setJavaScriptEnabled(true)
        awvElCty!!.getSettings().setBuiltInZoomControls(true)
        awvElCty!!.getSettings().setDisplayZoomControls(false)
        awvElCty!!.getSettings().setSupportZoom(true)
        awvElCty!!.zoomIn()
        awvElCty!!.zoomOut()


        awvElCty!!.setDownloadListener(DownloadListener { url, userAgent, contentDisposition, mimeType, contentLength ->
            val uri = Uri.parse(url)
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = uri
            startActivity(intent)
        })

        hideAlertDialog()
        showAlertDialogLoading(this@WebViewElectronicCity, "Please Wait ...")
        awvElCty!!.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }

            override fun onPageFinished(view: WebView, url: String) {
                hideAlertDialog()
            }

            override fun onReceivedError(view: WebView, errorCode: Int, description: String, failingUrl: String) {
                hideAlertDialog()
            }
        })
        awvElCty!!.loadUrl("https://electronic-city.com/")
    }


    override fun onResume() {
        super.onResume()
        awvElCty!!.onResume()
    }

    override fun onPause() {
        awvElCty!!.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        awvElCty!!.onDestroy()
        super.onDestroy()
    }


    override fun onBackPressed() {
        if (!awvElCty!!.onBackPressed()) {
            return
        }
        // ...
        super.onBackPressed()
    }
}
