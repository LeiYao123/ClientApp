package com.quick.app.feature.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.quick.app.R
import com.quick.app.ui.theme.ClientAppTheme


@Composable
fun SplashRoute(
    modifier: Modifier = Modifier,
    navigate: (routeName: String) -> Unit = {},
) {
    Box(modifier = modifier.fillMaxSize()) {
        Image(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 150.dp),
            painter = painterResource(id = R.drawable.splash_banner),
            contentDescription = "App Logo"
        )
        Image(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .width(200.dp)
                .padding(bottom = 60.dp),
            painter = painterResource(id = R.drawable.splash_logo),
            contentDescription = "App Logo"
        )

        Text(
            "Copy Right 2024 Reversed",
            color = MaterialTheme.colorScheme.outline,
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .width(200.dp)
                .padding(bottom = 20.dp)
                .clickable { navigate("guide") },
        )
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PHONE,
//    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun SplashRoutePreview() {

    ClientAppTheme {
        Scaffold(modifier = Modifier.fillMaxSize().background(Color.Red)) {
            println(it)
            SplashRoute()
        }
    }
}