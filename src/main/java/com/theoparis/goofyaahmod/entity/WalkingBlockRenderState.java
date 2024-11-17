package com.theoparis.goofyaahmod.entity;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.level.block.state.BlockState;

public class WalkingBlockRenderState extends LivingEntityRenderState {
    public BlockState blockState;

    public WalkingBlockRenderState(BlockState blockState) {
        this.blockState = null;
    }

    public WalkingBlockRenderState() {
    }

}
