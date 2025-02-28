package com.quick.app.pages.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeRoute() {
    HomeScreen()
}

@Composable
fun HomeScreen() {
    Text("Home")
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeRoutePreview() {
}