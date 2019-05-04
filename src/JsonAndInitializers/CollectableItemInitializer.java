package JsonAndInitializers;

import Model.BuffClasses.ABuff;
import Model.CollectableItem;
import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.YaGsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class CollectableItemInitializer {
    public void collectableItemInit() {
        YaGson gson = new YaGsonBuilder().setPrettyPrinting().create();
        ArrayList<CollectableItem> collectableItem = new ArrayList<>();
        collectableItem.add(new CollectableItem("CollectableItem_1", "6 More HP on Random Unit",
                "Cure-All (Nooshdaroo)", new ArrayList<ABuff>()));

        collectableItem.add(new CollectableItem("CollectableItem_2", "6 More AP for a Random Hybrid or Ranged Unit",
                "Two-headed Arrow", new ArrayList<ABuff>()));

        collectableItem.add(new CollectableItem("CollectableItem_3", "3 More HP and an AP Power Buff for a Random Minion",
                "Elixir", new ArrayList<ABuff>()));

        collectableItem.add(new CollectableItem("CollectableItem_4", "3 More Mana on Next Turn",
                "Mana Potion", new ArrayList<ABuff>()));

        collectableItem.add(new CollectableItem("CollectableItem_5", "10 HolyBuff for 2 Turn on Random Friendly Unit",
                "Invincibility Potion", new ArrayList<ABuff>()));

        collectableItem.add(new CollectableItem("CollectableItem_6", "A Minion can do 8 DMG to one of Nearest Units on Random",
                "Death Curse", new ArrayList<ABuff>()));

        collectableItem.add(new CollectableItem("CollectableItem_7", "2 DMG to Random Unit on Battlefield",
                "Random Damage", new ArrayList<ABuff>()));

        collectableItem.add(new CollectableItem("CollectableItem_8", "6 More AP to Random Unit",
                "Blades of Agility", new ArrayList<ABuff>()));

        collectableItem.add(new CollectableItem("CollectableItem_9", "5 More Ap to Melee Unit",
                "Chinese Sword", new ArrayList<ABuff>()));


        try (Writer writer = new FileWriter("CollectableItems_YaGson.json")) {
            gson.toJson(collectableItem, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
