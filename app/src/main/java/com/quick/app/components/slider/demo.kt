package com.quick.app.components.slider

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.quick.app.ui.theme.RuTheme
import kotlin.math.roundToInt

@Composable
fun SliderDemoRoute() {
    var sliderPosition by remember { mutableStateOf(0.5f) }
    var sliderValue by remember { mutableStateOf(0.3f) }


    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Text(text = "Value: ${sliderPosition}")
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            valueRange = 0f..1f,
            steps = 4, // 总共 5 个点
            onValueChangeFinished = {
                // 滑动结束，提交或者做别的事
            }
        )

        Spacer(Modifier.height(16.dp))
        Slider(
            value = sliderValue,
            onValueChange = { sliderValue = it },
//            colors = SliderDefaults.colors(
//                thumbColor = Color.Magenta,
//                activeTrackColor = Color.Cyan,
//                inactiveTrackColor = Color.LightGray
//            )
        )
        SliderSample()

        StepsSliderSample()

        Slider3()

        Slider4()
    }
}

@Composable
fun SliderSample() {
    var sliderPosition by remember { mutableStateOf(0f) }
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(text = "%.2f".format(sliderPosition))
        Slider(value = sliderPosition, onValueChange = { sliderPosition = it })
    }
}


@Composable
fun StepsSliderSample() {
    var sliderPosition by remember { mutableStateOf(0f) }
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(text = sliderPosition.roundToInt().toString())
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            valueRange = 0f..100f,
            onValueChangeFinished = {
                // launch some business logic update with the state you hold
                // viewModel.updateSelectedSliderValue(sliderPosition)
            },
            // Only allow multiples of 10. Excluding the endpoints of `valueRange`,
            // there are 9 steps (10, 20, ..., 90).
            steps = 9,
            colors =
            SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.secondary,
                activeTrackColor = MaterialTheme.colorScheme.secondary
            )
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Slider3() {
    var sliderValue by remember { mutableFloatStateOf(0f) }

    CustomSlider(
        showLabel = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        value = sliderValue,
        onValueChange = { sliderValue = it },
        valueRange = 0f..100f,
        showIndicator = false,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Slider4() {
    var sliderValue by remember { mutableFloatStateOf(50f) }

    CustomSlider(
        showLabel = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        value = sliderValue,
        onValueChange = { sliderValue = it },
        valueRange = 0f..100f,
        showIndicator = false,
        thumb = { RuSliderDefaults.Thumb(it.toString(), RuTheme.colors.awayBase) },
        track = { RuSliderDefaults.Track(it, RuTheme.colors.awayBase) },
        label = {
            RuSliderDefaults.Label(
                it.toString(),
                bgColor = RuTheme.colors.errorBase,
                textColor = RuTheme.colors.textStrong,
                render = { "curr value: $it" })
        }
    )
}
