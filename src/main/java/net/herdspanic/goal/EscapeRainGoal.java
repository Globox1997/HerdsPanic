package net.herdspanic.goal;

import java.util.EnumSet;

import net.minecraft.entity.ai.goal.EscapeSunlightGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.world.World;

public class EscapeRainGoal extends EscapeSunlightGoal {

    protected final PathAwareEntity mob;
    private final World world;

    public EscapeRainGoal(PathAwareEntity mob, double speed) {
        super(mob, speed);
        this.mob = mob;
        this.world = mob.getWorld();
        this.setControls(EnumSet.of(Goal.Control.MOVE));
    }

    @Override
    public boolean canStart() {
        if (!this.world.getLevelProperties().isRaining()) {
            return false;
        } else if (!this.world.isSkyVisible(this.mob.getBlockPos())) {
            return false;
        } else {
            return this.targetShadedPos();
        }
    }

}
