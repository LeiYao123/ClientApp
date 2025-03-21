package com.quick.app.pages.splash.comps

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.quick.app.PreviewContent
import com.quick.app.R
import de.charlex.compose.material3.HtmlText

@Composable
fun TermServiceDialog(onAgree: () -> Unit, onDisagree: () -> Unit) {
    Dialog(onDismissRequest = { }) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            ),
            modifier = Modifier
                .fillMaxWidth(),
            shape = MaterialTheme.shapes.large,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                //标题
                Text(
                    text = "服务协议",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                )

                Spacer(modifier = Modifier.height(16.dp))

                val scrollState = rememberScrollState()

                //内容
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .verticalScroll(scrollState)
                ) {
                    HtmlText(
                        text = stringResource(id = R.string.term_service_privacy_content),
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 26.sp,
                        ),
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = onAgree,
                    modifier = Modifier.fillMaxWidth()
                ) { Text(text = "同意协议") }
                TextButton(
                    onClick = onDisagree,
                    colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.onSurface)
                ) {
                    Text(
                        text = "不同意",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.outline,
                    )
                }
            }

        }
    }
}

@Preview(showBackground = false)
@Composable
fun MyTermServiceDialogPreview() {
    PreviewContent(null) {
        TermServiceDialog(onAgree = {}, onDisagree = {})
    }
}