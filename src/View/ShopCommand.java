package View;

public enum ShopCommand {
    EXIT, HELP , SHOW, SEARCH_COLLECTION, SEARCH, SHOW_COLLECTION, BUY, SELL;

    String data;

    public ShopCommand setData(String data) {
        this.data = data;
        return this;
    }

    public String getData() {
        return data;
    }

}
