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

public class SoldierRenderer extends HumanoidMobRenderer<SoldierEntity, HumanoidModel<SoldierEntity>> {

    private static final String TEXTURE_ROOT = "textures/entity/soldier/";

    public SoldierRenderer(EntityRendererProvider.Context context) {
        super(
            context,
            new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER)),
            0.5F
        );

        this.addLayer(
            new ItemInHandLayer<SoldierEntity, HumanoidModel<SoldierEntity>>(this)
        );

        this.addLayer(
            new HumanoidArmorLayer<
                SoldierEntity,
                HumanoidModel<SoldierEntity>,
                HumanoidModel<SoldierEntity>
            >(
                this,
                new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)),
                new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR))
            )
        );
    }

    @Override
    public ResourceLocation getTextureLocation(SoldierEntity entity) {
        String variantName = entity.getVariant().name().toLowerCase();

        return new ResourceLocation(
            FrontierMilitaryMod.MOD_ID,
            TEXTURE_ROOT + variantName + ".png"
        );
    }
}
