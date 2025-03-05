package com.quick.app.pages.productDetail.comps

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.quick.app.models.ProductModel

@Composable
fun ContentView(data: ProductModel) {
    Text("${data.title} --> ${data.id}")
}
