package View;

import java.util.ArrayList;

public enum ShopCommand {
    BACK , EXIT , SHOW_MENU , SHOW_COLLECTION , SEARCH , SEARCH_COLLECTION , BUY , SELL ,SHOW ,HELP;
    String data  ;

    public ShopCommand setData(String data) {
        this.data = data;
        return this ;
    }

    public String getData() {
        return data;
    }

}
