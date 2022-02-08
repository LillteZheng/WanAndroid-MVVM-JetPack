package com.zhengsr.wanandroid_jetpack.databindadapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshListener
import com.zhengsr.wanandroid_jetpack.R
import com.zhengsr.wanandroid_jetpack.utils.GlideApp

/**
 * @author by zhengshaorui 2022/1/28
 * describe：
 */
object CommonBindingAdapter {
    @JvmStatic
    @BindingAdapter(value = ["imageUrl","placeholder","errorholder"], requireAll = false)
    fun imageUrl(imageView: ImageView,url:String?,placeholder:Int?,errorHolder:Int?){
        val place = placeholder?:R.drawable.ic_loading
        val error = errorHolder?:R.drawable.ic_not_network
        GlideApp.with(imageView)
            .load(url)
            .dontAnimate()
            .centerCrop()
            .placeholder(place)
            .error(error)
            .into(imageView);
    }
    @JvmStatic
    @BindingAdapter(value = ["textMsg","textRes"], requireAll = false)
    fun text(view: TextView,msg:String?,res:Int?){
        val text = msg?.let {
            it
        }?:res?.let {
            view.context.getString(it)
        }
        text?.let {
            view.text = text
        }
    }

    @JvmStatic
    @BindingAdapter("collected")
    fun collected(imageView: ImageView,isCollected:Boolean){
        if (isCollected){
            imageView.setImageResource(R.drawable.icon_like_article_select)
        }else{
            imageView.setImageResource(R.drawable.icon_like_article_not_selected)
        }
    }

    @JvmStatic
    @BindingAdapter("refreshListener")
    fun refreshListener(smartRefreshLayout: SmartRefreshLayout,listener:OnRefreshListener?){
        listener?.let {
            smartRefreshLayout.setOnRefreshListener(listener)
        }
    }
}