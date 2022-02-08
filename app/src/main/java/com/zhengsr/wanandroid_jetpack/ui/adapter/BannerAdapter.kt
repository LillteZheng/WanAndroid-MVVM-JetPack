package com.zhengsr.wanandroid_jetpack.ui.adapter

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.kevin.delegationadapter.extras.binding.BindingAdapterDelegate
import com.kevin.delegationadapter.extras.binding.BindingViewHolder
import com.zhengsr.wanandroid_jetpack.BR
import com.zhengsr.wanandroid_jetpack.R
import com.zhengsr.wanandroid_jetpack.bean.LoopBannerBean

/**
 * @author by zhengshaorui 2022/2/8
 * describe：轮播图代理类
 */
class BannerAdapter : BindingAdapterDelegate<LoopBannerBean>() {
    private  val TAG = "BannerAdapter"
    override val layoutRes = R.layout.bannerview


    override fun setVariable(binding: ViewDataBinding, item: LoopBannerBean, position: Int) {
       binding.setVariable(BR.banner,item)
    }


    override fun onItemClick(view: View, item: LoopBannerBean, position: Int) {
        super.onItemClick(view, item, position)
        Log.d(TAG, "zsr onItemClick: ")
    }
}