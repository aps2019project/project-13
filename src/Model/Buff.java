package Model;

public class Buff {

    enum PowerAndWeaknessBuffKind {
        HEALTH, ATTACK
    }

    private boolean isPositive;
    private BuffName buffName;
    private int effectDuration;

    public Buff(BuffName buffName, int effectDuration) {
        this.buffName = buffName;
        this.isPositive = buffName.isPositive();
        this.effectDuration = effectDuration;
    }

    public int holyBuff(int number) {
        return number - 1;
    }

    public void powerBuff(Card card, PowerAndWeaknessBuffKind powerBuffKind, int number) {
        if (card instanceof Minion) {
            if (powerBuffKind == PowerAndWeaknessBuffKind.ATTACK)
                ((Minion) card).increaseActionPower(number);
            else if (powerBuffKind == PowerAndWeaknessBuffKind.HEALTH)
                ((Minion) card).increaseHealthPoint(number);
        } else if (card instanceof Hero) {
            if (powerBuffKind == PowerAndWeaknessBuffKind.ATTACK)
                ((Hero) card).increaseActionPower(number);
            else if (powerBuffKind == PowerAndWeaknessBuffKind.HEALTH)
                ((Hero) card).increaseHealthPoint(number);
        }
    }

    public void poisonBuff(Card card) {
        if (card instanceof Minion) {
            ((Minion) card).decreaseHealthPoint(1);
        } else if (card instanceof Hero) {
            ((Hero) card).deacreaseHealthPoint(1);
        }
    }

    public void weaknessBuff(Card card, PowerAndWeaknessBuffKind weaknessBuffKind, int number) {
        if (card instanceof Minion) {
            if (weaknessBuffKind == PowerAndWeaknessBuffKind.ATTACK)
                ((Minion) card).decreaseActionPower(number);
            else if (weaknessBuffKind == PowerAndWeaknessBuffKind.HEALTH)
                ((Minion) card).decreaseHealthPoint(number);
        } else if (card instanceof Hero) {
            if (weaknessBuffKind == PowerAndWeaknessBuffKind.ATTACK)
                ((Hero) card).decreaseActionPower(number);
            else if (weaknessBuffKind == PowerAndWeaknessBuffKind.HEALTH)
                ((Hero) card).deacreaseHealthPoint(number);
        }
    }

    public void stunBuff(Card card) {
        //TODO Too batel reval mikonim
    }

    public void disarmBuff(Card card) {
        //TODO Too batel reval mikonim
    }

    public void flameBuff(Card card) {
        //TODO Too batel reval mikonim
    }

    public boolean isPositive() {
        return isPositive;
    }

    public BuffName getBuffName() {
        return buffName;
    }

    public void neutralizeBuff(Card card) {
        //TODO  hanooz nazadam
    }

    public int getEffectDuration() {
        return effectDuration;
    }

    public void decrementEffectDuration() {
        effectDuration--;
    }


}
