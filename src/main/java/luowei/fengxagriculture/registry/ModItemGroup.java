package luowei.fengxagriculture.registry;

import luowei.fengxagriculture.FengxAgriculture;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroup {
  public static final RegistryKey<ItemGroup> CUSTOM_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(FengxAgriculture.MOD_ID, "item_group"));
  public static final ItemGroup CUSTOM_ITEM_GROUP = FabricItemGroup.builder()
      .icon(() -> new ItemStack(ModItems.EXAMPLE_ITEM))
      .displayName(Text.translatable("itemGroup.fabric_docs_reference"))
      .build();
    // 创建并构建一个自定义物品组，包含图标和显示名称的配置。  
    public static void initialize() {
		// 注册自定义物品组到注册表中，使用自定义的注册键和物品组实例
		  Registry.register(Registries.ITEM_GROUP, CUSTOM_ITEM_GROUP_KEY, CUSTOM_ITEM_GROUP);
    }
}
