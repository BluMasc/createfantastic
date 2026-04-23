package net.blumasc.createfantastic.datagen;

import net.blumasc.createfantastic.CreateFantastic;
import net.blumasc.createfantastic.block.ModBlocks;
import net.blumasc.createfantastic.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, CreateFantastic.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModTags.Blocks.ENCHANTMENT_CATALYST)
                .add(ModBlocks.ENCHANTING_CATALYST.get());
        tag(ModTags.Blocks.MUTATION_CATALYST)
                .add(ModBlocks.MUTATING_CATALYST.get());
        tag(ModTags.Blocks.CURSE_CATALYST)
                .add(ModBlocks.CURSING_CATALYST.get());
        tag(ModTags.Blocks.SCULKING_CATALYST)
                .add(ModBlocks.SCULKING_CATALYST.get());
        tag(ModTags.Blocks.AGEING_CATALYST)
                .add(ModBlocks.AGEING_CATALYST.get());
        tag(ModTags.Blocks.DEAGEING_CATALYST)
                .add(ModBlocks.DEAGEING_CATALYST.get());
    }
}