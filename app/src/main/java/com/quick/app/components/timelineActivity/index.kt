package com.quick.app.components.timelineActivity

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.quick.app.components.spacer.Expanded
import com.quick.app.components.svgicon.SvgIcon
import com.quick.app.components.svgicon.SvgPath
import com.quick.app.ui.theme.RuTheme

@Composable
fun TimelineActivity(
    isHorizontal: Boolean = false, // default Horizontal
    list: List<TimelineModel>,
) {
    Column(
//        modifier = Modifier.height(IntrinsicSize.Min),
    ) {
        list.forEachIndexed { index, item ->
            Row(
                modifier = Modifier.height(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    SvgIcon(SvgPath.file_list_3_line, size = 16)
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .width(1.dp)
                            .background(RuTheme.colors.strokeSoft)
                    )
                }

                Column(
                    modifier = Modifier.let { if (index == list.size - 1) it else it.padding(bottom = 20.dp) },
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Row {
                        Text(
                            item.title,
                            style = RuTheme.typo.paragraphS,
                            color = RuTheme.colors.textStrong
                        )
                        if (isHorizontal) {
                            Expanded()
                            Text(
                                item.time,
                                style = RuTheme.typo.paragraphXS,
                                color = RuTheme.colors.textSub
                            )
                        }
                    }


                    if (item.desc != null)
                        Text(
                            item.desc,
                            style = RuTheme.typo.paragraphXS,
                            color = RuTheme.colors.textSub
                        )
                    if (item.action != null)
                        Text(
                            "See details",
                            style = TextStyle(
                                fontSize = 12.sp,
                                lineHeight = 16.sp,
                                fontWeight = FontWeight(500),
                                textDecoration = TextDecoration.Underline,
                            ),
                            color = RuTheme.colors.textSub
                        )
                    if (!isHorizontal)
                        Text(
                            item.time,
                            style = RuTheme.typo.paragraphXS,
                            color = RuTheme.colors.textSub
                        )
                }
            }
        }
    }
}


data class TimelineModel(
    val title: String,
    val desc: String? = null,
    val time: String,
    val action: String? = null,
)

