package View;

import java.util.ArrayList;

public enum GraveYardCommand {
    BACK , EXIT , SHOW_INFO , SHOW_CARD ;
    ArrayList<String> data = new java.util.ArrayList<>();

    public void setData(ArrayList<String> data) {
        this.data = data;
    }

    public ArrayList<String> getData() {
        return data;
    }
}
