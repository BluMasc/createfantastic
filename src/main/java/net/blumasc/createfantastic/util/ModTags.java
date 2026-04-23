package net.blumasc.createfantastic.util;

import net.blumasc.createfantastic.CreateFantastic;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks{
        public static final TagKey<Block> ENCHANTMENT_CATALYST= createTag("enchantment_catalyst");
        public static final TagKey<Block> CURSE_CATALYST= createTag("curse_catalyst");
        public static final TagKey<Block> MUTATION_CATALYST = createTag("mutation_catalyst");
        public static final TagKey<Block> AGEING_CATALYST = createTag("ageing_catalyst");
        public static final TagKey<Block> DEAGEING_CATALYST = createTag("deageing_catalyst");
        public static final TagKey<Block> SCULKING_CATALYST = createTag("sculking_catalyst");

        private static TagKey<Block> createTag(String name){
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(CreateFantastic.MODID, name));
        }

    }
}
