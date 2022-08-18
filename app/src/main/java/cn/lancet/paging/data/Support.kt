package cn.lancet.paging.data
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class Support(
    @Json(name = "text")
    val text: String,
    @Json(name = "url")
    val url: String
)