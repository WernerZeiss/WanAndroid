package com.zcrain.wanandroid.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.zcrain.wanandroid.Constant
import com.zcrain.wanandroid.R
import com.zcrain.wanandroid.databinding.ActivityWebBinding

/**
 * @author CWQ
 * @date 11/26/20
 */
class WebActivity : AppCompatActivity() {

    lateinit var mBinding: ActivityWebBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_web)
        initWebView()

        intent.getStringExtra(Constant.URL)?.run {
            mBinding.web.loadUrl(this)
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {
        mBinding.web.apply {
//            webViewClient = MyClient()
            settings.javaScriptEnabled = true
            settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            settings.javaScriptCanOpenWindowsAutomatically = true
            settings.javaScriptEnabled = true
            settings.setSupportZoom(false)
            settings.builtInZoomControls = false
            settings.useWideViewPort = false
            settings.loadWithOverviewMode = false
            settings.textZoom = 100
            settings.setSupportMultipleWindows(true)
            settings.allowFileAccess = true
            settings.cacheMode = WebSettings.LOAD_NO_CACHE
            settings.domStorageEnabled = true
            settings.databaseEnabled = true
            settings.blockNetworkImage = false
            settings.defaultTextEncodingName = "utf-8"
        }
    }

    class MyClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            return super.shouldOverrideUrlLoading(view, request)
        }
    }


    companion object {

        fun start(context: Context, url: String) =
            context.startActivity(Intent(context, WebActivity::class.java).apply {
                putExtra(Constant.URL, url)
            })
    }
}