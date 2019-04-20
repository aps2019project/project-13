package View;

import java.util.ArrayList;

public enum AccountCommand {
        BACK , EXIT , SHOW_MENU , CREATE_ACCOUNT , LOGIN , SHOW_LEADERBOARD , SAVE , LOGOUT , HELP;
    ArrayList<String> data = new ArrayList<>();

    public void setData(ArrayList<String> data) {
        this.data = data;
    }

    public ArrayList<String> getData() {
        return data;
    }
}
