package com.lmy.base.ui.load.more

/**
 * 单列表加载，不包含加载更多
 * CreateDate:2019/6/24
 * Author:lmy
 */
interface OnLoadDataListener : OnLoadMoreDataListener {
    override fun onAddData() {
    }

    //默认=-1 代表不设置空视图
    override fun getDataSize(): Int {
        return -1
    }

    override fun onLoadErr() {
        super.onLoadErr()
    }
}