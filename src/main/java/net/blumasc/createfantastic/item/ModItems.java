package net.blumasc.createfantastic.item;

import com.simibubi.create.infrastructure.data.CreateDatagen;
import net.blumasc.createfantastic.CreateFantastic;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CreateFantastic.MODID);

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
