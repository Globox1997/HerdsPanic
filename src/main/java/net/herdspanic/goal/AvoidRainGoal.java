package net.herdspanic.goal;

import net.herdspanic.HerdsPanicMain;
import net.minecraft.entity.ai.NavigationConditions;
import net.minecraft.entity.ai.goal.AvoidSunlightGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.TameableEntity;

public class AvoidRainGoal extends AvoidSunlightGoal {
    private final PathAwareEntity mob;

    public AvoidRainGoal(PathAwareEntity mob) {
        super(mob);
        this.mob = mob;
    }

    @Override
    public boolean canStart() {
        if (!HerdsPanicMain.CONFIG.shelter_seeking) {
            return false;
        }
        if (!NavigationConditions.hasMobNavigation(this.mob)) {
            return false;
        }
        if (!this.mob.getWorld().getLevelProperties().isRaining()) {
            return false;
        }
        if (this.mob instanceof TameableEntity tameableEntity && tameableEntity.isSitting()) {
            return false;
        }
        return true;
    }

}
