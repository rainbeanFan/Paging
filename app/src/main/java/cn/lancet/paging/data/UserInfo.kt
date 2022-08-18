package cn.lancet.paging.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class UserInfo(
    @Json(name = "avatar")
    val avatar: String,
    @Json(name = "email")
    val email: String,
    @Json(name = "first_name")
    val firstName: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "last_name")
    val lastName: String
) {
    override fun toString(): String {
        return "Data(avatar='$avatar', email='$email', firstName='$firstName', id=$id, lastName='$lastName')"
    }
}