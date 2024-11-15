package top.dragonmounts.capability;

import top.dragonmounts.api.IArmorEffect;
import top.dragonmounts.registry.CooldownCategory;
import net.minecraft.nbt.NBTTagCompound;

public interface IArmorEffectManager {
    @SuppressWarnings("UnusedReturnValue")
    int stackLevel(IArmorEffect effect);

    @SuppressWarnings("UnusedReturnValue")
    int setLevel(IArmorEffect effect, int level);

    int getLevel(IArmorEffect effect, boolean filtered);

    boolean isActive(IArmorEffect effect);

    void setCooldown(CooldownCategory category, int cooldown);

    int getCooldown(CooldownCategory category);

    boolean isAvailable(CooldownCategory category);

    void tick();

    NBTTagCompound saveNBT();

    void readNBT(NBTTagCompound tag);

    void sendInitPacket();

    interface Provider {
        default ArmorEffectManager dragonmounts$getManager() {
            throw new NullPointerException();
        }
    }
}
