package com.quick.app.route

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.quick.app.pages.category.CategoryRoute
import com.quick.app.pages.guide.GuideRoute
import com.quick.app.pages.home.HomeRoute
import com.quick.app.pages.index.IndexRoute
import com.quick.app.pages.login.LoginRoute
import com.quick.app.pages.me.MeRoute
import com.quick.app.pages.productDetail.ProductDetailRoute
import com.quick.app.pages.splash.SplashRoute
import com.quick.app.pages.video.VideoRoute


val LocalNavController = staticCompositionLocalOf<NavHostController> {
    error("No NavController  provided!")
}

@Composable
fun AppRoute(routeName: String? = null) {
    val navController = rememberNavController()

    CompositionLocalProvider(LocalNavController provides navController) {
        NavHost(navController = navController, startDestination = routeName ?: "index") {
            Log.d("Route", "route --> composable")
            composable(PageRoutes.Splash.route) { SplashRoute() }
            composable(PageRoutes.Guide.route) { GuideRoute() }
            composable(PageRoutes.Index.route) { IndexRoute() }
            composable(PageRoutes.Home.route) { HomeRoute() }
            composable(PageRoutes.Detail.route) { ProductDetailRoute() }
            composable(PageRoutes.Video.route) { VideoRoute() }
            composable(PageRoutes.Category.route) { CategoryRoute() }
            composable(PageRoutes.Cart.route) { CategoryRoute() }
            composable(PageRoutes.Me.route) { MeRoute() }
            composable(PageRoutes.Login.route) { LoginRoute() }
        }
    }
}


sealed class PageRoutes(val route: String) {
    data object Splash : PageRoutes("splash")
    data object Guide : PageRoutes("guide/{id}")
    data object Index : PageRoutes("index")
    data object Home : PageRoutes("home")

    data object Detail : PageRoutes("detail/{id}")

    // navigate 方法跳转时带参数
    class DetailParams(id: String) : PageRoutes("detail/$id")

    data object Video : PageRoutes("video")
    data object Category : PageRoutes("category")
    data object Cart : PageRoutes("cart")
    data object Me : PageRoutes("me")

    data object Login : PageRoutes("login")
}
