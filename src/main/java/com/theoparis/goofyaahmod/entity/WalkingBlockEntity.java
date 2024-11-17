package com.theoparis.goofyaahmod.entity;

import com.theoparis.goofyaahmod.GoofyAahMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class WalkingBlockEntity extends Monster {
    public BlockState blockState;

    public WalkingBlockEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);

        this.blockState = BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath("minecraft", "dirt"))
                .isPresent()
                ? BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath("minecraft", "dirt")).get().value().defaultBlockState()
                : null;
    }

    @Override
    public void load(@NotNull CompoundTag compoundTag) {
        super.load(compoundTag);

        if (compoundTag.contains("blockState"))
            this.blockState = BlockState.CODEC.parse(NbtOps.INSTANCE, compoundTag.get("blockState"))
                    .resultOrPartial(GoofyAahMod.LOGGER::error)
                    .orElse(null);
    }

    @Override
    public boolean save(@NotNull CompoundTag compoundTag) {

        if (this.blockState != null) {
            var blockStateTag = BlockState.CODEC.encodeStart(NbtOps.INSTANCE, this.blockState);
            if (blockStateTag.result().isPresent())
                compoundTag.put("blockState", blockStateTag.result().get());
        }

        return super.save(compoundTag);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.FOLLOW_RANGE, 35.0)
                .add(Attributes.MOVEMENT_SPEED, 0.23F)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.ARMOR, 2.0)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0, false));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }
}
