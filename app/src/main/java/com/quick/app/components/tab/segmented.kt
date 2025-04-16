package com.quick.app.components.tab

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import com.quick.app.ui.theme.RuTheme


@Composable
fun RuTabSegmented(
    modifier: Modifier = Modifier,
    options: List<TabItemModel>,
    selectIndex: Int = 0,
    onChange: (Int) -> Unit,
) {
    val rColor = RuTheme.colors
    var containerWidth by remember { mutableStateOf(0) }
    val tabWidth =
        if (options.isNotEmpty() && containerWidth > 0) containerWidth / options.size else 0
    val offsetX by animateDpAsState(
        targetValue = (tabWidth * selectIndex).dp,
        animationSpec = tween(300, easing = FastOutSlowInEasing),
        label = "TabBackgroundOffset"
    )
    Box(
        modifier = modifier
            .height(36.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(rColor.bgWeak)
            .onGloballyPositioned { containerWidth = it.size.width }
    ) {
        if (tabWidth > 0)
            Box(
                modifier = Modifier
                    .offset(x = offsetX)
                    .width(tabWidth.dp)
                    .fillMaxHeight()
                    .padding(4.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(rColor.bgWhite)
                    .shadow(
                        elevation = 4.dp,
                        spotColor = Color(0x080E121B),
                        ambientColor = Color(0x080E121B)
                    )
                    .shadow(
                        elevation = 10.dp,
                        spotColor = Color(0x0F0E121B),
                        ambientColor = Color(0x0F0E121B)
                    )
            )
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            options.forEachIndexed { index, item ->
                val selected = index == selectIndex
                val textColor = if (selected) rColor.textStrong else rColor.textSoft
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 4.dp)
                        .clickable { onChange(index) },
                    contentAlignment = Alignment.Center
                ) {
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        item.iconLeft?.let { it() }
                        Text(
                            text = item.text,
                            modifier = Modifier,
                            color = textColor,
                            style = RuTheme.typo.labelS,
                        )
                        item.iconRight?.let { it() }
                    }
                }
            }
        }
    }
}