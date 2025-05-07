package com.quick.app.pages.demo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.quick.app.components.bdage.RuBadgeDemo
import com.quick.app.components.button.RuButton
import com.quick.app.components.button.RuButtonDemo
import com.quick.app.components.buttonGroup.RuButtonGroupDemo
import com.quick.app.components.checkbox.RuCheckboxDemo
import com.quick.app.components.dialog.RuDialogDemo
import com.quick.app.components.drawer.DrawerDemo
import com.quick.app.components.input.InputRouteDemo
import com.quick.app.components.popover.PopoverDemoRoute
import com.quick.app.components.slider.SliderDemoRoute
import com.quick.app.components.tab.RuTabDemo
import com.quick.app.components.toast.ToastDemo
import com.quick.app.components.wheel.WheelDemoRoute
import com.quick.app.ui.theme.RuTheme

@Composable
fun ComponentDemoRoute() {
    Scaffold { pd ->
        println("$pd")
        Surface(
            modifier = Modifier
                .padding(pd)
                .padding(16.dp),
            color = Color.Transparent
        ) {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "wheel"
            ) {
                composable("demo_index") { DemoIndex(navController) }
                composable("button") { RuButtonDemo() }
                composable("checkbox") { RuCheckboxDemo() }
                composable("badge") { RuBadgeDemo() }
                composable("button_group") { RuButtonGroupDemo() }
                composable("tab") { RuTabDemo() }
                composable("toast") { ToastDemo() }
                composable("drawer") { DrawerDemo() }
                composable("dialog") { RuDialogDemo() }
                composable("input") { InputRouteDemo() }
                composable("popover") { PopoverDemoRoute() }
                composable("slider") { SliderDemoRoute() }
                composable("wheel") { WheelDemoRoute() }
            }
        }
    }
}


@Composable
fun DemoIndex(navController: NavHostController) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("component demo", color = RuTheme.colors.primaryBase, style = RuTheme.typo.titleH3)
        Spacer(modifier = Modifier.height(16.dp))
        RuButton("button demo", onClick = { navController.navigate("button") })
        RuButton("checkbox demo", onClick = { navController.navigate("checkbox") })
        RuButton("badge demo", onClick = { navController.navigate("badge") })
        RuButton("button group demo", onClick = { navController.navigate("button_group") })
        RuButton("tab demo", onClick = { navController.navigate("tab") })
        RuButton("toast demo", onClick = { navController.navigate("toast") })
        RuButton("drawer demo", onClick = { navController.navigate("drawer") })
        RuButton("dialog demo", onClick = { navController.navigate("dialog") })
        RuButton("input demo", onClick = { navController.navigate("input") })
        RuButton("popover demo", onClick = { navController.navigate("popover") })
        RuButton("slider demo", onClick = { navController.navigate("slider") })
        RuButton("wheel demo", onClick = { navController.navigate("wheel") })
    }
}