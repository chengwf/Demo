package com.chengwf.demo.utils.ext

import android.graphics.Color
import android.text.TextUtils
import androidx.annotation.Size
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

@Size(min = 1)
fun String.toColor() = Color.parseColor(this)

/**
 * 普判断一个字符串是否是json格式的，先判空，然后转jsonObj看是否报错
 */
fun String.isGoodJson(): Boolean {
    return !TextUtils.isEmpty(this) && (return try {
        JSONObject(this)
        true
    } catch (e: Exception) {
        false
    })
}

/**
 * 将json转为实体类对象，借助Gson，简略过程
 *
 */
fun <T> String.toEntity(): T = Gson().fromJson<T>(this, object : TypeToken<T>() {}.type)