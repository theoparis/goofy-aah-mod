package com.theoparis.goofyaahmod.entity.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

@OnlyIn(Dist.CLIENT)
public class LegEntityModel<T extends LivingEntityRenderState> extends EntityModel<T> {
    public final ModelPart rightLeg;
    public final ModelPart leftLeg;

    public LegEntityModel(ModelPart p_170677_) {
        this(p_170677_, RenderType::entityCutoutNoCull);
    }

    public LegEntityModel(ModelPart p_170679_, Function<ResourceLocation, RenderType> p_170680_) {
        super(p_170679_, p_170680_);
        this.rightLeg = p_170679_.getChild("right_leg");
        this.leftLeg = p_170679_.getChild("left_leg");
    }

    public void setupAnim(@NotNull T renderState) {
        super.setupAnim(renderState);

        float f = renderState.walkAnimationPos;
        float f1 = renderState.walkAnimationSpeed;
        this.rightLeg.xRot = Mth.cos(f * 0.6662F) * 1.4F * f1;
        this.leftLeg.xRot = Mth.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;
    }

    public static MeshDefinition createMesh() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();
        partDefinition.addOrReplaceChild(
                "right_leg",
                CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, CubeDeformation.NONE),
                PartPose.offset(-1.9F, 12.0F, 0.0F)
        );
        partDefinition.addOrReplaceChild(
                "left_leg",
                CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, CubeDeformation.NONE),
                PartPose.offset(1.9F, 12.0F, 0.0F)
        );

        return meshDefinition;
    }

    public static LayerDefinition createLayer() {
        return LayerDefinition.create(createMesh(), 64, 64);
    }
}