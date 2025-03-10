package com.quick.app.pages.me

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.quick.app.PreviewContent
import com.quick.app.route.LocalNavController
import com.quick.app.route.PageRoutes

@Composable
fun MeRoute() {
    MeScreen()
}

@Composable
fun MeScreen() {
    val navController = LocalNavController.current
    Scaffold {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Button(onClick = { navController.navigate(PageRoutes.Login.route) }) {
                Text("去登录")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MeRoutePreview() {
    PreviewContent(null) {
        MeRoute()
    }
}