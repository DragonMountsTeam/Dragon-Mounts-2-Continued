package net.dragonmounts.block.entity;

import net.dragonmounts.DragonMountsTags;
import net.dragonmounts.block.DragonCoreBlock;
import net.dragonmounts.inventory.DragonCoreContainer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;

import java.util.List;

/**
 * Dragon Core TileEntity
 *
 * @author WolfShotz
 */

public class DragonCoreBlockEntity extends TileEntityLockableLoot implements ITickable {
    private final NonNullList<ItemStack> chestContents = NonNullList.withSize(1, ItemStack.EMPTY);
    public int numPlayersUsing, ticksSinceSync;
    private float progress, progressOld;
    private DragonCoreBlockEntity.AnimationStatus animationStatus;

    public DragonCoreBlockEntity() {
        this.animationStatus = DragonCoreBlockEntity.AnimationStatus.CLOSED;
    }

    @Override
    public int getSizeInventory() {
        return 1;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean receiveClientEvent(int id, int type) {
        if (id == 1) {
            this.numPlayersUsing = type;

            if (type == 0) {
                this.animationStatus = DragonCoreBlockEntity.AnimationStatus.CLOSING;
            }

            if (type == 1) {
                this.animationStatus = DragonCoreBlockEntity.AnimationStatus.OPENING;
            }

            return true;
        } else {
            return super.receiveClientEvent(id, type);
        }
    }
    
    @Override
    public boolean isEmpty() {
        return this.chestContents.get(0).isEmpty();
    }

    @Override
    public String getName() {
        return this.hasCustomName() ? this.customName : "container.dragon_core";
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        if (!this.checkLootAndRead(compound)) {
            NBTTagCompound stack = compound.getCompoundTag("Item");
            if (!stack.isEmpty()) {
                this.chestContents.set(0, new ItemStack(stack));
            }
        }
        if (compound.hasKey("CustomName", 8)) this.customName = compound.getString("CustomName");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        if (!this.checkLootAndWrite(compound)) {
            NBTTagCompound item = new NBTTagCompound();
            this.chestContents.get(0).writeToNBT(item);
            compound.setTag("Item", item);
        }
        if (compound.hasKey("CustomName", 8)) compound.setString("CustomName", this.customName);
        return compound;
    }

    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
        return new DragonCoreContainer(playerInventory, this, playerIn);
    }

    @Override
    public String getGuiID() {
        return DragonMountsTags.MOD_ID + ":dragon_core";
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.chestContents;
    }

    /**
     * Like the old updateEntity(), except more generic.
     */
    public void update() {
        this.updateAnimation();

        if (this.animationStatus == DragonCoreBlockEntity.AnimationStatus.OPENING || this.animationStatus == DragonCoreBlockEntity.AnimationStatus.CLOSING) {
            this.moveCollidedEntities();
        }
    }

    private AxisAlignedBB getTopBoundingBox(EnumFacing p_190588_1_) {
        EnumFacing enumfacing = p_190588_1_.getOpposite();
        return this.getBoundingBox(p_190588_1_).contract((double) enumfacing.getXOffset(), (double) enumfacing.getYOffset(), (double) enumfacing.getZOffset());
    }

    public AxisAlignedBB getBoundingBox(EnumFacing p_190587_1_) {
        return Block.FULL_BLOCK_AABB.expand((double) (0.5F * this.getProgress(1.0F) * (float) p_190587_1_.getXOffset()), (double) (0.5F * this.getProgress(1.0F) * (float) p_190587_1_.getYOffset()), (double) (0.5F * this.getProgress(1.0F) * (float) p_190587_1_.getZOffset()));
    }

