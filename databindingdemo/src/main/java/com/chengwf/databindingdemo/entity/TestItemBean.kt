package com.chengwf.databindingdemo.entity

import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt

data class TestItemBean(val index: ObservableInt, val text: ObservableField<String>)