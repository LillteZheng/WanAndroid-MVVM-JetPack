package com.zhengsr.wanandroid_jetpack.databindadapter

import androidx.databinding.BindingAdapter
import com.scwang.smart.refresh.layout.SmartRefreshLayout

/**
 * @author by zhengshaorui 2022/2/8
 * describe：
 */
object SmartRefreshBindingAdapter {

    @JvmStatic
    @BindingAdapter("loadMore")
    fun loadMore(sr: SmartRefreshLayout,block:()->Unit){
        sr.setOnLoadMoreListener {
            block.invoke()
            sr.finishLoadMore(1500)
        }
    }

    @JvmStatic
    @BindingAdapter("refresh")
    fun refresh(sr: SmartRefreshLayout,block:()->Unit){
        sr.setOnRefreshListener {
            block.invoke()
            sr.finishRefresh(1500)
        }
    }

}