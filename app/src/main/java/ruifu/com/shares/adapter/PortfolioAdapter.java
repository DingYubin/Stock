package ruifu.com.shares.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ruifu.com.shares.R;
import ruifu.com.shares.data.Stock;

/**
 * Created by 王群 on 2015/10/12.
 */
public class PortfolioAdapter extends RecyclerView.Adapter<PortfolioAdapter.ViewHolder> {
    private List<Stock> stockNameList = new ArrayList();
    private final Drawable RED_COLOR = new ColorDrawable(Color.parseColor("#AD3702"));
    private final Drawable GREEN_COLOR = new ColorDrawable(Color.parseColor("#2F7C2D"));
    private final Drawable GRAY_COLOR = new ColorDrawable(Color.parseColor("#5C6369"));
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
            stockUpDownView = (TextView)view.findViewById(R.id.stockChangeRateView);
        }
    }
    public PortfolioAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
    }
    public boolean addStock(Stock stock) {
        return stockNameList.add(stock);
    }
    @Override
    public PortfolioAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.stock_line_layout, parent, false));
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Stock stock = stockNameList.get(stockNameList.size() - position - 1);
        holder.stockNameView.setText(stock.getName());
        holder.stockCodeView.setText(stock.getCode());
        holder.stockPriceView.setText(String.format("%.02f", stock.getPrice() / 100.0));
        if (stock.isDelist()) {
            holder.stockUpDownView.setText("停牌");
            holder.stockUpDownView.setBackground(new ColorDrawable(Color.GRAY));
        } else {
            int changeRate = stock.getChangeRate();
            if (changeRate > 0) {
                holder.stockUpDownView.setText(String.format("+%.02f%%", changeRate / 100.0));
                holder.stockUpDownView.setBackground(RED_COLOR);
            } else if (changeRate < 0) {
                holder.stockUpDownView.setText(String.format("-%.02f%%", changeRate / 100.0));
                holder.stockUpDownView.setBackground(GREEN_COLOR);
            } else {
                holder.stockUpDownView.setText("0.00%");
                holder.stockUpDownView.setBackground(GRAY_COLOR);
            }
        }
    }
    @Override
    public int getItemCount() {
        return stockNameList.size();
    }
}