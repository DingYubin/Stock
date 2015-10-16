package ruifu.com.shares.entity;

import java.io.Serializable;

/**
 * Created by qun on 15-10-13.
 */
public class Stock implements Serializable {
    private String name;
    private String code;
    private int price;
    private int change;
    private boolean isDelisted;
    StockHistoryData historyData;

    public Stock(String name, String code) {
        this.name = name;
        this.code = code;
        setPrice(0);
        setChange(0);
        setDelist(false);
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setChange(int change) {
        this.change = change;
    }
    public void setDelist(boolean isDelisted) {
        this.isDelisted = isDelisted;
    }
    public String getName() {
        return name;
    }
    public String getCode() {
        return code;
    }
    public int getPrice() {
        return price;
    }
    public int getChange() { return change; }
    public double getChangeRate() {
        return change * 1.0 / price;
    }
    public boolean isDelisted() {
        return isDelisted;
    }
}
