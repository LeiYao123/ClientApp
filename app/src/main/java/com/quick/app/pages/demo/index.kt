package com.quick.app.pages.demo

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComponentDemoRoute() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Components Demo") }
            )
        }
    ) {
        Surface(modifier = Modifier.padding(it)) {
            Text("Components Demo")
        }
    }
}