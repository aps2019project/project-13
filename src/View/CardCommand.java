package View;

import java.util.ArrayList;

public enum CardCommand {
    EXIT , MOVE , ATTACK , USE_SPECIAL_POWER ;
    ArrayList<String> data = new java.util.ArrayList<>();

    public CardCommand setData(ArrayList<String> data) {
        this.data = data;
        return this;
    }

    public ArrayList<String> getData() {
        return data;
    }
}
