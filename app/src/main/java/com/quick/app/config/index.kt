package com.quick.app.config

@Suppress("KotlinConstantConditions")
object EnvConfig {
    // DEMO / PRODUCTION
    val env = EnvironmentVariable.BUILD_ENV as Any

    private const val DEMO_URL = "https://quick-server-sp.ixuea.com"
    private const val PRODUCTION_URL = "https://quick-server-sp.ixuea.com"


    val base_url = if (env == "DEMO") DEMO_URL else PRODUCTION_URL
}