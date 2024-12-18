package net.dragonmounts.client.render.dragon.layer;

import net.dragonmounts.entity.TameableDragonEntity;
import net.dragonmounts.inventory.DragonInventory;
import net.dragonmounts.util.math.Interpolation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBanner;
import net.minecraft.client.renderer.BannerTextures;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraft.util.ResourceLocation;

public class LayerRendererDragonBanner extends DragonLayerRenderer {
    private final ModelBanner bannerModel=new ModelBanner();
    private final TileEntityBanner banner1=new TileEntityBanner();
    private final TileEntityBanner banner2=new TileEntityBanner();
    private final TileEntityBanner banner3=new TileEntityBanner();
    private final TileEntityBanner banner4=new TileEntityBanner();

    @Override
    public void doRenderLayer(TameableDragonEntity dragon, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        DragonInventory inventory = dragon.inventory;
        ItemStack itemstack1 = inventory.getBanner(0);
        ItemStack itemstack2 = inventory.getBanner(1);
        ItemStack itemstack3 = inventory.getBanner(2);
        ItemStack itemstack4 = inventory.getBanner(3);

        Minecraft mc = Minecraft.getMinecraft();

        GlStateManager.pushMatrix();

        if (itemstack1.getItem() == Items.BANNER) {
            banner1.setItemValues(itemstack1, false);
            model.body.postRender(0.0625F);
            ResourceLocation resourcelocation1=this.getBannerResourceLocation(banner1);
            //lower x++ higher x--
            GlStateManager.translate(0.7F, 0.0, Interpolation.smoothStep(-2.6F, -0.6F, dragon.getAnimator().getSpeed())); // all of it is get speed or one was pitch?
            // higher y-- lower y++
            GlStateManager.translate(0, Interpolation.smoothStep(0.2F, dragon.getAnimator().getModelOffsetY() + 1.2F, dragon.getAnimator().getSpeed()), 0);
            GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.rotate(-dragon.getBodyPitch(), 0.0F, 0.0F, 1.0F);
            GlStateManager.scale(0.625F, -0.625F, -0.625F);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1);
            renderer.bindTexture(resourcelocation1);
            bannerModel.bannerStand.showModel=false;
            bannerModel.renderBanner();

        }

        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();

        if (itemstack2.getItem() == Items.BANNER) {
            banner2.setItemValues(itemstack2, false);
            model.body.postRender(0.0625F);
            ResourceLocation resourcelocation2=this.getBannerResourceLocation(banner2);
            //lower x++ higher x--
            GlStateManager.translate(-0.7F, 0.0, Interpolation.smoothStep(-2.6F, -0.6F, dragon.getAnimator().getSpeed()));
            // higher y-- lower y++
            GlStateManager.translate(0, Interpolation.smoothStep(0.2F, dragon.getAnimator().getModelOffsetY() + 1.2F, dragon.getAnimator().getSpeed()), 0);
            GlStateManager.rotate(-90.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(-180.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.rotate(dragon.getBodyPitch(), 0.0F, 0.0F, 1.0F);
            GlStateManager.scale(0.625F, -0.625F, -0.625F);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1);
            renderer.bindTexture(resourcelocation2);
            bannerModel.bannerStand.showModel=false;
            bannerModel.renderBanner();

        }

        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();

        if (itemstack3.getItem() == Items.BANNER) {
            banner3.setItemValues(itemstack3, false);
            model.body.postRender(0.0625F);
            ResourceLocation resourcelocation3=this.getBannerResourceLocation(banner3);
            GlStateManager.translate(-0.4F, -1.7F, 1.7F);
            GlStateManager.translate(0, Interpolation.smoothStep(2.9F, dragon.getAnimator().getModelOffsetY() + 1.7F, dragon.getAnimator().getSpeed()), 0);
            GlStateManager.translate(0, 0, Interpolation.smoothStep(-2.3F, dragon.getAnimator().getModelOffsetZ() + 1.0F, dragon.getAnimator().getSpeed()));
            GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.rotate(dragon.getBodyPitch(), 1.0F, 0.0F, 0.0F);
            GlStateManager.scale(0.525F, -0.625F, -0.625F);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1);
            renderer.bindTexture(resourcelocation3);
            bannerModel.bannerStand.showModel=false;
            bannerModel.renderBanner();
        }

        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();

        if (itemstack4.getItem() == Items.BANNER) {
            banner4.setItemValues(itemstack4, false);
            model.body.postRender(0.0625F);
            ResourceLocation resourcelocation4=this.getBannerResourceLocation(banner4);
            GlStateManager.translate(0.4F, -1.7F, 1.7F);
            GlStateManager.translate(0, Interpolation.smoothStep(2.9F, dragon.getAnimator().getModelOffsetY() + 1.7F, dragon.getAnimator().getSpeed()), 0);
            GlStateManager.translate(0, 0, Interpolation.smoothStep(-2.3F, dragon.getAnimator().getModelOffsetZ() + 1.0F, dragon.getAnimator().getSpeed()));
            GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.rotate(dragon.getBodyPitch(), 1.0F, 0.0F, 0.0F);
            GlStateManager.scale(0.525F, -0.625F, -0.625F);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1);
            renderer.bindTexture(resourcelocation4);
            bannerModel.bannerStand.showModel=false;
            bannerModel.renderBanner();

        }

        GlStateManager.popMatrix();
    }

    private ResourceLocation getBannerResourceLocation(TileEntityBanner bannerObj) {
        return BannerTextures.BANNER_DESIGNS.getResourceLocation(bannerObj.getPatternResourceLocation(), bannerObj.getPatternList(), bannerObj.getColorList());
    }

    @Override
    public boolean shouldCombineTextures() {
        return true;
    }
}