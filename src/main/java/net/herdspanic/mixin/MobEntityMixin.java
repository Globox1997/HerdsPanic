package net.herdspanic.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import net.herdspanic.HerdsPanicMain;
import net.herdspanic.goal.AvoidRainGoal;
import net.herdspanic.goal.EscapeRainGoal;
import net.herdspanic.mixin.accessor.MobEntityAccessor;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.World;

@Mixin(MobEntity.class)
public abstract class MobEntityMixin extends LivingEntity {

    public MobEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "initGoals", at = @At("HEAD"))
    protected void initGoals(CallbackInfo info) {
        if ((Object) this instanceof AnimalEntity) {
            AnimalEntity animalEntity = (AnimalEntity) (Object) this;
            if (!HerdsPanicMain.CONFIG.excluded_entities.contains(this.getType().toString().replace("entity.", ""))) {
                ((MobEntityAccessor) animalEntity).getGoalSelector().add(4, new AvoidRainGoal(animalEntity));
                ((MobEntityAccessor) animalEntity).getGoalSelector().add(4, new EscapeRainGoal(animalEntity, 1.1D));
            }
        }
    }

}
