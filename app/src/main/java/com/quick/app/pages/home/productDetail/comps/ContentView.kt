package com.quick.app.pages.home.productDetail.comps

import android.util.Log
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.quick.app.PreviewContent
import com.quick.app.R
import com.quick.app.models.ProductModel
import com.quick.app.pages.home.HomeViewModel
import com.quick.app.pages.home.MOCK_DATA
import com.quick.app.route.LocalNavController

@Composable
fun ContentView(data: ProductModel) {
    val navController = LocalNavController.current
    // 共享父级 viewModel 数据
    val parentEntry = remember { navController.getBackStackEntry("index/home") }
    val vm: HomeViewModel = viewModel(parentEntry)

    Column(modifier = Modifier.fillMaxWidth()) {
        ProductBanner(data.icons)
        ProductInfo(data)
        Spacer(modifier = Modifier.height(12.dp))
        ProductSettingItem("已选", "爱学啊 计数-->${vm.count.intValue}", onClick = {
            vm.count.intValue++
        })
        ProductSettingItem("送至", "四川省 成都市 高新区", onClick = {})
        ProductSettingItem("门店", "小米之家 四川省成都市印象城专卖店", onClick = {})
        HtmlWebView(data.detail ?: "")
    }
}


@Composable
fun ProductBanner(icons: List<String>? = emptyList()) {
    if (icons == null) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.BottomCenter,
        ) {
            Image(
                painter = painterResource(R.drawable.ic_launcher_background),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds,
                contentDescription = null
            )
        }
        return
    }
    val pageState = rememberPagerState(pageCount = { icons.size })
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        contentAlignment = Alignment.BottomCenter,
    ) {
        HorizontalPager(
            state = pageState,
            modifier = Modifier.fillMaxSize(),
        ) {
            AsyncImage(
                model = icons[it],
                placeholder = painterResource(R.drawable.ic_launcher_foreground),
                error = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds,
            )
        }
        Row(modifier = Modifier.padding(bottom = 10.dp)) {
            for (i in icons.indices) {
                Icon(
                    if (pageState.currentPage == i) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}


@Composable
fun ProductInfo(data: ProductModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.Bottom,
        ) {
            Text(
                text = "\$${data.price}",
                style = MaterialTheme.typography.titleLarge,
                color = Color.Red
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "\$${data.originPrice}",
                style = MaterialTheme.typography.titleSmall.copy(
                    textDecoration = TextDecoration.LineThrough
                ),
                color = MaterialTheme.colorScheme.outline,
            )
        }
        Text(
            text = data.title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )
        data.highlight?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.outline,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
        Text(
            text = "${data.commentsCount} 条评论 ${data.commentsRate}% 好评",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.outline,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
fun ProductSettingItem(title: String, value: String, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(12.dp)
            .clickable { onClick() }

    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.widthIn(60.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.weight(1f)
        )
        Icon(Icons.Default.ChevronRight, contentDescription = null)
    }
}

@Composable
fun HtmlWebView(htmlContent: String) {
    Log.d("ttt", htmlContent)
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                settings.apply {
                    loadsImagesAutomatically = true // 自动加载图片
                    // 允许加载 HTTP 资源
                    settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                }
                loadDataWithBaseURL(null, wrapHtml(htmlContent), "text/html", "utf-8", null)
            }
        }
    )
}

fun wrapHtml(content: String): String {
    return """
        <html>
        <head>
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <style>
                * { max-width: 100% !important; overflow-x: hidden; word-break: break-word; }
                body { margin: 0; padding: 0; }
                img { width: 100% !important; height: auto !important; display: block; }
            </style>
        </head>
        <body>
            $content
        </body>
        </body>
        </html>
    """.trimIndent()
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ContentPreview() {
    PreviewContent(null) {
        ContentView(MOCK_DATA)
    }
}