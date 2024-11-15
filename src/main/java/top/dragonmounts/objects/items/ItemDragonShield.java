package top.dragonmounts.objects.items;

import top.dragonmounts.DragonMounts;
import top.dragonmounts.DragonMountsTags;
import top.dragonmounts.inits.ModItems;
import top.dragonmounts.util.DMUtils;
import top.dragonmounts.util.IHasModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class ItemDragonShield extends ItemShield implements IHasModel {

    public EnumItemBreedTypes type;
    public ToolMaterial material;
    public Item repair;

    public ItemDragonShield(EnumItemBreedTypes type, Item repair) {
        this.setRegistryName(new ResourceLocation(DragonMountsTags.MOD_ID, "dragon_shield_" + type.identifier));
        this.repair = repair;
        this.setTranslationKey("dragon_shield");
        this.setMaxDamage(2500);
        this.setMaxStackSize(1);
        this.type = type;

        ModItems.ITEMS.add(this);

    }

    @SideOnly(Side.CLIENT)
    private void setNameColor() {
        new ItemStack(this).setStackDisplayName(type.color + new ItemStack(this).getDisplayName());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(type.color + DMUtils.translateToLocal(type.translationKey));
    }
    
    //Necessary because were extending from ItemShield, which creates its own displayname method
    @Override
    public String getItemStackDisplayName(ItemStack stack) {return I18n.translateToLocal("item.dragon_shield.name");}

    @Override
    public boolean isShield(ItemStack stack, EntityLivingBase entity) {
        return true;
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack item) {
        return item.getItem() == repair ? true : super.getIsRepairable(toRepair, new ItemStack(repair));
    }

    /**
     * This method determines where the item is displayed
     */
    @Override
    public @Nonnull CreativeTabs[] getCreativeTabs() {
        return new CreativeTabs[]{DragonMounts.armoryTab};
    }

    @Override
    protected boolean isInCreativeTab(CreativeTabs targetTab) {
        for (CreativeTabs tab : this.getCreativeTabs())
            if (tab == targetTab) return true;
        return targetTab == CreativeTabs.SEARCH;
    }

    @Override
    public void RegisterModels() {
        DragonMounts.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
