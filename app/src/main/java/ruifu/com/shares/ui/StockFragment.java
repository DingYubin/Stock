package ruifu.com.shares.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ruifu.com.shares.BaseFragment;
import ruifu.com.shares.R;
import ruifu.com.shares.entity.Stock;

/**
 * Created by qun on 15-10-14.
 */
public class StockFragment extends BaseFragment {
    private static StockFragment instance = new StockFragment();
    public static BaseFragment newInstance(int index, Stock stock) {
        instance.setIndex(index);
        instance.setStock(stock);
        return instance;
    }
    private View layoutView;
    private Stock stock;

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("StockFragment", "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("StockFragment", "onCreateView");
        layoutView = inflater.inflate(R.layout.stock_page_layout,null);
        TextView stockTitle = (TextView)layoutView.findViewById(R.id.stockNameTitle);
        stockTitle.setText(stock.getName() + "(" + stock.getCode() + ")");
        return layoutView;
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("StockFragment", "onPause");

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("StockFragment", "onStart");
    }
}
