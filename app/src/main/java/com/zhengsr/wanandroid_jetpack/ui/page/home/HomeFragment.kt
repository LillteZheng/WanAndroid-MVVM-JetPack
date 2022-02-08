package com.zhengsr.wanandroid_jetpack.ui.page.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kevin.delegationadapter.DelegationAdapter
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshListener
import com.zhengsr.wanandroid_jetpack.BR
import com.zhengsr.wanandroid_jetpack.R
import com.zhengsr.wanandroid_jetpack.databinding.FragmentHomeBinding
import com.zhengsr.wanandroid_jetpack.ui.adapter.ArticleAdapter
import com.zhengsr.wanandroid_jetpack.ui.adapter.BannerAdapter
import com.zhengsr.wanandroid_jetpack.ui.base.BaseNetFragment
import com.zhengsr.wanandroid_jetpack.utils.linear


class HomeFragment : BaseNetFragment<HomeViewModel>() {
    private val TAG = "HomeFragment"
    private val adapterDelegate  by lazy { DelegationAdapter()}
    override fun initViewModel() {
        state = getViewModel(HomeViewModel::class.java)
    }


    override fun initData(view: View?) {
        super.initData(view)
        val bind = binding as FragmentHomeBinding
        adapterDelegate.addDelegate(BannerAdapter())
        adapterDelegate.addDelegate(ArticleAdapter())
        bind.rv.linear{
            it.adapter = adapterDelegate
        }
        showLoading()
        listenerData()
    }

    private fun listenerData() {
        state.bannerList.listener {
            adapterDelegate.setHeaderItem(it)
        }
        state.articleList.listener {
            adapterDelegate.setDataItems(it)
            showSuccess(1500)
        }
        state.loadMoreArticleList.listener {
            adapterDelegate.addDataItems(it)
        }
        state.onRefresh()
    }

    override fun onResume() {
        super.onResume()

    }


    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_home,BR.vm,state)
            .addBindingParam(BR.click,ClickProxy())
    }



    override fun onStart() {
        super.onStart()
        state.bannerAnim.set(true)
    }

    override fun onPause() {
        super.onPause()
        state.bannerAnim.set(false)
    }



   inner class ClickProxy {
        fun openMenu() {
            //打开侧边栏
            event.drawerOpenOrClose.value = true
        }
    }
    inner class RefreshListener : OnRefreshListener{
        override fun onRefresh(refreshLayout: RefreshLayout) {
            TODO("Not yet implemented")
        }

    }
}