package com.lmy.base.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * super fragment
 * CreateDate:2019/6/24
 * Author:lmy
 */
abstract class BaseHomeFragment : SuperClassFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View?
        val createView = getCreateView()
        view = if (createView != 0) {
            inflater.inflate(createView, null)
        } else {
            getFragmentView()
        }
        return view ?: View(mContext)
    }


    abstract fun getCreateView(): Int

    protected open fun getFragmentView(): View? {
        return null
    }

}