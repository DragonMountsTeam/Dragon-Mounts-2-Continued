/*
 ** 2016 March 07
 **
 ** The author disclaims copyright to this source code. In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */
package top.dragonmounts.client.render.dragon.breeds;

import top.dragonmounts.DragonMountsTags;
import top.dragonmounts.client.model.dragon.DragonModel;
import top.dragonmounts.client.render.dragon.DragonRenderer;
import top.dragonmounts.client.render.dragon.layer.*;
import top.dragonmounts.client.render.dragon.layer.*;
import top.dragonmounts.objects.entity.entitytameabledragon.EntityTameableDragon;
import top.dragonmounts.objects.entity.entitytameabledragon.breeds.EnumDragonBreed;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;

import java.util.function.Consumer;

/**
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class DefaultDragonBreedRenderer {

    protected final ObjectArrayList<LayerRenderer<EntityTameableDragon>> layers = new ObjectArrayList<>();

    private final DragonRenderer renderer;
    private final DragonModel model;

    private final ResourceLocation maleBodyTexture;
    private final ResourceLocation maleGlowTexture;
    private final ResourceLocation hmaleBodyTexture;
    private final ResourceLocation hmaleGlowTexture;

    private final ResourceLocation maleBodyTexturealt;
    private final ResourceLocation maleGlowTexturealt;
    private final ResourceLocation hmaleBodyTexturealt;
    private final ResourceLocation hmaleGlowTexturealt;

    private final ResourceLocation femaleBodyTexture;
    private final ResourceLocation femaleGlowTexture;
    private final ResourceLocation hfemaleBodyTexture;
    private final ResourceLocation hfemaleGlowTexture;

    private final ResourceLocation femaleBodyTexturealt;
    private final ResourceLocation femaleGlowTexturealt;
    private final ResourceLocation hfemaleBodyTexturealt;
    private final ResourceLocation hfemaleGlowTexturealt;

    private final ResourceLocation glowAnimTexture;
    private final ResourceLocation saddleTexture;
    private final ResourceLocation eggTexture;
    private final ResourceLocation dissolveTexture;
    private final ResourceLocation chestTexture;
//    private final ResourceLocation armorTexture;

    public DefaultDragonBreedRenderer(DragonRenderer parent, EnumDragonBreed breed) {
        renderer = parent;
        model = new DragonModel(breed);

        // standard layers
        layers.add(new LayerRendererDragonGlow(parent, this, model));
//        layers.add(new LayerRendererDragonGlowAnim(parent, this, model));
        layers.add(new LayerRendererDragonSaddle(parent, this, model));
        layers.add(new LayerRendererDragonArmor(parent, this, model));
        layers.add(new LayerRendererDragonChest(parent, this, model));
        layers.add(new LayerRendererDragonBanner(parent, this, model));


        // textures
        String skin = breed.getBreed().getSkin();
        // male
        maleBodyTexture = new ResourceLocation(DragonMountsTags.MOD_ID, DragonRenderer.TEX_BASE + skin + "/bodym.png");
        maleGlowTexture = new ResourceLocation(DragonMountsTags.MOD_ID, DragonRenderer.TEX_BASE + skin + "/glowm.png");
        hmaleBodyTexture = new ResourceLocation(DragonMountsTags.MOD_ID, DragonRenderer.TEX_BASE + skin + "/hbodym.png");
        hmaleGlowTexture = new ResourceLocation(DragonMountsTags.MOD_ID, DragonRenderer.TEX_BASE + skin + "/hglowm.png");

        // female
        femaleBodyTexture = new ResourceLocation(DragonMountsTags.MOD_ID, DragonRenderer.TEX_BASE + skin + "/bodyfm.png");
        femaleGlowTexture = new ResourceLocation(DragonMountsTags.MOD_ID, DragonRenderer.TEX_BASE + skin + "/glowfm.png");
        hfemaleBodyTexture = new ResourceLocation(DragonMountsTags.MOD_ID, DragonRenderer.TEX_BASE + skin + "/hbodyfm.png");
        hfemaleGlowTexture = new ResourceLocation(DragonMountsTags.MOD_ID, DragonRenderer.TEX_BASE + skin + "/hglowfm.png");

        // male alt
        maleBodyTexturealt = new ResourceLocation(DragonMountsTags.MOD_ID, DragonRenderer.TEX_BASE + skin + "/alt/bodymalt.png");
        maleGlowTexturealt = new ResourceLocation(DragonMountsTags.MOD_ID, DragonRenderer.TEX_BASE + skin + "/alt/glowmalt.png");
        hmaleBodyTexturealt = new ResourceLocation(DragonMountsTags.MOD_ID, DragonRenderer.TEX_BASE + skin + "/alt/hbodymalt.png");
        hmaleGlowTexturealt = new ResourceLocation(DragonMountsTags.MOD_ID, DragonRenderer.TEX_BASE + skin + "/alt/hglowmalt.png");

        // female alt
        femaleBodyTexturealt = new ResourceLocation(DragonMountsTags.MOD_ID, DragonRenderer.TEX_BASE + skin + "/alt/bodyfmalt.png");
        femaleGlowTexturealt = new ResourceLocation(DragonMountsTags.MOD_ID, DragonRenderer.TEX_BASE + skin + "/alt/glowfmalt.png");
        hfemaleBodyTexturealt = new ResourceLocation(DragonMountsTags.MOD_ID, DragonRenderer.TEX_BASE + skin + "/alt/hbodyfmalt.png");
        hfemaleGlowTexturealt = new ResourceLocation(DragonMountsTags.MOD_ID, DragonRenderer.TEX_BASE + skin + "/alt/hglowfmalt.png");

        // mic
        glowAnimTexture = new ResourceLocation(DragonMountsTags.MOD_ID, DragonRenderer.TEX_BASE + skin + "/glow_anim.png");
        saddleTexture = new ResourceLocation(DragonMountsTags.MOD_ID, DragonRenderer.TEX_BASE + skin + "/saddle.png");
        eggTexture = new ResourceLocation(DragonMountsTags.MOD_ID, DragonRenderer.TEX_BASE + skin + "/egg.png");
        dissolveTexture = new ResourceLocation(DragonMountsTags.MOD_ID, DragonRenderer.TEX_BASE + "dissolve.png");
        chestTexture = new ResourceLocation(DragonMountsTags.MOD_ID, DragonRenderer.TEX_BASE + skin + "/chest.png");
    }

    public void forEachLayer(Consumer<LayerRenderer<EntityTameableDragon>> action) {
        this.layers.forEach(action);
    }

    public DragonRenderer getRenderer() {
        return renderer;
    }

    public DragonModel getModel() {
        return model;
    }


    public ResourceLocation getMaleForestBodyTexture(boolean hatchling, String type) {
        return new ResourceLocation(DragonMountsTags.MOD_ID, DragonRenderer.TEX_BASE + "forest/" + type + "/bodym" + type + ".png");
    }

    public ResourceLocation getFemaleForestBodyTexture(boolean hatchling, String type) {
        return new ResourceLocation(DragonMountsTags.MOD_ID, DragonRenderer.TEX_BASE + "forest/" + type + "/bodyfm" + type + ".png");
    }

    public ResourceLocation getMaleForestGlowTexture(boolean hatchling, String type) {
        return new ResourceLocation(DragonMountsTags.MOD_ID, DragonRenderer.TEX_BASE + "forest/" + type + "/glowfm" + type + ".png");

    }

    public ResourceLocation getFemaleForestGlowTexture(boolean hatchling, String type) {
        return new ResourceLocation(DragonMountsTags.MOD_ID, DragonRenderer.TEX_BASE + "forest/" + type + "/glowfm" + type + ".png");

    }

    public ResourceLocation getMaleBodyTexture(boolean hatchling, boolean alt) {
        if (alt) {
            return hatchling ? hmaleBodyTexturealt : maleBodyTexturealt;
        } else {
            return hatchling ? hmaleBodyTexture : maleBodyTexture;
        }
    }

    public ResourceLocation getFemaleBodyTexture(boolean hatchling, boolean alt) {
        if (alt) {
            return hatchling ? hfemaleBodyTexturealt : femaleBodyTexturealt;
        } else {
            return hatchling ? hfemaleBodyTexture : femaleBodyTexture;
        }
    }

    public ResourceLocation getMaleGlowTexture(boolean hatchling, boolean alt) {
        if (alt) {
            return hatchling ? hmaleGlowTexturealt : maleGlowTexturealt;
        } else {
            return hatchling ? hmaleGlowTexture : maleGlowTexture;
        }
    }

    public ResourceLocation getFemaleGlowTexture(boolean hatchling, boolean alt) {
        if (alt) {
            return hatchling ? hfemaleGlowTexturealt : femaleGlowTexturealt;
        } else {
            return hatchling ? hfemaleGlowTexture : femaleGlowTexture;
        }
    }

    public ResourceLocation getGlowAnimTexture() {
        return glowAnimTexture;
    }

    public ResourceLocation getSaddleTexture() {
        return saddleTexture;
    }

    public ResourceLocation getEggTexture() {
        return eggTexture;
    }

    public ResourceLocation getDissolveTexture() {
        return dissolveTexture;
    }

    public ResourceLocation getChestTexture() {
        return chestTexture;
    }

    public ResourceLocation getArmorTexture() {
        return null;
    }

}
