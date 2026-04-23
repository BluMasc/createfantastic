package net.blumasc.createfantastic.fanprocessing;

import com.simibubi.create.api.registry.CreateRegistries;
import com.simibubi.create.content.kinetics.fan.processing.FanProcessingType;
import net.blumasc.createfantastic.CreateFantastic;
import net.blumasc.createfantastic.fanprocessing.custom.*;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModFanProcessingTypes {
    public static final ResourceKey<Registry<FanProcessingType>> REGISTRY_KEY = CreateRegistries.FAN_PROCESSING_TYPE;

    private static final DeferredRegister<FanProcessingType> TYPES = DeferredRegister.create(REGISTRY_KEY, CreateFantastic.MODID);

    public static final DeferredHolder<FanProcessingType, EnchantingFanProcessingType> ENCHANTING = TYPES.register("enchanting", EnchantingFanProcessingType::new);
    public static final DeferredHolder<FanProcessingType, MutatingFanProcessingType> MUTATING = TYPES.register("mutating", MutatingFanProcessingType::new);
    public static final DeferredHolder<FanProcessingType, CursingFanProcessingType> CURSING = TYPES.register("cursing", CursingFanProcessingType::new);
    public static final DeferredHolder<FanProcessingType, SculkingFanProcessingType> SCULKING = TYPES.register("sculking", SculkingFanProcessingType::new);
    public static final DeferredHolder<FanProcessingType, AgeingFanProcessingType> AGEING = TYPES.register("ageing", AgeingFanProcessingType::new);
    public static final DeferredHolder<FanProcessingType, DeageingFanProcessingType> DEAGEING = TYPES.register("deageing", DeageingFanProcessingType::new);

    public static void register(IEventBus modBus) {
        TYPES.register(modBus);
    }
}
