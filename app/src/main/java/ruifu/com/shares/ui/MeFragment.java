package ruifu.com.shares.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import ruifu.com.shares.BaseFragment;
import ruifu.com.shares.Global;
import ruifu.com.shares.R;
import ruifu.com.shares.util.NormalLoadPictrue;
import ruifu.com.shares.widget.CircularImage;


/**
 * Created by dyb on 15/9/27.
 */
public class MeFragment extends BaseFragment implements OnClickListener {
    private final static String TAG = "MeFragment";

    public static BaseFragment newInstance(int index) {
        BaseFragment fragment = new MeFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        fragment.setArguments(args);
        fragment.setIndex(index);
        return fragment;
    }
    
    private View layoutView;

    private RelativeLayout hk_account;
    private RelativeLayout us_account;
    private RelativeLayout system_settings;
    private RelativeLayout favorate_info;


    private TextView cover_user_name;
    CircularImage cover_user_photo;

    ScrollView scrollView;

    SharedPreferences sp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = getActivity().getSharedPreferences("users", Activity.MODE_PRIVATE);
//        Log.i("MeFragment","onCreate");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        Log.i("MeFragment","onCreateView");
        layoutView = inflater.inflate(R.layout.fragment_me,container,false);
        scrollView =  (ScrollView) layoutView.findViewById(R.id.scroll_view);

        cover_user_name = (TextView) layoutView.findViewById(R.id.cover_user_name);
        cover_user_photo = (CircularImage) layoutView.findViewById(R.id.cover_user_photo);

        hk_account = (RelativeLayout) layoutView.findViewById(R.id.market_pjhk);
        us_account = (RelativeLayout) layoutView.findViewById(R.id.marker_us);
        favorate_info = (RelativeLayout) layoutView.findViewById(R.id.favorate_info_layout);
        system_settings = (RelativeLayout) layoutView.findViewById(R.id.system_settings);

        hk_account.setOnClickListener(this);
        us_account.setOnClickListener(this);
        favorate_info.setOnClickListener(this);
        system_settings.setOnClickListener(this);
        cover_user_photo.setOnClickListener(this);



        return layoutView;
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("MeFragment", "onPause");

    }

    /**
     * 获取图片和名字
     * 1、网络获取
     * 2、缓存获取（还未实现）
     */
    @Override
    public void onStart() {
        super.onStart();
//        Log.i("MeFragment", "onStart");
        String headimgurl = sp.getString("headimgurl", "");
        String name = sp.getString("username","");
//        Log.i("MeFragment","headimgurl : " + headimgurl);
        if (headimgurl.equals("") && headimgurl.equals("")) {
            //如果获取的url为空，则默认头像
//            cover_user_photo.setImageResource(R.drawable.common_personal_defaultlogo);
            cover_user_name.setText(R.string.default_cover_user_name);
        }else{
            new NormalLoadPictrue().getPicture(headimgurl, cover_user_photo);
            cover_user_name.setTextColor(Color.BLACK);
            cover_user_name.setText(name);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.cover_user_photo:
                int result = sp.getInt("status",-1);
                if (result== Global.SUCCESS){
                    Intent intent = new Intent(getActivity(), ApproveActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.market_pjhk:
                Intent hkIntent = new Intent(getActivity(), HKAccountActivity.class);
                startActivity(hkIntent);
                break;
            case R.id.marker_us:
                Intent usIntent = new Intent(getActivity(), USAAccountActivity.class);
                startActivity(usIntent);
                break;
            case R.id.favorate_info_layout:
                Intent infoIntent = new Intent(getActivity(), InforCollectionActivity.class);
                startActivity(infoIntent);
                break;
            case R.id.system_settings:
                    Log.i(TAG,"系统设置");
                     Intent systemIntent = new Intent(getActivity(), SettingsMainActivity.class);
                     startActivity(systemIntent);
                break;
            default:
                break;

        }
    }
}
