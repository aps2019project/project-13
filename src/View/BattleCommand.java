package View;

import java.util.ArrayList;

public enum BattleCommand {
    GAME_INFO , SHHOW_MY_MINIONS , SHOW_OPPENEST_MINIONS ,SHOW_CARD_INFO , SELECT , INSERT , END_TURN , SHOW_COLLECTABLE , SHOW_NEXT_CARD , SHOW_HAND , ENTER_GRAVE_YARD , HELP , END_GAME;
    ArrayList<String> data = new ArrayList<>();

    public void setData(ArrayList<String> data) {
        this.data = data;
    }

    public ArrayList<String> getData() {
        return data;
    }
}
