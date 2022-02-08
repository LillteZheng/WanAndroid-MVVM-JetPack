package com.zhengsr.wanandroid_jetpack.databindadapter

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import com.zhengsr.viewpagerlib.callback.PageHelperListener
import com.zhengsr.viewpagerlib.view.BannerViewPager2
import com.zhengsr.wanandroid_jetpack.bean.BannerBean


import android.widget.ImageView
import androidx.lifecycle.DefaultLifecycleObserver
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.zhengsr.viewpagerlib.indicator.CircleIndicator
import com.zhengsr.wanandroid_jetpack.R
import com.zhengsr.wanandroid_jetpack.ui.BannerView
import com.zhengsr.wanandroid_jetpack.utils.GlideApp


/**
 * @author by zhengshaorui 2022/1/28
 * describe：
 */
object NetDataBindApter{
    private const val TAG = "NetDataBindApter"
    @JvmStatic
    @BindingAdapter("banner")
    fun banner(bannerView: BannerView,datas: MutableList<BannerBean>?){
        datas?.let {
            //bannerView.setData(datas)
        }
    }
    @JvmStatic
    @BindingAdapter("bannerAnim")
    fun bannerAnim(bannerView: BannerView,startBanner:Boolean){
        bannerView.startBanner(startBanner)
    }
}