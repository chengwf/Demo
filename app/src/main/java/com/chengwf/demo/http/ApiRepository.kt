package com.chengwf.demo.http

import com.chengwf.utils.RetrofitFactory

/**
 * 继承这个抽象类就可以了
 */
abstract class ApiRepository {
    protected val apiService: APIService by lazy {
        RetrofitFactory.instance.create(APIService::class.java)
    }
}