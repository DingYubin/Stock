package ruifu.com.shares.ui;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import ruifu.com.shares.BaseFragment;
import ruifu.com.shares.R;

/**
 * Created by dyb on 15/9/27.
 */
public class TranFragment extends BaseFragment implements View.OnClickListener {

    public static BaseFragment newInstance(int index) {
        BaseFragment fragment = new TranFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        fragment.setArguments(args);
        fragment.setIndex(index);
        return fragment;
    }

    private View layoutView;

    private BuyFragment buy;
    private SaleFragment sale;
    private RevokeFragment revoke;
    private PositionFragment position;
    private QueryFragment query;


    private LinearLayout my_tab_top_buy;
    private LinearLayout my_tab_top_salse;
    private LinearLayout my_tab_top_revoke;
    private LinearLayout my_tab_top_position;
    private LinearLayout my_tab_top_query;

    private FragmentManager fragmentManager;

    private MeFragment meFragment;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layoutView = inflater.inflate(R.layout.activity_tran,container,false);

        my_tab_top_buy = (LinearLayout) layoutView.findViewById(R.id.tab_top_buy);
        my_tab_top_salse = (LinearLayout) layoutView.findViewById(R.id.tab_top_salse);
        my_tab_top_revoke = (LinearLayout) layoutView.findViewById(R.id.tab_top_revoke);
        my_tab_top_position = (LinearLayout) layoutView.findViewById(R.id.tab_top_position);
        my_tab_top_query = (LinearLayout) layoutView.findViewById(R.id.tab_top_query);

        my_tab_top_buy.setOnClickListener(this);
        my_tab_top_salse.setOnClickListener(this);
        my_tab_top_revoke.setOnClickListener(this);
        my_tab_top_position.setOnClickListener(this);
        my_tab_top_query.setOnClickListener(this);
        fragmentManager = getFragmentManager();
        setTabSelection(0);
        return layoutView;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onPause() {
        super.onPause();
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
                ((ImageButton) my_tab_top_buy.findViewById(R.id.btn_tab_top_buy))
                        .setImageResource(R.drawable.transaction_tabbar_btn_a_pressed);
                if (buy == null)
                {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    buy = new BuyFragment();
                    transaction.add(R.id.id_content1,buy);
                } else
                {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(buy);
                }
                break;
            case 1:
                // 当点击了消息tab时，改变控件的图片和文字颜色
                ((ImageButton) my_tab_top_salse.findViewById(R.id.btn_tab_top_salse))
                        .setImageResource(R.drawable.transaction_tabbar_btn_b_normal_press);
                if (sale == null)
                {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    sale = new SaleFragment();
                    transaction.add(R.id.id_content1, sale);
                } else
                {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(sale);
                }
                break;
            case 2:
                // 当点击了动态tab时，改变控件的图片和文字颜色
                ((ImageButton) my_tab_top_revoke.findViewById(R.id.btn_tab_top_revoke))
                        .setImageResource(R.drawable.transaction_tabbar_btn_c_press);
                if (revoke == null)
                {
                    // 如果NewsFragment为空，则创建一个并添加到界面上
                    revoke = new RevokeFragment();
                    transaction.add(R.id.id_content1, revoke);
                } else
                {
                    // 如果NewsFragment不为空，则直接将它显示出来
                    transaction.show(revoke);
                }
                break;
            case 3:
                // 当点击了设置tab时，改变控件的图片和文字颜色
                ((ImageButton) my_tab_top_position.findViewById(R.id.btn_tab_top_position))
                        .setImageResource(R.drawable.transaction_tabbar_btn_d_normal_press);
                if (position == null)
                {
                    // 如果SettingFragment为空，则创建一个并添加到界面上
                    position = new PositionFragment();
                    transaction.add(R.id.id_content1, position);
                } else
                {
                    // 如果SettingFragment不为空，则直接将它显示出来
                    transaction.show(position);
                }
                break;
            case 4:
                // 当点击了设置tab时，改变控件的图片和文字颜色
                ((ImageButton) my_tab_top_query.findViewById(R.id.btn_tab_top_query))
                        .setImageResource(R.drawable.transaction_tabbar_btn_e_press);
                if (query == null)
                {
                    // 如果SettingFragment为空，则创建一个并添加到界面上
                    query = new QueryFragment();
                    transaction.add(R.id.id_content1, query);
                } else
                {
                    // 如果SettingFragment不为空，则直接将它显示出来
                    transaction.show(query);
                }
                break;
        }
        transaction.commit();
    }

        private void resetBtn()
        {
            ((ImageButton) my_tab_top_buy.findViewById(R.id.btn_tab_top_buy))
                    .setImageResource(R.drawable.transaction_tabbar_btn_a_normal);
            ((ImageButton) my_tab_top_salse.findViewById(R.id.btn_tab_top_salse))
                    .setImageResource(R.drawable.transaction_tabbar_btn_b_normal);
            ((ImageButton) my_tab_top_revoke.findViewById(R.id.btn_tab_top_revoke))
                    .setImageResource(R.drawable.transaction_tabbar_btn_c_normal);
            ((ImageButton) my_tab_top_position.findViewById(R.id.btn_tab_top_position))
                    .setImageResource(R.drawable.transaction_tabbar_btn_d_normal);
            ((ImageButton) my_tab_top_query.findViewById(R.id.btn_tab_top_query))
                    .setImageResource(R.drawable.transaction_tabbar_btn_e_normal);
        }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction
     *            用于对Fragment执行操作的事务
     */
    @SuppressLint("NewApi")
    private void hideFragments(FragmentTransaction transaction)
    {
        if (buy != null)
        {
            transaction.hide(buy);
        }
        if (sale != null)
        {
            transaction.hide(sale);
        }
        if (revoke != null)
        {
            transaction.hide(revoke);
        }
        if (position != null)
        {
            transaction.hide(position);
        }
        if (query != null)
        {
            transaction.hide(query);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.tab_top_buy:
                setTabSelection(0);
                break;
            case R.id.tab_top_salse:
                setTabSelection(1);
                break;
            case R.id.tab_top_revoke:
                setTabSelection(2);
                break;
            case R.id.tab_top_position:
                setTabSelection(3);
                break;
            case R.id.tab_top_query:
                setTabSelection(4);
                break;
            default:
                break;
        }
    }
}
