package com.quick.app.pages.login

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.quick.app.LocalViewModel
import com.quick.app.PreviewContent
import com.quick.app.R
import com.quick.app.components.RuTopAppBar
import com.quick.app.pages.me.MeViewModel
import com.quick.app.route.LocalNavController
import com.quick.app.route.PageRoutes

@Composable
fun LoginRoute() {
    LoginScreen()
}

@Composable
fun LoginScreen() {
    val vm = viewModel<MeViewModel>()
    val appVm = LocalViewModel.current

    val navController = LocalNavController.current
    Scaffold(topBar = { RuTopAppBar(toBack = true) }) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
        ) {
            Image(
                painter = painterResource(R.drawable.login_logo),
                contentDescription = "logo",
                modifier = Modifier
                    .padding(top = 60.dp)
                    .size(100.dp)
                    .align(Alignment.TopCenter),
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp)
                    .align(BottomCenter)
            ) {

                Text("me page Cart， ${vm.num.intValue}")
                Text("app Cart， ${appVm.appNum.intValue}")
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp),
                    onClick = { navController.navigate(PageRoutes.LoginAccount.route) }) {
                    Text("用户名登录")
                }
                OutlinedButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp, vertical = 24.dp),
                    onClick = { }) {
                    Text("验证码登录")
                }
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OtherLoginBtn(R.drawable.passport_sns_qq, onClick = {})
                    OtherLoginBtn(R.drawable.passport_sns_google, onClick = {})
                    OtherLoginBtn(R.drawable.passport_sns_wechat, onClick = {})
                }
                TermsAndConditionsText(
                    onClick = {}
                )
            }
        }
    }
}

@Composable
fun TermsAndConditionsText(onClick: (String) -> Unit) {
    val annotatedText = buildAnnotatedString {
        append("登录即代表同意 ")

        // 用户协议（蓝色 + 可点击）
        val userAgreementStart = length
        append("用户协议")
        addStyle(
            SpanStyle(color = Color.Blue, textDecoration = TextDecoration.Underline),
            userAgreementStart,
            length
        )
        addStringAnnotation("URL", "user_agreement", userAgreementStart, length)

        append(" 和 ")

        // 隐私政策（蓝色 + 可点击）
        val privacyPolicyStart = length
        append("隐私政策")
        addStyle(
            SpanStyle(color = Color.Blue, textDecoration = TextDecoration.Underline),
            privacyPolicyStart,
            length
        )
        addStringAnnotation("URL", "privacy_policy", privacyPolicyStart, length)
    }
    var textLayoutResult by remember { mutableStateOf<TextLayoutResult?>(null) }

    Text(
        text = annotatedText,
        style = TextStyle(fontSize = 16.sp),
        onTextLayout = { textLayoutResult = it },
        modifier = Modifier.pointerInput(Unit) {
            detectTapGestures { offset ->
                textLayoutResult?.let {
                    val position = it.getOffsetForPosition(offset) // 转换 Offset → 文本索引
                    annotatedText.getStringAnnotations("URL", position, position).firstOrNull()
                        ?.let { annotation ->
                            when (annotation.item) {
                                "user_agreement" -> onClick("http://www.ixuea.com/articles/4449")
                                "privacy_policy" -> onClick("http://www.ixuea.com/articles/4467")
                            }
                        }
                }
            }
        }
    )
}


@Composable
fun OtherLoginBtn(@DrawableRes icon: Int, onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Image(
            painter = painterResource(icon),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.size(48.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginRoutePreview() {
    PreviewContent("login")
}