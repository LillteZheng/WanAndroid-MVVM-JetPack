package com.zhengsr.wanandroid_jetpack.ui.window;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

public class CusDialog {
    private static final String TAG = "CusDialog";
    private final Mydialog mDialog;
    private CusDialog(Builder builder){
        mDialog = new Mydialog(builder.context,builder);
        mDialog.show(); //必须先show，否则 create 不会创建，所以 contentview 会为null,这里id就获取不到了
    }

    public void show(){
        if (mDialog != null){
            mDialog.show();
        }
    }
    public void dismiss(){
        if (mDialog != null){
            mDialog.dismiss();
        }
    }

    public  <T extends View> T getViewbyId(int id){

        if (mDialog != null){
            return (T) mDialog.getContentView().findViewById(id);
        }
        return null;


    }

    public Mydialog getDialog() {
        return mDialog;
    }

    public boolean isShowing(){
        return mDialog.isShowing();
    }

    public CusDialog setVisible(int viewid, boolean visiable){

        getViewbyId(viewid).setVisibility(visiable?View.VISIBLE:View.GONE);
        return this;
    }

    public CusDialog setTextView(int viewid, String msg){
        TextView textView = getViewbyId(viewid);
        if (msg != null) {
            textView.setText(msg);
        }
        return this;
    }

    public CusDialog setClickDismissView(int viewid){
        View view = getViewbyId(viewid);
        if (view != null) {
            view.setOnClickListener(v -> {
                dismiss();
            });
        }
        return this;
    }


    public CusDialog setImageview(int viewid, String path){
        //TextView textView = getViewbyId(viewid);
        ImageView imageView = getViewbyId(viewid);
        imageView.setImageBitmap(BitmapFactory.decodeFile(path));

        return this;
    }

    public CusDialog setEnable(int viewid, boolean enable){
        getViewbyId(viewid).setEnabled(enable);
        return this;
    }
    public CusDialog setOnClickListener(int viewid, View.OnClickListener listener){
        View view = getViewbyId(viewid);
        view.setClickable(true);
        view.setOnClickListener(listener);
        return this;
    }
    public CusDialog setOnCancelListener(DialogInterface.OnCancelListener litener){
        if (mDialog != null){
            mDialog.setOnCancelListener(litener);
        }
        return this;
    }


    public static class Builder{
        private int layoutid = -1;
        private boolean outsideDimiss;
        private Context context;
        private int width;
        private int height;
        private int styleanim;
        private View view;
        private int gravity = -1;
        private boolean isSystem = false;
        private boolean showBackground = true;
        private boolean disableBackKey;
        private boolean isDataBinding;
        public Builder setContext(Context context){
            this.context = context;
            return this;
        }

        public Builder setLayoutId(int layoutid){
            this.layoutid = layoutid;
            return this;
        }

        public Builder isSystem(boolean issystem){
            this.isSystem = issystem;
            return this;
        }

        public Builder setLayoutView(View view){
            this.view = view;
            return this;
        }
        public Builder setWidth(int width){
            this.width = width;
            return this;
        }
        public Builder setHeight(int height){
            this.height = height;
            return this;
        }

        public Builder setAnimation(int styleanim){
            this.styleanim = styleanim;
            return this;
        }
        public Builder setOutSideDimiss(boolean outSideDimiss){
            this.outsideDimiss = outSideDimiss;
            return this;
        }

        public Builder setGravity(int gravity){
            this.gravity = gravity;
            return this;
        }

        public Builder showBackground(boolean showBackground){
            this.showBackground = showBackground;
            return this;
        }

        public Builder disableBackKey(boolean disableBackKey){
            this.disableBackKey = disableBackKey;
            return this;
        }

        public Builder useDataBinding(boolean dataBinding){
            this.isDataBinding = dataBinding;
            return this;
        }


        public CusDialog builder(){
            CusDialog dialog = new CusDialog(this);
            return dialog;
        }
    }




    class Mydialog extends Dialog {

        Builder builder;
        View contentview;
        ViewDataBinding dataBinding;
        public Mydialog(Context context, Builder builder) {
            super(context);
            this.builder = builder;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            //配置 dialog 的window 参数
            Window window = getWindow();
            if (builder.gravity != -1){
                window.setGravity(builder.gravity);
            }

            if(builder.isSystem){
                window.setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
            }
            if (builder.styleanim != 0) {
                window.setWindowAnimations(builder.styleanim);
            }
            window.setBackgroundDrawableResource(android.R.color.transparent);
            if(builder.layoutid != -1){
                if (builder.isDataBinding){
                    dataBinding = DataBindingUtil.inflate(LayoutInflater.from(builder.context), builder.layoutid,
                            null, false);
                    this.contentview = dataBinding.getRoot();
                }else {
                    this.contentview = LayoutInflater.from(builder.context).
                            inflate(this.builder.layoutid, null);
                }
            }
            if (builder.view != null){
                this.contentview = builder.view;
            }


            setContentView( this.contentview);

            //设置位置大小的参数
            WindowManager.LayoutParams lp = window.getAttributes();
            if (builder.width != 0){
                lp.width = builder.width;

            }
            if (builder.height!=0) {
                lp.height = builder.height;
            }
            if (!builder.showBackground){
                lp.dimAmount = 0.0f;
            }
            if (builder.disableBackKey){
                setCancelable(false);
            }

            getWindow().setAttributes(lp);
            setCanceledOnTouchOutside(this.builder.outsideDimiss);// 点击Dialog外部消失
        }


        public View getContentView(){
            return  this.contentview;
        }


    }


}