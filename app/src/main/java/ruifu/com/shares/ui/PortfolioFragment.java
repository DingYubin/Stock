package ruifu.com.shares.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import ruifu.com.shares.BaseFragment;
import ruifu.com.shares.R;
import ruifu.com.shares.adapter.PortfolioAdapter;
import ruifu.com.shares.entity.StockEntity;

public class PortfolioFragment extends BaseFragment implements View.OnClickListener {
    public static BaseFragment newInstance(int index) {
        BaseFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        fragment.setArguments(args);
        fragment.setIndex(index);
        return fragment;
    }

    private View layoutView;
    private RecyclerView portfolioRecyclerView;
    private RecyclerView.LayoutManager portfolioLayoutManager;
    private PortfolioAdapter portfolioAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i("PortfolioFragment", "onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("PortfolioFragment", "onCreateView");
        layoutView = inflater.inflate(R.layout.fragment_portfolio,container,false);
        portfolioRecyclerView = (RecyclerView)layoutView.findViewById(R.id.stockListView);
        portfolioRecyclerView.setHasFixedSize(true);
        portfolioLayoutManager = new LinearLayoutManager(getActivity());
        portfolioRecyclerView.setLayoutManager(portfolioLayoutManager);
        portfolioAdapter = new PortfolioAdapter(this,getActivity());
        StockEntity[] stockEntities = new StockEntity[11];
        stockEntities[0] = new StockEntity("平安银行", "000001");
        stockEntities[1] = new StockEntity("招商银行", "600036");
        stockEntities[2] = new StockEntity("五粮液", "000858");
        stockEntities[3] = new StockEntity("中信银行", "601998");
        stockEntities[4] = new StockEntity("中国神华", "601088");
        stockEntities[5] = new StockEntity("雅戈尔", "600177");
        stockEntities[6] = new StockEntity("长安汽车", "000625");
        stockEntities[7] = new StockEntity("伊利股份", "600887");
        stockEntities[8] = new StockEntity("苏宁云商", "002024");
        stockEntities[9] = new StockEntity("华侨城A", "000069");
        stockEntities[10] = new StockEntity("TCL集团", "000100");
        for (int i = 0; i < stockEntities.length; i++) {
            portfolioAdapter.addStock(stockEntities[i]);
        }
        portfolioRecyclerView.setAdapter(portfolioAdapter);
        return layoutView;
    }

    @Override
    public void onPause() {
        Log.i("PortfolioFragment", "onPause");
        super.onPause();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        Log.d("PortfolioFragment", "create context for item: " + v.getId());
        menu.add(Menu.NONE, 1, v.getId(), "删除");
        menu.add(Menu.NONE, 2, v.getId(), "置顶");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Log.d("PortfolioFragment", "onContextItemSelected, id: " + item.getItemId());
        switch (item.getItemId()) {
            case 1:
                Log.d("PortfolioFragment", "delete operation");
                //Toast.makeText(this.getActivity(), "删除操作选中", Toast.LENGTH_LONG).show();
                portfolioAdapter.removeStock(item.getOrder());
                break;
            case 2:
                Log.d("PortfolioFragment", "top operation");
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
        Log.d("PortfolioFragment", "onClick event, position: " + v.getId());
        Intent intent = new Intent(getActivity(), StockActivity.class);
        intent.putExtra("stock.current", portfolioAdapter.getStock(v.getId()));
        startActivity(intent);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("PortfolioFragment", "onStart");
    }
}