package Model.BuffClasses;

public abstract class ABuff {
    int duration;

    public abstract  <T> void affect(T t);
    public abstract  <T> void update(T t);

}
