package com.frontiermilitary.mod.item;

import com.frontiermilitary.mod.sound.ModSounds;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.Optional;

/**
 * Hitscan firearm base class (rifle / shotgun / pistol share this behaviour,
 * only damage / cooldown / range / ammo item differ via the constructor).
 * <p>
 * This is a deliberately simple, dependency-free weapon implementation:
 * right-click performs a server-side ray trace, deals direct damage, plays
 * a sound and spawns vanilla particles along the trace. It intentionally
 * does not use a custom projectile entity or a custom item renderer yet -
 * that is the natural next step once a modelled weapon (GeckoLib, or a
 * custom BlockEntityWithoutLevelRenderer) is layered on top of this class.
 */
public class FirearmItem extends Item {

    private final float damage;
    private final double range;
    private final int cooldownTicks;
    private final Item ammoItem;

    public FirearmItem(Properties properties, float damage, double range, int cooldownTicks, Item ammoItem) {
        super(properties);
        this.damage = damage;
        this.range = range;
        this.cooldownTicks = cooldownTicks;
        this.ammoItem = ammoItem;
    }

    public FirearmItem(Properties properties, float damage, double range, int cooldownTicks) {
        this(properties, damage, range, cooldownTicks, null);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (player.getCooldowns().isOnCooldown(this)) {
            return InteractionResultHolder.fail(stack);
        }

        boolean hasAmmo = ammoItem == null || player.getAbilities().instabuild
                || findAmmo(player) != null;
        if (!hasAmmo) {
            level.playSound(null, player.blockPosition(), ModSounds.WEAPON_DRY_FIRE.get(),
                    SoundSource.PLAYERS, 1.0F, 1.0F);
            player.getCooldowns().addCooldown(this, 10);
            return InteractionResultHolder.fail(stack);
        }

        if (!level.isClientSide) {
            fire(level, player);
            if (ammoItem != null && !player.getAbilities().instabuild) {
                ItemStack ammo = findAmmo(player);
                if (ammo != null) {
                    ammo.shrink(1);
                }
            }
        }

        player.getCooldowns().addCooldown(this, cooldownTicks);
        player.swing(hand, true);
        return InteractionResultHolder.success(stack);
    }

    private ItemStack findAmmo(Player player) {
        for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
            ItemStack candidate = player.getInventory().getItem(i);
            if (candidate.is(ammoItem)) {
                return candidate;
            }
        }
        return null;
    }

    private void fire(Level level, Player player) {
        level.playSound(null, player.blockPosition(), ModSounds.WEAPON_FIRE.get(),
                SoundSource.PLAYERS, 1.0F, 1.0F + (level.random.nextFloat() - 0.5F) * 0.1F);

        Vec3 start = player.getEyePosition();
        Vec3 look = player.getViewVector(1.0F);
        Vec3 end = start.add(look.x * range, look.y * range, look.z * range);

        HitResult blockHit = level.clip(new ClipContext(start, end,
                ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player));
        double maxDistSq = blockHit.getType() == HitResult.Type.MISS
                ? range * range
                : blockHit.getLocation().distanceToSqr(start);

        LivingEntity target = null;
        Vec3 hitPos = blockHit.getLocation();

        for (LivingEntity candidate : level.getEntitiesOfClass(LivingEntity.class,
                player.getBoundingBox().expandTowards(look.x * range, look.y * range, look.z * range).inflate(1.0D),
                e -> e != player && e.isAlive())) {
            Optional<Vec3> optionalHit = candidate.getBoundingBox().inflate(0.3D).clip(start, end);
            if (optionalHit.isPresent()) {
                double distSq = optionalHit.get().distanceToSqr(start);
                if (distSq < maxDistSq) {
                    maxDistSq = distSq;
                    target = candidate;
                    hitPos = optionalHit.get();
                }
            }
        }

        if (target != null) {
            target.hurt(level.damageSources().playerAttack(player), damage);
        }

        if (level instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.CRIT, hitPos.x, hitPos.y, hitPos.z,
                    6, 0.1, 0.1, 0.1, 0.05);
            serverLevel.sendParticles(ParticleTypes.SMOKE, start.x, start.y - 0.1, start.z,
                    3, 0.05, 0.05, 0.05, 0.01);
        }
    }

    public float getDamage() {
        return damage;
    }
}
