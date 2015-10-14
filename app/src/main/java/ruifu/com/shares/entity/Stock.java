package ruifu.com.shares.entity;

/**
 * Created by qun on 15-10-13.
 */
public class Stock {
    private String name;
    private String code;
    private int price;
    private int changeRate;
    private boolean isDelisted;

    public Stock(String name, String code) {
        this.name = name;
        this.code = code;
        setPrice(0);
        setChangeRate(0);
        setDelist(false);
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setChangeRate(int changeRate) {
        this.changeRate = changeRate;
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
    public int getChangeRate() {
        return changeRate;
    }
    public boolean isDelisted() {
        return isDelisted;
    }
}
