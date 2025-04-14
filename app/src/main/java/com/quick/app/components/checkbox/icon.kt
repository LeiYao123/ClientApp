package com.quick.app.components.checkbox

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.quick.app.components.svgicon.SvgIcon


object CheckBoxSvg {
    const val defaultStatus = "svg/checkbox/default.svg"
    const val defaultDisable = "svg/checkbox/defaultDisable.svg"
    const val checked = "svg/checkbox/checked.svg"
    const val checkedDisable = "svg/checkbox/checkedDisable.svg"
    const val indeterminate = "svg/checkbox/indeterminate.svg"
    const val indeterminateDisable = "svg/checkbox/indeterminateDisable.svg"
}

@Composable
fun CheckBoxIcon(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    checked: Boolean = false,
    isToggle: Boolean = false,
    indeterminate: Boolean = false,
) {
    var path = CheckBoxSvg.defaultStatus
    if (enabled) {
        if (checked) {
            path = CheckBoxSvg.checked
        }
        if (indeterminate) {
            path = CheckBoxSvg.indeterminate
        }
    } else {
        path = CheckBoxSvg.defaultDisable
        if (checked) {
            path = CheckBoxSvg.checkedDisable
        }
        if (indeterminate) {
            path = CheckBoxSvg.indeterminateDisable
        }
    }
    SvgIcon(
        path = path,
        modifier = modifier,
    )
}

