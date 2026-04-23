package net.blumasc.createfantastic.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.simibubi.create.content.kinetics.fan.AirCurrent;
import com.simibubi.create.content.kinetics.fan.processing.FanProcessingType;
import net.blumasc.createfantastic.fanprocessing.ModFanProcessingTypes;
import net.blumasc.createfantastic.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AirCurrent.class)
public class AirCurrentMixin {
    @ModifyExpressionValue(method = "rebuild", at = @At(value = "INVOKE", target = "Lcom/simibubi/create/content/kinetics/fan/processing/FanProcessingType;getAt(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)Lcom/simibubi/create/content/kinetics/fan/processing/FanProcessingType;"))
    private @Nullable FanProcessingType rebuild$checkShimmerFluid(
            @Nullable FanProcessingType original,
            @Local(name = "world") Level world,
            @Local(name = "currentPos") BlockPos currentPos) {
        if (original != null) {
            return original;
        }

        var blockState = world.getBlockState(currentPos);

        if (blockState.is(ModTags.Blocks.ENCHANTMENT_CATALYST)) {
            return ModFanProcessingTypes.ENCHANTING.get();
        }

        if (blockState.is(ModTags.Blocks.MUTATION_CATALYST)) {
            return ModFanProcessingTypes.MUTATING.get();
        }

        if (blockState.is(ModTags.Blocks.CURSE_CATALYST)) {
            return ModFanProcessingTypes.CURSING.get();
        }

        if (blockState.is(ModTags.Blocks.SCULKING_CATALYST)) {
            return ModFanProcessingTypes.SCULKING.get();
        }

        if (blockState.is(ModTags.Blocks.AGEING_CATALYST)) {
            return ModFanProcessingTypes.AGEING.get();
        }

        if (blockState.is(ModTags.Blocks.DEAGEING_CATALYST)) {
            return ModFanProcessingTypes.DEAGEING.get();
        }

        return original;
    }
}
