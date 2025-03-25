package com.quick.app.models

data class NetworkPageData<T>(
    /**
     * 列表
     */
    val list: List<T>? = null,

    /**
     * 分页数据
     */
    val pagination: NetworkPageMeta? = null,
)

data class NetworkPageMeta(
    /**
     * 有多少条
     */
    val total: Int? = null,

    /**
     * 有多少页
     */
    val pages: Int? = null,

    /**
     * 当前每页显示多少条
     */
    val size: Int? = null,

    /**
     * 当前页
     */
    val page: Int? = null,

    /**
     * 下一页
     */
    val next: Int? = null,
)