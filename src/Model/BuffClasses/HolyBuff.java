package Model.BuffClasses;

import Model.Warrior;


public class HolyBuff extends ABuff {
    private int shield;
    private boolean isAffected;
    public HolyBuff(int shield) {
        this.shield = shield;
    }

    public int getShield() {
        return shield;
    }

    public void setShield(int shield) {
        this.shield = shield;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration=duration;
    }

    public void changeDuration(int i){
        setDuration(getDuration()-i);
    }

    public boolean isAffected() {
        return isAffected;
    }

    public void setAffected(boolean affected) {
        isAffected = affected;
    }

    public void affectOnCard(Warrior warrior)
    {
        if (getDuration()>0 && !isAffected())
        {
            warrior.changeShield(getShield());
            setAffected(true);
        }
        if (getDuration()<=0 && isAffected())
        {
            warrior.changeShield(-getShield());
            setAffected(false);
        }
    }



    @Override
    public <T>  void affect(T t) {
        if (t instanceof Warrior)
        {
            Warrior warrior = (Warrior) t;
            affectOnCard(warrior);
        }


    }


    @Override
    public <T> void update(T t) {
        changeDuration(-1);


    }
}
