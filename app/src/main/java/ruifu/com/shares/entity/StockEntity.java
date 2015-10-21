package ruifu.com.shares.entity;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by qun on 15-10-13.
 */
public class StockEntity implements Serializable {
    public static final int MSG_SUCCESS = 0;
    public static final int MSG_FAILURE = 1;

    private String name;
    private String code;
    private float currentPrice;
    private float todayOpeningPrice;
    private float yesterdayClosingPrice;
    private float todayCeilPrice;
    private float todayFloorPrice;
    private Date currentDateTime;
    private long tradeStockAmount;
    private double tradeMoneyAmount;
    private List<Float> orderedSalePrices = new ArrayList<>();
    private List<Integer> orderedSaleAmounts = new ArrayList<>();
    private List<Float> orderedBuyPrices = new ArrayList<>();
    private List<Integer> orderedBuyAmounts = new ArrayList<>();
    private boolean isUpdated = false;

    public StockEntity(String name, String code) {
        this.name = name;
        this.code = code;
        syncData(null);
    }

    public void syncData(final Handler handler) {
        isUpdated = false;
        new Thread(new Runnable() {
            @Override
            public void run() {
                int msgFlag;
                StringBuilder reqUrl = new StringBuilder();
                reqUrl.append("http://hq.sinajs.cn/list=");
                if (getCode().startsWith("00")) {
                    reqUrl.append("sz").append(getCode());
                } else if (getCode().startsWith("60")){
                    reqUrl.append("sh").append(getCode());
                }
                try {
                    URL url = new URL(reqUrl.toString());
                    Log.i("StockEntity", "URL: " + url);
                    try {
                        URLConnection urlConnection = url.openConnection();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "GBK"));
                        StringBuilder stringBuilder = new StringBuilder();
                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
                            stringBuilder.append(line);
                        }
                        String sinaFinanceValues = stringBuilder.substring(stringBuilder.indexOf("\"") + 1, stringBuilder.lastIndexOf("\""));
                        Log.i("StockEntity", "response: " + sinaFinanceValues);
                        String[] values = sinaFinanceValues.split(",");
                        todayOpeningPrice = Float.parseFloat(values[1]);
                        yesterdayClosingPrice = Float.parseFloat(values[2]);
                        currentPrice = Float.parseFloat(values[3]);
                        todayCeilPrice = Float.parseFloat(values[4]);
                        todayFloorPrice = Float.parseFloat(values[5]);
                        tradeStockAmount = Long.parseLong(values[8]);
                        tradeMoneyAmount = Double.parseDouble(values[9]);
                        orderedBuyAmounts.clear();
                        orderedBuyPrices.clear();
                        for (int i = 10; i < 20; i += 2) {
                            orderedBuyAmounts.add(Integer.parseInt(values[i]));
                            orderedBuyPrices.add(Float.parseFloat(values[i + 1]));
                        }
                        orderedSaleAmounts.clear();
                        orderedSalePrices.clear();
                        for (int i = 20; i < 30; i += 2) {
                            orderedSaleAmounts.add(Integer.parseInt(values[i]));
                            orderedSalePrices.add(Float.parseFloat(values[i + 1]));
                        }
                        DateFormat dateFormat = new SimpleDateFormat("yy-MM-ddhh:mm:ss");
                        try {
                            setCurrentDateTime(dateFormat.parse(values[30] + values[31]));
                            msgFlag = MSG_SUCCESS;
                        } catch (ParseException e) {
                            msgFlag = MSG_FAILURE;
                            Log.e("StockEntity", "ParseException: " + e.getMessage());
                        }
                    } catch (IOException e) {
                        msgFlag = MSG_FAILURE;
                        Log.e("StockEntity", "IOException: " + e.getMessage());
                    }
                } catch (MalformedURLException e) {
                    msgFlag = MSG_FAILURE;
                    Log.e("StockEntity", "URL not exist: " + e.getMessage());
                }
                if (handler != null) {
                    handler.obtainMessage(msgFlag).sendToTarget();
                }
                setUpdate(true);
            }
        }).start();
    }

    public float getCurrentPrice() {
        return currentPrice;
    }

    public float getTodayOpeningPrice() {
        return todayOpeningPrice;
    }

    public float getYesterdayClosingPrice() {
        return yesterdayClosingPrice;
    }

    public float getTodayCeilPrice() {
        return todayCeilPrice;
    }

    public float getTodayFloorPrice() {
        return todayFloorPrice;
    }

    public long getTradeStockAmount() {
        return tradeStockAmount;
    }

    public double getTradeMoneyAmount() {
        return tradeMoneyAmount;
    }

    public Date getCurrentDateTime() {
        return currentDateTime;
    }

    public void setCurrentDateTime(Date currentDateTime) {
        this.currentDateTime = currentDateTime;
    }

    public String getName() {
        return name;
    }
    public String getCode() {
        return code;
    }
    public float getChange() { return currentPrice == 0 ? currentPrice : currentPrice - yesterdayClosingPrice; }
    public float getChangeRate() {
        if (yesterdayClosingPrice == 0) {
            return 0;
        } else {
            return getChange() / yesterdayClosingPrice;
        }
    }

    public void setUpdate(boolean isUpdated) {
        this.isUpdated = isUpdated;
    }
    public boolean isUpdated() {
        return isUpdated;
    }
}
