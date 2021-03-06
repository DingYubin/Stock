package ruifu.com.shares.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

import ruifu.com.shares.BaseActivity;
import ruifu.com.shares.Global;
import ruifu.com.shares.MyApplication;
import ruifu.com.shares.R;

public class FragmentMainActivity extends BaseActivity implements OnClickListener
{
    private PortfolioFragment mTab01;
    private MarketsFragment mTab02;
    private NewsFragment mTab03;
    private MeFragment mTab04;
    private TranFragment mTab05;
    private Handler mHandler;
    SharedPreferences sp;
    private long exitTime = 0;
    /**
     * 底部四个按钮
     */
    private LinearLayout mTabBtnPortfolio;
    private LinearLayout mTabBtnNews;
    private LinearLayout mTabBtnMarkets;
    private LinearLayout mTabBtnMe;
    /**
     * 用于对Fragment进行管理
     */
    private FragmentManager fragmentManager;

    public void setHandler(Handler handler){
        this.mHandler = handler;
    }
    @Override
    public void setUpView() {
        mTabBtnPortfolio = (LinearLayout) findViewById(R.id.id_tab_bottom_portfolio);
        mTabBtnNews = (LinearLayout) findViewById(R.id.id_tab_bottom_news);
        mTabBtnMarkets = (LinearLayout) findViewById(R.id.id_tab_bottom_markets);
        mTabBtnMe = (LinearLayout) findViewById(R.id.id_tab_bottom_me);

        fragmentManager = getFragmentManager();
        setTabSelection(0);
    }

    @Override
    public void addListener() {
        mTabBtnPortfolio.setOnClickListener(this);
        mTabBtnNews.setOnClickListener(this);
        mTabBtnMarkets.setOnClickListener(this);
        mTabBtnMe.setOnClickListener(this);
    }

    @Override
    public void fillData() {

    }

    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        sp = getSharedPreferences("transaction_login", Activity.MODE_PRIVATE);
    }

