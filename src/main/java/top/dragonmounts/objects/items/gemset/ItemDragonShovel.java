package top.dragonmounts.objects.items.gemset;

import top.dragonmounts.DragonMounts;
import top.dragonmounts.DragonMountsTags;
import top.dragonmounts.inits.ModTools;
import top.dragonmounts.objects.items.EnumItemBreedTypes;
import top.dragonmounts.util.DMUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class ItemDragonShovel extends ItemSpade {

    public EnumItemBreedTypes type;

    public ItemDragonShovel(ToolMaterial material, String unlocalizedName, EnumItemBreedTypes type) {
        super(material);
        this.setTranslationKey("dragon_shovel");
        this.setRegistryName(new ResourceLocation(DragonMountsTags.MOD_ID, unlocalizedName));
        this.type=type;
        
        ModTools.TOOLS.add(this);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(type.color + DMUtils.translateToLocal(type.translationKey));
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
}