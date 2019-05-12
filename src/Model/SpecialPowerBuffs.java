package Model;

import Model.BuffClasses.ABuff;

import java.util.ArrayList;
import java.util.Arrays;

public class SpecialPowerBuffs {
    //TODO MAY NEED COMPLETE CHANGES. BUT I THINK THIS CLASS IS NEEDED TO MAKE HEROES AND MINIONS INDEPENDENT OF SPELL
    ArrayList<ABuff> buffs;
    ActivationCondition activationCondition;
    TargetSocietyKind targetSocietyKind;

    public SpecialPowerBuffs(ActivationCondition activationCondition, TargetSocietyKind targetSocietyKind, ABuff... abuffs) {
        this.buffs = (ArrayList<ABuff>) Arrays.asList(abuffs);
        this.activationCondition = activationCondition;
        this.targetSocietyKind = targetSocietyKind;
    }

    public SpecialPowerBuffs(ActivationCondition activationCondition, TargetSocietyKind targetSocietyKind, ArrayList<ABuff> aBuffs) {
        this.buffs = aBuffs;
        this.activationCondition = activationCondition;
        this.targetSocietyKind = targetSocietyKind;
    }


    public <T> void useBuffsOnGeneric(T t) {
        if (t != null) {
            for (int i = 0; i < getBuffs().size(); i++) {
                if (getBuffs().get(i) != null) {

                    getBuffs().get(i).affect(t);
                }
            }
        }
    }

    public <T> void useBuffsOnGenericArrayList(ArrayList<T> tArrayList) {
        if (tArrayList.size() > 0) {
            for (int i = 0; i < tArrayList.size(); i++) {
                T t = tArrayList.get(i);
                if (t != null) {
                    useBuffsOnGeneric(t);
                }
            }
        }
    }

    public <T> void useBuffsOnGenericArray(T... ts) {
        if (ts.length > 0) {
            for (int i = 0; i < ts.length; i++) {
                T t = ts[i];
                if (t != null) {
                    useBuffsOnGeneric(t);
                }
            }
        }
    }

    public <T> void updateBuffs(T t) {
        //TODO MAY NEED SOME CHANGES
        for (int i = 0; i < getBuffs().size(); i++) {
            if (getBuffs().get(i) != null) {
                getBuffs().get(i).update(t);
            }
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        SpecialPowerBuffs specialPowerBuffs = new SpecialPowerBuffs(this.getActivationCondition(), this.getTargetSocietyKind(), this.getBuffs());
        ArrayList<ABuff> newAbuffs = ABuff.aBuffClone(specialPowerBuffs.getBuffs());
        specialPowerBuffs.setBuffs(newAbuffs);
        return specialPowerBuffs;
    }

    public ArrayList<ABuff> getBuffs() {
        return buffs;
    }

    public void setBuffs(ArrayList<ABuff> buffs) {
        this.buffs = buffs;
    }

    public ActivationCondition getActivationCondition() {
        return activationCondition;
    }

    public void setActivationCondition(ActivationCondition activationCondition) {
        this.activationCondition = activationCondition;
    }

    public TargetSocietyKind getTargetSocietyKind() {
        return targetSocietyKind;
    }

    public void setTargetSocietyKind(TargetSocietyKind targetSocietyKind) {
        this.targetSocietyKind = targetSocietyKind;
    }

}
