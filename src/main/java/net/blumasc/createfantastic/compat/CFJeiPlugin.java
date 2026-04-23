package net.blumasc.createfantastic.compat;

import com.google.common.base.Preconditions;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.Create;
import com.simibubi.create.content.kinetics.deployer.ItemApplicationRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.blumasc.createfantastic.CreateFantastic;
import net.blumasc.createfantastic.recipes.ModRecipes;
import net.blumasc.createfantastic.util.ModTags;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.loading.FMLLoader;
import org.jetbrains.annotations.ApiStatus;
import plus.dragons.createdragonsplus.util.ErrorMessages;

import java.util.LinkedHashSet;
import java.util.stream.Collectors;

@JeiPlugin
public class CFJeiPlugin implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(CreateFantastic.MODID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        if (isTagAvailable(ModTags.Blocks.ENCHANTMENT_CATALYST)) {
            registration.addRecipeCategories(FanEnchantingCategory.create());
        }
        if (isTagAvailable(ModTags.Blocks.MUTATION_CATALYST)) {
            registration.addRecipeCategories(FanMutatingCategory.create());
        }
        if (isTagAvailable(ModTags.Blocks.CURSE_CATALYST)) {
            registration.addRecipeCategories(FanCursingCategory.create());
        }
        if (isTagAvailable(ModTags.Blocks.SCULKING_CATALYST)) {
            registration.addRecipeCategories(FanSculkingCategory.create());
        }
        if (isTagAvailable(ModTags.Blocks.AGEING_CATALYST)) {
            registration.addRecipeCategories(FanAgeingCategory.create());
        }
        if (isTagAvailable(ModTags.Blocks.DEAGEING_CATALYST)) {
            registration.addRecipeCategories(FanDeageingCategory.create());
        }
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        var recipeManager = getRecipeManager();
        if (isTagAvailable(ModTags.Blocks.ENCHANTMENT_CATALYST)) {
            var enchantingRecipes = recipeManager.getAllRecipesFor(ModRecipes.ENCHANTING.getType());
            registration.addRecipes(FanEnchantingCategory.TYPE, enchantingRecipes);
        }
        if (isTagAvailable(ModTags.Blocks.MUTATION_CATALYST)) {
            var mutatingRecipes = recipeManager.getAllRecipesFor(ModRecipes.MUTATING.getType());
            registration.addRecipes(FanMutatingCategory.TYPE, mutatingRecipes);
        }
        if (isTagAvailable(ModTags.Blocks.CURSE_CATALYST)) {
            var mutatingRecipes = recipeManager.getAllRecipesFor(ModRecipes.CURSING.getType());
            registration.addRecipes(FanCursingCategory.TYPE, mutatingRecipes);
        }
        if (isTagAvailable(ModTags.Blocks.SCULKING_CATALYST)) {
            var mutatingRecipes = recipeManager.getAllRecipesFor(ModRecipes.SCULKING.getType());
            registration.addRecipes(FanSculkingCategory.TYPE, mutatingRecipes);
        }
        if (isTagAvailable(ModTags.Blocks.AGEING_CATALYST)) {
            var mutatingRecipes = recipeManager.getAllRecipesFor(ModRecipes.AGEING.getType());
            registration.addRecipes(FanAgeingCategory.TYPE, mutatingRecipes);
        }
        if (isTagAvailable(ModTags.Blocks.DEAGEING_CATALYST)) {
            var mutatingRecipes = recipeManager.getAllRecipesFor(ModRecipes.DEAGING.getType());
            registration.addRecipes(FanDeageingCategory.TYPE, mutatingRecipes);
        }

    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        if (isTagAvailable(ModTags.Blocks.ENCHANTMENT_CATALYST)) {
            registration.addRecipeCatalysts(FanEnchantingCategory.TYPE, AllBlocks.ENCASED_FAN);
            BuiltInRegistries.BLOCK
                    .getTag(ModTags.Blocks.ENCHANTMENT_CATALYST)
                    .ifPresent(tag -> tag.stream()
                            .map(holder -> holder.value().asItem())
                            .collect(Collectors.toCollection(LinkedHashSet::new))
                            .forEach(item -> registration.addRecipeCatalyst(
                                    new ItemStack(item),
                                    FanEnchantingCategory.TYPE
                            ))
                    );
        }
        if (isTagAvailable(ModTags.Blocks.MUTATION_CATALYST)) {
            registration.addRecipeCatalysts(FanMutatingCategory.TYPE, AllBlocks.ENCASED_FAN);
            BuiltInRegistries.BLOCK
                    .getTag(ModTags.Blocks.MUTATION_CATALYST)
                    .ifPresent(tag -> tag.stream()
                            .map(holder -> holder.value().asItem())
                            .collect(Collectors.toCollection(LinkedHashSet::new))
                            .forEach(item -> registration.addRecipeCatalyst(
                                    new ItemStack(item),
                                    FanMutatingCategory.TYPE
                            ))
                    );
        }
        if (isTagAvailable(ModTags.Blocks.CURSE_CATALYST)) {
            registration.addRecipeCatalysts(FanCursingCategory.TYPE, AllBlocks.ENCASED_FAN);
            BuiltInRegistries.BLOCK
                    .getTag(ModTags.Blocks.CURSE_CATALYST)
                    .ifPresent(tag -> tag.stream()
                            .map(holder -> holder.value().asItem())
                            .collect(Collectors.toCollection(LinkedHashSet::new))
                            .forEach(item -> registration.addRecipeCatalyst(
                                    new ItemStack(item),
                                    FanCursingCategory.TYPE
                            ))
                    );
        }
        if (isTagAvailable(ModTags.Blocks.SCULKING_CATALYST)) {
            registration.addRecipeCatalysts(FanSculkingCategory.TYPE, AllBlocks.ENCASED_FAN);
            BuiltInRegistries.BLOCK
                    .getTag(ModTags.Blocks.SCULKING_CATALYST)
                    .ifPresent(tag -> tag.stream()
                            .map(holder -> holder.value().asItem())
                            .collect(Collectors.toCollection(LinkedHashSet::new))
                            .forEach(item -> registration.addRecipeCatalyst(
                                    new ItemStack(item),
                                    FanSculkingCategory.TYPE
                            ))
                    );
        }
        if (isTagAvailable(ModTags.Blocks.AGEING_CATALYST)) {
            registration.addRecipeCatalysts(FanAgeingCategory.TYPE, AllBlocks.ENCASED_FAN);
            BuiltInRegistries.BLOCK
                    .getTag(ModTags.Blocks.AGEING_CATALYST)
                    .ifPresent(tag -> tag.stream()
                            .map(holder -> holder.value().asItem())
                            .collect(Collectors.toCollection(LinkedHashSet::new))
                            .forEach(item -> registration.addRecipeCatalyst(
                                    new ItemStack(item),
                                    FanAgeingCategory.TYPE
                            ))
                    );
        }
        if (isTagAvailable(ModTags.Blocks.DEAGEING_CATALYST)) {
            registration.addRecipeCatalysts(FanDeageingCategory.TYPE, AllBlocks.ENCASED_FAN);
            BuiltInRegistries.BLOCK
                    .getTag(ModTags.Blocks.DEAGEING_CATALYST)
                    .ifPresent(tag -> tag.stream()
                            .map(holder -> holder.value().asItem())
                            .collect(Collectors.toCollection(LinkedHashSet::new))
                            .forEach(item -> registration.addRecipeCatalyst(
                                    new ItemStack(item),
                                    FanDeageingCategory.TYPE
                            ))
                    );
        }
        
    }

    private static boolean isTagAvailable(TagKey<Block> blockTag) {
        return BuiltInRegistries.BLOCK
                .getTag(blockTag)
                .map(tag -> tag.stream().findAny().isPresent())
                .orElse(false);
    }

    @ApiStatus.Internal
    public static RecipeManager getRecipeManager() {
        if (FMLLoader.getDist() != Dist.CLIENT)
            throw new IllegalStateException("Retreiving recipe manager from client level is only supported for client");
        var minecraft = Minecraft.getInstance();
        Preconditions.checkNotNull(minecraft, ErrorMessages.notNull("minecraft"));
        var level = minecraft.level;
        Preconditions.checkNotNull(level, ErrorMessages.notNull("level"));
        return level.getRecipeManager();
    }
}
