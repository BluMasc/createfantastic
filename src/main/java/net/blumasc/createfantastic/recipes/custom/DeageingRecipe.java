package net.blumasc.createfantastic.recipes.custom;

import com.simibubi.create.content.processing.recipe.ProcessingRecipeParams;
import com.simibubi.create.content.processing.recipe.StandardProcessingRecipe;
import net.blumasc.createfantastic.recipes.ModRecipes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;

public class DeageingRecipe extends StandardProcessingRecipe<SingleRecipeInput> {
    public DeageingRecipe(ProcessingRecipeParams params) {
        super(ModRecipes.DEAGING, params);
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

    public static Builder<DeageingRecipe> builder(ResourceLocation id) {
        return new Builder<>(DeageingRecipe::new, id);
    }
}