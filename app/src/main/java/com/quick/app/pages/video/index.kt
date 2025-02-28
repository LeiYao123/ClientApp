package com.quick.app.pages.video

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun VideoRoute() {
    VideoScreen()
}

@Composable
fun VideoScreen() {
    Text("Video")
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun VideoRoutePreview() {
}