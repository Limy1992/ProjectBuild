package com.lmy.base.net.call

import android.content.Context
import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.google.gson.JsonSyntaxException
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import com.lmy.base.net.AppLoadingDialog
import com.lmy.base.net.LoadCancelListener
import com.lmy.base.net.R
import com.lmy.base.util.ToastUtils
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

/**
 * CreateDate:2019/7/12
 * Author:lmy
 */
abstract class RxLoadSubscriber<T> : LoadCancelListener, Observer<T> {

    var appLoadingDialog: AppLoadingDialog? = null

    companion object {
        const val TAG = "RxLoadSubscriber"
    }

    fun showDialog(mContext: Context) {
        val fragmentActivity = mContext as? FragmentActivity
        if (fragmentActivity != null) {
            appLoadingDialog = AppLoadingDialog()
            appLoadingDialog?.setLoadingCancelListener(this)
            appLoadingDialog?.showDialog(fragmentActivity.supportFragmentManager)
        }
    }

    var disposable: Disposable? = null

    override fun onComplete() {
    }

    override fun onSubscribe(d: Disposable) {
        disposable = d
    }

    override fun onNext(t: T) {
        onLoadSuccess(t)
        dismiss()
    }

    override fun onError(e: Throwable) {
        if (e.localizedMessage != null) {
            //code管理
        } else {
            exception(e)
        }
        dismiss()
    }

    /**
     * 异常错误提示
     */
    private fun exception(e: Throwable) {
        if (e is TimeoutException || e is SocketTimeoutException) {
            ToastUtils.showLongToast(R.string.net_time_out)
        } else if (e is SocketException || e is HttpException || e is UnknownHostException) {
            ToastUtils.showLongToast(R.string.net_err)
        } else if (e is JsonSyntaxException) {
            ToastUtils.showLongToast(R.string.net_json_err)
        } else {
            ToastUtils.showShortToast(R.string.net_other_err)
        }
        onLoadError(e.toString())
    }

    abstract fun onLoadSuccess(t:T)

    open fun onLoadError(errMsg: String) {}

    private fun dismiss() {
        appLoadingDialog?.dismissAllowingStateLoss()
        appLoadingDialog = null
        onCanceLoad()
    }

    override fun onCanceLoad() {
        disposable?.dispose()
        disposable = null
        Log.d(TAG, "请求销毁")
    }
}