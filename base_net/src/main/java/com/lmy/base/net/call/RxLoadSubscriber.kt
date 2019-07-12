package com.lmy.base.net.call

import android.content.Context
import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.lmy.base.net.AppLoadingDialog
import com.lmy.base.net.LoadCancelListener
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * CreateDate:2019/7/12
 * Author:lmy
 */
class RxLoadSubscriber<T> : LoadCancelListener, Observer<T> {

    var appLoadingDialog : AppLoadingDialog?=null

    companion object{
        const val TAG = "RxLoadSubscriber"
    }

    fun showDialog(mContext:Context){
        val fragmentActivity = mContext as? FragmentActivity
        if (fragmentActivity != null) {
            appLoadingDialog = AppLoadingDialog()
            appLoadingDialog?.setLoadingCancelListener(this)
            appLoadingDialog?.showDialog(fragmentActivity.supportFragmentManager)
        }
    }

    var disposable : Disposable?=null

    override fun onComplete() {
    }

    override fun onSubscribe(d: Disposable) {
        disposable = d
    }

    override fun onNext(t: T) {

        dismiss()
    }

    override fun onError(e: Throwable) {

        dismiss()
    }

    private fun dismiss(){
        appLoadingDialog?.dismissAllowingStateLoss()
        appLoadingDialog = null
        onCanceLoad()
    }

    override fun onCanceLoad() {
        disposable?.dispose()
        disposable=null
        Log.d(TAG,"请求销毁")
    }
}