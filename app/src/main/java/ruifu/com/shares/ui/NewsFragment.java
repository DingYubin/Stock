package ruifu.com.shares.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ruifu.com.shares.BaseFragment;
import ruifu.com.shares.R;

/**
 * Created by dyb on 15/9/27.
 */
public class NewsFragment extends BaseFragment {
    public static BaseFragment newInstance(int index) {
        BaseFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        fragment.setArguments(args);
        fragment.setIndex(index);
        return fragment;
    }

    private View layoutView;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layoutView = inflater.inflate(R.layout.fragment_news,container,false);
        return layoutView;
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
