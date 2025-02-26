package com.quick.app.route

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.quick.app.feature.splash.GuideRoute
import com.quick.app.feature.splash.SplashRoute

@Composable
fun IndexRoute() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") { SplashRoute { navController.navigate(it)} }
        composable("guide") { GuideRoute() }
    }
}