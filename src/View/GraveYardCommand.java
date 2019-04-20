package View;

import java.util.ArrayList;

public enum GraveYardCommand {
    BACK , EXIT , SHOW_INFO , SHOW_CARD ;
    String data ;

    public GraveYardCommand setData(String data) {
        this.data = data;
        return this ;
    }

    public String getData() {
        return data;
    }
}
