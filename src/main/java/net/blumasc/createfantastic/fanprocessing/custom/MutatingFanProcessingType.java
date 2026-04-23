package net.blumasc.createfantastic.fanprocessing.custom;

import com.simibubi.create.content.kinetics.fan.processing.FanProcessingType;
import com.simibubi.create.foundation.recipe.RecipeApplier;
import net.blumasc.createfantastic.recipes.ModRecipes;
import net.blumasc.createfantastic.util.ModTags;
import net.createmod.catnip.theme.Color;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MutatingFanProcessingType implements FanProcessingType {
    @Override
    public boolean isValidAt(Level level, BlockPos pos) {
        var blockState = level.getBlockState(pos);
        if (blockState.is(ModTags.Blocks.MUTATION_CATALYST)) {
            return true;
        }

        return false;
    }

    @Override
    public int getPriority() {
        return 381;
    }

    @Override
    public boolean canProcess(ItemStack stack, Level level) {
        var recipeManager = level.getRecipeManager();
        var input = new SingleRecipeInput(stack);
        return recipeManager
                .getRecipeFor(ModRecipes.MUTATING.getType(), input, level)
                .isPresent();
    }

    @Override
    public @Nullable List<ItemStack> process(ItemStack stack, Level level) {
        var recipeManager = level.getRecipeManager();
        var input = new SingleRecipeInput(stack);
        return recipeManager
                .getRecipeFor(ModRecipes.MUTATING.getType(), input, level)
                .map(recipe -> RecipeApplier.applyRecipeOn(level, stack, recipe.value(), false))
                .orElse(null);
    }

    @Override
    public void spawnProcessingParticles(Level level, Vec3 pos) {
        if (level.random.nextInt(6) == 0) {
            level.addParticle(
                    ParticleTypes.SPORE_BLOSSOM_AIR,
                    pos.x + (level.random.nextFloat() - .5f) * .5f,
                    pos.y + .5f,
                    pos.z + (level.random.nextFloat() - .5f) * .5f,
                    (level.random.nextFloat() - .5f) * 0.1f,
                    0.15f,
                    (level.random.nextFloat() - .5f) * 0.1f);
        }
    }

    @Override
    public void morphAirFlow(AirFlowParticleAccess particleAccess, RandomSource random) {
        particleAccess.setColor(Color.mixColors(0x2E6F40, 0x89F336, random.nextFloat()));
        particleAccess.setAlpha(0.5f);
    }

    @Override
    public void affectEntity(Entity target, Level level) {
        if (!(level instanceof ServerLevel server)) return;

        LightningBolt bolt = EntityType.LIGHTNING_BOLT.create(level);
        if (bolt == null) return;

        int ticks = target.getRemainingFireTicks();

        bolt.moveTo(target.position());
        bolt.setDamage(0);

        target.thunderHit(server, bolt);
        target.setRemainingFireTicks(ticks);
    }
}
