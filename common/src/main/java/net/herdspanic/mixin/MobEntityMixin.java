package net.herdspanic.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import net.herdspanic.HerdsPanicMain;
import net.herdspanic.goal.AvoidRainGoal;
import net.herdspanic.goal.EscapeRainGoal;
import net.herdspanic.mixin.accessor.MobEntityAccessor;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;

@Mixin(Mob.class)
public abstract class MobEntityMixin extends LivingEntity {

    public MobEntityMixin(EntityType<? extends LivingEntity> entityType, Level world) {
        super(entityType, world);
    }

    @Inject(method = "registerGoals", at = @At("HEAD"))
    protected void initGoals(CallbackInfo info) {
        if ((Object) this instanceof Animal animalEntity) {
            if (!HerdsPanicMain.CONFIG.excluded_entities.contains(this.getType().toString().replace("entity.", "").replace(".", ":"))) {
                ((MobEntityAccessor) animalEntity).getGoalSelector().addGoal(4, new AvoidRainGoal(animalEntity));
                ((MobEntityAccessor) animalEntity).getGoalSelector().addGoal(4, new EscapeRainGoal(animalEntity, 1.1D));
            }
        }
    }

}
