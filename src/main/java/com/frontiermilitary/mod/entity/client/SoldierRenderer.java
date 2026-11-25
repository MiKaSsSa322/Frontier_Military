package com.frontiermilitary.mod.entity.client;

import com.frontiermilitary.mod.FrontierMilitaryMod;
import com.frontiermilitary.mod.entity.SoldierEntity;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;

public class SoldierRenderer extends HumanoidMobRenderer<
        SoldierEntity,
        HumanoidModel<SoldierEntity>> {

    private static final String TEXTURE_ROOT =
            "textures/entity/soldier/";

    public SoldierRenderer(EntityRendererProvider.Context context) {
        super(
                context,
                new HumanoidModel<>(
                        context.bakeLayer(ModelLayers.PLAYER)
                ),
                0.5F
        );

        // Item in hand rendering
        this.addLayer(
                new ItemInHandLayer<>(this, context.getItemInHandRenderer())
        );

        // Armor rendering
        this.addLayer(
                new HumanoidArmorLayer<>(
                        this,
                        new HumanoidModel<>(
                                context.bakeLayer(
                                        ModelLayers.PLAYER_INNER_ARMOR
                                )
                        ),
                        new HumanoidModel<>(
                                context.bakeLayer(
                                        ModelLayers.PLAYER_OUTER_ARMOR
                                )
                        ),
                        context.getModelManager()
                )
        );
    }

    @Override
    public ResourceLocation getTextureLocation(SoldierEntity entity) {

        String variantName = entity.getVariant()
                .name()
                .toLowerCase(java.util.Locale.ROOT);

        return new ResourceLocation(
                FrontierMilitaryMod.MOD_ID,
                TEXTURE_ROOT + variantName + ".png"
        );
    }
}
