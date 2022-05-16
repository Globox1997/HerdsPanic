package net.herdspanic.mixin.accessor;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.mob.MobEntity;

@Mixin(MobEntity.class)
public abstract interface MobEntityAccessor {

    @Accessor("goalSelector")
    GoalSelector getGoalSelector();

}
