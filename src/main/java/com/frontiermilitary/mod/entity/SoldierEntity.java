package com.frontiermilitary.mod.entity;

import com.frontiermilitary.mod.entity.ai.PatrolGoal;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;

/**
 * Soldier NPC. A single entity class covers every visual
 * {@link SoldierVariant} and {@link SoldierFaction}: both are synced entity
 * data so the renderer can pick the right texture and so faction can be
 * checked for targeting (see {@link #getFaction()}).
 * <p>
 * Behaviour:
 * - HOSTILE faction soldiers target the player automatically.
 * - FRIENDLY/NEUTRAL/INDEPENDENT soldiers never target the player unless
 *   hurt by them first (vanilla's hurt-response AI, since no hostile
 *   targeting goal is added for those factions).
 * - All soldiers patrol around their spawn position when no target is
 *   present (see {@link PatrolGoal}).
 */
public class SoldierEntity extends PathfinderMob {

    private static final EntityDataAccessor<Integer> DATA_VARIANT =
            SynchedEntityData.defineId(SoldierEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_FACTION =
            SynchedEntityData.defineId(SoldierEntity.class, EntityDataSerializers.INT);

    @Nullable
    private BlockPos homePosition;

    public SoldierEntity(EntityType<? extends SoldierEntity> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 24.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.28D)
                .add(Attributes.ATTACK_DAMAGE, 4.0D)
                .add(Attributes.FOLLOW_RANGE, 24.0D)
                .add(Attributes.ARMOR, 2.0D);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_VARIANT, SoldierVariant.REGULAR.ordinal());
        this.entityData.define(DATA_FACTION, SoldierFaction.NEUTRAL.ordinal());
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(2, new PatrolGoal(this, 0.9D, 16));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));

        if (getFaction() == SoldierFaction.HOSTILE) {
            this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        }
    }

    @Override
    public void finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType,
                               @Nullable net.minecraft.world.entity.SpawnGroupData spawnGroupData) {
        super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
        this.homePosition = this.blockPosition();
    }

    public SoldierVariant getVariant() {
        return SoldierVariant.values()[this.entityData.get(DATA_VARIANT) % SoldierVariant.values().length];
    }

    public void setVariant(SoldierVariant variant) {
        this.entityData.set(DATA_VARIANT, variant.ordinal());
    }

    public SoldierFaction getFaction() {
        return SoldierFaction.values()[this.entityData.get(DATA_FACTION) % SoldierFaction.values().length];
    }

    public void setFaction(SoldierFaction faction) {
        this.entityData.set(DATA_FACTION, faction.ordinal());
    }

    @Nullable
    public BlockPos getHomePosition() {
        return homePosition;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("Variant", getVariant().ordinal());
        tag.putInt("Faction", getFaction().ordinal());
        if (homePosition != null) {
            tag.putLong("HomePos", homePosition.asLong());
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("Variant")) {
            setVariant(SoldierVariant.values()[tag.getInt("Variant") % SoldierVariant.values().length]);
        }
        if (tag.contains("Faction")) {
            setFaction(SoldierFaction.values()[tag.getInt("Faction") % SoldierFaction.values().length]);
        }
        if (tag.contains("HomePos")) {
            homePosition = BlockPos.of(tag.getLong("HomePos"));
        }
    }

    @Override
    public boolean removeWhenFarAway(double distanceSq) {
        return false;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevelAccessor level, AgeableMob otherParent) {
        return null;
    }
}
