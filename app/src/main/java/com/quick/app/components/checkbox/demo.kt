package com.quick.app.components.checkbox


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.quick.app.components.spacer.RuSpacer
import com.quick.app.ui.theme.RuTheme

@Composable
fun RuCheckboxDemo() {
    Scaffold { pd ->
        Surface(
            modifier = Modifier
                .padding(pd)
                .padding(16.dp),
            color = Color.Transparent
        ) {
            Column {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    CheckBoxIcon()
                    CheckBoxIcon(enabled = false)
                    CheckBoxIcon(checked = true)
                    CheckBoxIcon(checked = true, enabled = false)
                    CheckBoxIcon(indeterminate = true)
                    CheckBoxIcon(indeterminate = true, enabled = false)
                }
                RuSpacer(h = 12)

                // =========================================================================================================

                LabelCheckbox()
                RuSpacer(h = 12)
                CardCheckbox()
            }
        }
    }
}


@Composable
fun LabelCheckbox() {
    val subLabel: @Composable () -> Unit = {
        Text(
            text = "(subLabel)",
            style = RuTheme.typo.paragraphXS,
        )
    }

    val badge: @Composable () -> Unit = {
        Text(text = "Badge")
    }

    val desc: @Composable () -> Unit = {
        Text(
            text = "Insert the checkbox description here.",
            style = RuTheme.typo.paragraphXS,
        )
    }

    val checked = remember { mutableStateOf(false) }
    RuCheckBox(
        modifier = Modifier.width(280.dp),
        text = "CheckBox",
        checked = checked.value,
        enabled = false,
        onChange = { checked.value = it },
        isFlip = false,
        subLabel = { subLabel() },
        badge = { badge() },
        desc = { desc() },
    )
    RuSpacer(h = 12)
    RuCheckBox(
        modifier = Modifier.width(280.dp),
        text = "CheckBox",
        checked = checked.value,
        onChange = { checked.value = it },
        isFlip = true,
        subLabel = { subLabel() },
        badge = { badge() },
        desc = { desc() },
    )
}


@Composable
fun CardCheckbox() {
    val subLabel: @Composable () -> Unit = {
        Text(
            text = "(subLabel)",
            style = RuTheme.typo.paragraphXS,
        )
    }

    val badge: @Composable () -> Unit = {
        Text(text = "Badge")
    }

    val desc: @Composable () -> Unit = {
        Text(
            text = "Insert the checkbox description here.",
            style = RuTheme.typo.paragraphXS,
        )
    }

    val checked = remember { mutableStateOf(false) }
    RuCheckBoxCard(
        modifier = Modifier.width(280.dp),
        text = "CheckBox",
        checked = checked.value,
        enabled = false,
        onChange = { checked.value = it },
        subLabel = { subLabel() },
        badge = { badge() },
        desc = { desc() },
    )
    RuSpacer(h = 12)
    RuCheckBoxCard(
        modifier = Modifier.width(380.dp),
        text = "CheckBox",
        checked = checked.value,
        onChange = { checked.value = it },
        subLabel = { subLabel() },
        badge = { badge() },
        desc = { desc() },
        icon = {
            Icon(
                Icons.Default.Check,
                modifier = Modifier.size(40.dp),
                contentDescription = ""
            )

        }
    )
}