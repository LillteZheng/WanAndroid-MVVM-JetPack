package com.zhengsr.wanandroid_jetpack.ui;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.bumptech.glide.Glide;
import com.zhengsr.viewpagerlib.callback.PageHelperListener;
import com.zhengsr.viewpagerlib.indicator.CircleIndicator;
import com.zhengsr.viewpagerlib.view.BannerViewPager;
import com.zhengsr.viewpagerlib.view.BannerViewPager2;
import com.zhengsr.wanandroid_jetpack.BR;
import com.zhengsr.wanandroid_jetpack.R;
import com.zhengsr.wanandroid_jetpack.bean.BannerBean;
import com.zhengsr.wanandroid_jetpack.bean.LoopBannerBean;
import com.zhengsr.wanandroid_jetpack.bean.WebBean;
import com.zhengsr.wanandroid_jetpack.utils.GlideApp;

import java.util.List;
import java.util.function.BinaryOperator;

/**
 * Created by zhengshaorui
 * time: 2018/9/2
 */
public class BannerView extends FrameLayout {

    private static final String TAG = "BannerView";
    private View mView;
    private BannerViewPager2 mBannerViewPager;
    private ViewDataBinding dataBinding;

    public BannerView(@NonNull Context context) {
        this(context, null);
    }

    public BannerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        removeAllViews();
        //DataBindingUtil.inflate(LayoutInflater.from(getContext()),R.layout.banner_layout,this,false)
        mView = LayoutInflater.from(getContext()).inflate(R.layout.banner_layout, this, false);
        addView(mView);
       // mView.setTag("layout/lm_condition_holder_0");
        dataBinding = DataBindingUtil.bind(mView);
       // bind.setVariable(BR.bean,)
        Log.d(TAG, "zsr BannerView: "+mView);
        mBannerViewPager = mView.findViewById(R.id.banner);
        CircleIndicator indicator = mView.findViewById(R.id.banner_indicator);

        mBannerViewPager.addIndicator(indicator);
    }

    public void setData(LoopBannerBean beans){
        if (beans != null && beans.data != null) {
            Log.d(TAG, "zsr setData: "+dataBinding);
            mBannerViewPager.setPageListener(R.layout.banner_item_layout, beans.data, new PageHelperListener<BannerBean>() {
                @Override
                public void bindView(View view, BannerBean data, int position) {
                    /*String title = data.getTitle().replaceAll("&ldquo;", "\"").replaceAll("&rdquo;", "\"");
                    setText(view, R.id.banner_text, title);
                    ImageView imageView = view.findViewById(R.id.banner_icon);
                    GlideApp.with(view)
                            .load(data.getImagePath())
                            .dontAnimate()
                            .centerCrop()
                            .placeholder(R.drawable.ic_loading)
                            .error(R.drawable.ic_not_network)
                            .into(imageView);*/
                    ViewDataBinding bind = DataBindingUtil.bind(view);
                    if (bind != null) {
                        bind.setVariable(BR.bean,data);
                    }
                }

                /*@Override
                public void onItemClick(View view, BannerBean bean, int position) {
                    super.onItemClick(view, bean, position);
                    *//*WebBean webBean = new WebBean();
                    webBean.id = bean.getId();
                    webBean.title = bean.getTitle();
                    webBean.url = bean.getUrl();
                    webBean.isShowIcon = false;
                    Intent intent = new Intent(getContext(), WebViewActivity.class);
                    intent.putExtra("bean",webBean);
                    getContext().startActivity(intent);*//*
                }*/
            });
            mBannerViewPager.startAnim();

        }
    }




    public void startBanner(boolean startBanner) {
        if (startBanner) {
            mBannerViewPager.startAnim();
        } else {
            mBannerViewPager.stopAnim();
        }
    }


}
