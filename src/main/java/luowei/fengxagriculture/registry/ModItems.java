package luowei.fengxagriculture.registry;

import java.util.function.Function;
import luowei.fengxagriculture.FengxAgriculture;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
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

    //flavour
    public static final Item VINEGAR = register("vinegar", Item::new, new Item.Settings());

    //seed
    public static final Item CHINECE_CABBAGE_SEED = ModItems.register("chinese_cabbage_seed", Item::new, new Item.Settings());
    public static final Item CHILI_PEPPER_SEED = ModItems.register("chili_pepper_seed", Item::new, new Item.Settings());

    //food
    public static final FoodComponent UNTREATED_VEGETABLE_COMPONENT = new FoodComponent.Builder()
		.alwaysEdible()
    .nutrition(2)
    .saturationModifier(0.3f)
		.build();
    public static final FoodComponent FRUIT_COMPONENT = new FoodComponent.Builder()
		.alwaysEdible()
    .nutrition(2)
    .saturationModifier(0.3f)
		.build();
    public static final FoodComponent GRAIN_COMPONENT = new FoodComponent.Builder()
		.alwaysEdible()
    .nutrition(2)
    .saturationModifier(0.3f)
		.build();
    
    //vegetable
    public static final Item CHINECE_CABBAGE = ModItems.register(
		"chinese_cabbage",
		Item::new,
		new Item.Settings().food(UNTREATED_VEGETABLE_COMPONENT)
    );
    public static final Item CHILI_PEPPER = ModItems.register(
    "chili_pepper",
    Item::new,
    new Item.Settings().food(UNTREATED_VEGETABLE_COMPONENT)
    );

    //fruit

    //grain

    //dish
    public static final ConsumableComponent SPICY_AND_SOUR_CHINECE_CABBAGE_CONSUMABLE_COMPONENT = ConsumableComponents.food()
		// The duration is in ticks, 20 ticks = 1 second
		.consumeEffect(new ApplyEffectsConsumeEffect(new StatusEffectInstance(StatusEffects.STRENGTH,24 * 60 * 20, 1), 1.0f))
		.build();
    public static final FoodComponent SPICY_AND_SOUR_CHINECE_CABBAGE_COMPONENT = new FoodComponent.Builder()
		.alwaysEdible()
        .nutrition(2)
        .saturationModifier(0.3f)
		.build();
    public static final Item SPICY_AND_SOUR_CHINECE_CABBAGE = ModItems.register(
		"poisonous_apple",
		Item::new,
		new Item.Settings().food(SPICY_AND_SOUR_CHINECE_CABBAGE_COMPONENT, SPICY_AND_SOUR_CHINECE_CABBAGE_CONSUMABLE_COMPONENT)
    );
    public static void initialize() {
        //堆肥
        CompostingChanceRegistry.INSTANCE.add(CHINECE_CABBAGE, 0.3f);

        //eg
        CompostingChanceRegistry.INSTANCE.add(EXAMPLE_ITEM, 0.3f);
        //燃烧
        FuelRegistryEvents.BUILD.register((builder,displayContext) -> {
            builder.add(CHINECE_CABBAGE, 200);
            builder.add(CHILI_PEPPER, 200);
            
            //eg
            builder.add(EXAMPLE_ITEM, 200);
        });
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register((itemGroup) -> {
            itemGroup.add(ModItems.EXAMPLE_ITEM);
            itemGroup.add(CHINECE_CABBAGE);
            itemGroup.add(CHINECE_CABBAGE_SEED);
        });
        ItemGroupEvents.modifyEntriesEvent(ModItemGroup.CUSTOM_ITEM_GROUP_KEY).register((itemGroup) -> {
            itemGroup.add(ModItems.EXAMPLE_ITEM);
            itemGroup.add(CHINECE_CABBAGE);
            itemGroup.add(CHINECE_CABBAGE_SEED);
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