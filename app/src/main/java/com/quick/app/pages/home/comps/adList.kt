package com.quick.app.pages.home.comps

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import coil3.compose.AsyncImage
import com.quick.app.models.AdModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun AdsList(items: List<AdModel>, onClick: (AdModel) -> Unit) {
    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .aspectRatio(2.4f) //图片的宽度/高度
            .clip(MaterialTheme.shapes.extraSmall)
    ) {
        val pagerState = rememberPagerState(pageCount = { items.size })

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
        ) { page ->
            val item = items[page]
            AsyncImage(
                model = item.icon,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onClick(item) },
            )
        }


        val scope = rememberCoroutineScope()
        val lifecycle = LocalLifecycleOwner.current.lifecycle
        LaunchedEffect(Unit) {
            var job: Job? = null

            val observer = object : DefaultLifecycleObserver {
                override fun onResume(owner: LifecycleOwner) {
                    job = scope.launch {
                        while (true) {
                            delay(3000L)
                            val nextPage = (pagerState.currentPage + 1) % items.size
                            pagerState.animateScrollToPage(nextPage)
                        }
                    }
                }

                // 切到后台后会暂停滚动
                override fun onPause(owner: LifecycleOwner) {
                    super.onPause(owner)
                    job?.cancel()
                }
            }
            lifecycle.addObserver(observer)
        }
    }
}
