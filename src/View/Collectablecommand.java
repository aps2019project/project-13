package View;

import java.util.ArrayList;

public enum Collectablecommand {
    SHOW;
    ArrayList<String> data = new ArrayList<>();

    public void setData(ArrayList<String> data) {
        this.data = data;
    }

    public ArrayList<String> getData() {
        return data;
    }
}
