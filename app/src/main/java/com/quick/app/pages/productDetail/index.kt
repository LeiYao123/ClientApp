package com.quick.app.pages.productDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.quick.app.PreviewContent
import com.quick.app.route.LocalNavController

@Composable
fun ProductDetailRoute() {
    ProductDetailScreen()
}

@Composable
fun ProductDetailScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            TopBar()
            Column(modifier = Modifier.weight(1f)) { ContentView() }
            BottomBar(onAddCart = { }, onBuy = { })
        }
    }
}

@Composable
fun TopBar() {
    val navController = LocalNavController.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = 16.dp, vertical = 6.dp)
    ) {
        CircleButton(Icons.Default.ArrowBackIosNew) { navController.popBackStack() }
        Spacer(modifier = Modifier.weight(1f))
        CircleButton(Icons.Default.Share) {}
        Spacer(modifier = Modifier.width(8.dp))
        CircleButton(Icons.Default.MoreVert) { }
    }
}

@Composable
fun CircleButton(icon: ImageVector, onClick: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(Color.Black.copy(alpha = 0.2f))
            .clickable { onClick() }
    ) {
        Icon(
            icon,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(20.dp),
        )

    }
}

@Composable
fun ContentView() {
    Text("product detail --")
}

@Composable
fun BottomBar(
    onAddCart: () -> Unit,
    onBuy: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Outlined.ShoppingCart,
            contentDescription = null,
            modifier = Modifier.clickable {
                println("click add cart")
            }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .weight(1f)
                .height(40.dp)
                .clip(RoundedCornerShape(topStart = 32.dp, bottomStart = 32.dp))
                .background(brush = Brush.horizontalGradient(listOf(Color.Red, Color.Blue)))
                .clickable { onAddCart() },
        ) {
            Text(
                "加入购物车",
                color = MaterialTheme.colorScheme.surface,
                style = MaterialTheme.typography.titleMedium
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .weight(1f)
                .height(40.dp)
                .clip(RoundedCornerShape(topEnd = 32.dp, bottomEnd = 32.dp))
                .background(brush = Brush.horizontalGradient(listOf(Color.Blue, Color.Red)))
                .clickable { onBuy() },
        ) {
            Text(
                "立即购买",
                color = MaterialTheme.colorScheme.surface,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GuideRoutePreview() {
    PreviewContent("detail/123456")
}