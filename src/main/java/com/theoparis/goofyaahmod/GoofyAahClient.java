package com.theoparis.goofyaahmod;

import com.theoparis.goofyaahmod.entity.WalkingBlockEntityRenderer;
import com.theoparis.goofyaahmod.entity.model.LegEntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = GoofyAahMod.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class GoofyAahClient {
    public static final ModelLayerLocation WALKING_BLOCK_LAYER = new ModelLayerLocation(GoofyAahMod.id("walking_block"), "main");

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(GoofyAahClient.WALKING_BLOCK_LAYER, LegEntityModel::createLayer);
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(GoofyAahMod.WALKING_BLOCK.get(), WalkingBlockEntityRenderer::new);
    }
}
