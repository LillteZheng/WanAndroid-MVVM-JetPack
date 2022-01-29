package com.zhengsr.wanandroid_jetpack.databindadapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.zhengsr.wanandroid_jetpack.R

/**
 * @author by zhengshaorui 2022/1/28
 * describe：
 */
object CommonBindingAdapter {
    @JvmStatic
    @BindingAdapter(value = ["imageUrl"])
    fun imageUrl(imageView: ImageView,url:String?){
        Glide.with(imageView)
            .load(url)
            .into(imageView)
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
}