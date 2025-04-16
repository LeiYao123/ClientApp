package com.quick.app.components.tab

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.quick.app.ui.theme.RuTheme
import kotlinx.coroutines.launch


data class TabItemModel(
    val text: String,
    val iconLeft: (@Composable () -> Unit)? = null,
    val iconRight: (@Composable () -> Unit)? = null,
)

@Composable
fun RuTab(
    modifier: Modifier = Modifier,
    options: List<TabItemModel>,
    selectIndex: Int = 0,
    onChange: (Int) -> Unit,
) {
    val rColor = RuTheme.colors

    val scope = rememberCoroutineScope()
    val tabWidths = remember { mutableStateListOf<IntSize>() }
    val tabPositions = remember { mutableStateListOf<Float>() }

    var indicatorOffset by remember { mutableFloatStateOf(0f) }
    var indicatorWidth by remember { mutableStateOf(0f) }

    val animatedOffset by animateFloatAsState(
        targetValue = indicatorOffset,
        animationSpec = tween(300, easing = FastOutSlowInEasing),
        label = "offset"
    )
    val animatedWidth by animateFloatAsState(
        targetValue = indicatorWidth,
        animationSpec = tween(300, easing = FastOutSlowInEasing),
        label = "width"
    )

    Box(
        modifier = modifier
            .height(IntrinsicSize.Min)
            .drawBehind {
                // Draw bottom gray line
                drawLine(
                    color = Color.LightGray,
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = 1.dp.toPx()
                )

                // Draw active indicator
                drawLine(
                    color = rColor.primaryBase,
                    start = Offset(animatedOffset, size.height),
                    end = Offset(animatedOffset + animatedWidth, size.height),
                    strokeWidth = 2.dp.toPx()
                )
            }
    ) {

        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            options.forEachIndexed { index, item ->
                val selected = index == selectIndex
                val textColor = if (selected) rColor.textStrong else rColor.textSub
                Box(
                    modifier = Modifier
                        .padding(vertical = 14.dp)
                        .onGloballyPositioned {
                            val position = it.positionInParent()
                            val size = it.size
                            if (tabWidths.size <= index) {
                                tabWidths.add(size)
                                tabPositions.add(position.x)
                            } else {
                                tabWidths[index] = size
                                tabPositions[index] = position.x
                            }

                            if (index == selectIndex) {
                                indicatorOffset = position.x
                                indicatorWidth = size.width.toFloat()
                            }
                        }
                        .clickable {
                            onChange(index)
                            if (tabPositions.size > index && tabWidths.size > index) {
                                scope.launch {
                                    indicatorOffset = tabPositions[index]
                                    indicatorWidth = tabWidths[index].width.toFloat()
                                }
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier.clickable { onChange(index) },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                    ) {
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