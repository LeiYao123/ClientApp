package com.quick.app.components.profileIndicator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun ProfileIndicatorDemoRoute() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            ProfileIndicator(name = "-", stage = ProfileStage.None, badgeNum = "3")
            ProfileIndicator(name = "Kendrick...", stage = ProfileStage.New, badgeNum = "3")
            ProfileIndicator(name = "Kendrick...", stage = ProfileStage.Returning, badgeNum = "3")
            ProfileIndicator(name = "Kendrick...", stage = ProfileStage.Regular, badgeNum = "3")
            ProfileIndicator(name = "Kendrick...", stage = ProfileStage.Loyal, badgeNum = "3")
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            ProfileIndicatorMinimal(name = "-", stage = ProfileStage.None, badgeNum = "3")
            ProfileIndicatorMinimal(name = "KL——kl", stage = ProfileStage.New, badgeNum = "3")
            ProfileIndicatorMinimal(
                name = "KL--demo",
                stage = ProfileStage.Returning,
                badgeNum = "3"
            )
            ProfileIndicatorMinimal(name = "KL", stage = ProfileStage.Regular, badgeNum = "3")
            ProfileIndicatorMinimal(
                name = "KL------demo123",
                stage = ProfileStage.Loyal,
                badgeNum = "3"
            )
        }
    }
}