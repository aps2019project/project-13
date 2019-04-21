package View;

import java.util.ArrayList;

public enum GraveYardCommand {
    EXIT , SHOW_INFO , SHOW_CARD ;
    String data ;

    public GraveYardCommand setData(String data) {
        this.data = data;
        return this ;
    }

    public String getData() {
        return data;
    }
}
