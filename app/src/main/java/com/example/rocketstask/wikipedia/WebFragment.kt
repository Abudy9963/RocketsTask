package com.example.rocketstask.wikipedia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.rocketstask.R
import com.example.rocketstask.databinding.FragmentOverviewBinding
import com.example.rocketstask.detail.DetailFragmentArgs
import kotlinx.android.synthetic.main.fragment_web.*

class WebFragment : Fragment(R.layout.fragment_web) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val url = WebFragmentArgs.fromBundle(requireArguments()).url
        webView.webViewClient= WebViewClient()
        webView.loadUrl(url)
    }
}