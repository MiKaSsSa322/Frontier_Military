package com.frontiermilitary.mod.entity;

/**
 * Visual/role class of a soldier. Each variant maps to a texture under
 * assets/frontiermilitary/textures/entity/soldier/ (see
 * {@link com.frontiermilitary.mod.entity.client.SoldierRenderer}) and can
 * later be given its own equipment table and AI tweaks.
 */
public enum SoldierVariant {
    REGULAR,
    GUARD,
    PATROL,
    COMMANDER,
    MEDIC,
    ENGINEER,
    SCOUT,
    HEAVY
}
