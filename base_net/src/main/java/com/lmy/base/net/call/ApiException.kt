package com.lmy.base.net.call

import java.lang.RuntimeException

/**
 * code错误
 * CreateDate:2019/7/12
 * Author:lmy
 */
class ApiException(var resultCode:Int, var msg:String) : RuntimeException() {
    override fun getLocalizedMessage(): String {
        return msg
    }

    override val message: String?
        get() = resultCode.toString()
}