package ruifu.com.shares.ui;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ruifu.com.shares.BaseFragment;
import ruifu.com.shares.Global;
import ruifu.com.shares.R;
import ruifu.com.shares.biz.TransactionBiz;

/**
 * Created by dyb on 15/9/27.
 */
public class NewsFragment extends BaseFragment implements View.OnClickListener {

    public static BaseFragment newInstance(int index) {
        BaseFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        fragment.setArguments(args);
        fragment.setIndex(index);
        return fragment;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            hiderLoading();
            int resId = 0;
            switch (msg.what) {
                case Global.LOGIN_EMPTY:
                    resId = R.string.login_pwd_empty;
                    break;
                case Global.LOGIN_PASS_TOSHORT:
                    resId = R.string.login_pass_to_short;
                    break;
                case Global.SUCCESS:
                    resId = R.string.login_success;
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    mActivity.hideFragments(transaction);
                    TranFragment tranfragment = new TranFragment();

                    transaction.replace(R.id.id_content,tranfragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
//                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                    startActivity(intent);
//                    finish();
                    break;
                case Global.INVALID_REQUEST:
                    resId = R.string.login_fail;
                    break;
                case Global.FAILED:
                    resId = R.string.login_fail;
                    break;
                case Global.NET_PROBLEM:
                    resId = R.string.login_net;
                    break;
                default:
                    break;
            }
            if (resId != 0) {
                showToast(resId);
            }
            super.handleMessage(msg);
        }

    };

        private FragmentMainActivity mActivity;
        private TransactionBiz transaction;
        private View layoutView;

        private EditText et_login_name;
        private EditText et_login_pass;
        private Button bt_login;

        /*显示加载的提示信息*/
        public TextView tv_showLoadingText;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (FragmentMainActivity)activity;
        mActivity.setHandler(handler);
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            layoutView = inflater.inflate(R.layout.fragment_news, container, false);
            et_login_name = (EditText) layoutView.findViewById(R.id.nav_et_login_name);
            et_login_pass = (EditText) layoutView.findViewById(R.id.nav_et_login_pass);
            bt_login = (Button) layoutView.findViewById(R.id.nav_bt_confirm);

            mRloading = (RelativeLayout) layoutView.findViewById(R.id.rl_nav_loading);
            transaction = new TransactionBiz(getActivity());

            et_login_name.setOnClickListener(this);
            et_login_pass.setOnClickListener(this);
            bt_login.setOnClickListener(this);

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

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.nav_bt_confirm:
                    final String login = et_login_name.getText().toString().trim();
                    final String pwd = et_login_pass.getText().toString().trim();
                    login(login, pwd);
                    break;

                default:
                    break;
            }
        }

        private void login(final String login, final String pwd) {
            if (TextUtils.isEmpty(login) || TextUtils.isEmpty(pwd)) {
                handler.sendEmptyMessage(Global.LOGIN_EMPTY);
                return;
            }
            if (pwd.length() < 6 || pwd.length() > 40) {
                handler.sendEmptyMessage(Global.LOGIN_PASS_TOSHORT);
                return;
            }
            hiderKeyBoard();
            showLoading("正在验证登录...");
            new Thread() {
                @Override
                public void run() {
                    super.run();

                    try {
                        sleep(2000);
                        int result = transaction.login(login,pwd);
                        handler.sendEmptyMessage(result);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }.start();
        }

        private void showLoading(String text) {
            mRloading.setVisibility(View.VISIBLE);
            tv_showLoadingText = (TextView) layoutView.findViewById(R.id.tv_nav_loading);
            tv_showLoadingText.setText(text);
            AlphaAnimation aa = new AlphaAnimation(0.0f, 1.0f);
            ScaleAnimation sa = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f);
            AnimationSet set = new AnimationSet(false);
            set.addAnimation(sa);
            set.addAnimation(aa);
            set.setDuration(200);
        }

    /**
     * 隐藏软键盘
     */
    public void hiderKeyBoard(){
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        System.out.println(imm == null);
        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }


}