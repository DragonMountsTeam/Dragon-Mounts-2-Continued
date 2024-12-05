package net.dragonmounts.event;

import net.dragonmounts.DragonMounts;
import net.dragonmounts.DragonMountsTags;
import net.dragonmounts.block.entity.DragonCoreBlockEntity;
import net.dragonmounts.client.gui.GuiHandler;
import net.dragonmounts.init.DMArmorEffects;
import net.dragonmounts.init.DMBlocks;
import net.dragonmounts.init.DragonTypes;
import net.dragonmounts.init.DragonVariants;
import net.dragonmounts.inits.ModItems;
import net.dragonmounts.registry.CooldownCategory;
import net.dragonmounts.registry.DragonType;
import net.dragonmounts.registry.DragonVariant;
import net.dragonmounts.util.DMUtils;
import net.dragonmounts.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.DataSerializerEntry;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Objects;

import static net.dragonmounts.DragonMounts.makeId;

@Mod.EventBusSubscriber
public class RegistryEventHandler {
	
    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();
        for (Block block : DMBlocks.BLOCKS) {
            registry.register(block);
        }
        GameRegistry.registerTileEntity(DragonCoreBlockEntity.class, makeId("dragon_core"));
        DMUtils.getLogger().info("Block Registries Successfully Registered");
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        for (Item item : ModItems.ITEMS) {
            registry.register(item);
        }
        DMUtils.getLogger().info("Item Registries Successfully Registered!");
    }

    @SubscribeEvent
    public static void registerCooldownCategory(RegistryEvent.Register<CooldownCategory> event) {
        IForgeRegistry<CooldownCategory> registry = event.getRegistry();
        registry.register(DMArmorEffects.AETHER_EFFECT);
        registry.register(DMArmorEffects.ENDER_EFFECT);
        registry.register(DMArmorEffects.FIRE_EFFECT);
        registry.register(DMArmorEffects.FOREST_EFFECT);
        registry.register(DMArmorEffects.ICE_EFFECT);
        registry.register(DMArmorEffects.NETHER_EFFECT);
        registry.register(DMArmorEffects.SUNLIGHT_EFFECT);
        registry.register(DMArmorEffects.ZOMBIE_EFFECT);
    }

    @SubscribeEvent
    public static void registerDragonType(RegistryEvent.Register<DragonType> event) {
        DragonTypes.BUILTIN_VALUES.forEach(event.getRegistry()::register);
    }

    @SubscribeEvent
    public static void registerDragonVariant(RegistryEvent.Register<DragonVariant> event) {
        DragonVariants.BUILTIN_VALUES.forEach(event.getRegistry()::register);
    }

    @SubscribeEvent
    public static void registerDataSerializer(RegistryEvent.Register<DataSerializerEntry> event) {
        IForgeRegistry<DataSerializerEntry> registry = event.getRegistry();
        registry.register(new DataSerializerEntry(DragonVariant.SERIALIZER).setRegistryName(DragonMountsTags.MOD_ID + ":dragon_variant"));
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        for (Item item : ModItems.ITEMS) {
            if (item instanceof IHasModel) {
                ((IHasModel) item).registerModel();
            } else {
                ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(Objects.requireNonNull(item.getRegistryName()), "inventory"));
            }
        }
        DMUtils.getLogger().info("Models Sucessfully Registered");
    }

    public static void preInitRegistries() {
    }

    public static void initRegistries() {
        NetworkRegistry.INSTANCE.registerGuiHandler(DragonMounts.instance, new GuiHandler());
        DMUtils.getLogger().info("Gui's Successfully Registered");
    }
}