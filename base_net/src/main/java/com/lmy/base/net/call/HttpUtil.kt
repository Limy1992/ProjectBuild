package com.lmy.base.net.call

import com.lmy.base.net.base.bean.BaseHttpResult
import com.lmy.base.util.RxSchedulersHelper
import io.reactivex.Observable


/**
 * CreateDate:2019/7/12
 * Author:lmy
 */
object HttpUtil {


    private var isShowDialog: Boolean = false
    private lateinit var observable: Observable<BaseHttpResult>

    fun create(observable: Observable<BaseHttpResult>):HttpUtil{
        this.observable = observable
        return this
    }

    fun showDialog(isShowDialog:Boolean):HttpUtil{
        this.isShowDialog = isShowDialog
        return this
    }

    fun subscriber(rxLoadSubscriber: RxLoadSubscriber<BaseHttpResult>){
        observable.compose(RxSchedulersHelper.ioMain())
            .doOnSubscribe {
                rxLoadSubscriber.onSubscribe(it)
                if (isShowDialog) {
                    rxLoadSubscriber.showDialog()
                }
            }.subscribe({

            },{

            },{

            },{

            })


    }




}