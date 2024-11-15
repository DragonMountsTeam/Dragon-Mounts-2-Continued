package top.dragonmounts.objects.items;

import top.dragonmounts.DragonMounts;
import top.dragonmounts.inits.ModItems;
import top.dragonmounts.inits.ModSounds;
import top.dragonmounts.objects.entity.entitytameabledragon.EntityTameableDragon;
import top.dragonmounts.util.DMUtils;
import top.dragonmounts.util.IHasModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemDragonGender extends Item implements IHasModel
{

    public ItemDragonGender(String name)
    {
        this.setCreativeTab(DragonMounts.mainTab);
        this.setRegistryName(name);
        this.setTranslationKey(name);
        this.setMaxStackSize(1);
        
        ModItems.ITEMS.add(this);
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase target, EnumHand hand) {
        if (target.world.isRemote) return false;
        if (target instanceof EntityTameableDragon) {
            EntityTameableDragon dragon = (EntityTameableDragon) target;
            if (dragon.isTamedFor(player)) { // needs the actual owner even if dragon is unlocked
                dragon.setOppositeGender();
                dragon.world.playSound(null, player.getPosition(), ModSounds.DRAGON_SWITCH, SoundCategory.PLAYERS, 1, 1);
                if (!player.isCreative()) stack.shrink(1);
                return true;
            } else {
                player.sendStatusMessage(new TextComponentTranslation("dragon.notOwned"), true);
            }
        }
        return false;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(DMUtils.translateToLocal("item.gui.dragon_gender"));
    }
    
	@Override
	public void RegisterModels()
	{
		DragonMounts.proxy.registerItemRenderer(this, 0, "inventory");
	}
}
