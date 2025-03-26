package com.quick.app.pages.home

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.quick.app.MyApplication
import com.quick.app.PreviewContent
import com.quick.app.components.ErrorTips
import com.quick.app.components.Loading
import com.quick.app.models.AdModel
import com.quick.app.models.ProductModel
import com.quick.app.pages.home.comps.ProductGridItem
import com.quick.app.pages.home.comps.ProductItem
import com.quick.app.pages.home.comps.ProductListAppBar
import com.quick.app.route.LocalNavController
import com.quick.app.route.PageRoutes
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HomeRoute() {
    HomeScreen()
}

@Composable
fun HomeScreen(vm: HomeViewModel = viewModel()) {
    val navController = LocalNavController.current
    val datum by vm.datum
    val ads by vm.ads
    val listUiState by vm.listUiState
    Log.d("homeViewModel", "$ads")
    Scaffold(topBar = { ProductListAppBar() }) { pd ->
        Box(modifier = Modifier.padding(top = pd.calculateTopPadding())) {
            when (val state = listUiState) {
                is HomeListUiState.Loading -> Loading()

                is HomeListUiState.Error -> ErrorTips(
                    message = state.error.message ?: "加载失败",
                    onClick = { vm.getProducts() })

                is HomeListUiState.Success -> {
                    Column {
                        AdsList(ads) { item ->
                            Log.d("homeViewModel", "${item.uri}")
                            if (item.uri?.contains("https") == true) {
                                navController.navigate(PageRoutes.Web.routeParam(item.uri))
                            } else {
                                Toast.makeText(
                                    MyApplication.instance,
                                    "暂不支持 ${item.uri}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                        HomeList(datum) { navController.navigate(PageRoutes.Detail.routeParam(it.id)) }
                    }
                }
            }
        }
    }
}

@Composable
fun AdsList(items: List<AdModel>, onClick: (AdModel) -> Unit) {
    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
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

@Composable
fun HomeList(datum: List<ProductModel>, onClick: (ProductModel) -> Unit) {
    val gridState = rememberLazyGridState()

    LazyVerticalGrid(
        state = gridState,
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        items(datum.size, span = { GridItemSpan(2) }) { idx ->
            ProductItem(
                data = datum[idx],
                modifier = Modifier.clickable { onClick(datum[idx]) })
        }
        items(datum.size) { idx ->
            ProductGridItem(
                data = datum[idx],
                modifier = Modifier.clickable { onClick(datum[idx]) })
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeRoutePreview() {
    PreviewContent("index/home")
}