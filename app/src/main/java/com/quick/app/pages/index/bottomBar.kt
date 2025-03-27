package com.quick.app.pages.index

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material.icons.outlined.VideoStable
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.quick.app.route.PageRoutes
import com.quick.app.ui.theme.RuTheme


@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    currItem: String,
    onChangeItem: (String, Int) -> Unit,
) {
    Surface(
        modifier = modifier
            .fillMaxWidth(),
        color = RuTheme.colors.bgWhite,
        tonalElevation = 6.dp,
        shadowElevation = 0.dp,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            BottomBarItem.entries.forEachIndexed { idx, item ->
                val color =
                    if (currItem == item.route) Color.Red else MaterialTheme.colorScheme.onSurface
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .width(52.dp)
                        .clickable { onChangeItem(item.route, idx) }
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.desc,
                        tint = color,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = item.desc, fontSize = 12.sp, color = color)
                }
            }
        }
    }
}


enum class BottomBarItem(
    val icon: ImageVector,
    val desc: String,
    val route: String,
) {
    Home(
        icon = Icons.Outlined.Home,
        desc = "Home",
        route = PageRoutes.Home.route,
    ),
    Video(
        icon = Icons.Outlined.VideoStable,
        desc = "Video",
        route = PageRoutes.Video.route,
    ),
    Me(
        icon = Icons.Outlined.PersonOutline,
        desc = "Me",
        route = PageRoutes.Me.route,
    ),
}
