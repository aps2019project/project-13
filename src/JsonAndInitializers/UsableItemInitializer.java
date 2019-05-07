package JsonAndInitializers;

import Model.*;
import Model.BuffClasses.ABuff;
import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.YaGsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class UsableItemInitializer {
    public static void usableItemInit() {
        YaGson gson = new YaGsonBuilder().setPrettyPrinting().create();
        ArrayList<UsableItem> usableItems = new ArrayList<>();
        usableItems.add(new UsableItem("UsableItem_1", "Increase Mana in 3 turns", "Wisdom_Crown", 300,
                new SpecialPowerBuffs(ActivationCondition.PASSIVE, TargetSocietyKind.FRIENDLY_CARDS, new ArrayList<ABuff>())));

        usableItems.add(new UsableItem("UsableItem_2", "12 Holy Buff in Friendly Hero", "Namoos_Separ", 4000,
                new SpecialPowerBuffs(ActivationCondition.PASSIVE, TargetSocietyKind.FRIENDLY_CARDS, new ArrayList<ABuff>())));

        usableItems.add(new UsableItem("UsableItem_3", "Friendly Hero disarm Enemy For One Trun", "Bow_of_Damol", 30000,
                new SpecialPowerBuffs(ActivationCondition.PASSIVE, TargetSocietyKind.FRIENDLY_CARDS, new ArrayList<ABuff>())));

        usableItems.add(new UsableItem("UsableItem_4", "Weakness power buff for enemy Ranged", "Simorq_Feather", 3500,
                new SpecialPowerBuffs(ActivationCondition.PASSIVE, TargetSocietyKind.FRIENDLY_CARDS, new ArrayList<ABuff>())));

        usableItems.add(new UsableItem("UsableItem_5", "Weakness power buff for random enemty for one turn", "Terror_Hood", 5000,
                new SpecialPowerBuffs(ActivationCondition.PASSIVE, TargetSocietyKind.FRIENDLY_CARDS, new ArrayList<ABuff>())));

        usableItems.add(new UsableItem("UsableItem_6", "One extra Mana for whole game", "King_Wisdom", 9000,
                new SpecialPowerBuffs(ActivationCondition.PASSIVE, TargetSocietyKind.FRIENDLY_CARDS, new ArrayList<ABuff>())));

        usableItems.add(new UsableItem("UsableItem_7", "One damage to Enemy Hero each time you put a card on the field", "Assassination_Dagger", 15000,
                new SpecialPowerBuffs(ActivationCondition.PASSIVE, TargetSocietyKind.FRIENDLY_CARDS, new ArrayList<ABuff>())));

        usableItems.add(new UsableItem("UsableItem_8", "Poison damage to random enemy", "Poisonous_Dagger", 7000,
                new SpecialPowerBuffs(ActivationCondition.PASSIVE, TargetSocietyKind.FRIENDLY_CARDS, new ArrayList<ABuff>())));

        usableItems.add(new UsableItem("UsableItem_9", "Hero disarm One enemy for one turn", "Shock_Hammer", 15000,
                new SpecialPowerBuffs(ActivationCondition.PASSIVE, TargetSocietyKind.FRIENDLY_CARDS, new ArrayList<ABuff>())));

        usableItems.add(new UsableItem("UsableItem_10", "A power buff to a unit when some of units die", "Soul_Eater", 25000,
                new SpecialPowerBuffs(ActivationCondition.PASSIVE, TargetSocietyKind.FRIENDLY_CARDS, new ArrayList<ABuff>())));

        usableItems.add(new UsableItem("UsableItem_11", "2 turn holy buff for each unit", "Baptism", 20000,
                new SpecialPowerBuffs(ActivationCondition.PASSIVE, TargetSocietyKind.FRIENDLY_CARDS, new ArrayList<ABuff>())));


        try (Writer writer = new FileWriter("Items_YaGson_New.json")) {
            gson.toJson(usableItems, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
