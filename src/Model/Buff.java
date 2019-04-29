package Model;

import javax.tools.ForwardingFileObject;

public class Buff {

    enum PowerAndWeaknessBuffKind {
        HEALTH, ATTACK
    }

    private boolean isPositive;
    private BuffName buffName;
    private int effectDuration;
    private PowerAndWeaknessBuffKind powerAndWeaknessBuffKind;
    private int buffNumber;

    public Buff(BuffName buffName, int effectDuration, int buffNumber) {
        this.buffName = buffName;
        this.isPositive = buffName.isPositive();
        this.effectDuration = effectDuration;
        this.buffNumber = buffNumber;
    }

    public void affectOnCard(Card card, PowerAndWeaknessBuffKind powerBuffKind, int number) {
        switch (this.buffName) {
            case POWER_BUFF:
                this.powerBuff(card, powerBuffKind, number);
                break;
            case HOLY_BUFF:
                this.holyBuff(number);
                break;
            case POISON_BUFF:
                this.poisonBuff(card);
                break;
            case MANA_BUFF:
                //TODO
                break;
            case STUN_BUFF:
                //TODO
                this.stunBuff(card);
                break;
            case FLAME_BUFF:
                //TODO
                this.flameBuff(card);
                break;
            case DISARM_BUFF:
                //TODO
                this.disarmBuff(card);
                break;
            case WEAKNESS_BUFF:
                //TODO
                this.weaknessBuff(card, powerBuffKind, number);
                break;
            case NEUTRALIZE_All_BUFFS:
                //TODO
                this.neutralizeBuff(card);
                break;
        }
    }

    public int holyBuff(int number) {
        return number - 1;
        //TODO COMPLETE
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

        if (!(card instanceof Warrior))
            return;
        Warrior warrior = (Warrior) card;
        warrior.decreaseHealthPoint(1);

    }

    public void weaknessBuff(Card card, PowerAndWeaknessBuffKind weaknessBuffKind, int number) {

        Warrior warrior = (Warrior) card;

        if (weaknessBuffKind == PowerAndWeaknessBuffKind.ATTACK)
            warrior.decreaseActionPower(number);
        else if (weaknessBuffKind == PowerAndWeaknessBuffKind.HEALTH)
            warrior.decreaseHealthPoint(number);
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

    public PowerAndWeaknessBuffKind getPowerAndWeaknessBuffKind() {
        return powerAndWeaknessBuffKind;
    }

    public int getBuffNumber() {
        return buffNumber;
    }
}
