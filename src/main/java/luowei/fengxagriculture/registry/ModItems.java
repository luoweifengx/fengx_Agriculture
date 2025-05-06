package luowei.fengxagriculture.registry;

import java.util.function.Function;
import luowei.fengxagriculture.FengxAgriculture;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item EXAMPLE_ITEM = register("example_item", Item::new, new Item.Settings());
    public static final Item CHINECE_CABBAGE = register("chinese_cabbage", Item::new, new Item.Settings());
    public static final Item CHINECE_CABBAGE_SEED = register("chinese_cabbage_seed", Item::new, new Item.Settings());
    public static final ConsumableComponent POISON_FOOD_CONSUMABLE_COMPONENT = ConsumableComponents.food()
		// The duration is in ticks, 20 ticks = 1 second
		.consumeEffect(new ApplyEffectsConsumeEffect(new StatusEffectInstance(StatusEffects.POISON, 6 * 20, 1), 1.0f))
		.build();
    public static final FoodComponent POISON_FOOD_COMPONENT = new FoodComponent.Builder()
		.alwaysEdible()
		.build();
    public static final Item POISONOUS_APPLE = register(
		"poisonous_apple",
		Item::new,
		new Item.Settings().food(POISON_FOOD_COMPONENT, POISON_FOOD_CONSUMABLE_COMPONENT)
);
    public static void initialize() {
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register((itemGroup) -> {
            itemGroup.add(ModItems.EXAMPLE_ITEM);
            itemGroup.add(ModItems.CHINECE_CABBAGE);
            itemGroup.add(ModItems.CHINECE_CABBAGE_SEED);
        });
        ItemGroupEvents.modifyEntriesEvent(ModItemGroup.CUSTOM_ITEM_GROUP_KEY).register((itemGroup) -> {
            itemGroup.add(ModItems.EXAMPLE_ITEM);
            itemGroup.add(ModItems.CHINECE_CABBAGE);
            itemGroup.add(ModItems.CHINECE_CABBAGE_SEED);
        });
    }
    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        // 创建物品键
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(FengxAgriculture.MOD_ID, name));

        // 创建物品实例
        Item item = itemFactory.apply(settings.registryKey(itemKey));

        // 注册物品
        Registry.register(Registries.ITEM, itemKey, item);

        return item;
    }

}