package com.zhengsr.wanandroid_jetpack.net

import com.franmontiel.persistentcookiejar.ClearableCookieJar
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.zhengsr.wanandroid_jetpack.MainApplication
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.fastjson.FastJsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author by zhengshaorui 2022/1/28
 * describe：
 */

fun getApi() = HttpHolder.getServer()
object HttpHolder {
    fun getServer(): HttpServerApi {
        val retrofit = Retrofit.Builder() //这里采用这个，因为有多个baseurl
            .baseUrl("https://www.wanandroid.com/") //转字符串
            .addConverterFactory(ScalarsConverterFactory.create()) //fastjson
            .addConverterFactory(FastJsonConverterFactory.create())
            .client(OkHttpHolder.BUILDER)
            .build()
        return retrofit.create(HttpServerApi::class.java)
    }

    /**
     * 配置okhttp3 client
     */
    private var cookieJar: ClearableCookieJar = PersistentCookieJar(
        SetCookieCache(),
        SharedPrefsCookiePersistor(MainApplication.context)
    )

    private object OkHttpHolder {
        var BUILDER: OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(
                10,
                TimeUnit.SECONDS
            ) //.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .cookieJar(cookieJar)
            .build()
    }
}