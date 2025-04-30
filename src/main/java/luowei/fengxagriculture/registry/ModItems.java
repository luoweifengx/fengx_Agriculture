package luowei.fengxagriculture.registry;


import java.util.function.Function;
import luowei.fengxagriculture.FengxAgriculture;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItems {
	public static final Item EXAMPLE_ITEM = register("example_item", Item::new, new Item.Settings());
	
	public static final RegistryKey<ItemGroup> CUSTOM_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(FengxAgriculture.MOD_ID, "item_group"));
	public static final ItemGroup CUSTOM_ITEM_GROUP = FabricItemGroup.builder()
		.icon(() -> new ItemStack(ModItems.EXAMPLE_ITEM))
		.displayName(Text.translatable("itemGroup.fabric_docs_reference"))
		.build();
	public static void initialize() {
		// Get the event for modifying entries in the ingredients group.
		// And register an event handler that adds our suspicious item to the ingredients group.
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS)
		.register((itemGroup) -> itemGroup.add(ModItems.EXAMPLE_ITEM));
		// This method is called to initialize the items in the mod.
		// You can add item registration calls here if needed.
	}
	public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
		// Create the item key.
		RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(FengxAgriculture.MOD_ID, name));

		// Create the item instance.
		Item item = itemFactory.apply(settings.registryKey(itemKey));

		// Register the item.
		Registry.register(Registries.ITEM, itemKey, item);

		return item;
	}

}