package com.chengwf.utils

import java.lang.RuntimeException

class APIException(val errorCode: Int, val errorMsg: String) : RuntimeException()