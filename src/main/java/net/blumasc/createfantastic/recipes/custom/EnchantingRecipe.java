package net.blumasc.createfantastic.recipes.custom;

import com.simibubi.create.content.processing.recipe.ProcessingRecipeParams;
import com.simibubi.create.content.processing.recipe.StandardProcessingRecipe;
import net.blumasc.createfantastic.recipes.ModRecipes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;

public class EnchantingRecipe extends StandardProcessingRecipe<SingleRecipeInput> {
    public EnchantingRecipe(ProcessingRecipeParams params) {
        super(ModRecipes.ENCHANTING, params);
    }

    @Override
    protected int getMaxInputCount() {
        return 1;
    }

    @Override
    protected int getMaxOutputCount() {
        return 12;
    }

    @Override
    public boolean matches(SingleRecipeInput input, Level level) {
        return getIngredients().getFirst().test(input.item());
    }

    public static StandardProcessingRecipe.Builder<EnchantingRecipe> builder(ResourceLocation id) {
        return new StandardProcessingRecipe.Builder<>(EnchantingRecipe::new, id);
    }
}