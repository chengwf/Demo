package com.chengwf.utils

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitFactory private constructor() {


    companion object {
        val instance by lazy {
            RetrofitFactory()
        }
    }


    fun <T> create(clazz: Class<T>) : T {
        return retrofit.create(clazz)
    }


    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("")
        .addConverterFactory(GsonConverterFactory.create())
        // 配置请求头什么的
        .client(buildOkHttpClient())
        .build()

    private fun buildOkHttpClient() = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(5, TimeUnit.SECONDS)
        .writeTimeout(5, TimeUnit.SECONDS)
        .addInterceptor { chain ->
            val data = chain.request().newBuilder()
//                    .addHeader("Platform-Version", "Android:" + BuildConfig.VERSION_NAME + ":" + SystemUtils.getPhoneModel() + ":" + SystemUtils.getOS())
//                    .header("Build-Version", BuildConfig.VERSION_CODE.toString())
                .build()

            chain.proceed(data)
        }
        .addInterceptor(
            HttpLoggingInterceptor {
                Log.d("TAG_HTTP", it)
            }.setLevel(HttpLoggingInterceptor.Level.HEADERS)
                .setLevel(HttpLoggingInterceptor.Level.BODY)
        )
        .build()
}