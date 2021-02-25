package com.chengwf.utils.ext

import android.graphics.Color
import android.text.TextUtils
import androidx.annotation.Size
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import java.util.*


@Size(min = 4)
fun String.toColor() = Color.parseColor(this)

/**
 * 普判断一个字符串是否是json格式的，先判空，然后转jsonObj看是否报错
 */
fun String.isJson(): Boolean {
    return !TextUtils.isEmpty(this) && (return try {
        JSONObject(this)
        true
    } catch (e: Exception) {
        false
    })
}

/**
 * 将json字符串转成对象
 */
inline fun <reified T> String.toEntity(): T = Gson().fromJson<T>(this, T::class.java)

fun String.candyFormat() = this.toBigDecimal().toInt().toString()

/**
 * 根据语言获得国家名称
 *
 * @param language 传入的语言，若没有则取设备系统设置的
 */
fun String?.toCountryName(language: String = Locale.getDefault().language) =
    Locale(language, this ?: Locale.getDefault().country).displayCountry

/**
 * 将字符转成emoji国旗图标
 */
fun String?.toEmojiFlag(): String {

    if (this.isNullOrEmpty()) return ""

    if (this.length != 2) {
        return this
    }
    val upperCase = this.toUpperCase(Locale.getDefault())
    if (!Character.isLetter(upperCase[0])
        || !Character.isLetter(upperCase[1])
    ) {
        return this
    }
    return StringBuilder().apply {
        append(String(Character.toChars(Character.codePointAt(upperCase, 0) - 65 + 127462)))

        append(String(Character.toChars(Character.codePointAt(upperCase, 1) - 65 + 127462)))
    }.toString()
}

/**
 * 转成json，暂时就放这里
 */
fun Any.toJson(): String = Gson().toJson(this)