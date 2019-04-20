package View;

import java.util.ArrayList;

public enum CollectionCommand {
    BACK , EXIT , SHOW_MENU , SHOW , SEARCH ,SAVE , CREATE_DECK , DELETE_DECK , ADD_TO_DECK ,REMOVE_FROM_DECK ,VALIDATE_DECK , SELECTE_DECK , SHOW_ALL_DECK , SHOW_DECK , HELP ;
    ArrayList<String> data = new java.util.ArrayList<>();

    public CollectionCommand setData(ArrayList<String> data) {
        this.data = data;
        return this ;
    }

    public ArrayList<String> getData() {
        return data;
    }
}
