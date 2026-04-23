package net.blumasc.createfantastic.recipes;

import com.simibubi.create.content.processing.recipe.StandardProcessingRecipe;
import net.blumasc.createfantastic.CreateFantastic;
import net.blumasc.createfantastic.recipes.custom.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredRegister;
import plus.dragons.createdragonsplus.common.recipe.RecipeTypeInfo;

import java.util.function.Supplier;

public class ModRecipes {
    private static final DeferredRegister<RecipeType<?>> TYPES = DeferredRegister.create(BuiltInRegistries.RECIPE_TYPE, CreateFantastic.MODID);
    private static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, CreateFantastic.MODID);

    public static final RecipeTypeInfo<EnchantingRecipe> ENCHANTING = register("enchanting", () -> new StandardProcessingRecipe.Serializer<>(EnchantingRecipe::new));
    public static final RecipeTypeInfo<MutatingRecipe> MUTATING = register("mutating", () -> new StandardProcessingRecipe.Serializer<>(MutatingRecipe::new));
    public static final RecipeTypeInfo<CurseRecipe> CURSING = register("cursing", () -> new StandardProcessingRecipe.Serializer<>(CurseRecipe::new));
    public static final RecipeTypeInfo<SculkingRecipe> SCULKING = register("sculking", () -> new StandardProcessingRecipe.Serializer<>(SculkingRecipe::new));
    public static final RecipeTypeInfo<AgeingRecipe> AGEING = register("ageing", () -> new StandardProcessingRecipe.Serializer<>(AgeingRecipe::new));
    public static final RecipeTypeInfo<DeageingRecipe> DEAGING = register("deageing", () -> new StandardProcessingRecipe.Serializer<>(DeageingRecipe::new));

    public static void register(IEventBus modBus) {
        TYPES.register(modBus);
        SERIALIZERS.register(modBus);
    }

    private static <R extends Recipe<?>> RecipeTypeInfo<R> register(String name, Supplier<? extends RecipeSerializer<R>> serializer) {
        return new RecipeTypeInfo<>(name, serializer, SERIALIZERS, TYPES);
    }
}
