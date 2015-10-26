package ruifu.com.shares.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import ruifu.com.shares.BaseFragment;
import ruifu.com.shares.R;
import ruifu.com.shares.swiperefresh.PullToRefreshBase;
import ruifu.com.shares.swiperefresh.PullToRefreshBase.OnRefreshListener;
import ruifu.com.shares.swiperefresh.PullToRefreshScrollView;

/**
 * Created by dyb on 15/9/27.
 */
public class MarketsFragment extends BaseFragment {
    public static BaseFragment newInstance(int index) {
        BaseFragment fragment = new MarketsFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        fragment.setArguments(args);
        fragment.setIndex(index);
        return fragment;
    }

    private View layoutView;
    private PullToRefreshScrollView mPullToRefreshScrollView;
    private ScrollView mScrollView;
    private TextView tv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layoutView = inflater.inflate(R.layout.fragment_markets,container,false);
        tv = (TextView) layoutView.findViewById(R.id.text);
        mPullToRefreshScrollView = (PullToRefreshScrollView) layoutView.findViewById(R.id.pull_refresh_scrollview);
        Log.i("dingyubin","markd");
        mPullToRefreshScrollView.setOnRefreshListener(new OnRefreshListener<ScrollView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
                Log.i("dingyubin","刷新------------------->");
                new GetDataTask().execute();
            }
        });
        mScrollView = mPullToRefreshScrollView.getRefreshableView();

        return layoutView;
    }

    /**
     * 测试用异步类
     */
    private class GetDataTask extends AsyncTask<Void, Void, String[]> {

        @Override
        protected String[] doInBackground(Void... params) {
            // Simulates a background job.
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
            }
            return null;
        }

        @Override
        protected void onPostExecute(String[] result) {
            // Do some stuff here

            // Call onRefreshComplete when the list has been refreshed.
            mPullToRefreshScrollView.onRefreshComplete();

            super.onPostExecute(result);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
