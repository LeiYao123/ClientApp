package com.quick.app.components.checkbox

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.quick.app.components.svgicon.SvgIcon


object BoxSvg {
    const val defaultCheckbox = "svg/checkbox/default.svg"
    const val defaultDisable = "svg/checkbox/defaultDisable.svg"
    const val checked = "svg/checkbox/checked.svg"
    const val checkedDisable = "svg/checkbox/checkedDisable.svg"
    const val indeterminate = "svg/checkbox/indeterminate.svg"
    const val indeterminateDisable = "svg/checkbox/indeterminateDisable.svg"

    const val defaultRadioDisabled = "svg/checkbox/defaultRadioDisabled.svg"
    const val defaultRadio = "svg/checkbox/defaultRadio.svg"
    const val checkedRadioDisabled = "svg/checkbox/checkedRadioDisabled.svg"
    const val checkedRadio = "svg/checkbox/checkedRadio.svg"
}

enum class BoxType { RADIO, CHECKBOX, TOGGLE }

@Composable
fun BoxIcon(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    checked: Boolean = false,
    type: BoxType = BoxType.CHECKBOX,
    indeterminate: Boolean = false, // checkbox 独有
) {
    val path = getIcon(type, enabled, indeterminate)
    SvgIcon(
        path = if (checked) path.checkedIcon else path.icon,
        modifier = modifier,
    )
}

data class IconStatus(
    val icon: String,
    val checkedIcon: String,
)

fun getIcon(type: BoxType, enabled: Boolean, indeterminate: Boolean): IconStatus {
    return when (type) {
        BoxType.RADIO -> {
            if (enabled) IconStatus(BoxSvg.defaultRadio, BoxSvg.checkedRadio)
            else IconStatus(BoxSvg.defaultRadioDisabled, BoxSvg.checkedRadioDisabled)
        }

        BoxType.CHECKBOX -> {
            if (indeterminate) {
                if (enabled) IconStatus(BoxSvg.indeterminate, BoxSvg.indeterminate)
                else IconStatus(BoxSvg.indeterminateDisable, BoxSvg.indeterminateDisable)
            } else {
                if (enabled) IconStatus(BoxSvg.defaultCheckbox, BoxSvg.checked)
                else IconStatus(BoxSvg.defaultDisable, BoxSvg.checkedDisable)
            }
        }
        // TODO 待添加 toggle 图标
        BoxType.TOGGLE -> {
            if (enabled) IconStatus(BoxSvg.defaultCheckbox, BoxSvg.checked)
            else IconStatus(BoxSvg.defaultDisable, BoxSvg.checkedDisable)
        }
    }
}