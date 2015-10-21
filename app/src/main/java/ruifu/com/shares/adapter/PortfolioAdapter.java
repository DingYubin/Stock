package ruifu.com.shares.adapter;

import android.os.Handler;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ruifu.com.shares.R;
import ruifu.com.shares.entity.StockEntity;

/**
 * Created by 王群 on 2015/10/12.
 */
public class PortfolioAdapter extends RecyclerView.Adapter<PortfolioAdapter.ViewHolder> {
    private final Fragment fragment;
    private List<StockEntity> stockEntityList = new ArrayList();
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
    public boolean addStock(StockEntity stockEntity) {
        return stockEntityList.add(stockEntity);
    }
    public void removeStock(int position) {
        if (position < stockEntityList.size()) {
            stockEntityList.remove(position);
            notifyDataSetChanged();
        }
    }
    public boolean moveStock(int current, int object) {
        if (current != object && current < stockEntityList.size() && object < stockEntityList.size()) {
            StockEntity tmp = stockEntityList.get(current);
            stockEntityList.set(current, stockEntityList.get(object));
            stockEntityList.set(object, tmp);
            notifyDataSetChanged();
        }
        return false;
    }
    public StockEntity getStock(int position) {
        return stockEntityList.get(position);
    }
    @Override
    public PortfolioAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.stock_single_line, parent, false), fragment);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final StockEntity stockEntity = stockEntityList.get(position);
        holder.itemView.setId(position);
        holder.stockNameView.setText(stockEntity.getName());
        holder.stockCodeView.setText(stockEntity.getCode());
        if (stockEntity.isUpdated()) {
            holder.stockPriceView.setText(String.format("%.02f", stockEntity.getCurrentPrice()));
            if (Float.compare(stockEntity.getTodayOpeningPrice(), 0.0f) == 0) {
                holder.stockChangeRateView.setText("停牌");
                holder.stockChangeRateView.setBackground(GRAY_COLOR);
            } else {
                float changeRate = stockEntity.getChangeRate();
                if (Float.compare(changeRate, 0.0f) > 0) {
                    holder.stockChangeRateView.setText(String.format("+%.02f%%", changeRate * 100));
                    holder.stockChangeRateView.setBackground(RED_COLOR);
                } else if (Float.compare(changeRate, 0.0f) < 0) {
                    holder.stockChangeRateView.setText(String.format("-%.02f%%", changeRate * 100));
                    holder.stockChangeRateView.setBackground(GREEN_COLOR);
                } else {
                    holder.stockChangeRateView.setText("0.00%");
                    holder.stockChangeRateView.setBackground(GRAY_COLOR);
                }
            }
        } else {
            holder.stockPriceView.setText("--");
            holder.stockChangeRateView.setText("--");
            holder.stockChangeRateView.setBackground(GRAY_COLOR);
        }
    }

    @Override
    public int getItemCount() {
        return stockEntityList.size();
    }
}