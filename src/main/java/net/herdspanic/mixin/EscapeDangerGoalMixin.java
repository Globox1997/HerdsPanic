package net.herdspanic.mixin;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import net.herdspanic.HerdsPanicMain;
import net.minecraft.entity.ai.goal.EscapeDangerGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.predicate.entity.EntityPredicates;

@Mixin(EscapeDangerGoal.class)
public abstract class EscapeDangerGoalMixin extends Goal {
    @Shadow
    protected final PathAwareEntity mob;

    public EscapeDangerGoalMixin(PathAwareEntity mob, double speed) {
        this.mob = mob;
    }

    @Inject(method = "start", at = @At("HEAD"))
    public void startMixin(CallbackInfo info) {

        if (HerdsPanicMain.CONFIG.herd_panic && !HerdsPanicMain.CONFIG.excluded_entities.contains(mob.getType().toString().replace("entity.", ""))) {
            List<PathAwareEntity> list = mob.world.getEntitiesByClass(PathAwareEntity.class, mob.getBoundingBox().expand(10D), EntityPredicates.EXCEPT_SPECTATOR);
            for (int i = 0; i < list.size(); ++i) {
                PathAwareEntity entity = (PathAwareEntity) list.get(i);
                if (entity.getType() == mob.getType() && !mob.world.isClient) {
                    if (mob.getAttacker() != null && entity.getAttacker() == null && mob.distanceTo(entity) < 16F && mob.distanceTo(mob.getAttacker()) < 8F) {
                        entity.setAttacker(mob.getAttacker());
                    }
                }
            }
        }

    }

}
