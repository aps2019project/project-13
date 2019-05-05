package View;

import java.util.ArrayList;

public enum BattleCommand {
    EXIT ,GAME_INFO, SHOW_MY_MINIONS, SHOW_OPPONENT_MINIONS, SHOW_COLLECTABLE, SHOW_NEXT_CARD, END_TURN, SHOW_HAND, ENTER_GRAVE_YARD, HELP, END_GAME, SHOW_CARD_INFO, SELECT, INSERT;
    ArrayList<String> data = new ArrayList<>();

    public BattleCommand setData(ArrayList<String> data) {
        this.data = data;
        return this;
    }

    public ArrayList<String> getData() {
        return data;
    }
}
