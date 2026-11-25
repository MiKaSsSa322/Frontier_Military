package com.frontiermilitary.mod.entity.ai;

import com.frontiermilitary.mod.entity.SoldierEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

/**
 * Simple patrol behaviour: when the soldier has no attack target, walk to a
 * randomly chosen point within {@code radius} blocks of its home position
 * (set on spawn, see {@link SoldierEntity#getHomePosition()}), wait a bit,
 * then pick a new point.
 */
public class PatrolGoal extends Goal {

    private final SoldierEntity soldier;
    private final double speedModifier;
    private final int radius;
    private int cooldown;

    public PatrolGoal(SoldierEntity soldier, double speedModifier, int radius) {
        this.soldier = soldier;
        this.speedModifier = speedModifier;
        this.radius = radius;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        if (soldier.getTarget() != null) {
            return false;
        }
        if (cooldown > 0) {
            cooldown--;
            return false;
        }
        return soldier.getNavigation().isDone();
    }

    @Override
    public void start() {
        BlockPos home = soldier.getHomePosition() != null ? soldier.getHomePosition() : soldier.blockPosition();
        RandomSource random = soldier.getRandom();

        for (int attempt = 0; attempt < 6; attempt++) {
            double dx = (random.nextDouble() - 0.5D) * 2.0D * radius;
            double dz = (random.nextDouble() - 0.5D) * 2.0D * radius;
            Vec3 target = new Vec3(home.getX() + dx + 0.5D, home.getY(), home.getZ() + dz + 0.5D);
            if (soldier.getNavigation().moveTo(target.x, target.y, target.z, speedModifier)) {
                break;
            }
        }
        cooldown = 60 + random.nextInt(100);
    }

    @Override
    public boolean canContinueToUse() {
        return !soldier.getNavigation().isDone() && soldier.getTarget() == null;
    }
}
