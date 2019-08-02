package com.lmy.base.net

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

/**
 * AppLoading
 * CreateDate:2019/7/12
 * Author:lmy
 */
class AppLoadingDialog : DialogFragment(), DialogInterface.OnKeyListener {

    private var cancelable: Boolean? = true
    private var loadCancelListener: LoadCancelListener? = null

    companion object {
        const val APP_LOADING_TAG = "AppLoadingDialog"
    }

    fun setLoadingCancelListener(loadCancelListener: LoadCancelListener) {
        this.loadCancelListener = loadCancelListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_FRAME, R.style.APPDialog)
    }

    @SuppressLint("InflateParams")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.loading, null)
    }

    /**
     * 设置是否收到关闭dialog
     * @param cancelable false 禁止手动关闭
     */
    fun isCancelable(cancelable: Boolean) {
        this.cancelable = cancelable
        dialog?.setCancelable(cancelable)
    }

    override fun onStart() {
        super.onStart()

        dialog?.setCanceledOnTouchOutside(false)
        dialog?.setOnKeyListener(this)
        val window = dialog?.window
        window?.setGravity(Gravity.CENTER)
        window?.setLayout(dip2px(100f), dip2px(100f))
    }


    fun showDialog(fragmentManager: FragmentManager) {
        try {
            show(fragmentManager, APP_LOADING_TAG)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun showDialog(fragmentTransaction: FragmentTransaction) {
        try {
            show(fragmentTransaction, APP_LOADING_TAG)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onKey(dialog: DialogInterface?, keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (cancelable == true) {
                loadCancelListener?.onCanceLoad()
                loadCancelListener = null
            }
        }
        return false
    }


    override fun onDestroyView() {
        super.onDestroyView()
    }

    /**
     * dp转换
     */
    fun dip2px(dpValue: Float): Int {
        val scale = resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }
}