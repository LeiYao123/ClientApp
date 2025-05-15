package com.quick.app.components.menuCard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.quick.app.components.checkbox.RuCheckBox
import com.quick.app.components.spacer.RuSpacer
import com.quick.app.components.svgicon.SvgIcon
import com.quick.app.components.svgicon.SvgPath
import com.quick.app.extension.bottomBorder
import com.quick.app.extension.topBorder
import com.quick.app.ui.theme.RuTheme

@Composable
fun MenuCard(
    modifier: Modifier = Modifier,
    optionName: String? = null,
    title: String,
    price: String,
    image: String? = null,
    time: String,
    checked: Boolean = false,
    onCheckedChange: (Boolean) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(RuTheme.colors.bgWhite, shape = RoundedCornerShape(10.dp))
            .border(
                width = 1.dp,
                color = RuTheme.colors.strokeSoft,
                shape = RoundedCornerShape(10.dp)
            )
            .then(modifier)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
        ) {
            Text("Choose your entry", style = RuTheme.typo.labelXS, color = RuTheme.colors.textSub)
            RuSpacer(4)
            Text(
                optionName ?: "",
                style = RuTheme.typo.paragraphXS,
                color = RuTheme.colors.textSoft
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .topBorder(1.dp, RuTheme.colors.strokeSoft)
                .bottomBorder(1.dp, RuTheme.colors.strokeSoft),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                modifier = Modifier
                    .padding(12.dp)
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RuCheckBox(checked = checked, onChange = onCheckedChange)
                Column {
                    Text(
                        title,
                        style = RuTheme.typo.paragraphXS,
                        color = RuTheme.colors.textStrong
                    )
                    RuSpacer(h = 4)
                    Text(
                        price,
                        style = RuTheme.typo.paragraphXS,
                        color = RuTheme.colors.textSub
                    )
                }
            }
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .padding(4.dp)
                    .background(Color.Gray, shape = RoundedCornerShape(8.dp))
            )
        }
        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 4.dp)
                .height(24.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SvgIcon(SvgPath.time_fill, size = 16)
            RuSpacer(4)
            Text(
                time,
                style = RuTheme.typo.labelXS,
                color = RuTheme.colors.textSub
            )
        }
    }
}