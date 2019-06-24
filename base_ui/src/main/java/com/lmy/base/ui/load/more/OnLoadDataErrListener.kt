package com.lmy.base.ui.load.more

/**
 * 加载错误
 * CreateDate:2019/6/24
 * Author:lmy
 */
interface OnLoadDataErrListener : OnLoadMoreDataListener {
    override fun onAddData() {
    }

    override fun onNewData() {
    }

    override fun onLoadErr() {
        super.onLoadErr()
    }
}