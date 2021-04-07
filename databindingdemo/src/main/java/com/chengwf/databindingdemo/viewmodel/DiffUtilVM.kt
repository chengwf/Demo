package com.chengwf.databindingdemo.viewmodel

import androidx.lifecycle.MutableLiveData
import com.chengwf.utils.base.BaseViewModel

class DiffUtilVM : BaseViewModel() {
    val listLiveData = MutableLiveData<ArrayList<String>>()

    private val mList = ArrayList<String>()
    fun buildTestData() {
        mList.clear()
        (0..5).forEach {
            mList.add("第${it}行")
        }
        listLiveData.postValue(mList)
    }

    fun addListTestData() {
        repeat((0..3).count()) {
            mList.add("第${mList.size}行")
        }

        listLiveData.postValue(mList)
    }

    fun addTestData() {
        mList.add("第${mList.size}行")
        listLiveData.postValue(mList)
    }
}