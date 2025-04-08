package com.quick.app.pages.guide

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.quick.app.PreviewContent
import com.quick.app.R
import com.quick.app.route.LocalNavController
import com.quick.app.route.PageRoutes

@Composable
fun GuideRoute() {
    GuideScreen()
}

@Composable
fun GuideScreen() {
    // 拦截返回键
//    BackHandler(enabled = true) {
//        Log.d("GuideRoute", "GuideRoute - BackHandler")
//    }
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(it)
        ) {
            SplashView(Modifier.weight(1f))
            ControlView()
        }
    }
}

@Composable
fun ControlView() {
    val navController = LocalNavController.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Button(
            onClick = { navController.navigate(PageRoutes.Login.route) },
            shape = MaterialTheme.shapes.extraSmall,
            modifier = Modifier
                .weight(1f)
        ) {
            Text(text = "去登录")
        }


        OutlinedButton(
            onClick = { navController.navigate(PageRoutes.Index.routeParam("home")) },
            shape = MaterialTheme.shapes.extraSmall,
            modifier = Modifier
                .weight(1f)
        ) {
            Text("进入应用")
        }
    }
}

@Composable
fun SplashView(modifier: Modifier = Modifier) {
    val guideImages = remember {
        listOf(
            R.drawable.guide1,
            R.drawable.guide2,
            R.drawable.guide3,
            R.drawable.guide4,
            R.drawable.guide5,
        )
    }
    val pagerState = rememberPagerState { guideImages.size }

    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = modifier.fillMaxWidth()
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            Image(
                painter = painterResource(id = guideImages[page]),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
            )
        }
        Row(modifier = Modifier.padding(bottom = 10.dp)) {
            for (i in guideImages.indices) {
                Icon(
                    if (pagerState.currentPage == i) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GuideRoutePreview() {
    PreviewContent("guide")
}


//    val navController = LocalNavController.current
//    val args = navController.currentBackStackEntry?.arguments?.getString("id")
//    Log.d("Route", "GuideRoute - $args")