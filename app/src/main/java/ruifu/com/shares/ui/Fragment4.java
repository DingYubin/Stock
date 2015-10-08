package ruifu.com.shares.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import ruifu.com.shares.BaseFragment;
import ruifu.com.shares.R;
import ruifu.com.shares.util.NormalLoadPictrue;
import ruifu.com.shares.widget.CircularImage;

/**
 * Created by dyb on 15/9/27.
 */
public class Fragment4 extends BaseFragment implements OnClickListener {

    public static BaseFragment newInstance(int index) {
        BaseFragment fragment = new Fragment4();
        Bundle args = new Bundle();
        args.putInt("index", index);
        fragment.setArguments(args);
        fragment.setIndex(index);
        return fragment;
    }
    
    private View layoutView;
//    private ImageButton mImg;
    private FragmentTabHost mTabHost;
    CircularImage cover_user_photo;

    SharedPreferences sp;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = getActivity().getSharedPreferences("users", Activity.MODE_PRIVATE);
        Log.i("Fragment4","onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("Fragment4","onCreateView");
        layoutView = inflater.inflate(R.layout.fragment4,null);
        cover_user_photo = (CircularImage) layoutView.findViewById(R.id.cover_user_photo);
        cover_user_photo.setOnClickListener(this);

        return layoutView;
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("Fragment4", "onPause");

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("Fragment4", "onStart");
        String headimgurl = sp.getString("headimgurl", "");
        Log.i("Fragment4","headimgurl : " + headimgurl);
        if (headimgurl==null) {
            //如果获取的url为空，则默认头像
            cover_user_photo.setImageResource(R.drawable.head);
        }else{
            new NormalLoadPictrue().getPicture(headimgurl,cover_user_photo);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.cover_user_photo:
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
                break;
            default:
                break;

        }
    }
}
