package com.quick.app.components.slimeNumberPad

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.quick.app.components.svgicon.SvgIcon
import com.quick.app.components.svgicon.SvgPath
import com.quick.app.ui.theme.RuTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SlimeNumberPad(
    modifier: Modifier = Modifier,
    onValueChange: (Int) -> Unit = {},
    onClear: () -> Unit = {},
    onDelete: () -> Unit = {},
) {

    val padList = listOf(
        NumberPadModel("7", 7),
        NumberPadModel("8", 8),
        NumberPadModel("9", 9),
        NumberPadModel("4", 4),
        NumberPadModel("5", 5),
        NumberPadModel("6", 6),
        NumberPadModel("1", 1),
        NumberPadModel("2", 2),
        NumberPadModel("3", 3),
        NumberPadModel("Clear"),
        NumberPadModel("0", 0),
        NumberPadModel("Delete", icon = SvgPath.delete_back_2_line),
    )
    Row(
        modifier = modifier.size(440.dp, 364.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = "file:///android_asset/icons/slime-connector.svg",
            contentDescription = "slime-connector",
            modifier = modifier.size(40.dp, 60.dp),
        )
        FlowRow(
            modifier = modifier
                .width(400.dp)
                .background(RuTheme.colors.bgWhite, shape = RoundedCornerShape(10.dp))
                .padding(20.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            padList.forEach {
                SlimeNumberPadItem(
                    padItem = it,
                    modifier = Modifier.clickable {
                        if (it.value >= 0) onValueChange(it.value)
                        else if (it.text == "Clear") onClear()
                        else if (it.text == "Delete") onDelete()
                    }
                )
            }

        }
    }
}

@Composable
fun SlimeNumberPadItem(padItem: NumberPadModel, modifier: Modifier) {
    Box(
        modifier = modifier
            .size(112.dp, 72.dp)
            .border(
                1.dp,
                color = RuTheme.colors.strokeSoft,
                shape = RoundedCornerShape(RuTheme.radius.radius10)
            ),
        contentAlignment = Alignment.Center
    ) {
        if (padItem.icon != null)
            SvgIcon(SvgPath.delete_back_2_line, size = 28)
        else
            Text(padItem.text, style = RuTheme.typo.titleH6, color = RuTheme.colors.textSub)
    }
}


data class NumberPadModel(
    val text: String,
    val value: Int = -1,
    val icon: String? = null,
)
