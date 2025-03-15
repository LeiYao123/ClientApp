package com.quick.app.pages.me

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.quick.app.LocalViewModel
import com.quick.app.PreviewContent
import com.quick.app.route.LocalNavController
import com.quick.app.route.PageRoutes

@Composable
fun MeRoute() {
    MeScreen()
}

@Composable
fun MeScreen() {
    val navController = LocalNavController.current
    val vm: MeViewModel = viewModel()
    val session = vm.session.value
    val isLoginIn = session?.userId?.isNotEmpty() ?: false
    val user = session?.user

    Scaffold {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Column {
                if (isLoginIn) {
                    Text("${user?.nickname}")
                    Button(onClick = { vm.logout() }) {
                        Text("退出登录")
                    }
                } else {
                    Button(onClick = { navController.navigate(PageRoutes.Login.route) }) {
                        Text("去登录")
                    }
                }
                AddNumBtn()
            }
        }
    }
}

@Composable
fun AddNumBtn() {
    val vm = viewModel<MeViewModel>()
    val appVm = LocalViewModel.current
    Button(onClick = { vm.num.intValue++ }) {
        Text("me page ${vm.num.intValue}")
    }

    Button(onClick = { appVm.appNum.intValue++ }) {
        Text("app ${appVm.appNum.intValue}")
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MeRoutePreview() {
    PreviewContent(null) {
        MeRoute()
    }
}