package com.quick.app.pages.loginAccount

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.quick.app.PreviewContent
import com.quick.app.components.RuTopAppBar
import com.quick.app.route.LocalNavController
import com.quick.app.route.PageRoutes

@Composable
fun LoginAccountRoute() {
    LoginAccountScreen()
}

@Composable
fun LoginAccountScreen(vm: LoginAccountViewModel = viewModel()) {
    val navController = LocalNavController.current
    val uiState by vm.uiState


    LaunchedEffect(uiState) {
        if (uiState is LoginUiState.Success)
            navController.navigate(PageRoutes.Index.routeParam(PageRoutes.Me.route))
    }


    Scaffold(topBar = { RuTopAppBar("登录", true) }) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                OutlinedTextField(
                    value = vm.phone,
                    onValueChange = { vm.phone = it },
                    leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                    label = { Text("手机号或邮箱") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 16.dp),
                )
                OutlinedTextField(
                    value = vm.pwd,
                    onValueChange = { vm.pwd = it },
                    leadingIcon = { Icon(Icons.Default.Password, contentDescription = null) },
                    label = { Text("密码") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 16.dp),
                )
                Button(
                    onClick = { vm.login() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 16.dp),
                ) {
                    Text("登录")
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginAccountRoutePreview() {
    PreviewContent("login/account")
}