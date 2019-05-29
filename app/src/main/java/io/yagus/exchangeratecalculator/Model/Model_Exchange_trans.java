package io.yagus.exchangeratecalculator.Model;

public class Model_Exchange_trans {


    // 통화명
    private String currencyName;
    // 현찰 사실때
    private String buyCash;
    // 현찰 파실때
    private String sellCash;
    // 매매기준율
    private String tradingStandardRate;


    public Model_Exchange_trans() {
    }

    public Model_Exchange_trans(String currencyName, String buyCash, String sellCash, String tradingStandardRate) {
        this.currencyName = currencyName;
        this.buyCash = buyCash;
        this.sellCash = sellCash;
        this.tradingStandardRate = tradingStandardRate;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getBuyCash() {
        return buyCash;
    }

    public void setBuyCash(String buyCash) {
        this.buyCash = buyCash;
    }

    public String getSellCash() {
        return sellCash;
    }

    public void setSellCash(String sellCash) {
        this.sellCash = sellCash;
    }

    public String getTradingStandardRate() {
        return tradingStandardRate;
    }

    public void setTradingStandardRate(String tradingStandardRate) {
        this.tradingStandardRate = tradingStandardRate;
    }
}
