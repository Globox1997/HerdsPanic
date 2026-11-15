package net.herdspanic.goal;

import net.herdspanic.HerdsPanicMain;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.RestrictSunGoal;
import net.minecraft.world.entity.ai.util.GoalUtils;

public class AvoidRainGoal extends RestrictSunGoal {
    private final PathfinderMob mob;

    public AvoidRainGoal(PathfinderMob mob) {
        super(mob);
        this.mob = mob;
    }

    @Override
    public boolean canUse() {
        if (!HerdsPanicMain.CONFIG.shelter_seeking) {
            return false;
        }
        if (!GoalUtils.hasGroundPathNavigation(this.mob)) {
            return false;
        }
        if (!this.mob.level().isRaining()) {
            return false;
        }
        if (this.mob instanceof TamableAnimal tameableEntity && tameableEntity.isOrderedToSit()) {
            return false;
        }
        return true;
    }

}
