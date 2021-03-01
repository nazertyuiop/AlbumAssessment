package com.leboncoin.core.network

import java.lang.Exception
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

fun Exception.isNetworkRelated(): Boolean = this is ConnectException ||
        this is UnknownHostException ||
        this is SocketTimeoutException ||
        this is TimeoutException
