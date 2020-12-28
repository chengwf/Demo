package com.chengwf.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * 使用Gson将json字符串转成对象
 */
inline fun <reified T> String.toEntity(): Any {
    return Gson().fromJson(this, object : TypeToken<T>() {}.type)
}