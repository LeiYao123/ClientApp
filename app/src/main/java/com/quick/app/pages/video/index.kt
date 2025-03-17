package com.quick.app.pages.video

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.quick.app.PreviewContent

@Composable
fun VideoRoute() {
    VideoScreen()
}

@Composable
fun VideoScreen() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text("Video")
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MeRoutePreview() {
    PreviewContent("index/video") {
        VideoRoute()
    }
}