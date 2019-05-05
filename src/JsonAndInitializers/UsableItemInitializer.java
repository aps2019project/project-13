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
        usableItems.add(new UsableItem("2", "Increase Mana in 3 turns", "Wisdom Crown", 300,
                new SpecialPowerBuffs(ActivationCondition.PASSIVE, TargetSocietyKind.FRIENDLY_CARDS, new ArrayList<ABuff>())));

        usableItems.add(new UsableItem("2", "12 Holy Buff in Friendly Hero", "Namoos Separ", 4000,
                new SpecialPowerBuffs(ActivationCondition.PASSIVE, TargetSocietyKind.FRIENDLY_CARDS, new ArrayList<ABuff>())));

        usableItems.add(new UsableItem("2", "Friendly Hero disarm Enemy For One Trun", "Bow of Damol", 30000,
                new SpecialPowerBuffs(ActivationCondition.PASSIVE, TargetSocietyKind.FRIENDLY_CARDS, new ArrayList<ABuff>())));

        usableItems.add(new UsableItem("2", "Weakness power buff for enemy Ranged", "Simorq Feather", 3500,
                new SpecialPowerBuffs(ActivationCondition.PASSIVE, TargetSocietyKind.FRIENDLY_CARDS, new ArrayList<ABuff>())));

        usableItems.add(new UsableItem("2", "Weakness power buff for random enemty for one turn", "Terror Hood", 5000,
                new SpecialPowerBuffs(ActivationCondition.PASSIVE, TargetSocietyKind.FRIENDLY_CARDS, new ArrayList<ABuff>())));

        usableItems.add(new UsableItem("2", "One extra Mana for whole game", "King Wisdom", 9000,
                new SpecialPowerBuffs(ActivationCondition.PASSIVE, TargetSocietyKind.FRIENDLY_CARDS, new ArrayList<ABuff>())));

        usableItems.add(new UsableItem("2", "One damage to Enemy Hero each time you put a card on the field", "Assassination Dagger", 15000,
                new SpecialPowerBuffs(ActivationCondition.PASSIVE, TargetSocietyKind.FRIENDLY_CARDS, new ArrayList<ABuff>())));

        usableItems.add(new UsableItem("2", "Poison damage to random enemy", "Poisonous Dagger", 7000,
                new SpecialPowerBuffs(ActivationCondition.PASSIVE, TargetSocietyKind.FRIENDLY_CARDS, new ArrayList<ABuff>())));

        usableItems.add(new UsableItem("2", "Hero disarm One enemy for one turn", "Shock Hammer", 15000,
                new SpecialPowerBuffs(ActivationCondition.PASSIVE, TargetSocietyKind.FRIENDLY_CARDS, new ArrayList<ABuff>())));

        usableItems.add(new UsableItem("2", "A power buff to a unit when some of units die", "Soul Eater", 25000,
                new SpecialPowerBuffs(ActivationCondition.PASSIVE, TargetSocietyKind.FRIENDLY_CARDS, new ArrayList<ABuff>())));

        usableItems.add(new UsableItem("2", "2 turn holy buff for each unit", "Baptism (Ghosl Ta'amid)", 20000,
                new SpecialPowerBuffs(ActivationCondition.PASSIVE, TargetSocietyKind.FRIENDLY_CARDS, new ArrayList<ABuff>())));


        try (Writer writer = new FileWriter("Items_YaGson_New.json")) {
            gson.toJson(usableItems, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}