package com.quick.app.api

import com.quick.app.models.ProductListModel
import com.quick.app.models.ProductModel
import com.quick.app.models.network.NetworkRes
import retrofit2.http.GET
import retrofit2.http.Query


val HomeApi: HomeApiService = retrofit.create(HomeApiService::class.java)


interface HomeApiService {
    @GET("/v1/products/page")
    suspend fun getProducts(): NetworkRes<ProductListModel<ProductModel>>

    @GET("/v1/products/info")
    suspend fun productDetail(@Query(value = "id") id: String): NetworkRes<ProductModel>
}