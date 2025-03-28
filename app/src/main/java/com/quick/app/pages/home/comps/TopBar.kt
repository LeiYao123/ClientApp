package com.quick.app.pages.home.comps

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Sms
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.quick.app.pages.home.HomeViewModel


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ProductListAppBar() {
    val count = viewModel<HomeViewModel>().count
    TopAppBar(
        navigationIcon = {
            Icon(imageVector = Icons.Outlined.Menu, contentDescription = null)
        },
        title = {
            Row(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth()
                    .height(32.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .clickable { },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Icon(imageVector = Icons.Outlined.Search, contentDescription = null)
                Text(text = "Search")
                Text(
                    text = "计数: ${count.intValue}",
                    modifier = Modifier.clickable { count.intValue++ })
            }
        },
        actions = {
            Icon(
                imageVector = Icons.Outlined.Sms,
                contentDescription = null
            )
        }
    )
}

@Composable
fun TopBar(
    alpha: Int = 0,
    onBack: () -> Unit,
    onShareText: () -> Unit,
    onShareImage: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(255, 255, 255, alpha))
            .statusBarsPadding()
            .padding(horizontal = 16.dp, vertical = 6.dp)
    ) {
        CircleButton(Icons.Default.ArrowBackIosNew, onBack)
        Spacer(modifier = Modifier.weight(1f))
        CircleButton(Icons.Default.Share, onShareImage)
        Spacer(modifier = Modifier.width(8.dp))
        CircleButton(Icons.Default.MoreVert, onShareText)
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