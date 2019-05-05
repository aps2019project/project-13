package JsonAndInitializers;

import Model.ActivationCondition;
import Model.BuffClasses.ABuff;
import Model.Spell;
import Model.SpellName;
import Model.TargetSocietyKind;
import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.YaGsonBuilder;

import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;

public class SpellInitializer {
    public void spellInit()
    {
        YaGson yaGson = new YaGsonBuilder().setPrettyPrinting().create();
        ArrayList<Spell> spells = new ArrayList<>();
        spells.add(new Spell("Total Disarm" , "Spell_1" , 0 , 1000 ,
                "Disarm an Enemy for whole game" , TargetSocietyKind.ENEMY_CARDS ,
                SpellName.ALL_DISARM , ActivationCondition.PASSIVE , new ArrayList<ABuff>() ));

        spells.add(new Spell("Area Dispel" , "Spell_2" , 2 , 1500 ,
                "Dispel Friendly negative buffs and enemy positive buffs on a 2x2 grid " , TargetSocietyKind.CELL ,
                SpellName.AREA_DISPEL , ActivationCondition.PASSIVE , new ArrayList<ABuff>() ));

        spells.add(new Spell("Empower" , "Spell_3" , 1 , 250 ,
                "2 More AP for one unit " , TargetSocietyKind.FRIENDLY_CARDS ,
                SpellName.AREA_DISPEL , ActivationCondition.PASSIVE , new ArrayList<ABuff>() ));

        spells.add(new Spell("Fireball" , "Spell_4" , 1 , 400 ,
                "4 DMG to an enemy " , TargetSocietyKind.ENEMY_CARDS ,
                SpellName.FIREBALL , ActivationCondition.PASSIVE , new ArrayList<ABuff>() ));

        spells.add(new Spell("God Strength" , "Spell_5" , 2 , 450 ,
                "2 More AP for one unit " , TargetSocietyKind.FRIENDLY_CARDS ,
                SpellName.AREA_DISPEL , ActivationCondition.PASSIVE , new ArrayList<ABuff>() ));

        spells.add(new Spell("HellFire" , "Spell_6" , 3 , 600 ,
                "Make a 2x2 grid burning for 2 turns " , TargetSocietyKind.CELL ,
                SpellName.AREA_DISPEL , ActivationCondition.PASSIVE , new ArrayList<ABuff>() ));

        spells.add(new Spell("Lightning Bolt" , "Spell_7" , 2 , 1250 ,
                "8 DMG To Enemy Hero " , TargetSocietyKind.ENEMY_CARDS ,
                SpellName.AREA_DISPEL , ActivationCondition.PASSIVE , new ArrayList<ABuff>() ));

        spells.add(new Spell("Poison Lake" , "Spell_8" , 5 , 900 ,
                "Make a 3x3 grid poisonous for 1 turn " , TargetSocietyKind.CELL ,
                SpellName.AREA_DISPEL , ActivationCondition.PASSIVE , new ArrayList<ABuff>() ));

        spells.add(new Spell("Madness" , "Spell_9" , 0 , 650 ,
                "Increase 4 AP of a unit for 3 turns but Disarm that unit  " , TargetSocietyKind.FRIENDLY_CARDS ,
                SpellName.AREA_DISPEL , ActivationCondition.PASSIVE , new ArrayList<ABuff>() ));

        spells.add(new Spell("All Disarm" , "Spell_10" , 9 , 2000 ,
                "Make all  Enemy Units Disarmed for 1 Turn " , TargetSocietyKind.ENEMY_CARDS ,
                SpellName.AREA_DISPEL , ActivationCondition.PASSIVE , new ArrayList<ABuff>() ));

        spells.add(new Spell("All Poison" , "Spell_11" , 8 , 1500 ,
                "Make all Enemy Units poisoned for 1 Turn " , TargetSocietyKind.ENEMY_CARDS ,
                SpellName.AREA_DISPEL , ActivationCondition.PASSIVE , new ArrayList<ABuff>() ));

        spells.add(new Spell("Dispel" , "Spell_12" , 0 , 2100 ,
                "Dispel 1 Friendly negative buffs OR enemy positive buffs " , TargetSocietyKind.FRIENDLY_CARDS ,
                SpellName.AREA_DISPEL , ActivationCondition.PASSIVE , new ArrayList<ABuff>() ));

        spells.add(new Spell("Health with Profit" , "Spell_13" , 0 , 2250 ,
                "1 Weakness buff with 6 DMG alongside 3 turns of 2 HolyBuff" , TargetSocietyKind.FRIENDLY_CARDS ,
                SpellName.AREA_DISPEL , ActivationCondition.PASSIVE , new ArrayList<ABuff>() ));

        spells.add(new Spell("Power Up" , "Spell_14" , 2 , 2500 ,
                "6 More AP for one Unit " , TargetSocietyKind.FRIENDLY_CARDS ,
                SpellName.AREA_DISPEL , ActivationCondition.PASSIVE , new ArrayList<ABuff>() ));

        spells.add(new Spell("All Power" , "Spell_15" , 4 , 2000 ,
                "2 More AP for All units " , TargetSocietyKind.FRIENDLY_CARDS ,
                SpellName.AREA_DISPEL , ActivationCondition.PASSIVE , new ArrayList<ABuff>() ));

        spells.add(new Spell("All Attack" , "Spell_16" , 4 , 1500 ,
                "6 DMG to All Enemy Units on a Row " , TargetSocietyKind.ENEMY_CARDS ,
                SpellName.AREA_DISPEL , ActivationCondition.PASSIVE , new ArrayList<ABuff>() ));

        spells.add(new Spell("Weakening" , "Spell_17" , 1 , 1000 ,
                "4 Weakness buff of AP on an Enemy Minion" , TargetSocietyKind.ENEMY_CARDS ,
                SpellName.AREA_DISPEL , ActivationCondition.PASSIVE , new ArrayList<ABuff>() ));

        spells.add(new Spell("Sacrifice" , "Spell_18" , 2 , 1600 ,
                "6 Weakness buff on HP With 8 AP PowerBuff on Friendly Minion " , TargetSocietyKind.FRIENDLY_CARDS ,
                SpellName.AREA_DISPEL , ActivationCondition.PASSIVE , new ArrayList<ABuff>() ));

        spells.add(new Spell("Kings Guard" , "Spell_19" , 9 , 1750 ,
                "Kills one random minion in adjacent cells of Hero " , TargetSocietyKind.ENEMY_CARDS ,
                SpellName.AREA_DISPEL , ActivationCondition.PASSIVE , new ArrayList<ABuff>() ));

        spells.add(new Spell("Shock" , "Spell_20" , 1 , 1200 ,
                "Enemy is stunned for 2 turns " , TargetSocietyKind.ENEMY_CARDS ,
                SpellName.AREA_DISPEL , ActivationCondition.PASSIVE , new ArrayList<ABuff>() ));



        //TODO CHECK BUFFS

        try {
            Writer writer = new FileWriter("Spells_YaGson.json");
            String s = new String(yaGson.toJson(spells));
            writer.write(s.toString());
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
