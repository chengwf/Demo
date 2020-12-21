package com.chengwf.demo.ext

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * 使用Gson将json字符串转成对象
 *
 * 简单点，不过没做验证，应该没有人会用非json字符串调这个方法吧？
 */
fun <T> String.toEntity(cls: Class<*>): T {
    return Gson().fromJson<T>(this, cls)
}

/**
 * 会报错(泛型擦除的问题)，请用上面那个方法。当然，如果你有更好的方法请提出
 */
fun <T> String.toEntity(): Any {
    return Gson().fromJson(this, object : TypeToken<T>() {}.type)
}