//    @Override
//    public void onAttachFragment(Fragment fragment) {
//        super.onAttachFragment(fragment);
//        Log.i("FragmentMainActivity", "onAttachFragment");
//        if (mTab01 == null && fragment instanceof PortfolioFragment){
//            mTab01 = (PortfolioFragment) fragment;
//        }else if (mTab02 == null && fragment instanceof MarketsFragment){
//            mTab02 = (MarketsFragment) fragment;
//        }else if (mTab03 == null && fragment instanceof NewsFragment){
//            mTab03 = (NewsFragment) fragment;
//        }else if (mTab04 == null && fragment instanceof MeFragment ){
//            mTab04 = (MeFragment) fragment;
//        }else if (mTab05 == null && fragment instanceof TranFragment){
//            mTab05 = (TranFragment) fragment;
//        }
//    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.id_tab_bottom_portfolio:
                setTabSelection(0);
                break;
            case R.id.id_tab_bottom_news:
                setTabSelection(2);
                break;
            case R.id.id_tab_bottom_markets:
                setTabSelection(1);
                break;
            case R.id.id_tab_bottom_me:
                setTabSelection(3);
                break;

            default:
                break;
        }
    }

    /**
     * 根据传入的index参数来设置选中的tab页。
     *
     */
    @SuppressLint("NewApi")
    private void setTabSelection(int index)
    {
        // 重置按钮
        resetBtn();
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (index)
        {
            case 0:
                // 当点击了消息tab时，改变控件的图片和文字颜色
                ((ImageButton) mTabBtnPortfolio.findViewById(R.id.btn_tab_bottom_portfolio))
                        .setImageResource(R.drawable.portfolio_tabbar_btn_a_pressed);
                if (mTab01 == null)
                {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    mTab01 = new PortfolioFragment();
                    transaction.add(R.id.id_content,mTab01);
                } else
                {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(mTab01);
                }
                break;
            case 1:
                // 当点击了消息tab时，改变控件的图片和文字颜色
                ((ImageButton) mTabBtnMarkets.findViewById(R.id.btn_tab_bottom_markets))
                        .setImageResource(R.drawable.portfolio_tabbar_btn_c_pressed);

                if (mTab02 == null)
                {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    mTab02 = new MarketsFragment();
                    transaction.add(R.id.id_content, mTab02);
                } else
                {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(mTab02);
                }
                break;
            case 2:
                // 当点击了动态tab时，改变控件的图片和文字颜色
                ((ImageButton) mTabBtnNews.findViewById(R.id.btn_tab_bottom_news))
                        .setImageResource(R.drawable.portfolio_tabbar_btn_b_pressed);
                int result = sp.getInt("status", Global.FAILED);

                if (result== Global.SUCCESS){
                    if (mTab05 == null)
                    {
                        // 如果NewsFragment为空，则创建一个并添加到界面上
                        mTab05 = new TranFragment();
                        transaction.add(R.id.id_content, mTab05);
                    } else {
                        // 如果NewsFragment不为空，则直接将它显示出来
                        transaction.show(mTab05);
                    }
                }else {
                    if (mTab03 == null)
                    {
                        // 如果NewsFragment为空，则创建一个并添加到界面上
                        mTab03 = new NewsFragment();
                        transaction.add(R.id.id_content, mTab03);
                    } else
                    {
                        // 如果NewsFragment不为空，则直接将它显示出来
                        transaction.show(mTab03);
                    }
                }

                break;
            case 3:
                // 当点击了设置tab时，改变控件的图片和文字颜色
                ((ImageButton) mTabBtnMe.findViewById(R.id.btn_tab_bottom_me))
                        .setImageResource(R.drawable.portfolio_tabbar_btn_d_pressed);
                if (mTab04 == null)
                {
                    // 如果SettingFragment为空，则创建一个并添加到界面上
                    mTab04 = new MeFragment();
                    transaction.add(R.id.id_content, mTab04);
                } else
                {
                    // 如果SettingFragment不为空，则直接将它显示出来
                    transaction.show(mTab04);
                }
                break;
        }
        transaction.commit();
    }

    /**
     * 清除掉所有的选中状态。
     */
    private void resetBtn()
    {
        ((ImageButton) mTabBtnPortfolio.findViewById(R.id.btn_tab_bottom_portfolio))
                .setImageResource(R.drawable.portfolio_tabbar_btn_a_normal);
        ((ImageButton) mTabBtnNews.findViewById(R.id.btn_tab_bottom_news))
                .setImageResource(R.drawable.portfolio_tabbar_btn_b_normal);
        ((ImageButton) mTabBtnMarkets.findViewById(R.id.btn_tab_bottom_markets))
                .setImageResource(R.drawable.portfolio_tabbar_btn_c_normal);
        ((ImageButton) mTabBtnMe.findViewById(R.id.btn_tab_bottom_me))
                .setImageResource(R.drawable.portfolio_tabbar_btn_d_normal);
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction
     *            用于对Fragment执行操作的事务
     */
    @SuppressLint("NewApi")
    public void hideFragments(FragmentTransaction transaction)
    {
        if (mTab01 != null)
        {
            transaction.hide(mTab01);
        }
        if (mTab02 != null)
        {
            transaction.hide(mTab02);
        }
        if (mTab03 != null)
        {
            transaction.hide(mTab03);
        }
        if (mTab04 != null)
        {
            transaction.hide(mTab04);
        }
        if (mTab05 !=null)
        {
            transaction.hide(mTab05);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO 拦截后退按钮
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitApp();
            return true;
        }
        return false;
    }

    private void exitApp() {
        // TODO 退出
        if ((System.currentTimeMillis() - exitTime)>2000) {
            Toast.makeText(this, R.string.exit_Process, Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        }else {
            finishApp();
        }
    }

    private void finishApp() {
        // TODO 结束程序的代码
        MyApplication app = (MyApplication) getApplication();
        List<Activity> list = app.getActivityList();
        for (Activity activity : list) {
            activity.finish();
        }
        app = null;
        android.os.Process.killProcess(getTaskId());
    }
}
