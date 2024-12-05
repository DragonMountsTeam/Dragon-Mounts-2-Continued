package net.dragonmounts.item;

import net.dragonmounts.init.DMItemGroups;
import net.dragonmounts.objects.entity.entitytameabledragon.EntityTameableDragon;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.util.UUID;

public class DragonArmorItem extends Item {
    public static final String TEXTURE_PREFIX = "textures/entities/armor/armor_";
    public static final UUID MODIFIER_UUID = UUID.fromString("f4dbd212-cf15-57e9-977c-0019cc5a8933");
    public final ResourceLocation texture;
    public final int protection;

    public DragonArmorItem(ResourceLocation texture, int protection) {
        this.texture = texture;
        this.protection = protection;
        this.setMaxStackSize(1);
        this.setCreativeTab(CreativeTabs.COMBAT);
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase entity, EnumHand hand) {
        if (stack.isEmpty() || !(entity instanceof EntityTameableDragon)) return false;
        EntityTameableDragon dragon = (EntityTameableDragon) entity;
        if (dragon.isArmored() || !dragon.isOwner(player)) return false;
        if (dragon.world.isRemote) return true;
        if (player.capabilities.isCreativeMode) {
            ItemStack armor = stack.copy();
            armor.setCount(1);
            dragon.setArmor(armor);
        } else {
            dragon.setArmor(stack.splitStack(1));
        }
        player.swingArm(hand);
        return true;
    }

    @Override
    public @Nonnull CreativeTabs[] getCreativeTabs() {
        return new CreativeTabs[]{DMItemGroups.COMBAT};
    }

    @Override
    protected boolean isInCreativeTab(CreativeTabs targetTab) {
        for (CreativeTabs tab : this.getCreativeTabs())
            if (tab == targetTab) return true;
        return targetTab == CreativeTabs.SEARCH;
    }
}