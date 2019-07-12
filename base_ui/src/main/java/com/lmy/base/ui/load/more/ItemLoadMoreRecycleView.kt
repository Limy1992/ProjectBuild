package com.lmy.base.ui.load.more

import android.content.Context
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.lmy.base.util.ActivityManger
import com.lmy.base.ui.R
import com.lmy.base.util.ToastUtils

/**
 * recycleView 列表加载更多
 * CreateDate:2019/6/24
 * Author:lmy
 */
class ItemLoadMoreRecycleView(
    private val recycleView: RecyclerView? = null
    , private val baseQuickAdapter: BaseQuickAdapter<Any, BaseViewHolder>? = null
    , private val swipeRefreshLayout: SwipeRefreshLayout? = null
) : SwipeRefreshLayout.OnRefreshListener,
    BaseQuickAdapter.RequestLoadMoreListener {
    companion object {
        const val LOAD_SIZE = 20
    }

    private val emptyViewManger =  EmptyViewManger()

    private var mOnLoadMoreDataListener: OnLoadMoreDataListener? = null
    private var mContext: Context = ActivityManger.getContext()

    /**
     * 是否已加载数据
     */
    private var isLoadMore: Boolean = false
    /**
     * 是否刷新
     */
    private var isRefresh: Boolean = false
    /**
     * 是否加载错误
     */
    private var isLoadErr: Boolean = false
    /**
     * 当前加载页数 默认1
     */
    private var pagerNum = 1
    /**
     * 是否隐藏数据加载完毕，显示无更多数据的文本， 默认隐藏
     */
    private var isLoadMoreEnd = true

    /**
     * 加载成功
     * @param onLoadMoreDataListener 加载成功回调
     */
    fun setOnLoadMoreDataSuccessListener(onLoadMoreDataListener: OnLoadMoreDataListener) {
        mOnLoadMoreDataListener = null
        this.mOnLoadMoreDataListener = onLoadMoreDataListener
        isLoadErr = false
        //当前页数的个数
        val currentPagerDataCount = mOnLoadMoreDataListener?.getDataSize() ?: 0
        if (!isLoadMore) {
            isLoadMore = true
            mOnLoadMoreDataListener!!.onNewData()
            if (currentPagerDataCount > LOAD_SIZE) {
                baseQuickAdapter?.setOnLoadMoreListener(this, recycleView)
            }
            //设置空视图
            baseQuickAdapter?.emptyView = emptyViewManger.getEmptyView()
        } else {
            if (isRefresh) {
                isRefresh = false
                mOnLoadMoreDataListener?.onNewData()
                //设置空视图
                baseQuickAdapter?.emptyView = emptyViewManger.getEmptyView()
            } else {
                //加载更多
                if (currentPagerDataCount > 0) {
                    mOnLoadMoreDataListener?.onAddData()
                    baseQuickAdapter?.loadMoreComplete()
                } else {
                    //没有更多数据
                    baseQuickAdapter?.loadMoreEnd(isLoadMoreEnd)
                }
            }
        }
    }

    /**
     * 单列表，不包含加载更多
     */
    fun setOnLoadDataListener(onLoadDataListener:OnLoadDataListener){
        mOnLoadMoreDataListener = null
        this.mOnLoadMoreDataListener = onLoadDataListener
        isLoadErr = false
        mOnLoadMoreDataListener?.onNewData()
        if (mOnLoadMoreDataListener?.getDataSize() == 0) {
            val emptyView = baseQuickAdapter?.emptyView
            (emptyView as? FrameLayout)?.removeAllViews()
            //设置空视图
            baseQuickAdapter?.emptyView = emptyViewManger.getEmptyView()
        }
        swipeRefreshLayout?.isRefreshing = false
    }

    /**
     * 加载错误
     * @param onLoadDataErrListener 加载错误回调
     */
    fun setOnLoadMoreDataErrorListener(onLoadDataErrListener: OnLoadDataErrListener) {
        mOnLoadMoreDataListener = null
        this.mOnLoadMoreDataListener = onLoadDataErrListener
        isLoadErr = true
        if (isRefresh) {
            //刷新错误
            ToastUtils.showShortToast(R.string.base_refresh_err)
        } else {
            if (isLoadMore) {
                //加载更多错误
                baseQuickAdapter?.loadMoreFail()
            } else {
                //首次加载页面错误
                mOnLoadMoreDataListener?.onLoadErr()
            }
        }

        swipeRefreshLayout?.isRefreshing = false
    }

    /**
     * 刷新
     */
    override fun onRefresh() {
        if (!isLoadErr || isLoadMore) {
            pagerNum = 1
            isRefresh = true
            baseQuickAdapter?.setEnableLoadMore(false)
        }
        mOnLoadMoreDataListener?.onRequestData()
    }

    /**
     * BaseQuickAdapter 加载更多
     */
    override fun onLoadMoreRequested() {
        if (!isLoadErr) {
            swipeRefreshLayout?.isEnabled = false
            pagerNum += 1
        }
        mOnLoadMoreDataListener?.onRequestData()
    }
}