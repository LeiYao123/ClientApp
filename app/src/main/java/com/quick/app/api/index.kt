package com.quick.app.api

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.quick.app.MyApplication
import com.quick.app.config.EnvConfig
import com.quick.app.data.UserUtil
import okhttp3.Call
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(EnvConfig.base_url)
    .callFactory(myOkHttpClient())
    .addConverterFactory(GsonConverterFactory.create())
    .build()


// 添加 okHttp 自定义配置
fun myOkHttpClient(): Call.Factory {
    return OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .apply {
            // if (BuildConfig.DEBUG) {
            // 传递 application 级别的 context，确保 Chucker 跟随应用的完整的生命周期
            addInterceptor(ChuckerInterceptor(MyApplication.instance))
        }
        .addInterceptor {
            var request = it.request()
            val session = UserUtil.getPrefsSession()?.session
            if (!session.isNullOrEmpty()) {
                request = request.newBuilder()
                    .header("Authorization", session)
                    .build()
            }

            it.proceed(request)
        }
        .build()
}
