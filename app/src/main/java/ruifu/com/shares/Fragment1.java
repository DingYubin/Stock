package ruifu.com.shares;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dyb on 15/9/27.
 */
public class Fragment1 extends BaseFragment{
    public static BaseFragment newInstance(int index) {
        BaseFragment fragment = new Fragment1();
        Bundle args = new Bundle();
        args.putInt("index", index);
        fragment.setArguments(args);
        fragment.setIndex(index);
        return fragment;
    }

    private View layoutView;
    private FragmentTabHost mTabHost;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layoutView = inflater.inflate(R.layout.fragment1,null);
        return layoutView;
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
