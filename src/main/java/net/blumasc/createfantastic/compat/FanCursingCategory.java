package net.blumasc.createfantastic.compat;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import com.simibubi.create.compat.jei.DoubleItemIcon;
import com.simibubi.create.compat.jei.EmptyBackground;
import com.simibubi.create.compat.jei.category.ProcessingViaFanCategory;
import com.simibubi.create.compat.jei.category.animations.AnimatedKinetics;
import net.blumasc.createfantastic.CreateFantastic;
import net.blumasc.createfantastic.recipes.ModRecipes;
import net.blumasc.createfantastic.recipes.custom.CurseRecipe;
import net.blumasc.createfantastic.recipes.custom.CurseRecipe;
import net.blumasc.createfantastic.util.ModTags;
import net.createmod.catnip.gui.element.GuiGameElement;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.Blocks;

import java.util.List;

public class FanCursingCategory extends ProcessingViaFanCategory<CurseRecipe> {
    public static final mezz.jei.api.recipe.RecipeType<RecipeHolder<CurseRecipe>> TYPE = mezz.jei.api.recipe.RecipeType.createRecipeHolderType(ModRecipes.CURSING.getId());

    private FanCursingCategory(Info<CurseRecipe> info) {
        super(info);
    }

    public static FanCursingCategory create() {
        var id = ResourceLocation.fromNamespaceAndPath(CreateFantastic.MODID, "fan_cursing");
        var title = Component.translatable("createfantastic.recipe.fan_cursing");
        var background = new EmptyBackground(178, 72);
        var itemStack = new ItemStack(BuiltInRegistries.BLOCK
                .getTag(ModTags.Blocks.CURSE_CATALYST)
                .flatMap(tag -> tag.stream().findFirst())
                .map(Holder::value)
                .orElse(Blocks.BARRIER).asItem());
        var icon = new DoubleItemIcon(AllItems.PROPELLER::asStack, () -> itemStack);

        var catalyst = AllBlocks.ENCASED_FAN.asStack();
        catalyst.set(DataComponents.CUSTOM_NAME,
                Component.translatable("createfantastic.recipe.fan_cursing.fan")
                        .withStyle(style -> style.withItalic(false)));
        var info = new Info<>(TYPE, title, background, icon, FanCursingCategory::getAllRecipes, List.of(() -> catalyst));
        return new FanCursingCategory(info);
    }

    @Override
    protected void renderAttachedBlock(GuiGraphics graphics) {
        var block = BuiltInRegistries.BLOCK
                .getTag(ModTags.Blocks.CURSE_CATALYST)
                .flatMap(tag -> tag.stream().findFirst())
                .map(Holder::value)
                .orElse(Blocks.BARRIER);
        GuiGameElement.of(block.defaultBlockState())
                .scale(SCALE)
                .atLocal(0, 0, 2)
                .lighting(AnimatedKinetics.DEFAULT_LIGHTING)
                .render(graphics);
    }

    private static List<RecipeHolder<CurseRecipe>> getAllRecipes() {
        var manager = CFJeiPlugin.getRecipeManager();
        return manager.getAllRecipesFor(ModRecipes.CURSING.getType());
    }
}