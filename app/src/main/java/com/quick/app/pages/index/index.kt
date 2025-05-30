package com.quick.app.pages.index

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.quick.app.PreviewContent
import com.quick.app.pages.home.HomeRoute
import com.quick.app.pages.me.MeRoute
import com.quick.app.pages.video.VideoRoute
import kotlinx.coroutines.launch

@Composable
fun IndexRoute() {
    IndexScreen()
}


@Composable
fun IndexScreen(vm: IndexViewModel = viewModel()) {
    val scope = rememberCoroutineScope()
    val currTab = vm.currTab
    val pageIndex = pagesArr.indexOf(currTab.value)

    val pagerState =
        rememberPagerState(initialPage = pageIndex, pageCount = { BottomBarItem.entries.size })

    Column(modifier = Modifier
        .fillMaxSize()
        .navigationBarsPadding()) {
        HorizontalPager(
            state = pagerState,
            userScrollEnabled = false,
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            when (it) {
                0 -> HomeRoute()
                1 -> VideoRoute()
                2 -> MeRoute()
            }
        }
        BottomBar(currItem = currTab.value) { item, idx ->
            currTab.value = item
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