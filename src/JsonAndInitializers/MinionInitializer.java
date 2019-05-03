package JsonAndInitializers;

import Model.*;
import Model.BuffClasses.ABuff;
import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.YaGsonBuilder;

import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;

public class MinionInitializer {
    public static void minionInit()
    {
        YaGson yaGson = new YaGsonBuilder().setPrettyPrinting().create();
        ArrayList<Minion> Minions = new ArrayList<>();
        Minions.add(new Minion("Persian Archer" , "Minion_1" , 2 ,300 ,
                "No Special Power" , MinionName.KAMANDARE_FARS,
                6,4,7,
                AttackKind.RANGED,
                new Spell("NoName" , "1000" , 1,0,"NoDesc",
                        TargetSocietyKind.FRIENDLY_CARDS,SpellName.POWER_UP, ActivationCondition.PASSIVE,new ArrayList<ABuff>())));

        Minions.add(new Minion("Persian Swordsman" , "Minion_2" , 2 ,400 ,
                "Stun on Attack" , MinionName.SHAMSHIRZANE_FARS,
                6,4,1,
                AttackKind.MELEE,
                new Spell("NoName" , "1000" , 1,0,"NoDesc",
                        TargetSocietyKind.ENEMY_CARDS,SpellName.ALL_POISON, ActivationCondition.ATTACK,new ArrayList<ABuff>())));

        Minions.add(new Minion("Persian Spearman" , "Minion_3" , 1 ,500 ,
                "No Special Power" , MinionName.NEYZEDARE_FARS,
                5,3,3,
                AttackKind.HYBRID,
                new Spell("NoName" , "1000" , 1,0,"NoDesc",
                        TargetSocietyKind.FRIENDLY_CARDS,SpellName.POWER_UP, ActivationCondition.PASSIVE,new ArrayList<ABuff>())));

        Minions.add(new Minion("Persian Horseman" , "Minion_4" , 4 ,200 ,
                "No Special Power" , MinionName.ASBSAVARE_FARS,
                10,6,1,
                AttackKind.MELEE,
                new Spell("NoName" , "1000" , 1,0,"NoDesc",
                        TargetSocietyKind.FRIENDLY_CARDS,SpellName.POWER_UP, ActivationCondition.PASSIVE,new ArrayList<ABuff>())));

        Minions.add(new Minion("Persian Pahlavan" , "Minion_5" , 9 ,600 ,
                "Attack 5 points more for each turn he attacked someone" , MinionName.PAHLEVANE_FARS,
                24,6,1,
                AttackKind.MELEE,
                new Spell("NoName" , "1000" , 1,0,"NoDesc",
                        TargetSocietyKind.ENEMY_CARDS,SpellName.POWER_UP, ActivationCondition.ATTACK,new ArrayList<ABuff>())));

        Minions.add(new Minion("Persian General" , "Minion_6" , 7 ,800 ,
                "Combo" , MinionName.SEPAHSALARE_FARS,
                12,4,1,
                AttackKind.MELEE,
                new Spell("NoName" , "1000" , 1,0,"NoDesc",
                        TargetSocietyKind.ENEMY_CARDS,SpellName.POWER_UP, ActivationCondition.ATTACK,new ArrayList<ABuff>())));

        Minions.add(new Minion("Toranian Archer" , "Minion_7" , 1 ,500 ,
                "No Special Power" , MinionName.KAMANDARE_TOORANI,
                3,4,5,
                AttackKind.RANGED,
                new Spell("NoName" , "1000" , 1,0,"NoDesc",
                        TargetSocietyKind.FRIENDLY_CARDS,SpellName.POWER_UP, ActivationCondition.PASSIVE,new ArrayList<ABuff>())));

        Minions.add(new Minion("Toranian Slinger" , "Minion_8" , 1 ,600 ,
                "No Special Power" , MinionName.GHOLLABSANGDARE_TOORANI,
                4,2,7,
                AttackKind.RANGED,
                new Spell("NoName" , "1000" , 1,0,"NoDesc",
                        TargetSocietyKind.FRIENDLY_CARDS,SpellName.POWER_UP, ActivationCondition.PASSIVE,new ArrayList<ABuff>())));

        Minions.add(new Minion("Toranian Spearman" , "Minion_9" , 1 ,600 ,
                "No Special Power" , MinionName.NEYZEDARE_TOORANI,
                4,4,3,
                AttackKind.HYBRID,
                new Spell("NoName" , "1000" , 1,0,"NoDesc",
                        TargetSocietyKind.FRIENDLY_CARDS,SpellName.POWER_UP, ActivationCondition.PASSIVE,new ArrayList<ABuff>())));

        Minions.add(new Minion("Toranian Spy" , "Minion_10" , 4 ,700 ,
                "1 turn Disarm - 4 Turn Poison" , MinionName.JASOOSE_TOORANI,
                6,6,1,
                AttackKind.MELEE,
                new Spell("NoName" , "1000" , 1,0,"NoDesc",
                        TargetSocietyKind.ENEMY_CARDS,SpellName.POWER_UP, ActivationCondition.ATTACK,new ArrayList<ABuff>())));

        Minions.add(new Minion("Toranian Maceman" , "Minion_11" , 2 ,450 ,
                "No Special Power" , MinionName.JASOOSE_TOORANI,
                3,10,1,
                AttackKind.MELEE,
                new Spell("NoName" , "1000" , 1,0,"NoDesc",
                        TargetSocietyKind.ENEMY_CARDS,SpellName.POWER_UP, ActivationCondition.ATTACK,new ArrayList<ABuff>())));

        Minions.add(new Minion("Toranian Prince" , "Minion_12" , 6 ,800 ,
                "No Special Power" , MinionName.SHAHZADEYE_TOORANI,
                6,10,1,
                AttackKind.MELEE,
                new Spell("NoName" , "1000" , 1,0,"NoDesc",
                        TargetSocietyKind.ENEMY_CARDS,SpellName.POWER_UP, ActivationCondition.ATTACK,new ArrayList<ABuff>())));

        Minions.add(new Minion("Dive Siah" , "Minion_13" , 9 ,300 ,
                "No Special Power" , MinionName.DIVE_SIAH,
                14,10,7,
                AttackKind.HYBRID,
                new Spell("NoName" , "1000" , 1,0,"NoDesc",
                        TargetSocietyKind.ENEMY_CARDS,SpellName.POWER_UP, ActivationCondition.ATTACK,new ArrayList<ABuff>())));

        Minions.add(new Minion("Rock Thrower Giant" , "Minion_14" , 9,300 ,
                "No Special Power" , MinionName.GHOOLE_SANGANDAZ,
                12,12,7,
                AttackKind.RANGED,
                new Spell("NoName" , "1000" , 1,0,"NoDesc",
                        TargetSocietyKind.ENEMY_CARDS,SpellName.POWER_UP, ActivationCondition.ATTACK,new ArrayList<ABuff>())));

        Minions.add(new Minion("Eagle " , "Minion_15" , 200 ,2 ,
                "10 Health PowerBuff" , MinionName.OGHAB,
                0,2,3,
                AttackKind.RANGED,
                new Spell("NoName" , "1000" , 1,0,"NoDesc",
                        TargetSocietyKind.FRIENDLY_CARDS,SpellName.POWER_UP, ActivationCondition.PASSIVE,new ArrayList<ABuff>())));

        Minions.add(new Minion("Boarrider Dive" , "Minion_16" , 6 ,300 ,
                "No Special Power" , MinionName.DIVE_GORAZSAVAR,
                18,8,1,
                AttackKind.MELEE,
                new Spell("NoName" , "1000" , 1,0,"NoDesc",
                        TargetSocietyKind.ENEMY_CARDS,SpellName.POWER_UP, ActivationCondition.ATTACK,new ArrayList<ABuff>())));

        Minions.add(new Minion("OneEyed Giant" , "Minion_17" , 7 ,500 ,
                "2 Damage to Adjacents on death" , MinionName.JASOOSE_TOORANI,
                12,11,3,
                AttackKind.HYBRID,
                new Spell("NoName" , "1000" , 1,0,"NoDesc",
                        TargetSocietyKind.ENEMY_CARDS,SpellName.POWER_UP, ActivationCondition.DEATH,new ArrayList<ABuff>())));

        Minions.add(new Minion("Poisonous Snake" , "Minion_18" , 4 ,300 ,
                "Posion for 3 Turns" , MinionName.MARE_SAMMI,
                5,6,4,
                AttackKind.RANGED,
                new Spell("NoName" , "1000" , 1,0,"NoDesc",
                        TargetSocietyKind.ENEMY_CARDS,SpellName.ALL_POISON, ActivationCondition.ATTACK,new ArrayList<ABuff>())));

        Minions.add(new Minion("Firing Dragon" , "Minion_19" , 5 ,250 ,
                "No Special Power" , MinionName.EJDEHAYE_ATASHANDAZ,
                9,5,4,
                AttackKind.RANGED,
                new Spell("NoName" , "1000" , 1,0,"NoDesc",
                        TargetSocietyKind.ENEMY_CARDS,SpellName.POWER_UP, ActivationCondition.ATTACK,new ArrayList<ABuff>())));

        Minions.add(new Minion("Wild Lion" , "Minion_20" , 2 ,600 ,
                "Holy ABuff has no effect on him" , MinionName.SHIRE_DARANDE,
                1,8,1,
                AttackKind.MELEE,
                new Spell("NoName" , "1000" , 1,0,"NoDesc",
                        TargetSocietyKind.ENEMY_CARDS,SpellName.POWER_UP, ActivationCondition.ATTACK,new ArrayList<ABuff>())));

        Minions.add(new Minion("Giant Snake" , "Minion_21" , 8 ,500 ,
                "1 More Damage To 2-cell adjacent units" , MinionName.MARE_GHOOLPEIKAR,
                14,7,5,
                AttackKind.RANGED,
                new Spell("NoName" , "1000" , 1,0,"NoDesc",
                        TargetSocietyKind.ENEMY_CARDS,SpellName.POWER_UP, ActivationCondition.SPAWN,new ArrayList<ABuff>())));

        Minions.add(new Minion("White Wolf" , "Minion_22" , 5 ,400 ,
                "After Attack,After 1 turn 6 more dmg , After Another turn 4 more dmg to that minion" , MinionName.GORGE_SEFID,
                8,2,1,
                AttackKind.MELEE,
                new Spell("NoName" , "1000" , 1,0,"NoDesc",
                        TargetSocietyKind.ENEMY_CARDS,SpellName.POWER_UP, ActivationCondition.ATTACK,new ArrayList<ABuff>())));

        Minions.add(new Minion("Panther" , "Minion_23" , 4 ,400 ,
                "After Attak, After 1 turn 8 more damage to that minion" , MinionName.PALANG,
                6,2,1,
                AttackKind.MELEE,
                new Spell("NoName" , "1000" , 1,0,"NoDesc",
                        TargetSocietyKind.ENEMY_CARDS,SpellName.POWER_UP, ActivationCondition.ATTACK,new ArrayList<ABuff>())));

        Minions.add(new Minion("Gorg" , "Minion_24" , 3 ,400 ,
                "After One turn, enemy minion get 6 dmg" , MinionName.PALANG,
                6,1,1,
                AttackKind.MELEE,
                new Spell("NoName" , "1000" , 1,0,"NoDesc",
                        TargetSocietyKind.ENEMY_CARDS,SpellName.WEAKENING, ActivationCondition.ATTACK,new ArrayList<ABuff>())));

        Minions.add(new Minion("Mage" , "Minion_25" , 4 ,550 ,
                "2 Power buff and 1 Weakness buff to adjacent friendly and itself for one turn" , MinionName.JADOOGAR,
                5,4,3,
                AttackKind.RANGED,
                new Spell("NoName" , "1000" , 1,0,"NoDesc",
                        TargetSocietyKind.ENEMY_CARDS,SpellName.POWER_UP, ActivationCondition.PASSIVE,new ArrayList<ABuff>())));

        Minions.add(new Minion("Archmage" , "Minion_26" , 6 ,550 ,
                "2 Power buff and 1 Holy buff to itself and 8 adjacent friendly untis" , MinionName.JADOOGAR_AZAM,
                6,6,5,
                AttackKind.RANGED,
                new Spell("NoName" , "1000" , 1,0,"NoDesc",
                        TargetSocietyKind.FRIENDLY_CARDS,SpellName.POWER_UP, ActivationCondition.ATTACK,new ArrayList<ABuff>())));

        Minions.add(new Minion("Jin" , "Minion_27" , 5 ,500 ,
                "1 Power buff To all friendly minions continuous" , MinionName.JEN,
                10,4,4,
                AttackKind.RANGED,
                new Spell("NoName" , "1000" , 1,0,"NoDesc",
                        TargetSocietyKind.ENEMY_CARDS,SpellName.POWER_UP, ActivationCondition.PASSIVE,new ArrayList<ABuff>())));

        Minions.add(new Minion("Wild Boar" , "Minion_28" , 6 ,500 ,
                "Cannot be disarmed" , MinionName.GORAZE_VAHSHI,
                10,14,1,
                AttackKind.MELEE,
                new Spell("NoName" , "1000" , 1,0,"NoDesc",
                        TargetSocietyKind.FRIENDLY_CARDS,SpellName.POWER_UP, ActivationCondition.DEFEND,new ArrayList<ABuff>())));

        Minions.add(new Minion("Piran" , "Minion_29" , 8 ,400 ,
                "Cannot be poisoned" , MinionName.PIRAN,
                20,12,1,
                AttackKind.MELEE,
                new Spell("NoName" , "1000" , 1,0,"NoDesc",
                        TargetSocietyKind.FRIENDLY_CARDS,SpellName.POWER_UP, ActivationCondition.DEATH,new ArrayList<ABuff>())));

        Minions.add(new Minion("Giv" , "Minion_30" , 4 ,450 ,
                "No negative effect from other cards" , MinionName.GIV,
                5,7,5,
                AttackKind.RANGED,
                new Spell("NoName" , "1000" , 1,0,"NoDesc",
                        TargetSocietyKind.FRIENDLY_CARDS,SpellName.POWER_UP, ActivationCondition.DEFEND,new ArrayList<ABuff>())));

        Minions.add(new Minion("Bahman" , "Minion_31" , 8 ,450 ,
                "Random 16 dmg to one of Enemy Units on spawn" , MinionName.BAHMAN,
                16,9,1,
                AttackKind.MELEE,
                new Spell("NoName" , "1000" , 1,0,"NoDesc",
                        TargetSocietyKind.ENEMY_CARDS,SpellName.POWER_UP, ActivationCondition.SPAWN,new ArrayList<ABuff>())));

        Minions.add(new Minion("Ashkboos" , "Minion_32" , 7 ,400 ,
                "Cannot be damaged by weaker units" , MinionName.ASHKBOOS,
                14,8,1,
                AttackKind.MELEE,
                new Spell("NoName" , "1000" , 1,0,"NoDesc",
                        TargetSocietyKind.ENEMY_CARDS,SpellName.POWER_UP, ActivationCondition.DEFEND,new ArrayList<ABuff>())));

        Minions.add(new Minion("Iraj" , "Minion_33" , 4 ,500 ,
                "No Special Power" , MinionName.IRAJ,
                6,20,3,
                AttackKind.RANGED,
                new Spell("NoName" , "1000" , 1,0,"NoDesc",
                        TargetSocietyKind.ENEMY_CARDS,SpellName.POWER_UP, ActivationCondition.ATTACK,new ArrayList<ABuff>())));

        Minions.add(new Minion("Big Giant" , "Minion_34" , 9 ,600 ,
                "No Special Power" , MinionName.GHOOLE_BOZORG2,
                30,8,2,
                AttackKind.HYBRID,
                new Spell("NoName" , "1000" , 1,0,"NoDesc",
                        TargetSocietyKind.ENEMY_CARDS,SpellName.POWER_UP, ActivationCondition.ATTACK,new ArrayList<ABuff>())));

        Minions.add(new Minion("Two-headed Giant" , "Minion_35" , 4 ,550 ,
                "Negates all positive effect on attack card" , MinionName.GHOOLE_DOSAR,
                10,4,1,
                AttackKind.MELEE,
                new Spell("NoName" , "1000" , 1,0,"NoDesc",
                        TargetSocietyKind.ENEMY_CARDS,SpellName.POWER_UP, ActivationCondition.ATTACK,new ArrayList<ABuff>())));

        Minions.add(new Minion("Naneh Sarma" , "Minion_36" , 3 ,500 ,
                "Stun Adjacent Enemy Minions for turn" , MinionName.NANESARMA,
                3,4,5,
                AttackKind.RANGED,
                new Spell("NoName" , "1000" , 1,0,"NoDesc",
                        TargetSocietyKind.ENEMY_CARDS,SpellName.POWER_UP, ActivationCondition.SPAWN,new ArrayList<ABuff>())));

        Minions.add(new Minion("Fooladzereh" , "Minion_37" , 3 ,650 ,
                "12 Holy Buff" , MinionName.FOOLADZERE,
                3,1,1,
                AttackKind.MELEE,
                new Spell("NoName" , "1000" , 1,0,"NoDesc",
                        TargetSocietyKind.FRIENDLY_CARDS,SpellName.POWER_UP, ActivationCondition.PASSIVE,new ArrayList<ABuff>())));

        Minions.add(new Minion("Siavash" , "Minion_38" , 4 ,350 ,
                "6 Damage to Enemy hero On death" , MinionName.SIAVASH,
                8,5,1,
                AttackKind.MELEE,
                new Spell("NoName" , "1000" , 1,0,"NoDesc",
                        TargetSocietyKind.ENEMY_CARDS,SpellName.POWER_UP, ActivationCondition.DEATH,new ArrayList<ABuff>())));

        Minions.add(new Minion("Giant King" , "Minion_39" , 5 ,600 ,
                "Combo" , MinionName.SHAHGHOOL,
                10,4,1,
                AttackKind.MELEE,
                new Spell("NoName" , "1000" , 1,0,"NoDesc",
                        TargetSocietyKind.ENEMY_CARDS,SpellName.POWER_UP, ActivationCondition.ATTACK,new ArrayList<ABuff>())));

        Minions.add(new Minion("Arzhang Div" , "Minion_40" , 3 ,600 ,
                "Combo" , MinionName.ARJANG_DIV,
                6,6,1,
                AttackKind.MELEE,
                new Spell("NoName" , "1000" , 1,0,"NoDesc",
                        TargetSocietyKind.ENEMY_CARDS,SpellName.POWER_UP, ActivationCondition.ATTACK,new ArrayList<ABuff>())));



        //TODO CHECK BUFFS

        try {
            Writer writer = new FileWriter("Minions_YaGson.json");
            String s=new String( yaGson.toJson(Minions));
            writer.write(s.toString());
            writer.close();

        }
        catch (Exception e)
        {

        }
    }
}
