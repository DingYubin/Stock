package ruifu.com.shares.ui;

import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
        layoutView = inflater.inflate(R.layout.fragment_stock_page,null);
        layoutView.findViewById(R.id.returnTextView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getFragmentManager();
                if (fragmentManager != null)
                    fragmentManager.popBackStack();
            }
        });
        layoutView.findViewById(R.id.refreshTextView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }
        });
        TextView stockTitle = (TextView)layoutView.findViewById(R.id.stockNameTitle);
        stockTitle.setText(stock.getName() + "(" + stock.getCode() + ")");
        refresh();
        return layoutView;
    }

    private void refresh() {
        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("MM-dd HH:mm:ss");
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TextView tradeStatus = (TextView) layoutView.findViewById(R.id.tradeStatusTextView);
        if ((hour > 9 && hour < 15) || (hour == 9 && minute >= 30)) {
            tradeStatus.setText("交易中 " + dateFormat.format(calendar.getTime()));
        } else if (hour >= 0 && hour <= 9) {
            tradeStatus.setText("未开盘 " + dateFormat.format(calendar.getTime()));
        } else {
            tradeStatus.setText("已收盘 " + dateFormat.format(calendar.getTime()));
        }
        TextView currentPrice = (TextView)layoutView.findViewById(R.id.currentPrice);
        currentPrice.setText(String.format("%.02f", stock.getPrice() / 100.0));
        TextView currentChange = (TextView)layoutView.findViewById(R.id.currentChange);
        currentChange.setText(String.format("%.02f", stock.getChange() / 100.0));
        TextView currentChangeRate = (TextView)layoutView.findViewById(R.id.currentChangeRate);
        currentChangeRate.setText(String.format("%.02f%%", stock.getChangeRate()));
        if (stock.getChange() > 0) {
            currentPrice.setTextColor(Color.RED);
            currentChange.setTextColor(Color.RED);
            currentChangeRate.setTextColor(Color.RED);
        } else if (stock.getChange() < 0) {
            currentPrice.setTextColor(Color.GREEN);
            currentChange.setTextColor(Color.GREEN);
            currentChangeRate.setTextColor(Color.GREEN);
        } else {
            currentPrice.setTextColor(Color.GRAY);
            currentChange.setTextColor(Color.GRAY);
            currentChangeRate.setTextColor(Color.GRAY);
        }
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
