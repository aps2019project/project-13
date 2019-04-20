package View;

import java.util.ArrayList;

public enum MainCommand {
    BACK , EXIT , SHOW_MENU , ENTER_COLLECTION , ENTER_SHOP , ENTER_BATTLE ,ENTER_EXIT , ENTER_HELP ;
    ArrayList<String> data = new ArrayList<>();

    public void setData(ArrayList<String> data) {
        this.data = data;
    }

    public ArrayList<String> getData() {
        return data;
    }
}
