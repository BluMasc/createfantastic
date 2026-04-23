package net.blumasc.createfantastic.item;

import net.blumasc.createfantastic.CreateFantastic;
import net.blumasc.createfantastic.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CreateFantastic.MODID);

    public static final Supplier<CreativeModeTab> SELECTIVE_POWERS_TAB = CREATIVE_MODE_TAB.register("create_fantastic_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.MUTATING_CATALYST.asItem())).title(Component.translatable("itemGroup.create_fantastic"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.ENCHANTING_CATALYST);
                        output.accept(ModBlocks.CURSING_CATALYST);
                        output.accept(ModBlocks.MUTATING_CATALYST);
                        output.accept(ModBlocks.SCULKING_CATALYST);
                        output.accept(ModBlocks.AGEING_CATALYST);
                        output.accept(ModBlocks.DEAGEING_CATALYST);
                    }).build());

    public static void register(IEventBus eventBus)
    {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
