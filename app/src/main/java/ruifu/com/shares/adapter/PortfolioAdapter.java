package ruifu.com.shares.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ruifu.com.shares.R;

/**
 * Created by 王群 on 2015/10/12.
 */
public class PortfolioAdapter extends RecyclerView.Adapter<PortfolioAdapter.ViewHolder> {
    private List<String> stockNameList = new ArrayList<>();
    private final LayoutInflater layoutInflater;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView stockNameView;
        private TextView stockCodeView;
        private TextView stockPriceView;
        private TextView stockUpDownView;
        public ViewHolder(View view) {
            super((view));
            stockNameView = (TextView)view.findViewById(R.id.stockNameView);
            stockCodeView = (TextView)view.findViewById(R.id.stockCodeView);
            stockPriceView = (TextView)view.findViewById(R.id.stockPriceView);
            stockUpDownView = (TextView)view.findViewById(R.id.stockUpDownView);
        }
    }
    public PortfolioAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
    }
    public boolean addStock(String stockName) {
        return stockNameList.add(stockName);
    }
    @Override
    public PortfolioAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.stock_line_layout, parent, false));
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.stockNameView.setText(stockNameList.get(position));
        holder.stockCodeView.setText("000000");
        holder.stockPriceView.setText(Integer.toString(position * 2) + ".10");
        holder.stockUpDownView.setText(Integer.toString(position) + "%");
    }
    @Override
    public int getItemCount() {
        return stockNameList.size();
    }
}