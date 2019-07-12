package com.lmy.base.util
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * rx线层
 * CreateDate:2019/7/12
 * Author:lmy
 */
object RxSchedulersHelper {
    fun <T> ioMain(): ObservableTransformer<T, T> {
        return ObservableTransformer { upstream ->
            upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }
}