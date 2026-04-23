package net.blumasc.createfantastic.block;

import net.blumasc.createfantastic.CreateFantastic;
import net.blumasc.createfantastic.block.custom.CatalystBlock;
import net.blumasc.createfantastic.item.ModItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(CreateFantastic.MODID);

    public static final DeferredBlock<CatalystBlock> MUTATING_CATALYST = registerBlock("mutating_catalyst",
            () -> new CatalystBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSS_BLOCK).noOcclusion(), () -> ParticleTypes.SPORE_BLOSSOM_AIR));

    public static final DeferredBlock<CatalystBlock> ENCHANTING_CATALYST = registerBlock("enchanting_catalyst",
            () -> new CatalystBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSS_BLOCK).sound(SoundType.STONE).noOcclusion(), () -> ParticleTypes.ENCHANT));

    public static final DeferredBlock<CatalystBlock> CURSING_CATALYST = registerBlock("cursing_catalyst",
            () -> new CatalystBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSS_BLOCK).sound(SoundType.STONE).noOcclusion(), () -> ParticleTypes.WITCH));

    public static final DeferredBlock<CatalystBlock> SCULKING_CATALYST = registerBlock("sculking_catalyst",
            () -> new CatalystBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSS_BLOCK).noOcclusion(), () -> ParticleTypes.SCULK_SOUL));

    public static final DeferredBlock<CatalystBlock> AGEING_CATALYST = registerBlock("ageing_catalyst",
            () -> new CatalystBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSS_BLOCK).sound(SoundType.CROP).noOcclusion(), () -> ParticleTypes.HAPPY_VILLAGER));

    public static final DeferredBlock<CatalystBlock> DEAGEING_CATALYST = registerBlock("deageing_catalyst",
            () -> new CatalystBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSS_BLOCK).sound(SoundType.CROP).noOcclusion(), () -> ParticleTypes.HEART));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block){
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block)
    {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
