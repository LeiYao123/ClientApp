package com.quick.app.pages.category

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CategoryRoute() {
    CategoryScreen()
}

@Composable
fun CategoryScreen() {
    Text("Category")
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CategoryRoutePreview() {
}