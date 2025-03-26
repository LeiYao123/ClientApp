package com.quick.app.models

import com.google.gson.JsonObject

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


data class AdModel(
    val title: String? = null,
    val icon: String,
    val uri: String? = null,
    //* 类型，0：图片；10：视频；20：应用
    val style: Int = 0,
) {
    companion object {
        fun fromJson(json: JsonObject?): List<AdModel> {
            if (json == null) return emptyList()
            // 获取 "list" 数组
            val listArray = json.get("list")?.asJsonArray ?: return emptyList()

            // 获取 list 数组中的第一项
            val firstItem = listArray.firstOrNull()?.asJsonObject ?: return emptyList()

            // 获取 "ads" 数组
            val adsArray = firstItem.get("ads")?.asJsonArray ?: return emptyList()

            // 解析 ads 数组中的每个元素为 AdModel
            return adsArray.mapNotNull {
                // 确保每个 adElement 都能转换为 JsonObject
                val adObject = it.asJsonObject
                AdModel(
                    title = adObject.get("title")?.asString,
                    icon = adObject.get("icon")?.asString ?: "",
                    uri = adObject.get("uri")?.asString,
                    style = adObject.get("style")?.asInt ?: 0
                )
            }
        }
    }
}

