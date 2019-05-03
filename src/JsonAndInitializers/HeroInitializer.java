package JsonAndInitializers;

import Model.*;
import Model.BuffClasses.ABuff;
import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.YaGsonBuilder;

import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;

public class HeroInitializer {
    public static void HeroInit() {
        YaGson yaGson = new YaGsonBuilder().setPrettyPrinting().create();
        ArrayList<Hero> heroes = new ArrayList<>();
        heroes.add(new Hero("Dive Sefid", "Hero_1", 1, 8000,
                "Has continous power buff with 4 units of AP", HeroName.DIVE_SEFID, AttackKind.MELEE,
                50, 4, 1,
                new Spell("NoName", "1000", 1, 0, "No",
                        TargetSocietyKind.FRIENDLY_CARDS, SpellName.POWER_UP, ActivationCondition.PASSIVE, new ArrayList<ABuff>()),
                2));
        //TODO CHECK BUFFS
        heroes.add(new Hero("Simorq", "Hero_2", 5, 9000,
                "Stun All enemy units for one turn", HeroName.SIMORGH, AttackKind.MELEE,
                50, 4, 1,
                new Spell("NoName", "1001", 1, 0, "No",
                        TargetSocietyKind.ENEMY_CARDS, SpellName.ALL_DISARM, ActivationCondition.ATTACK, new ArrayList<ABuff>()),
                8));
        heroes.add(new Hero("Ejdeha Haft Sar", "Hero_3", 0, 8000,
                "Disarm One Enemey", HeroName.EJDEHA, AttackKind.MELEE,
                50, 4, 1,
                new Spell("NoName", "1000", 1, 0, "No",
                        TargetSocietyKind.ENEMY_CARDS, SpellName.ALL_DISARM, ActivationCondition.ATTACK, new ArrayList<ABuff>()),
                1));
        heroes.add(new Hero("Rakhsh", "Hero_4", 1, 8000,
                "Stun One Enemey for one turn", HeroName.RAKSH, AttackKind.MELEE,
                50, 4, 1,
                new Spell("NoName", "1000", 1, 0, "No",
                        TargetSocietyKind.ENEMY_CARDS, SpellName.ALL_DISARM, ActivationCondition.ATTACK, new ArrayList<ABuff>()),
                2));
        heroes.add(new Hero("Zahak", "Hero_5", 0, 10000,
                "Poison Enemy for 3 turn on Attack", HeroName.ZAHHAK, AttackKind.MELEE,
                50, 2, 1,
                new Spell("NoName", "1000", 1, 0, "No",
                        TargetSocietyKind.ENEMY_CARDS, SpellName.ALL_POISON, ActivationCondition.ATTACK, new ArrayList<ABuff>()),
                0));
        heroes.add(new Hero(" Kaveh", "Hero_6", 1, 8000,
                "Holy Buff a cell for 3 turns", HeroName.KAVEH, AttackKind.MELEE,
                50, 4, 1,
                new Spell("NoName", "1000", 1, 0, "No",
                        TargetSocietyKind.CELL, SpellName.SACRIFICE, ActivationCondition.ATTACK, new ArrayList<ABuff>()),
                3));
        heroes.add(new Hero("Arash", "Hero_7", 2, 10000,
                "Damages all units on the hero row", HeroName.ARASH, AttackKind.RANGED,
                30, 2, 6,
                new Spell("NoName", "1000", 1, 0, "No",
                        TargetSocietyKind.ENEMY_CARDS, SpellName.ALL_DISARM, ActivationCondition.ATTACK, new ArrayList<ABuff>()),
                2));
        heroes.add(new Hero("Afsaneh", "Hero_8", 1, 11000,
                "DISPEL one Enemy Unit", HeroName.AFSANEH, AttackKind.RANGED,
                40, 3, 3,
                new Spell("NoName", "1000", 1, 0, "No",
                        TargetSocietyKind.ENEMY_CARDS, SpellName.DISPEL, ActivationCondition.ATTACK, new ArrayList<ABuff>()),
                2));
        ArrayList<Hero> heroes2 = new ArrayList<>();
        heroes2.add(new Hero("Esfandyar", "Hero_9", 0, 12000,
                "3 Holy Buff Continuous", HeroName.ESFANDIAR, AttackKind.HYBRID,
                35, 3, 3,
                new Spell("NoName", "1000", 1, 0, "No",
                        TargetSocietyKind.FRIENDLY_CARDS, SpellName.ALL_DISARM, ActivationCondition.PASSIVE, new ArrayList<ABuff>()),
                0));
        heroes2.add(new Hero("Rostam", "Hero_10", 0, 8000,
                "No Special Power", HeroName.ARASH, AttackKind.HYBRID,
                55, 7, 4,
                new Spell("NoName", "1000", 1, 0, "No",
                        TargetSocietyKind.ENEMY_CARDS, SpellName.ALL_DISARM, ActivationCondition.ATTACK, new ArrayList<ABuff>()),
                0));
        try {
            Writer writer = new FileWriter("Heroes_YaGson.json");
            String s = new String(yaGson.toJson(heroes));
            System.out.println(s);
            String s2 = new String(yaGson.toJson(heroes2));
            writer.write(s.toString());
            writer.close();
            Writer writer2 = new FileWriter("Heroes_YaGson2.json");
            writer2.write(s2);
            writer2.close();
        } catch (Exception e) {

        }
    }
}
