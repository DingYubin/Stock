package ruifu.com.shares.adapter;

import android.app.Fragment;
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
import ruifu.com.shares.entity.Stock;

/**
 * Created by 王群 on 2015/10/12.
 */
public class PortfolioAdapter extends RecyclerView.Adapter<PortfolioAdapter.ViewHolder> {
    private final Fragment fragment;
    private List<Stock> stockList = new ArrayList();
    private final Drawable RED_COLOR = new ColorDrawable(Color.parseColor("#AD3702"));
    private final Drawable GREEN_COLOR = new ColorDrawable(Color.parseColor("#2F7C2D"));
    private final Drawable GRAY_COLOR = new ColorDrawable(Color.parseColor("#5C6369"));
    private final LayoutInflater layoutInflater;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView stockNameView;
        private TextView stockCodeView;
        private TextView stockPriceView;
        private TextView stockChangeRateView;

        public ViewHolder(View view, Fragment fragment) {
            super((view));
            stockNameView = (TextView)view.findViewById(R.id.stockNameView);
            stockCodeView = (TextView)view.findViewById(R.id.stockCodeView);
            stockPriceView = (TextView)view.findViewById(R.id.stockPriceView);
            stockChangeRateView = (TextView)view.findViewById(R.id.stockChangeRateView);
            view.setOnClickListener((View.OnClickListener)fragment);
            view.setOnCreateContextMenuListener(fragment);
        }
    }
    public PortfolioAdapter(Fragment fragment, Context context) {
        this.fragment = fragment;
        this.layoutInflater = LayoutInflater.from(context);
    }
    public boolean addStock(Stock stock) {
        return stockList.add(stock);
    }
    public void removeStock(int position) {
        if (position < stockList.size()) {
            stockList.remove(position);
            notifyDataSetChanged();
        }
    }
    public boolean moveStock(int current, int object) {
        if (current != object && current < stockList.size() && object < stockList.size()) {
            Stock tmp = stockList.get(current);
            stockList.set(current, stockList.get(object));
            stockList.set(object, tmp);
            notifyDataSetChanged();
        }
        return false;
    }
    public Stock getStock(int position) {
        return stockList.get(position);
    }
    @Override
    public PortfolioAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.stock_single_line, parent, false), fragment);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Stock stock = stockList.get(position);
        holder.itemView.setId(position);
        holder.stockNameView.setText(stock.getName());
        holder.stockCodeView.setText(stock.getCode());
        holder.stockPriceView.setText(String.format("%.02f", stock.getPrice() / 100.0));
        if (stock.isDelisted()) {
            holder.stockChangeRateView.setText("停牌");
            holder.stockChangeRateView.setBackground(new ColorDrawable(Color.GRAY));
        } else {
            double changeRate = stock.getChangeRate();
            if (changeRate > 0) {
                holder.stockChangeRateView.setText(String.format("+%.02f%%", changeRate));
                holder.stockChangeRateView.setBackground(RED_COLOR);
            } else if (changeRate < 0) {
                holder.stockChangeRateView.setText(String.format("-%.02f%%", changeRate));
                holder.stockChangeRateView.setBackground(GREEN_COLOR);
            } else {
                holder.stockChangeRateView.setText("0.00%");
                holder.stockChangeRateView.setBackground(GRAY_COLOR);
            }
        }
    }
    @Override
    public int getItemCount() {
        return stockList.size();
    }
}