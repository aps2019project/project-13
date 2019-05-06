package JsonAndInitializers;

import Model.*;
import Model.BuffClasses.*;

import java.util.ArrayList;

public class SpecialPowerBuffsMaker {
    public static final int VERY_LONG_TIME = 100000;

    static ArrayList<ABuff> persianSwordsmanBuffs = new ArrayList<>();
    static ArrayList<ABuff> toranianSpyBuffs = new ArrayList<>();
    static ArrayList<ABuff> eagleBuffs = new ArrayList<>();
    static ArrayList<ABuff> poisonousSnakeBuffs = new ArrayList<>();
    static ArrayList<ABuff> giantSnakeBuffs = new ArrayList<>();
    static ArrayList<ABuff> pantherBuffs = new ArrayList<>();
    static ArrayList<ABuff> wolfBuffs = new ArrayList<>();
    static ArrayList<ABuff> mageBuffs = new ArrayList<>();


    static SpecialPowerBuffs persianSwordsmanSPB = new SpecialPowerBuffs(ActivationCondition.ATTACK, TargetSocietyKind.ENEMY_CARDS,
            persianSwordsmanBuffs);

    static SpecialPowerBuffs toranianSpySPB = new SpecialPowerBuffs(ActivationCondition.ATTACK, TargetSocietyKind.ENEMY_CARDS,
            toranianSpyBuffs);

    static SpecialPowerBuffs eagleSPB = new SpecialPowerBuffs(ActivationCondition.PASSIVE, TargetSocietyKind.FRIENDLY_CARDS,
            eagleBuffs);

    static SpecialPowerBuffs poisonousSnakeSPB = new SpecialPowerBuffs(ActivationCondition.ATTACK, TargetSocietyKind.FRIENDLY_CARDS,
            poisonousSnakeBuffs);

    static SpecialPowerBuffs giantSnakeSPB = new SpecialPowerBuffs(ActivationCondition.SPAWN, TargetSocietyKind.CELL,
            giantSnakeBuffs);

    static SpecialPowerBuffs pantherSPB = new SpecialPowerBuffs(ActivationCondition.ATTACK, TargetSocietyKind.ENEMY_CARDS,
            pantherBuffs);

    static SpecialPowerBuffs wolfSPB = new SpecialPowerBuffs(ActivationCondition.ATTACK, TargetSocietyKind.ENEMY_CARDS,
            wolfBuffs);

    static SpecialPowerBuffs mageSPB = new SpecialPowerBuffs(ActivationCondition.PASSIVE , TargetSocietyKind.FRIENDLY_CARDS , mageBuffs);

    public static void buffMaker() {
        //Persian Swordsman
        persianSwordsmanBuffs.add(new StunBuff(null, 1, true));

        //Toranian Spy
        toranianSpyBuffs.add(new DisarmBuff(null, 1, true));
        toranianSpyBuffs.add(new PoisonBuff(null, 4, 1, true));

        //Eagle
        eagleBuffs.add(new PowerBuff(PowerAndWeaknessBuffType.HEALTH, 10, null, VERY_LONG_TIME, true));

        //Poisonous Snake
        poisonousSnakeBuffs.add(new PoisonBuff(null, 3, 1, true));

        //Giant Snake
        giantSnakeBuffs.add(new HolyBuff(-1, null, VERY_LONG_TIME, true));

        //Panther
        pantherBuffs.add(new PoisonBuff(null, 1, 8, true));

        //Wolf
        wolfBuffs.add(new PoisonBuff(null, 1, 6, true));

        //Mage
        mageBuffs.add(new PowerBuff(PowerAndWeaknessBuffType.ATTACK,2,null,1,true));
        mageBuffs.add(new WeaknessBuff(PowerAndWeaknessBuffType.HEALTH,1,null,1,true));


    }


}
