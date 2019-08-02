package com.lmy.base.net.call

import android.annotation.SuppressLint
import com.lmy.base.net.base.bean.BaseHttpResult
import com.lmy.base.util.ActivityManger
import com.lmy.base.util.LifeCycleManger
import com.lmy.base.util.RxSchedulersHelper
import io.reactivex.Observable
import java.util.concurrent.TimeUnit


/**
 * CreateDate:2019/7/12
 * Author:lmy
 */
object HttpUtil {


    private var delayTime: Long= 0L
    private var cancelDialog: Boolean = true
    private var isShowDialog: Boolean = true
    private lateinit var observable: Observable<BaseHttpResult>

    fun create(observable: Observable<BaseHttpResult>): HttpUtil {
        this.observable = observable
        return this
    }

    fun showDialog(isShowDialog: Boolean): HttpUtil {
        this.isShowDialog = isShowDialog
        return this
    }

    fun cancleableDialog() {
        this.cancelDialog = false
    }

    fun delayLoad(delayTime:Long){
        this.delayTime = delayTime
    }

    @SuppressLint("CheckResult")
    fun subscriber(rxLoadSubscriber: RxLoadSubscriber<BaseHttpResult>) {
        observable.compose(RxSchedulersHelper.ioMain())
            .compose(LifeCycleManger.getDestroyLifeCycle())
            .delay(delayTime,TimeUnit.MILLISECONDS)
            .doOnSubscribe {
                rxLoadSubscriber.onSubscribe(it)
                if (isShowDialog) {
                    rxLoadSubscriber.showDialog(cancelDialog)
                }
            }.subscribe({
                rxLoadSubscriber.onNext(it as BaseHttpResult)
            }, {
                rxLoadSubscriber.onError(it)
            }, {
                rxLoadSubscriber.onComplete()
            }, {
            })
    }
}