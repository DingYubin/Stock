package ruifu.com.shares.entity;

import java.util.ArrayList;

import cn.limc.androidcharts.series.ChartDataRow;

/**
 * Created by 王群 on 2015/10/16.
 */
public class StockPriceInfo {
    public static class TimeLineData {
        private ArrayList<ChartDataRow> price;
        private ArrayList<ChartDataRow> avgPrice;
        private ArrayList<ChartDataRow> tradeAmount;
    }
    public static class KLineData {
        private  ArrayList<ChartDataRow> topPrice;
        private ArrayList<ChartDataRow> floorPrice;
        private ArrayList<ChartDataRow> openingPrice;
        private ArrayList<ChartDataRow> closingPrice;
        private ArrayList<ChartDataRow> changeRate;
        private ArrayList<ChartDataRow> ma5Price;
        private ArrayList<ChartDataRow> ma10Price;
        private ArrayList<ChartDataRow> ma20Price;
        private ArrayList<ChartDataRow> tradeAmount;
    }
    // 分时图
    private TimeLineData timeIndexChartData;
    // 五日分时图
    private TimeLineData fiveDaystimeIndexChartData;
    // 日K图
    private KLineData dailyKData;
    // 周K图
    private KLineData weeklyKData;
    // 月K图
    private KLineData monthlyKData;
}
