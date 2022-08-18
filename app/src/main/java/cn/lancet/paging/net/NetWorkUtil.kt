package cn.lancet.paging.net

import android.util.Log
import cn.lancet.paging.data.ResponseEntity
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Lancet at 2022/8/18 18:02
 */
class NetWorkUtil {

    private val BASE_URL = "https://reqres.in/api/"

    private val mLoggingInterceptor =
        HttpLoggingInterceptor { message -> Log.d("Lancet ", message) }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    private val mOkHttpClient =
        OkHttpClient.Builder()
            .addNetworkInterceptor(mLoggingInterceptor)
            .build()

    private val mMoShi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val mRetrofit: Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(mOkHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(mMoShi))
            .build()

    private var mRetrofitService: HttpApi

    suspend fun getUserInfo(pageNumber: Int, pageSize: Int): ResponseEntity {
        return mRetrofitService.getUserInfo(pageNumber, pageSize)
    }

    internal interface HttpApi {
        @GET("users")
        suspend fun getUserInfo(
            @Query("page") pageNumber: Int,
            @Query("per_page") pageSize: Int
        ): ResponseEntity
    }


    companion object {
        var instance: NetWorkUtil? = null
            get() {
                if (field == null) {
                    field = NetWorkUtil()
                }
                return field
            }
            private set
    }

    init {
        mRetrofitService = mRetrofit.create(HttpApi::class.java)
    }

}