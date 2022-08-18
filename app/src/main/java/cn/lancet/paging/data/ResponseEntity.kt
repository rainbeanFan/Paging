package cn.lancet.paging.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseEntity(
    @Json(name = "data") val userInfo: List<UserInfo>,
    @Json(name = "page") val page: Int,
    @Json(name = "per_page") val perPage: Int,
    @Json(name = "support") val support: Support,
    @Json(name = "total") val total: Int,
    @Json(name = "total_pages") val totalPages: Int
)