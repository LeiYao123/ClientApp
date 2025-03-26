package com.quick.app.pages.web

import android.content.Intent
import android.net.Uri
import android.os.Message
import android.util.Log
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.quick.app.config.Constant.HTML_DATA_END
import com.quick.app.config.Constant.HTML_DATA_START
import com.quick.app.route.LocalNavController

@Composable
fun WebRoute(vm: WebViewModel = viewModel()) {
    val uiState by vm.uiState.collectAsState()
    val navController = LocalNavController.current

    when (val state = uiState) {
        is WebUiState.Success -> WebScreen(state.data, onBack = { navController.popBackStack() })
        else -> {}
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WebScreen(data: WebParam, onBack: () -> Unit) {
    val context = LocalContext.current
    var webTitle by remember { mutableStateOf("") }
    var currentProgress by remember { mutableIntStateOf(0) }

    val webView = remember {
        WebView(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )

            settings.apply {
                javaScriptEnabled = true
                loadsImagesAutomatically = true
                useWideViewPort = true
                loadWithOverviewMode = true
                setSupportZoom(true)
                builtInZoomControls = true
                displayZoomControls = false
                setSupportMultipleWindows(false)
                javaScriptCanOpenWindowsAutomatically = true
                safeBrowsingEnabled = true
                mediaPlaybackRequiresUserGesture = false
                domStorageEnabled = true
            }

            webViewClient = object : WebViewClient() {
                @Deprecated("Deprecated in Java")
                override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                    Log.d("web_view", "WebView onReceivedError: shouldOverrideUrlLoading: $url")
                    if (url.startsWith("http://") || url.startsWith("https://")) {
                        view.loadUrl(url)
                        return false
                    } else {
                        // 使用Intent打开其他类型的链接
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        context.startActivity(intent)
                        return true
                    }
                }

                override fun onReceivedError(
                    view: WebView,
                    request: WebResourceRequest,
                    error: WebResourceError,
                ) {
                    Log.d("web_view", "WebView onReceivedError: ${error.description}")
                }
            }
            webChromeClient = object : WebChromeClient() {
                override fun onCreateWindow(
                    view: WebView?,
                    isDialog: Boolean,
                    isUserGesture: Boolean,
                    resultMsg: Message?,
                ): Boolean {
                    val newWebView = WebView(context)
                    val transport = resultMsg?.obj as WebView.WebViewTransport
                    transport.webView = newWebView
                    resultMsg.sendToTarget()
                    return true
                }

                override fun onReceivedTitle(view: WebView, title: String) {
                    super.onReceivedTitle(view, title)
                    webTitle = title
                }

                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                    super.onProgressChanged(view, newProgress)
                    currentProgress = newProgress
                    Log.d("web_view", "onProgressChanged: $newProgress")

                }
            }

            if (data.uri != null) {
                loadUrl(data.uri)
            } else {
                //加载字符串html
                loadDataWithBaseURL(
                    null,
                    "${HTML_DATA_START}${data.content!!}${HTML_DATA_END}",
                    "text/html",
                    "utf-8",
                    null
                )
            }
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = webTitle,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    Icon(
                        Icons.Default.ArrowBackIosNew,
                        contentDescription = "返回",
                        modifier = Modifier.clickable {
                            Log.d("web_view", "我执行了。。。")
                            if (webView.canGoBack()) webView.goBack()
                            else onBack()
                        }
                    )
                },
                actions = {
                    IconButton(onClick = {
                        Log.d("web_view", "关闭webview")
                        onBack()
                    }) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = null)
                    }
                }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            AndroidView(
                factory = { _ -> webView },
                modifier = Modifier.fillMaxSize()
            )

            if (currentProgress < 100) {
                LinearProgressIndicator(
                    progress = { currentProgress * 1.0F },
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}
