package View;

import java.util.ArrayList;

public enum AccountCommand {
    EXIT, SHOW_LEADERBOARD, SAVE, LOGOUT, HELP, CREATE_ACCOUNT, LOGIN;
    String data;

    public AccountCommand setData(String data) {
        this.data = data;
        return this;
    }

    public String getData() {
        return data;
    }
}
