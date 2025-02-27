package com.quick.app.feature.splash

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.quick.app.route.LocalNavController

@Composable
fun GuideRoute() {
    GuideScreen()
}

@Composable
fun GuideScreen() {
    val navController = LocalNavController.current
    val args = navController.currentBackStackEntry?.arguments?.getString("id")
    Log.d("Route", "GuideRoute - $args")
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text("Guide -- $args")
            Button(onClick = {
                navController.popBackStack()
            }) {
                Text("Go Back")
            }
        }
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PHONE,
)
@Composable
fun GuideRoutePreview() {
    GuideRoute()
}