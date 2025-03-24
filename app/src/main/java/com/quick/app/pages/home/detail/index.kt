package com.quick.app.pages.home.detail

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.quick.app.PreviewContent
import com.quick.app.components.ErrorTips
import com.quick.app.components.Loading
import com.quick.app.pages.home.DetailUiState
import com.quick.app.pages.home.HomeViewModel
import com.quick.app.pages.home.comps.BottomBar
import com.quick.app.pages.home.comps.ContentView
import com.quick.app.pages.home.comps.TopBar
import com.quick.app.route.LocalNavController
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ProductDetailRoute() {
    ProductDetailScreen()
}

@Composable
fun ProductDetailScreen() {
    val ctx = LocalContext.current
    val navController = LocalNavController.current
    val id = navController.currentBackStackEntry?.arguments?.getString("id") ?: ""
    val owner = navController.getBackStackEntry("index/home")
    val vm: HomeViewModel = viewModel(owner)
    val uiState by vm.detailUiState

    LaunchedEffect(Unit) {
        vm.loadDetail(id)
    }

    when (val state = uiState) {
        is DetailUiState.Loading -> Loading()

        is DetailUiState.Error -> ErrorTips(
            message = state.error.message ?: "加载失败",
            onClick = { vm.loadDetail(id) })

        is DetailUiState.Success -> {
            val scrollState = rememberScrollState()
            val alpha = remember { mutableIntStateOf(0) }
            LaunchedEffect(scrollState) {
                snapshotFlow { scrollState.value }.collectLatest { scrollY ->
                    alpha.intValue = scrollY
                    if (alpha.intValue > 255) alpha.intValue = 255
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .verticalScroll(scrollState)
                    ) { ContentView(state.data) }
                    TopBar(
                        alpha = alpha.intValue,
                        onBack = { navController.popBackStack() },
                        onShareText = { shareText(ctx, state.data.title) },
                        onShareImage = {}
                    )
                }
                BottomBar(
                    onAddCart = { vm.showBottomDialog.value = true },
                    onBuy = { }
                )
                if (vm.showBottomDialog.value) {
                    SpecDialog(
                        data = state.data,
                        onDismissRequest = { vm.showBottomDialog.value = false }
                    )
                }
            }
        }
    }
}

private fun shareText(context: Context, title: String) {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(
            Intent.EXTRA_TEXT,
            "推荐一个好商品：${title}"
        )
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)
    context.startActivity(shareIntent)
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GuideRoutePreview() {
    PreviewContent("detail/123456")
}