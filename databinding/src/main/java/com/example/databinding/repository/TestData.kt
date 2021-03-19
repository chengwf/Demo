package com.example.databinding.repository

import androidx.lifecycle.LiveData

data class TestData(val index: Int, val desc: String) : LiveData<TestData>()