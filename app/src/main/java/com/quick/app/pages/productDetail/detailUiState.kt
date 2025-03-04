package com.quick.app.pages.productDetail

import com.quick.app.models.ProductModel

// 定义一个商品详情成功、失败、加载中的密封类
sealed class DetailUiState {
    data object Loading : DetailUiState()
    data class Success(val data: ProductModel) : DetailUiState()
    data class Error(val error: Throwable) : DetailUiState()
}