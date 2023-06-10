package net.herdspanic.goal;

import net.minecraft.entity.ai.NavigationConditions;
import net.minecraft.entity.ai.goal.AvoidSunlightGoal;
import net.minecraft.entity.mob.PathAwareEntity;

public class AvoidRainGoal extends AvoidSunlightGoal {
    private final PathAwareEntity mob;

    public AvoidRainGoal(PathAwareEntity mob) {
        super(mob);
        this.mob = mob;
    }

    @Override
    public boolean canStart() {
        return this.mob.getWorld().getLevelProperties().isRaining() && NavigationConditions.hasMobNavigation(this.mob);
    }

}
