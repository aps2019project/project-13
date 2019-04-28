package View;

public class Error extends Exception {

    private String massage;

    public Error(String massage) {
        this.massage = massage;
    }

    @Override
    public String toString() {
        return massage;
    }
}
