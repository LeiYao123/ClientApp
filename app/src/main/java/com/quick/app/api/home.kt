package com.quick.app.api

import com.google.gson.JsonObject
import com.quick.app.models.LoginParams
import com.quick.app.models.NetworkPageData
import com.quick.app.models.Order
import com.quick.app.models.ProductListModel
import com.quick.app.models.ProductModel
import com.quick.app.models.Session
import com.quick.app.models.network.NetworkRes
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.QueryMap


val HomeApi: HomeApiService = retrofit.create(HomeApiService::class.java)

interface HomeApiService {
    @GET("/v1/products/page")
    suspend fun getProducts(): NetworkRes<ProductListModel<ProductModel>>

    // 首页其他数据
    @GET("/v1/indexes")
    suspend fun getHomeExtraData(): NetworkRes<JsonObject>

    @GET("/v1/products/info")
    suspend fun productDetail(@Query(value = "id") id: String): NetworkRes<ProductModel>

    @POST("v1/login")
    suspend fun login(@Body data: LoginParams): NetworkRes<Session>


    @GET("v1/orders/info")
    suspend fun orderDetail(@Query(value = "id") id: String): NetworkRes<Order>

    @GET("v1/orders/page")
    suspend fun orders(@QueryMap data: Map<String, String>): NetworkRes<NetworkPageData<Order>>
}