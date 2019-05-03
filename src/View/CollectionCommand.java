package View;

import java.util.ArrayList;

public enum CollectionCommand {
    EXIT, SHOW, SAVE, HELP, SHOW_ALL_DECK, SEARCH, CREATE_DECK, DELETE_DECK, VALIDATE_DECK, SELECTE_DECK, SHOW_DECK, ADD_TO_DECK, REMOVE_FROM_DECK;
    ArrayList<String> data = new java.util.ArrayList<>();

    public CollectionCommand setData(ArrayList<String> data) {
        this.data = data;
        return this;
    }

    public ArrayList<String> getData() {
        return data;
    }
}

