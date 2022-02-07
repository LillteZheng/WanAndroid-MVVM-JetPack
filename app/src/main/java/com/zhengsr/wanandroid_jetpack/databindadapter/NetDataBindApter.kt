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
       /* datas?.let {
            val viewGroup = bannerViewPager2.parent as ViewGroup
            //通过拿到父布局去加载 indicator
            val circleIndicator = viewGroup.findViewById<CircleIndicator>(R.id.banner_indicator)
            bannerViewPager2.addIndicator(circleIndicator)
            bannerViewPager2.setPageListener(R.layout.banner_item_layout,it,object:PageHelperListener<BannerBean>(){
                override fun bindView(view: View, data: BannerBean, position: Int) {
                    val imageView: ImageView = view.findViewById(R.id.banner_icon)
                    val title: String =
                        data.title.replace("&ldquo;", "\"").replace("&rdquo;", "\"")
                    setText(view, R.id.banner_text, title)
                    GlideApp.with(view)
                        .load(data.imagePath)
                        .dontAnimate()
                        .centerCrop()
                        .placeholder(R.drawable.ic_loading)
                        .error(R.drawable.ic_not_network)
                        .into(imageView)
                }

            })
            bannerViewPager2.startAnim()
        }*/
        datas?.let {
            bannerView.setData(datas)
        }
    }
}