package com.quick.app.models

data class ProductListModel<T>(
    val pagination: Pagination? = null,
    val list: List<T> = emptyList(),
)

data class Pagination(
    val total: Int = 0,
    val pages: Int = 0,
    val size: Int = 0,
    val page: Int = 0,
)

data class ProductModel(
    val id: String,
    val title: String,
    val number: String? = null,
    val icon: String? = null,
    val icons: List<String> = emptyList(),
    val video: String? = null,
    val keyword: String? = null,
    val category1Id: String? = null,
    val category2Id: String? = null,
    val category3Id: String? = null,
    val originPrice: Int = 0,
    val price: Int = 0,
    val memberPrice: Int = 0,
    val highlight: String? = null,
    val detail: String? = null,
    val sale: Byte = 0,
    val deleted: Byte = 0,
    val hot: Byte = 0,
    val news: Byte = 0,
    val recommend: Byte = 0,
    val commentsRate: Float = 0.0f,
    val commentsCount: Int = 0,
    val salesCount: Int = 0,
    val stockCount: Long = 0,
)



