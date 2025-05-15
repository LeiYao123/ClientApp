package com.quick.app.components.profileIndicator

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.quick.app.components.bdage.RuBadge
import com.quick.app.components.bdage.RuBadgeSize
import com.quick.app.components.bdage.RuBadgeType
import com.quick.app.components.svgicon.SvgIcon
import com.quick.app.components.svgicon.SvgPath
import com.quick.app.ui.theme.PaletteTokens
import com.quick.app.ui.theme.RuTheme


enum class ProfileStage {
    None,
    New,
    Returning,
    Regular,
    Loyal
}


/**
 * 普通模式
 * */

@Composable
fun ProfileIndicator(
    modifier: Modifier = Modifier,
    stage: ProfileStage = ProfileStage.None,
    name: String,
    badgeNum: String? = null,
) {
    val stageConfig = getConfigByState(stage)

    Row(
        modifier = modifier.height(36.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .shadow(
                    elevation = 2.dp,
                    spotColor = Color(0x080A0D14),
                    ambientColor = Color(0x080A0D14)
                )
                .border(
                    width = 1.dp,
                    shape = RoundedCornerShape(8.dp),
                    color = RuTheme.colors.strokeSoft
                )
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            SvgIcon(SvgPath.user_line, tint = RuTheme.colors.textSub)
            Text(name, style = RuTheme.typo.labelS, color = RuTheme.colors.textSub)
            if (badgeNum != null)
                RuBadge(badgeNum, type = RuBadgeType.RED, size = RuBadgeSize.S, isNumber = true)
        }
        if (stage != ProfileStage.None)
            Column(
                modifier = Modifier
                    .width(86.dp)
                    .fillMaxHeight()
                    .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 6.dp),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(stageConfig.text, style = RuTheme.typo.labelS, color = RuTheme.colors.textSub)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    for (i in 1..4) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(2.dp)
                                .clip(RoundedCornerShape(1.dp))
                                .let {
                                    if (i <= stageConfig.num) {
                                        it.background(RuTheme.colors.successBase)
                                    } else {
                                        it.background(RuTheme.colors.strokeSoft)
                                    }
                                }
                        )
                    }
                }
            }
    }
}


/***
 * minimal 形式
 */
@Composable
fun ProfileIndicatorMinimal(
    modifier: Modifier = Modifier,
    stage: ProfileStage = ProfileStage.None,
    name: String,
    badgeNum: String? = null,
) {
    val content: @Composable () -> Unit = {
        Text(name, style = RuTheme.typo.labelS, color = RuTheme.colors.textSub)
        if (badgeNum != null)
            RuBadge(badgeNum, type = RuBadgeType.RED, size = RuBadgeSize.S, isNumber = true)
    }

    Box(modifier = modifier) {
        if (stage == ProfileStage.None) MinimalProfileNone { content() }
        else MinimalProfile(stage) { content() }
    }
}


@Composable
fun MinimalProfileNone(content: @Composable () -> Unit) {
    Row(
        modifier = Modifier
            .height(36.dp)
            .shadow(
                elevation = 2.dp,
                spotColor = Color(0x080A0D14),
                ambientColor = Color(0x080A0D14)
            )
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(8.dp),
                color = RuTheme.colors.strokeSoft
            )
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        content()
    }
}

@Composable
fun MinimalProfile(stage: ProfileStage = ProfileStage.None, content: @Composable () -> Unit) {
    var width by remember { mutableStateOf(0.dp) }
    val density = LocalDensity.current

    Box(
        modifier = Modifier.onGloballyPositioned {
            width = with(density) { it.size.width.toDp() }
        }
    ) {
        Row(
            modifier = Modifier
                .height(36.dp)
                .shadow(
                    elevation = 2.dp,
                    spotColor = Color(0x080A0D14),
                    ambientColor = Color(0x080A0D14)
                )
                .border(
                    width = 2.dp,
                    shape = RoundedCornerShape(8.dp),
                    color = RuTheme.colors.strokeSoft
                )
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            content()
        }
        if (stage == ProfileStage.Loyal)
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .border(
                        width = 2.dp,
                        color = PaletteTokens.green_500,
                        shape = RoundedCornerShape(8.dp)
                    )
            )

        Box(
            modifier = Modifier
                .width(3.dp)
                .height(3.dp)
                .background(Color.White)
                .align(Alignment.TopCenter)
        )
        Box(
            modifier = Modifier
                .width(3.dp)
                .height(3.dp)
                .background(Color.White)
                .align(Alignment.BottomCenter)
        )
        Box(
            modifier = Modifier
                .width(3.dp)
                .height(3.dp)
                .background(Color.White)
                .align(Alignment.CenterStart)
        )
        if (stage == ProfileStage.New)
            Box(modifier = Modifier.align(Alignment.TopEnd)) {
                NewPolyline((width - 3.dp) / 2, 18.dp, stage)
            }
        if (stage == ProfileStage.Returning || stage == ProfileStage.Regular)
            Box(modifier = Modifier.align(Alignment.CenterEnd)) {
                NewPolyline((width - 3.dp) / 2, 36.dp, stage)
            }
        if (stage == ProfileStage.Regular) {
            Box(modifier = Modifier.align(Alignment.BottomStart)) {
                NewPolyline((width - 3.dp) / 2, (36.dp - 3.dp) / 2, stage, true)
            }
        }
    }
}


data class StateConfig(
    val text: String,
    val num: Int,
)

fun getConfigByState(stage: ProfileStage): StateConfig {
    return when (stage) {
        ProfileStage.New -> StateConfig("New", 1)
        ProfileStage.Returning -> StateConfig("Returning", 2)
        ProfileStage.Regular -> StateConfig("Regular", 3)
        ProfileStage.Loyal -> StateConfig("Loyal", 4)
        ProfileStage.None -> StateConfig("None", 0)
    }
}