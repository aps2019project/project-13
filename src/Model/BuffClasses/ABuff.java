package Model.BuffClasses;

public abstract class ABuff {
    int duration;

    public ABuff(int duration) {
        this.duration = duration;
    }

    public abstract <T> void affect(T t);

    public abstract <T> void update(T t);

}
