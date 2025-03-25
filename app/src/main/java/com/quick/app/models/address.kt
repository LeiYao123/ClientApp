package com.quick.app.models

data class Address(
    val name: String = "",
    val telephone: String = "",

    /**
     * 省
     */
    val province: String = "",

    /**
     * 省编码
     */
    val provinceCode: String = "",

    /**
     * 市
     */
    val city: String = "",

    /**
     * 市编码
     */
    val cityCode: String = "",

    /**
     * 区
     */
    val area: String = "",

    /**
     * 区编码
     */
    val areaCode: String = "",

    /**
     * 剩余的地址部分
     */
    val detail: String = "",

    /**
     * 是否是默认地址
     *
     *
     * 0：不是默认
     * 1：默认
     */
//    @SerializedName("default") //Gson
//    @JsonProperty("default") //Jackson
    val default: Boolean = false,

    /**
     * Id
     */
    val id: String = "",
) {
    /**
     * 获取收货地区
     *
     *
     * 省市区拼接
     *
     * @return
     */
    fun getReceiverArea(): String {
        return String.format("%s%s%s", province, city, area)
    }

    fun getContact(): String {
        return String.format("%s %s", name, telephone)
    }

    val areaDisplay: String
        get() = "$province$city$area"
}