package com.quick.app.pages.me.comps

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.quick.app.R
import com.quick.app.components.ArrowIcon
import com.quick.app.models.User


@Composable
fun UserProfile(user: User?, toLogout: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
    ) {

        AsyncImage(
            model = user?.icon,
            error = painterResource(id = R.drawable.default_avatar),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape)
                .border(2.dp, Color.White, CircleShape),
        )

        Spacer(modifier = Modifier.width(24.dp))

        Column(
            modifier = Modifier
                .weight(1f),
        ) {
            Text(
                text = user?.nickname ?: "未知",
                color = Color.White,
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = user?.detail ?: "未知",
                style = MaterialTheme.typography.bodySmall,
                color = Color.White,
            )
        }


        Spacer(modifier = Modifier.width(12.dp))

        Icon(Icons.Default.ExitToApp, contentDescription = null, tint = Color.White)
        Text("退出", color = Color.White, modifier = Modifier.clickable { toLogout() })
    }
}

@Composable
fun DefaultUserProfile(toLogin: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { toLogin() },
    ) {
        Image(
            painter = painterResource(id = R.drawable.default_avatar),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .border(2.dp, MaterialTheme.colorScheme.onPrimary, CircleShape),
        )

        Spacer(modifier = Modifier.width(24.dp))

        Column(
            modifier = Modifier
                .weight(1f),
        ) {
            Text(
                text = "登录或注册",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "填写兴趣更懂你",
                style = MaterialTheme.typography.bodySmall,
                color = Color.White,
            )
        }

        ArrowIcon(tint = Color.White)
    }
}


@Composable
fun VIPHint() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(top = 35.dp)
            .background(
                Color(0xFF555555),
                shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
            )
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Icon(
            painter = painterResource(id = R.drawable.mall_vip),
            contentDescription = null,
            tint = Color(0xFFFFDBA6),
            modifier = Modifier.size(25.dp),
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = "超级会员",
            style = MaterialTheme.typography.bodyLarge,
            color = Color(0xFFFFDBA6),
            modifier = Modifier.weight(1f),
        )

        Text(
            text = "会员可享更多权益",
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFFFFDBA6),
        )

        ArrowIcon(tint = Color(0xFFFFDBA6))

    }
}
