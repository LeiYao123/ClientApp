package com.quick.app.route

import androidx.compose.runtime.Composable
import com.quick.app.pages.category.CategoryRoute
import com.quick.app.pages.guide.GuideRoute
import com.quick.app.pages.home.HomeRoute
import com.quick.app.pages.index.IndexRoute
import com.quick.app.pages.login.LoginRoute
import com.quick.app.pages.loginAccount.LoginAccountRoute
import com.quick.app.pages.me.MeRoute
import com.quick.app.pages.productDetail.ProductDetailRoute
import com.quick.app.pages.splash.SplashRoute
import com.quick.app.pages.video.VideoRoute

enum class PageRoutes(
    val route: String,
    val composable: @Composable () -> Unit,
    val routeParam: ((String) -> String) = { it -> it },
) {
    Splash("splash", { SplashRoute() }),
    Guide("guide/{id}", { GuideRoute() }, { "guide/$it" }),

    Index("index/{page}", { IndexRoute() }, { "index/$it" }),
    Home("home", { HomeRoute() }),
    Video("video", { VideoRoute() }),
    Category("category", { CategoryRoute() }),
    Cart("cart", { CategoryRoute() }),
    Me("me", { MeRoute() }),

    Detail("detail/{id}", { ProductDetailRoute() }, { "detail/$it" }),
    Login("login", { LoginRoute() }),
    LoginAccount("login/account", { LoginAccountRoute() })
}