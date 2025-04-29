package com.quick.app.components.slider

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.quick.app.ui.theme.RuTheme
import kotlin.math.roundToInt


private enum class CustomSliderComponents {
    SLIDER, LABEL, INDICATOR, THUMB
}

private const val Gap = 1
private val ValueRange = 0f..10f

/**
 * A custom slider composable that allows selecting a value within a given range.
 *
 * @param value The current value of the slider.
 * @param onValueChange The callback invoked when the value of the slider changes.
 * @param modifier The modifier to be applied to the slider.
 * @param valueRange The range of values the slider can represent.
 * @param gap The spacing between indicators on the slider.
 * @param showIndicator Determines whether to show indicators on the slider.
 * @param showLabel Determines whether to show a label above the slider.
 * @param enabled Determines whether the slider is enabled for interaction.
 * @param thumb The composable used to display the com.quick.app.components.slider.thumb of the slider.
 * @param track The composable used to display the com.quick.app.components.slider.track of the slider.
 * @param indicator The composable used to display the indicators on the slider.
 * @param label The composable used to display the label above the slider.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    valueRange: ClosedFloatingPointRange<Float> = ValueRange,
    gap: Int = Gap,
    showIndicator: Boolean = false,
    showLabel: Boolean = false,
    enabled: Boolean = true,
    thumb: @Composable (thumbValue: Int) -> Unit = {
        RuSliderDefaults.Thumb(it.toString())
    },
    track: @Composable (sliderState: SliderState) -> Unit = { sliderState ->
        RuSliderDefaults.Track(sliderState = sliderState)
    },
    indicator: @Composable (indicatorValue: Int) -> Unit = { indicatorValue ->
        RuSliderDefaults.Indicator(indicatorValue = indicatorValue.toString())
    },
    label: @Composable (labelValue: Int) -> Unit = { labelValue ->
        RuSliderDefaults.Label(labelValue = labelValue.toString())
    },
) {
    val itemCount = (valueRange.endInclusive - valueRange.start).roundToInt()
    val steps = if (gap == 1) 0 else (itemCount / gap - 1)

    Box(modifier = modifier) {
        Layout(
            measurePolicy = customSliderMeasurePolicy(
                itemCount = itemCount,
                gap = gap,
                value = value,
                startValue = valueRange.start
            ),
            content = {
                if (showLabel) Box(modifier = Modifier.layoutId(CustomSliderComponents.LABEL)) {
                    label(value.roundToInt())
                }

                Box(modifier = Modifier.layoutId(CustomSliderComponents.THUMB)) {
                    thumb(value.roundToInt())
                }

                Slider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .layoutId(CustomSliderComponents.SLIDER),
                    value = value,
                    valueRange = valueRange,
                    steps = steps,
                    onValueChange = { onValueChange(it) },
                    thumb = {
                        thumb(value.roundToInt())
                    },
                    track = { track(it) },
                    enabled = enabled
                )

                if (showIndicator)
                    Indicator(
                        modifier = Modifier.layoutId(CustomSliderComponents.INDICATOR),
                        valueRange = valueRange,
                        gap = gap,
                        indicator = indicator
                    )
            })
    }
}

@Composable
private fun Indicator(
    modifier: Modifier = Modifier,
    valueRange: ClosedFloatingPointRange<Float>,
    gap: Int,
    indicator: @Composable (indicatorValue: Int) -> Unit,
) {
    // Iterate over the value range and display indicators at regular intervals.
    for (i in valueRange.start.roundToInt()..valueRange.endInclusive.roundToInt() step gap) {
        Box(
            modifier = modifier
        ) {
            indicator(i)
        }
    }
}

private fun customSliderMeasurePolicy(
    itemCount: Int,
    gap: Int,
    value: Float,
    startValue: Float,
) = MeasurePolicy { measurables, constraints ->
    // Measure the com.quick.app.components.slider.thumb component and calculate its radius.
    val thumbPlaceable = measurables.first {
        it.layoutId == CustomSliderComponents.THUMB
    }.measure(constraints)
    val thumbRadius = (thumbPlaceable.width / 2).toFloat()

    val indicatorPlaceables = measurables.filter {
        it.layoutId == CustomSliderComponents.INDICATOR
    }.map { measurable ->
        measurable.measure(constraints)
    }
    val indicatorHeight = placeables(indicatorPlaceables).maxByOrNull { it.height }?.height ?: 0

    val sliderPlaceable = measurables.first {
        it.layoutId == CustomSliderComponents.SLIDER
    }.measure(constraints)
    val sliderHeight = sliderPlaceable.height

    val labelPlaceable = measurables.find {
        it.layoutId == CustomSliderComponents.LABEL
    }?.measure(constraints)
    val labelHeight = labelPlaceable?.height ?: 0

    // Calculate the total width and height of the custom slider layout
    val width = sliderPlaceable.width
    val height = labelHeight + sliderHeight + indicatorHeight

    // Calculate the available width for the com.quick.app.components.slider.track (excluding com.quick.app.components.slider.thumb radius on both sides).
    val trackWidth = width - (2 * thumbRadius)

    // Calculate the width of each section in the com.quick.app.components.slider.track.
    val sectionWidth = trackWidth / itemCount
    // Calculate the horizontal spacing between indicators.
    val indicatorSpacing = sectionWidth * gap

    // To calculate offset of the label, first we will calculate the com.quick.app.components.slider.progress of the slider
    // by subtracting startValue from the current value.
    // After that we will multiply this com.quick.app.components.slider.progress by the sectionWidth.
    // Add com.quick.app.components.slider.thumb radius to this resulting value.
    val labelOffset = (sectionWidth * (value - startValue)) + thumbRadius

    layout(width = width, height = height) {
        var indicatorOffsetX = thumbRadius
        // Place label at top.
        // We have to subtract the half width of the label from the labelOffset,
        // to place our label at the center.
        labelPlaceable?.placeRelative(
            x = (labelOffset - (labelPlaceable.width / 2)).roundToInt(),
            y = 20
        )

        // Place slider placeable below the label.
        sliderPlaceable.placeRelative(x = 0, y = labelHeight)

        // Place indicators below the slider.
        indicatorPlaceables.forEach { placeable ->
            // We have to subtract the half width of the each indicator from the indicatorOffset,
            // to place our indicators at the center.
            placeable.placeRelative(
                x = (indicatorOffsetX - (placeable.width / 2)).roundToInt(),
                y = labelHeight + sliderHeight
            )
            indicatorOffsetX += indicatorSpacing
        }
    }
}

private fun placeables(indicatorPlaceables: List<Placeable>) =
    indicatorPlaceables

/**
 * Object to hold defaults used by [CustomSlider]
 */
