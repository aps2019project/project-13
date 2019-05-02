package View;

public enum ShopCommand {

    EXIT, HELP, SHOW, SHOW_COLLECTION, SEARCH, SEARCH_COLLECTION, BUY, SELL;
    String data;

    public ShopCommand setData(String data) {
        this.data = data;
        return this;
    }

    public String getData() {
        return data;
    }
}
