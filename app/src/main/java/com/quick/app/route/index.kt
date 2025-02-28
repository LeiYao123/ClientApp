package com.quick.app.route

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.quick.app.pages.guide.GuideRoute
import com.quick.app.pages.index.IndexRoute
import com.quick.app.pages.splash.SplashRoute


val LocalNavController = staticCompositionLocalOf<NavHostController> {
    error("No NavController  provided!")
}
@Composable
fun AppRoute(routeName: String? = null) {
    val navController = rememberNavController()

    CompositionLocalProvider(LocalNavController provides navController) {
        NavHost(navController = navController, startDestination = routeName ?: "splash") {
            Log.d("Route", "route --> composable")
            composable(PageRoutes.Splash.route) { SplashRoute() }
            composable(PageRoutes.Guide.route) { GuideRoute() }
            composable(PageRoutes.Index.route) { IndexRoute() }
        }
    }
}



sealed class PageRoutes(val route: String) {
    data object Splash : PageRoutes("splash")
    data object Guide : PageRoutes("guide/{id}")
    class GuideParams(id: String) : PageRoutes("guide/$id")
    data object Index : PageRoutes("index")
}