object RuSliderDefaults {

    @Composable
    fun Thumb(thumbValue: String, color: Color = RuTheme.colors.primaryBase) {
        Box(
            modifier = Modifier
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
                .padding(1.dp)
                .size(16.dp)
                .clip(CircleShape)
                .background(RuTheme.colors.staticWhite, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(6.dp)
                    .clip(CircleShape)
                    .background(color, CircleShape)
            )
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Track(sliderState: SliderState, color: Color = RuTheme.colors.primaryBase) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(6.dp)
                .clip(CircleShape)
                .background(RuTheme.colors.bgSoft)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(fraction = (sliderState.value - sliderState.valueRange.start) / (sliderState.valueRange.endInclusive - sliderState.valueRange.start))
                    .heightIn(6.dp)
                    .clip(CircleShape)
                    .background(color)
            )
        }
    }

    /**
     * Composable function that represents the indicator of the slider.
     *
     * @param indicatorValue The value to display as the indicator.
     * @param modifier The modifier for styling the indicator.
     * @param style The style of the indicator text.
     */
    @Composable
    fun Indicator(
        indicatorValue: String,
        modifier: Modifier = Modifier,
        style: TextStyle = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Normal),
    ) {
        Box(modifier = modifier) {
            Text(
                text = indicatorValue,
                style = style,
                textAlign = TextAlign.Center
            )
        }
    }

    /**
     * Composable function that represents the label of the slider.
     *
     * @param labelValue The value to display as the label.】
     */
    @Composable
    fun Label(
        labelValue: String,
        bgColor: Color = RuTheme.colors.bgStrong,
        textColor: Color = RuTheme.colors.textWhite,
        render: (labelValue: String) -> String = { it },
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            Box(
                modifier = Modifier
                    .layoutId(CustomSliderComponents.LABEL)
                    .height(20.dp)
                    .clip(RoundedCornerShape(RuTheme.radius.radius4))
                    .background(bgColor, RoundedCornerShape(RuTheme.radius.radius4)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 6.dp),
                    text = render(labelValue),
                    style = RuTheme.typo.paragraphXS,
                    textAlign = TextAlign.Center,
                    color = textColor
                )

            }
            SvgTailTriangle(color = bgColor)
        }
    }
}


@Composable
fun SvgTailTriangle(
    modifier: Modifier = Modifier,
    color: Color,
) {
    Box(
        modifier = modifier
            .size(9.dp, 4.dp)
            .padding(start = 2.dp)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val path = Path().apply {
                moveTo(3.43934f, 2.93934f)
                cubicTo(4.02513f, 3.52513f, 4.97487f, 3.52513f, 5.56066f, 2.93934f)
                lineTo(8.5f, 0f)
                lineTo(0.5f, 0f)
                lineTo(3.43934f, 2.93934f)
                close()
            }

            // 缩放至 Canvas 的尺寸
            val scaleX = size.width / 9f
            val scaleY = size.height / 4f
            scale(scaleX, scaleY) {
                drawPath(path, color)
            }
        }
    }
}