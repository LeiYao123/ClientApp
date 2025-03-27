package com.quick.app.pages.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.quick.app.PreviewContent
import com.quick.app.R
import com.quick.app.components.RuTopAppBar
import com.quick.app.extension.shortToast
import com.quick.app.models.User
import com.quick.app.pages.me.MeViewModel
import com.quick.app.pages.profile.comps.SettingInput
import com.quick.app.pages.profile.comps.SettingRow
import com.quick.app.pages.profile.comps.SettingTitle
import com.quick.app.route.LocalNavController

@Composable
fun ProfileRoute() {
    ProfileScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
    val navController = LocalNavController.current
    val owner = navController.getBackStackEntry("index/me")
    val vm: MeViewModel = viewModel(owner)
    var user by remember { mutableStateOf(User()) }
    val state = rememberScrollState()

    LaunchedEffect(Unit) {
        user = vm.getPrefsSession()?.user ?: User()
    }
    Scaffold(
        topBar = {
            RuTopAppBar("Profile",
                toBack = true,
                actions = {
                    TextButton(onClick = {}) {
                        Text(text = "保存")
                    }
                })
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .imePadding()
                .padding(paddingValues)
                .verticalScroll(state),
        ) {
            SettingRow {
                val iconModifier = Modifier
                    .padding(vertical = 4.dp)
                    .size(64.dp)
                    .clip(MaterialTheme.shapes.extraSmall)

                SettingTitle(title = "头像", modifier = Modifier.weight(1f))
                if (user.icon != null)
                    AsyncImage(
                        model = user.icon,
                        error = painterResource(id = R.drawable.default_avatar),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = iconModifier,
                    )
                else
                    Image(
                        painter = painterResource(id = R.drawable.default_avatar),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = iconModifier,
                    )
            }

            SettingRow(modifier = Modifier.padding(top = 8.dp), showLine = true) {
                SettingTitle(title = "昵称", modifier = Modifier.weight(1f))
                SettingInput(
                    value = user.nickname ?: "",
                    onValueChanged = { user = user.copy(nickname = it) })
            }

            SettingRow(showLine = true) {
                SettingTitle(title = "性别", modifier = Modifier.weight(1f))
                Text(text = user.gender)
            }

            SettingRow(showLine = true) {
                SettingTitle(title = "生日", modifier = Modifier.weight(1f))
                Text(text = user.birthday ?: "")
            }

            SettingRow {
                SettingTitle(title = "地区", modifier = Modifier.weight(1f))
                Text(text = user.city ?: "")
            }

            SettingRow(modifier = Modifier.padding(top = 8.dp)) {
                Column(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .height(140.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("个人介绍", fontSize = 22.sp)
                    SettingInput(
                        value = user.detail ?: "",
                        onValueChanged = { user = user.copy(detail = it) })
                }
            }
            SettingRow(showLine = true) {
                SettingTitle(title = "手机号", modifier = Modifier.weight(1f))
                Text(text = user.phone ?: "")
            }
            SettingRow(showLine = true) {
                SettingTitle(title = "邮箱", modifier = Modifier.weight(1f))
                Text(text = user.email ?: "")
            }
            SettingRow(modifier = Modifier.padding(top = 8.dp)) {
                TextField(
                    value = "测试不遮挡键盘",
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth()
                )
            }

            OutlinedButton(
                onClick = {
                    vm.logout()
                    navController.popBackStack()
                    "退出登录".shortToast()
                },
                modifier = Modifier
                    .padding(vertical = 24.dp)
                    .height(48.dp)
                    .fillMaxWidth()
            ) { Text("退出登录") }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileRoutePreview() {
    PreviewContent("profile") {
        ProfileRoute()
    }
}