    private void moveCollidedEntities() {
        IBlockState iblockstate = this.world.getBlockState(this.getPos());

        if (iblockstate.getBlock() instanceof DragonCoreBlock) {
            EnumFacing enumfacing = iblockstate.getValue(BlockDirectional.FACING);
            AxisAlignedBB axisalignedbb = this.getTopBoundingBox(enumfacing).offset(this.pos);
            List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(null, axisalignedbb);

            if (!list.isEmpty()) {
                for (int i = 0; i < list.size(); ++i) {
                    Entity entity = list.get(i);

                    if (entity.getPushReaction() != EnumPushReaction.IGNORE) {
                        double d0 = 0.0D;
                        double d1 = 0.0D;
                        double d2 = 0.0D;
                        AxisAlignedBB axisalignedbb1 = entity.getEntityBoundingBox();

                        switch (enumfacing.getAxis()) {
                            case X:

                                if (enumfacing.getAxisDirection() == EnumFacing.AxisDirection.POSITIVE) {
                                    d0 = axisalignedbb.maxX - axisalignedbb1.minX;
                                } else {
                                    d0 = axisalignedbb1.maxX - axisalignedbb.minX;
                                }

                                d0 = d0 + 0.01D;
                                break;
                            case Y:

                                if (enumfacing.getAxisDirection() == EnumFacing.AxisDirection.POSITIVE) {
                                    d1 = axisalignedbb.maxY - axisalignedbb1.minY;
                                } else {
                                    d1 = axisalignedbb1.maxY - axisalignedbb.minY;
                                }

                                d1 = d1 + 0.01D;
                                break;
                            case Z:

                                if (enumfacing.getAxisDirection() == EnumFacing.AxisDirection.POSITIVE) {
                                    d2 = axisalignedbb.maxZ - axisalignedbb1.minZ;
                                } else {
                                    d2 = axisalignedbb1.maxZ - axisalignedbb.minZ;
                                }

                                d2 = d2 + 0.01D;
                        }

                        entity.move(MoverType.SHULKER_BOX, d0 * (double) enumfacing.getXOffset(), d1 * (double) enumfacing.getYOffset(), d2 * (double) enumfacing.getZOffset());
                    }
                }
            }
        }
    }

    protected void updateAnimation() {
        this.progressOld = this.progress;

        switch (this.animationStatus) {
            case CLOSED:
                this.progress = 0.0F;
                break;
            case OPENING:
                this.progress += 0.1F;
                if (this.progress >= 1.0F) {
                    this.animationStatus = DragonCoreBlockEntity.AnimationStatus.OPENED;
                    this.progress = 1.0F;
                }
                break;
            case CLOSING:
                this.progress -= 0.1F;
                if (this.progress <= 0.0F) {
                    this.animationStatus = DragonCoreBlockEntity.AnimationStatus.CLOSED;
                    this.progress = 0.0F;
                    if (!this.world.isRemote && this.isEmpty()) {
                        this.world.destroyBlock(this.getPos(), false);
                    }
                }
                break;
            case OPENED:
                this.progress = 1.0F;
        }
    }

    public DragonCoreBlockEntity.AnimationStatus getAnimationStatus() {
        return this.animationStatus;
    }


    @Override
    public void openInventory(EntityPlayer player) {
        ++this.numPlayersUsing;
        this.world.addBlockEvent(pos, this.getBlockType(), 1, this.numPlayersUsing);
        this.world.notifyNeighborsOfStateChange(pos, this.getBlockType(), false);
        if (numPlayersUsing == 1) {
            double d1 = (double) pos.getX() + 0.5D;
            double d2 = (double) pos.getZ() + 0.5D;
            this.world.playSound((EntityPlayer) null, d1, (double) pos.getY() + 0.5D, d2, SoundEvents.BLOCK_ENDERCHEST_OPEN, SoundCategory.BLOCKS, 0.9F, this.world.rand.nextFloat() * 0.1F + 0.9F);
            this.world.playSound((EntityPlayer) null, d1, (double) pos.getY() + 0.5D, d2, SoundEvents.ENTITY_ENDERDRAGON_AMBIENT, SoundCategory.HOSTILE, 0.05F, this.world.rand.nextFloat() * 0.3F + 0.9F);
            this.world.playSound((EntityPlayer) null, d1, (double) pos.getY() + 0.5D, d2, SoundEvents.BLOCK_END_PORTAL_SPAWN, SoundCategory.BLOCKS, 0.08F, this.world.rand.nextFloat() * 0.1F + 0.9F);

        }
    }

    @Override
    public void closeInventory(EntityPlayer player) {
        --this.numPlayersUsing;
        this.world.addBlockEvent(pos, this.getBlockType(), 1, this.numPlayersUsing);
        this.world.notifyNeighborsOfStateChange(pos, this.getBlockType(), false);
        if (numPlayersUsing <= 0) {
            double d3 = (double) pos.getX() + 0.5D;
            double d0 = (double) pos.getZ() + 0.5D;
            this.world.playSound((EntityPlayer) null, d3, (double) pos.getY() + 0.5D, d0, SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.BLOCKS, 0.3F, this.world.rand.nextFloat() * 0.1F + 0.3F);
            this.world.playSound((EntityPlayer) null, d3, (double) pos.getY() + 0.5D, d0, SoundEvents.ENTITY_ENDEREYE_DEATH, SoundCategory.NEUTRAL, 2.0F, this.world.rand.nextFloat() * 0.1F + 0.3F);
            this.world.playEvent(2003, this.getPos().up(), 0);
        }
    }

    public float getProgress(float p_190585_1_) {
        return this.progressOld + (this.progress - this.progressOld) * p_190585_1_;
    }

    public static enum AnimationStatus {
        CLOSED,
        OPENING,
        OPENED,
        CLOSING;
    }
}
