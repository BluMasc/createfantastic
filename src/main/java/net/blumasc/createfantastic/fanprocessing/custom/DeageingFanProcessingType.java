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
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DeageingFanProcessingType implements FanProcessingType {
    @Override
    public boolean isValidAt(Level level, BlockPos pos) {
        var blockState = level.getBlockState(pos);
        if (blockState.is(ModTags.Blocks.DEAGEING_CATALYST)) {
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
                .getRecipeFor(ModRecipes.DEAGING.getType(), input, level)
                .isPresent();
    }

    @Override
    public @Nullable List<ItemStack> process(ItemStack stack, Level level) {
        var recipeManager = level.getRecipeManager();
        var input = new SingleRecipeInput(stack);
        return recipeManager
                .getRecipeFor(ModRecipes.DEAGING.getType(), input, level)
                .map(recipe -> RecipeApplier.applyRecipeOn(level, stack, recipe.value(), false))
                .orElse(null);
    }

    @Override
    public void spawnProcessingParticles(Level level, Vec3 pos) {
        if (level.random.nextInt(6) == 0) {
            level.addParticle(
                    ParticleTypes.HEART,
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
        particleAccess.setColor(Color.mixColors(0xd1bc8a, 0xd14152, random.nextFloat()));
        particleAccess.setAlpha(0.7f);
    }

    @Override
    public void affectEntity(Entity entity, Level level) {
        if (!(level instanceof ServerLevel server)) return;
        if(level.random.nextFloat()>0.1) return;
        if (entity instanceof AgeableMob ageable) {
            if (ageable.isBaby()) {
                ageable.setAge(Math.max(ageable.getAge() - 25, AgeableMob.BABY_START_AGE));
            } else {
                ageable.setAge(Math.min(ageable.getAge()-25, 4000));
            }
        }
    }
}
