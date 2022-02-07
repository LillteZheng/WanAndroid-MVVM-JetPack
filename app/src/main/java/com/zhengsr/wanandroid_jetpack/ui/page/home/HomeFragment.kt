package com.zhengsr.wanandroid_jetpack.ui.page.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.drake.brv.utils.BRV
import com.drake.brv.utils.bindingAdapter
import com.drake.brv.utils.linear
import com.drake.brv.utils.setup
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.zhengsr.wanandroid_jetpack.BR
import com.zhengsr.wanandroid_jetpack.R
import com.zhengsr.wanandroid_jetpack.bean.ArticleData
import com.zhengsr.wanandroid_jetpack.bean.BannerBean
import com.zhengsr.wanandroid_jetpack.databinding.FragmentHomeBinding
import com.zhengsr.wanandroid_jetpack.databinding.FragmentHomeBindingImpl
import com.zhengsr.wanandroid_jetpack.net.HttpHolder
import com.zhengsr.wanandroid_jetpack.net.getApi
import com.zhengsr.wanandroid_jetpack.ui.base.BaseFragment
import com.zhengsr.wanandroid_jetpack.utils.scopeIo
import com.zhengsr.wanandroid_jetpack.utils.withMain

class HomeFragment : BaseFragment<HomeViewModel>() {
    private val TAG = "HomeFragment"
    override fun initViewModel() {
        state = getViewModel(HomeViewModel::class.java)
        BRV.modelId = BR.vm
        BRV.modelId = BR.bean
        state.scopeIo {
            val bannerData = getApi().getBanner().data
            state.bannerBeans.postValue(bannerData)
            val data = getApi().getArticle(0).data
            val topData = getApi().getTopArticle().data
            topData.addAll(data.datas)
            withMain {
                val bind = binding as FragmentHomeBinding
                bind.rv.linear().setup {
                    addType<ArticleData>(R.layout.item_article_recy_layout)
                }.models = topData

            }
        }


    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_home,BR.vm,state)
            .addBindingParam(BR.click,ClickProxy())
    }


    override fun onResume() {
        super.onResume()
       // val bind = binding as FragmentHomeBinding
    }



   inner class ClickProxy {
        fun openMenu() {
            //打开侧边栏
            event.drawerOpenOrClose.value = true
        }
    }
}