package com.quick.app.pages.me

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.QrCodeScanner
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.quick.app.PreviewContent
import com.quick.app.components.RuTopAppBar
import com.quick.app.components.toast.Toast
import com.quick.app.config.EnvConfig
import com.quick.app.pages.me.comps.CountInfo
import com.quick.app.pages.me.comps.DefaultUserProfile
import com.quick.app.pages.me.comps.OrderInfo
import com.quick.app.pages.me.comps.UserProfile
import com.quick.app.pages.me.comps.VIPHint
import com.quick.app.route.LocalNavController
import com.quick.app.route.PageRoutes

@Composable
fun MeRoute() {
    MeScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeScreen() {
    val navController = LocalNavController.current
    val vm: MeViewModel = viewModel()
    val session = vm.session.value
    val isLoginIn = session?.userId?.isNotEmpty() ?: false
    val user = session?.user
    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            RuTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Red,
                    actionIconContentColor = Color.White,
                ),
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            Icons.Outlined.QrCodeScanner,
                            contentDescription = "Search",
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }
            )
        },
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .padding(top = it.calculateTopPadding())
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(bottom = 12.dp),
        ) {
            Column(
                modifier = Modifier
                    .background(Color.Red)
                    .padding(horizontal = 12.dp)
            ) {
                if (isLoginIn) UserProfile(user, toProfile = {
                    navController.navigate(PageRoutes.Profile.route)
                })
                else DefaultUserProfile(toLogin = {
                    navController.navigate(PageRoutes.Login.route)
                })
                VIPHint()
            }
            CountInfo(modifier = Modifier.padding(horizontal = 12.dp))
            OrderInfo(
                modifier = Modifier.padding(horizontal = 12.dp),
                goToOrders = { param ->
                    navController.navigate(PageRoutes.Orders.routeParam(param))
                }
            )
            Text("环境变量--${EnvConfig.env}")
            Text("base_url--${EnvConfig.base_url}")
            val ctx = LocalContext.current
//            Button(onClick = {
//                val t = Toast.makeText(ctx, "toast", Toast.LENGTH_SHORT)
//                t.setGravity(Gravity.TOP, 0, 50)
//                t.show()
//            }) {
//                Text("Toast")
//            }

            Button(onClick = {
                Toast.show("test toast in composable func")
            }) {
                Text("CustomToast")
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MeRoutePreview() {
    PreviewContent("index/me") {
        MeRoute()
    }
}


// 全局 viewModel 测试
//@Composable
//fun AddNumBtn() {
//    val vm = viewModel<MeViewModel>()
//    val appVm = LocalViewModel.current
//    Button(onClick = { vm.num.intValue++ }) {
//        Text("me page ${vm.num.intValue}")
//    }
//
//    Button(onClick = { appVm.appNum.intValue++ }) {
//        Text("app ${appVm.appNum.intValue}")
//    }
//}

@Composable
fun TestDrawer() {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(300.dp)
            .background(Color.Red)
    ) {
        Text("测试 drawer 在 viewModel 中调用")
    }
}