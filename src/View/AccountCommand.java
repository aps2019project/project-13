package View;

import java.util.ArrayList;

public enum AccountCommand {
        BACK , EXIT , SHOW_MENU , CREATE_ACCOUNT , LOGIN , SHOW_LEADERBOARD , SAVE , LOGOUT , HELP;
    String data ;

    public AccountCommand setData(String data) {
        this.data = data;
        return this ;
    }

    public String getData() {
        return data;
    }
}
