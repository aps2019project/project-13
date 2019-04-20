package View;

public enum CollectableCommand {
    BACK , EXIT , SHOW_INFO , USE ;
    String data ;

    public CollectableCommand setData(String data) {
        this.data = data;
        return this ;
    }

    public String getData() {
        return data;
    }
}
