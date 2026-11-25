package com.frontiermilitary.mod.entity;

/**
 * Soldier faction. Drives default hostility: {@link #HOSTILE} soldiers
 * attack the player on sight, {@link #FRIENDLY} and {@link #NEUTRAL} do not
 * unless provoked. Visual variant (texture layer) is picked from
 * {@link SoldierVariant} independently.
 */
public enum SoldierFaction {
    FRIENDLY,
    NEUTRAL,
    HOSTILE,
    INDEPENDENT
}
