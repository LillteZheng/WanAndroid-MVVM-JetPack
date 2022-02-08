package com.zhengsr.wanandroid_jetpack.ui.adapter

import android.util.Log
import android.view.View
import androidx.databinding.ViewDataBinding
import com.kevin.delegationadapter.extras.binding.BindingAdapterDelegate
import com.kevin.delegationadapter.extras.binding.BindingViewHolder
import com.zhengsr.wanandroid_jetpack.BR
import com.zhengsr.wanandroid_jetpack.R
import com.zhengsr.wanandroid_jetpack.bean.ArticleData
import com.zhengsr.wanandroid_jetpack.bean.LoopBannerBean

/**
 * @author by zhengshaorui 2022/2/8
 * describe：轮播图代理类
 */
class ArticleAdapter : BindingAdapterDelegate<ArticleData>() {
    override val layoutRes = R.layout.item_article_recy_layout

    override fun setVariable(binding: ViewDataBinding, item: ArticleData, position: Int) {
        binding.setVariable(BR.bean,item)
    }

    override fun onItemClick(view: View, item: ArticleData, position: Int) {
        super.onItemClick(view, item, position)
    }
}