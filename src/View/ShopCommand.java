package View;

import java.util.ArrayList;

public enum ShopCommand {
    BACK , EXIT , SHOW_MENU , SHOW_COLLECTION , SEARCH , SEARCH_COLLECTION , BUY , SELL ,SHOW ,HELP;
    ArrayList<String> data = new ArrayList<>();

    public void setData(ArrayList<String> data) {
        this.data = data;
    }

    public ArrayList<String> getData() {
        return data;
    }

}
