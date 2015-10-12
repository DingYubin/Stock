package ruifu.com.shares.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import ruifu.com.shares.BaseFragment;
import ruifu.com.shares.R;
import ruifu.com.shares.adapter.PortfolioAdapter;

public class Fragment1 extends BaseFragment {
    public static BaseFragment newInstance(int index) {
        BaseFragment fragment = new Fragment3();
        Bundle args = new Bundle();
        args.putInt("index", index);
        fragment.setArguments(args);
        fragment.setIndex(index);
        return fragment;
    }

    private View layoutView;
    private FragmentTabHost mTabHost;
    private RecyclerView portfolioRecyclerView;
    private RecyclerView.LayoutManager portfolioLayoutManager;
    private PortfolioAdapter portfolioAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i("Fragment1", "onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("Fragment1", "onCreateView");
        layoutView = inflater.inflate(R.layout.fragment1,null);
        portfolioRecyclerView = (RecyclerView)layoutView.findViewById(R.id.stockListView);
        portfolioRecyclerView.setHasFixedSize(true);
        portfolioLayoutManager = new LinearLayoutManager(getContext());
        portfolioRecyclerView.setLayoutManager(portfolioLayoutManager);
        portfolioAdapter = new PortfolioAdapter(getContext());
        for (int i = 0; i < 50; i++) {
            portfolioAdapter.addStock("test");
        }
        portfolioRecyclerView.setAdapter(portfolioAdapter);
        return layoutView;
    }

    @Override
    public void onPause() {
        Log.i("Fragment1", "onPause");
        super.onPause();
    }
}