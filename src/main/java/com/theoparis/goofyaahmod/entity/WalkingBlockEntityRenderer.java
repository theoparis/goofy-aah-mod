package com.theoparis.goofyaahmod.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.theoparis.goofyaahmod.GoofyAahClient;
import com.theoparis.goofyaahmod.entity.model.LegEntityModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.data.ModelData;
import org.jetbrains.annotations.NotNull;

public class WalkingBlockEntityRenderer extends MobRenderer<WalkingBlockEntity, WalkingBlockRenderState, LegEntityModel<WalkingBlockRenderState>> {
    public WalkingBlockEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new LegEntityModel<>(context.bakeLayer(GoofyAahClient.WALKING_BLOCK_LAYER)), 0.5F);
    }

    @Override
    public @NotNull WalkingBlockRenderState createRenderState() {
        return new WalkingBlockRenderState();
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull WalkingBlockRenderState entityRenderState) {
        return ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/zombie/zombie.png");
    }

    @Override
    public void render(@NotNull WalkingBlockRenderState renderState, @NotNull PoseStack poseStack, @NotNull MultiBufferSource multiBufferSource, int packedLight) {
        super.render(renderState, poseStack, multiBufferSource, packedLight);

        // Render the block above the legs
        poseStack.pushPose();

        poseStack.translate(-0.5D, 0.75D, -0.5D);

        Minecraft.getInstance().getBlockRenderer().renderSingleBlock(renderState.blockState, poseStack, multiBufferSource, packedLight, 0, ModelData.builder().build(), RenderType.solid());

        poseStack.popPose();
    }

    @Override
    public void extractRenderState(@NotNull WalkingBlockEntity entity, @NotNull WalkingBlockRenderState renderState, float p_361157_) {
        super.extractRenderState(entity, renderState, p_361157_);

        renderState.blockState = entity.blockState;
    }
}
