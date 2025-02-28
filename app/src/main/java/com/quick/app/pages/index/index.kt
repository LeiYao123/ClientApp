package com.quick.app.pages.index

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.quick.app.PreviewContent
import com.quick.app.pages.cart.CartRoute
import com.quick.app.pages.category.CategoryRoute
import com.quick.app.pages.home.HomeRoute
import com.quick.app.pages.me.MeRoute
import com.quick.app.pages.video.VideoRoute
import com.quick.app.route.PageRoutes
import kotlinx.coroutines.launch

@Composable
fun IndexRoute() {
    IndexScreen()
}

@Composable
fun IndexScreen() {
    val scope = rememberCoroutineScope()
    var currTab: String by remember { mutableStateOf(PageRoutes.Home.route) }
    val pagerState = rememberPagerState(pageCount = { BottomBarItem.entries.size })

    Column(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            state = pagerState,
            userScrollEnabled = false,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            when (it) {
                0 -> HomeRoute()
                1 -> VideoRoute()
                2 -> CategoryRoute()
                3 -> CartRoute()
                4 -> MeRoute()
            }
        }
        BottomBar(currItem = currTab) { item, idx ->
            currTab = item
            scope.launch {
                pagerState.scrollToPage(idx)
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun IndexRoutePreview() {
    PreviewContent("index")
}