package top.dragonmounts.items;

import top.dragonmounts.DragonMounts;
import top.dragonmounts.DragonMountsConfig;
import top.dragonmounts.DragonMountsTags;
import top.dragonmounts.inits.ModItems;
import top.dragonmounts.util.IHasModel;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

/**
 * Created by TGG on 3/07/2015.
 * The Dragon Orb.
 */
public class ItemDragonOrb extends Item implements IHasModel {

  public ItemDragonOrb()
  {
    final int MAXIMUM_NUMBER_OF_ORBS = 1;
    this.setTranslationKey("dragon_orb");
    this.setRegistryName(new ResourceLocation(DragonMountsTags.MOD_ID, "dragon_orb"));
    this.setMaxStackSize(MAXIMUM_NUMBER_OF_ORBS);

    if (DragonMountsConfig.isPrototypeBreathweapons()) {
      this.setCreativeTab(DragonMounts.mainTab);
      ModItems.ITEMS.add(this);
    }
  }

  /**
   * returns the action that specifies what animation to play when the items is being used
   */
  @Override
  public EnumAction getItemUseAction(ItemStack stack)
  {
    return EnumAction.NONE;
  }

  /**
   * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
   */
  @Override
  public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand)
  {
    ItemStack itemStackIn = playerIn.getHeldItem(hand);
    playerIn.setActiveHand(hand);
    return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStackIn);
  }

  /**
   * How long to hold the block action for
   */
  @Override
  public int getMaxItemUseDuration(ItemStack stack)
  {
    return 72000;
  }

  @Override
  public void RegisterModels()
  { DragonMounts.proxy.registerItemRenderer(this, 0, "inventory"); }

}
