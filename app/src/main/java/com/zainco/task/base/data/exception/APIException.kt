package com.zainco.task.base.data.exception

data class APIException(val isSucceed: Boolean?, override var message: String) :
    RuntimeException()