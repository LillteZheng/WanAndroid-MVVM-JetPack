package com.zhengsr.wanandroid_jetpack.ui.page.home

import android.content.Context
import android.net.wifi.WifiManager
import android.util.Log
import android.view.View
import com.kevin.delegationadapter.DelegationAdapter
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshListener
import com.zhengsr.wanandroid_jetpack.BR
import com.zhengsr.wanandroid_jetpack.R
import com.zhengsr.wanandroid_jetpack.databinding.FragmentHomeBinding
import com.zhengsr.wanandroid_jetpack.net.getApi
import com.zhengsr.wanandroid_jetpack.ui.adapter.ArticleAdapter
import com.zhengsr.wanandroid_jetpack.ui.adapter.BannerAdapter
import com.zhengsr.wanandroid_jetpack.ui.base.BaseNetFragment
import com.zhengsr.wanandroid_jetpack.ui.viewmodel.HomeViewModel
import com.zhengsr.wanandroid_jetpack.utils.linear
import com.zhengsr.wanandroid_jetpack.utils.scopeIo
import dev.utils.app.BarUtils
import dev.utils.app.NetWorkUtils
import dev.utils.app.wifi.WifiUtils


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
            state.pageNum.value?.let { num->
                if (num == 0){
                    adapterDelegate.setDataItems(it)
                }else{
                    adapterDelegate.addDataItems(it)
                }
            }

            showSuccess(1500)
        }
        state.failStatus.listener {
            if (it){
                showError()
            }
        }

        state.onRefresh()
    }

    override fun reload() {
        super.reload()
        showLoading()
        state.onRefresh()
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

}