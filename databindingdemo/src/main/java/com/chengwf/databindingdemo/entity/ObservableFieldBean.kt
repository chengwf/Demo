package com.chengwf.databindingdemo.entity

import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt

data class ObservableFieldBean(val index: ObservableInt, val name: ObservableField<String>)