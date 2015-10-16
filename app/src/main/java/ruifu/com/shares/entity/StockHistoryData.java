package ruifu.com.shares.entity;

import java.util.ArrayList;

import cn.limc.androidcharts.series.ChartDataRow;

/**
 * Created by 王群 on 2015/10/16.
 */
public class StockHistoryData {
    private ArrayList<ChartDataRow> realTimeLinePrice;
    private ArrayList<ChartDataRow> avgTimeLinePrice;
    private ArrayList<ChartDataRow> timeLineTradeAmount;
    private ArrayList<ChartDataRow> fiveDaysOfDailyPrice;
    private ArrayList<ChartDataRow> avgFiveDaysOfDailyPrice;
    private ArrayList<ChartDataRow> dailyPrice;
    private ArrayList<ChartDataRow> avgDailyPrice;
    private ArrayList<ChartDataRow> avgFiveDaysForDailyPrice;
    private ArrayList<ChartDataRow> avgTenDaysForDailyPrice;
    private ArrayList<ChartDataRow> avgTwentyDaysForDailyPrice;
    private ArrayList<ChartDataRow> weeklyPrice;
    private ArrayList<ChartDataRow> avgWeeklyPrice;
    private ArrayList<ChartDataRow> avgFiveDaysForWeeklyPrice;
    private ArrayList<ChartDataRow> avgTenDaysForWeeklyPrice;
    private ArrayList<ChartDataRow> avgTwentyDaysForWeeklyPrice;
    private ArrayList<ChartDataRow> monthlyPrice;
    private ArrayList<ChartDataRow> avgMonthlyPrice;
    private ArrayList<ChartDataRow> avgFiveDaysForMonthlyPrice;
    private ArrayList<ChartDataRow> avgTenDaysForMonthlyPrice;
    private ArrayList<ChartDataRow> avgTwentyDaysForMonthlyPrice;
}
