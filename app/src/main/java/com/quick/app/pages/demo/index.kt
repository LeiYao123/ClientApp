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
import com.quick.app.components.checkbox.RuCheckboxDemo
import com.quick.app.components.radio.RadioGroupExample
import com.quick.app.ui.theme.RuTheme

@Composable
fun ComponentDemoRoute() {
    Scaffold { pd ->
        Surface(
            modifier = Modifier
                .padding(pd)
                .padding(16.dp),
            color = Color.Transparent
        ) {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "badge"
            ) {
                composable("demo_index") { DemoIndex(navController) }
                composable("button") { RuButtonDemo() }
                composable("checkbox") { RuCheckboxDemo() }
                composable("badge") { RuBadgeDemo() }
                composable("radio") { RadioGroupExample() }
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
        RuButton("radio demo", onClick = { navController.navigate("radio") })
    }
}