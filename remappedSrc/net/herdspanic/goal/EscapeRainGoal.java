package net.herdspanic.goal;

import java.util.EnumSet;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.FleeSunGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;

public class EscapeRainGoal extends FleeSunGoal {

    protected final PathfinderMob mob;
    private final Level world;

    public EscapeRainGoal(PathfinderMob mob, double speed) {
        super(mob, speed);
        this.mob = mob;
        this.world = mob.level();
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        if (!this.world.getLevelData().isRaining()) {
            return false;
        } else if (!this.world.canSeeSky(this.mob.blockPosition())) {
            return false;
        } else {
            return this.setWantedPos();
        }
    }

}
