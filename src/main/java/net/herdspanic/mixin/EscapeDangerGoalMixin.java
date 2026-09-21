package net.herdspanic.mixin;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.At;

import net.herdspanic.HerdsPanicMain;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.PanicGoal;

@Mixin(PanicGoal.class)
public abstract class EscapeDangerGoalMixin extends Goal {

    @Shadow
    protected final PathfinderMob mob;

    public EscapeDangerGoalMixin(PathfinderMob mob, double speed) {
        this.mob = mob;
    }

    @Inject(method = "start", at = @At("HEAD"))
    public void startMixin(CallbackInfo info) {
        if (!mob.level().isClientSide() && HerdsPanicMain.CONFIG.herd_panic && !HerdsPanicMain.CONFIG.excluded_entities.contains(mob.getType().toString().replace("entity.", ""))) {
            List<PathfinderMob> list = mob.level().getEntitiesOfClass(PathfinderMob.class, mob.getBoundingBox().inflate(HerdsPanicMain.CONFIG.panic_distance),
                    EntitySelector.NO_SPECTATORS);
            for (PathfinderMob pathfinderMob : list) {
                if (pathfinderMob.getType() == mob.getType() || HerdsPanicMain.CONFIG.alert_all_animals) {
                    if (mob.getLastHurtByMob() != null && pathfinderMob.getLastHurtByMob() == null && mob.distanceTo(pathfinderMob) < HerdsPanicMain.CONFIG.panic_distance
                            && mob.distanceTo(mob.getLastHurtByMob()) < HerdsPanicMain.CONFIG.panic_distance / 2.0F) {
                        pathfinderMob.setLastHurtByMob(mob.getLastHurtByMob());
                    }
                }
            }
        }

    }

    @Inject(method = "shouldPanic", at = @At("HEAD"), cancellable = true)
    protected void isInDangerMixin(CallbackInfoReturnable<Boolean> info) {
        if (mob.getLastHurtByMob() != null) {
            info.setReturnValue(true);
        }
    }

}
