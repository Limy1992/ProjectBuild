package com.lmy.base.ui.fragment

/**
 * CreateDate:2019/6/24
 * Author:lmy
 */
abstract class BaseFragment : BaseHomeFragment() {
    override fun onStart() {
        super.onStart()
    }

    abstract fun initView()
    abstract fun initData()
}