package com.quick.app.pages.home

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.quick.app.PreviewContent
import com.quick.app.components.ErrorTips
import com.quick.app.components.RuPullToRefresh
import com.quick.app.components.loading.Loading
import com.quick.app.extension.shortToast
import com.quick.app.models.AdModel
import com.quick.app.models.ProductModel
import com.quick.app.pages.home.comps.AdsList
import com.quick.app.pages.home.comps.ProductGridItem
import com.quick.app.pages.home.comps.ProductItem
import com.quick.app.pages.home.comps.ProductListAppBar
import com.quick.app.route.LocalNavController
import com.quick.app.route.PageRoutes
import kotlinx.coroutines.delay

@Composable
fun HomeRoute() {
    HomeScreen()
}

@Composable
fun HomeScreen(vm: HomeViewModel = viewModel()) {
    val datum by vm.datum
    val ads by vm.ads
    val listUiState by vm.listUiState
    Scaffold(topBar = { ProductListAppBar() }) { pd ->
        Box(modifier = Modifier.padding(top = pd.calculateTopPadding())) {
            when (val state = listUiState) {
                is HomeListUiState.Loading -> Loading()

                is HomeListUiState.Error -> ErrorTips(
                    message = state.error.message ?: "加载失败",
                    onClick = { vm.getProducts() })

                is HomeListUiState.Success -> {
                    RuPullToRefresh(onRefreshApi = {
                        delay(2000)
                        "刷新成功".shortToast()
                    }) {
                        HomeList(datum, ads)
                    }
                }
            }
        }
    }
}

@Composable
fun HomeList(datum: List<ProductModel>, ads: List<AdModel>) {
    val navController = LocalNavController.current
    val gridState = rememberLazyGridState()

    LazyVerticalGrid(
        state = gridState,
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        items(1, span = { GridItemSpan(2) }) {
            AdsList(ads) { item ->
                Log.d("homeViewModel", "${item.uri}")
                if (item.uri?.contains("https") == true) {
                    navController.navigate(PageRoutes.Web.routeParam(item.uri))
                } else "No Support ${item.uri}".shortToast()
            }
        }
        items(datum.size, span = { GridItemSpan(2) }) { idx ->
            ProductItem(
                data = datum[idx],
                modifier = Modifier.clickable {
                    navController.navigate(PageRoutes.Detail.routeParam(datum[idx].id))
                })
        }
        items(datum.size) { idx ->
            ProductGridItem(
                data = datum[idx],
                modifier = Modifier.clickable {
                    navController.navigate(PageRoutes.Detail.routeParam(datum[idx].id))
                })
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeRoutePreview() {
    PreviewContent("index/home")
}