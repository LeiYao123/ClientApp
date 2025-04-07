# Toast 组件

1. 将 ToastHost 容器放置进 route 级别 `route/index.kt`
    ```
   @Composable
    fun AppRoute(routeName: String? = null) {
    val navController = rememberNavController()
    
        CompositionLocalProvider(LocalNavController provides navController) {
            NavHost(
                navController = navController,
                startDestination = routeName ?: "index/me"
            ) {
                PageRoutes.entries.forEach { pageRoute ->
                    composable(pageRoute.route) { pageRoute.composable() }
                }
            }
        }
        ToastHost()
    }
   ```
2. 使用
   `ToastCenter.show("加载失败", ToastType.ERROR)`