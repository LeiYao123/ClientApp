### 自己手动利用 JsonObject 序列化 api 响应

1. 创建 service 时返回 NetworkRes<JsonObject>
    ```kotlin
    interface HomeApiService {
      @GET("/v1/products/page")
      suspend fun getProducts(): NetworkRes<JsonObject>
    }
   ```
2. 创建 ItemModel 并提供 fromJson 方法
    ```kotlin
   data class ItemModel(
        val id: String,
        val title: String,
        val sale: String,
    ) {
        companion object {
            fun fromJson(json: JsonObject): ItemModel {
                val id = json.get("id").asString
                val title = json.get("title").asString
                val sale = json.get("sale").asString
                return ItemModel(id, title, sale)
            }
        }
    }
   ```
3. viewModel 里面在 api 响应拿到之后 手动调用序列化方法
    ```kotlin
   try {
         val res = ApiClient.homeApiService.getProducts()
         val list = Gson().fromJson(res.data, ProductListModel::class.java)
         val rr = res.data?.get("list")?.asJsonArray?.map { ItemModel.fromJson(it.asJsonObject) }
         Log.d("response", "$list $rr")
    } catch (e: Exception) {
         Log.d("response", e.toString())
    }
   ```


