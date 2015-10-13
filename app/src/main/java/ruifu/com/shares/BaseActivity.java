package ruifu.com.shares;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2015/10/9.
 */
public abstract class BaseActivity extends Activity{
    public MyApplication app;
    public RelativeLayout mRloading;
    /*显示加载的提示信息*/
    public TextView tv_showLoadingText;
    /**
     * 初始化界面
     */
    public abstract void setUpView();

    /**
     * 增加监听
     */
    public abstract void addListener();

    /**
     * 填充数据
     */
    public abstract void fillData();



    /**
     * 显示加载的提示信息
     * @param text 友好的提示信息
     */
    public void showLoading(String text){
        mRloading.setVisibility(View.VISIBLE);
        tv_showLoadingText = (TextView) findViewById(R.id.tv_nav_loading);
        tv_showLoadingText.setText(text);
        AlphaAnimation aa = new AlphaAnimation(0.0f, 1.0f);
        ScaleAnimation sa = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f);
        AnimationSet set = new AnimationSet(false);
        set.addAnimation(sa);
        set.addAnimation(aa);
        set.setDuration(200);
        //暂时不使用动画
        //mRloading.setAnimation(set);
        //mRloading.startAnimation(set);
    }

    public void hiderLoading(){
        mRloading.setVisibility(View.INVISIBLE);
    }

    public void showToast(String string,int time){
        Toast.makeText(this, string, time).show();
    }
    public void showToast(int resId ){
        Toast.makeText(this,resId,Toast.LENGTH_SHORT).show();
    }
    public void showToast(int resId,int time){
        Toast.makeText(this, resId ,time).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (MyApplication) getApplication();
        app.getActivityList().add(this);
        setUpView();
        fillData();
        addListener();
    }

    /**
     * 隐藏软键盘
     */
    public void hiderKeyBoard(){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        System.out.println(imm==null);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
    /**
     * 显示软键盘
     */
    public void showKeyBoard(){
        InputMethodManager imm =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInputFromInputMethod(getCurrentFocus().getWindowToken(), InputMethodManager.RESULT_SHOWN);
    }

    public void goToNext(Context packageContext,Class<?> cls,Bundle extras){
        goToFinish(packageContext, cls, extras);
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
    }

    public void goBackFinish(){
        finish();
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
    }

    public void goToFinish(Context packageContext,Class<?> cls,Bundle extras){
        Intent intent = new Intent(packageContext,cls);
        //该Intent传递了数据
        if (extras!=null) {
            intent.putExtras(extras);
        }
        startActivity(intent);
        finish();
    }

}
