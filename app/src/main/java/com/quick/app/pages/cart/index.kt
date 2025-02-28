package com.quick.app.pages.cart

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CartRoute() {
    CartScreen()
}

@Composable
fun CartScreen() {
    Text("Cart")
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CartRoutePreview() {
}