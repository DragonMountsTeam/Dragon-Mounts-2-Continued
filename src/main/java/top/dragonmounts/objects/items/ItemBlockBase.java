package top.dragonmounts.objects.items;

import top.dragonmounts.DragonMounts;
import top.dragonmounts.DragonMountsTags;
import top.dragonmounts.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemBlockBase extends ItemBlock implements IHasModel
{
	

	public ItemBlockBase(String name, Block block) {
		super(block);
		this.setRegistryName(DragonMountsTags.MOD_ID, name);
		this.setTranslationKey(this.getRegistryName().toString());
		this.setCreativeTab(DragonMounts.mainTab);
	}

	@Override
	public void RegisterModels()
	{
		DragonMounts.proxy.registerItemRenderer(this, 0, "inventory");
	}

}
