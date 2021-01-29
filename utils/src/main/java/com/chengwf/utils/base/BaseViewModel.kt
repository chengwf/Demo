package me.redcircle.utils.base

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coder.zzq.smartshow.toast.SmartToast
import com.google.gson.JsonParseException
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import me.redcircle.utils.APIException
import me.redcircle.utils.enum.LoadStatus
import java.lang.NullPointerException
import java.net.ConnectException
import java.net.SocketTimeoutException

typealias Error = suspend (e: Exception) -> Unit

/**
 * ViewModel基类
 */
abstract class BaseViewModel : ViewModel() {

    open val loadStatusLiveData = MutableLiveData<LoadStatus>()

    /**
     * 使用协程处理网络请求
     */
    protected open fun launch(block: suspend () -> Unit, error: Error = {}): Job {
        return viewModelScope.launch {
            try {
                loadStatusLiveData.value = LoadStatus.LOADING
                block()
                loadStatusLiveData.value = LoadStatus.SUCCESS
            } catch (e: Exception) {
                parseException(e)
                onError()
                error(e)
            }
        }
    }

    /**
     * 界面对于异常的处理
     */
    protected open fun onError() {
    }

    /**
     * 处理异常情况
     */
    private fun parseException(e: Exception) {
        when (e) {
            is APIException -> {
                handleAPIException(e)
                loadStatusLiveData.value = LoadStatus.FAIL
            }
            is ConnectException -> {
                SmartToast.showInCenter("connection failed")
                Log.e("TAG_response ", " 连接失败，请检查网络 $e")

                loadStatusLiveData.value = LoadStatus.FAIL
            }
            is SocketTimeoutException -> {
//                SmartToast.show("请求超时，请检查网络")
                Log.e("TAG_response ", " 请求超时，请检查网络 $e")
                SmartToast.showInCenter("request timeout")
                loadStatusLiveData.value = LoadStatus.FAIL
            }
            is JsonParseException -> {
                e.message?.let { Log.e("TAG_response ", "JSON解析错误: $it ") }

                SmartToast.showInCenter("data exception")
                loadStatusLiveData.value = LoadStatus.FAIL
            }
            is NullPointerException -> {
                loadStatusLiveData.value = LoadStatus.NULL
            }
            else -> {
                e.message?.let { Log.e("TAG_response ", " $it ") }
                SmartToast.showInCenter("connection fail")
                loadStatusLiveData.value = LoadStatus.FAIL
            }
        }
    }

    private fun handleAPIException(e: APIException) {
        when (e.errorCode) {
            -1001 -> {
                Log.e("TAG_response ", " 未登录 ")
                SmartToast.showInCenter("请先登录")
            }
            else -> {
                // 其他错误
                Log.e("TAG_response ", "${e.errorCode} ${e.errorMsg}")
                SmartToast.showInCenter(e.errorMsg)
            }
        }
    }

    protected fun <T> async(block: suspend () -> T): Deferred<T> {
        return viewModelScope.async { block() }
    }
}
