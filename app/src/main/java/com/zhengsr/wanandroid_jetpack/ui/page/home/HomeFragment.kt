package com.zhengsr.wanandroid_jetpack.ui.page.home

import android.util.Log
import com.kevin.delegationadapter.AdapterDelegate
import com.kevin.delegationadapter.DelegationAdapter
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshListener
import com.zhengsr.wanandroid_jetpack.BR
import com.zhengsr.wanandroid_jetpack.R
import com.zhengsr.wanandroid_jetpack.bean.ArticleData
import com.zhengsr.wanandroid_jetpack.bean.BannerBean
import com.zhengsr.wanandroid_jetpack.bean.LoopBannerBean
import com.zhengsr.wanandroid_jetpack.databinding.FragmentHomeBinding
import com.zhengsr.wanandroid_jetpack.net.getApi
import com.zhengsr.wanandroid_jetpack.ui.adapter.ArticleAdapter
import com.zhengsr.wanandroid_jetpack.ui.adapter.BannerAdapter
import com.zhengsr.wanandroid_jetpack.ui.base.BaseFragment
import com.zhengsr.wanandroid_jetpack.utils.linear
import com.zhengsr.wanandroid_jetpack.utils.scopeIo
import com.zhengsr.wanandroid_jetpack.utils.withMain
import okhttp3.internal.addHeaderLenient

class HomeFragment : BaseFragment<HomeViewModel>() {
    private val TAG = "HomeFragment"
    private val adapterDelegate  by lazy { DelegationAdapter()}
    override fun initViewModel() {
        state = getViewModel(HomeViewModel::class.java)

        state.scopeIo {
            val bannerData = getApi().getBanner().data


            val data = getApi().getArticle(0).data
            val topData = getApi().getTopArticle().data
            topData.addAll(data.datas)
            withMain {
                val bind = binding as FragmentHomeBinding
                adapterDelegate.addDelegate(BannerAdapter())
                adapterDelegate.addDelegate(ArticleAdapter())
                bind.rv.linear{
                    it.adapter = adapterDelegate
                    val loopBannerBean = LoopBannerBean()
                    loopBannerBean.data = bannerData
                    adapterDelegate.addHeaderItem(loopBannerBean)
                    adapterDelegate.addDataItems(topData)
                }

            }
        }
    }


    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_home,BR.vm,state)
            .addBindingParam(BR.click,ClickProxy())
    }


    override fun onResume() {
        super.onResume()
        val bind = binding as FragmentHomeBinding

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