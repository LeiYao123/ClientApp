package com.quick.app.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.quick.app.components.dialog.DialogHost
import com.quick.app.components.drawer.DrawerHost
import com.quick.app.components.toast.ToastHost

val LocalNavController = staticCompositionLocalOf<NavHostController> {
    error("No NavController provided!")
}

@Composable
fun AppRoute(routeName: String? = null) {
    val navController = rememberNavController()

    CompositionLocalProvider(LocalNavController provides navController) {
        NavHost(
            navController = navController,
            startDestination = routeName ?: "component/demo"
        ) {
            PageRoutes.entries.forEach { pageRoute ->
                composable(pageRoute.route) { pageRoute.composable() }
            }
        }
    }

    DrawerHost()
    DialogHost()
    ToastHost()
}
