package Model;

public class Buff {

    private boolean isPositive;
    private BuffName buffName;
    private int effectDuration;

    public Buff(BuffName buffName, int effectDuration) {
        this.buffName = buffName;
        this.isPositive = buffName.isPositive();
        this.effectDuration = effectDuration;
    }


    public boolean isPositive() {
        return isPositive;
    }

    public BuffName getBuffName() {
        return buffName;
    }

    public int getEffectDuration() {
        return effectDuration;
    }


}
