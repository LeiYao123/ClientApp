package com.quick.app.pages.me

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MeRoute() {
    MeScreen()
}

@Composable
fun MeScreen() {
    Text("Me")
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MeRoutePreview() {
}