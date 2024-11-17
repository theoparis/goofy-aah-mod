package com.theoparis.goofyaahmod;

import com.theoparis.goofyaahmod.entity.WalkingBlockEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = GoofyAahMod.MODID, bus = EventBusSubscriber.Bus.MOD)
public class GoofyAahEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(GoofyAahMod.WALKING_BLOCK.get(), WalkingBlockEntity.createAttributes().build());
    }
}
