package com.quick.app.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.quick.app.route.LocalNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RuTopAppBar(
    title: String = "",
    toBack: Boolean = false,
    actions: @Composable RowScope.() -> Unit = {},
) {

    CenterAlignedTopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            if (!toBack) return@CenterAlignedTopAppBar
            val navController = LocalNavController.current
            Icon(
                Icons.Default.ArrowBackIosNew,
                contentDescription = "返回",
                modifier = Modifier.clickable {
                    navController.popBackStack()
                }
            )
        },
        actions = actions
    )
}