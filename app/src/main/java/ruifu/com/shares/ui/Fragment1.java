package ruifu.com.shares.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import ruifu.com.shares.BaseFragment;
import ruifu.com.shares.R;
import ruifu.com.shares.adapter.PortfolioAdapter;
import ruifu.com.shares.entity.Stock;

public class Fragment1 extends BaseFragment implements View.OnClickListener {
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
        portfolioAdapter = new PortfolioAdapter(this, getContext());
        Stock[] stocks = new Stock[4];
        stocks[0] = new Stock("平安银行", "000001");
        stocks[0].setPrice(1235);
        stocks[0].setChange(25);
        stocks[1] = new Stock("招商银行", "600036");
        stocks[1].setPrice(1806);
        stocks[1].setChange(-71);
        stocks[2] = new Stock("五粮液", "000858");
        stocks[2].setPrice(2573);
        stocks[2].setDelist(true);
        stocks[3] = new Stock("中信银行", "601998");
        stocks[3].setPrice(318);
        stocks[3].setChange(0);
        for (int i = 0; i < 4; i++) {
            portfolioAdapter.addStock(stocks[i % 4]);
        }
        portfolioRecyclerView.setAdapter(portfolioAdapter);
        return layoutView;
    }

    @Override
    public void onPause() {
        Log.i("Fragment1", "onPause");
        super.onPause();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        Log.d("Fragment1", "create context for item: " + v.getId());
        menu.add(Menu.NONE, 1, v.getId(), "删除");
        menu.add(Menu.NONE, 2, v.getId(), "置顶");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Log.d("Fragment1", "onContextItemSelected, id: " + item.getItemId());
        switch (item.getItemId()) {
            case 1:
                Log.d("Fragment1", "delete operation");
                //Toast.makeText(this.getActivity(), "删除操作选中", Toast.LENGTH_LONG).show();
                portfolioAdapter.removeStock(item.getOrder());
                break;
            case 2:
                Log.d("Fragment1", "top operation");
                //Toast.makeText(this.getActivity(), "置顶操作选中", Toast.LENGTH_LONG).show();
                portfolioAdapter.moveStock(item.getOrder(), 0);
                break;
            default:
                return super.onContextItemSelected(item);
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        Log.d("Fragment1", "onClick event, position: " + v.getId());
        BaseFragment stockFragment = StockFragment.newInstance(getIndex(), portfolioAdapter.getStock(v.getId()));
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(this.getId(), stockFragment);
        getActivity().findViewById(R.id.main_radiogroup).setVisibility(View.INVISIBLE);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}