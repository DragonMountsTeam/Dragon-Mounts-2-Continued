/*
 ** 2013 October 27
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */
package net.dragonmounts.entity.helper;

import net.dragonmounts.entity.TameableDragonEntity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.EntityDataManager;

import java.util.Random;

/**
 *
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public abstract class DragonHelper {

    protected final TameableDragonEntity dragon;
    protected final EntityDataManager dataWatcher;
    protected final Random rand;

    public DragonHelper(TameableDragonEntity dragon) {
        this.dragon = dragon;
        this.dataWatcher = dragon.getDataManager();
        this.rand = dragon.getRNG();
    }
    
    public void writeToNBT(NBTTagCompound nbt) {}
    public void readFromNBT(NBTTagCompound nbt) {}
    public void applyEntityAttributes() {}
    public void onLivingUpdate() {}
    public void onDeathUpdate() {}
    public void onDeath() {}
}
