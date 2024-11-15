package top.dragonmounts.objects.entity.entitytameabledragon.breeds;

import top.dragonmounts.DragonMounts;
import top.dragonmounts.client.render.dragon.breathweaponFX.BreathWeaponFXEmitter;
import top.dragonmounts.inits.ModSounds;
import top.dragonmounts.objects.entity.entitytameabledragon.EntityTameableDragon;
import top.dragonmounts.objects.entity.entitytameabledragon.breath.BreathNode;
import top.dragonmounts.objects.entity.entitytameabledragon.breath.effects.FlameBreathFX;
import top.dragonmounts.objects.entity.entitytameabledragon.breath.nodes.BreathNodeFactory;
import top.dragonmounts.objects.entity.entitytameabledragon.breath.nodes.BreathProjectileFactory;
import top.dragonmounts.objects.entity.entitytameabledragon.breath.sound.*;
import top.dragonmounts.objects.entity.entitytameabledragon.breath.sound.*;
import top.dragonmounts.objects.entity.entitytameabledragon.breath.weapons.BreathWeapon;
import top.dragonmounts.objects.entity.entitytameabledragon.breath.weapons.BreathWeaponP;
import top.dragonmounts.objects.entity.entitytameabledragon.helper.DragonLifeStage;
import top.dragonmounts.objects.entity.entitytameabledragon.helper.util.Pair;
import top.dragonmounts.objects.items.EnumItemBreedTypes;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Base class for dragon breeds.
 */
public abstract class DragonBreed {
    protected final Random rand = new Random();
    private final String skin;
    private final int color;
    private final Set<String> immunities = new HashSet<>();
    private final Set<Block> breedBlocks = new HashSet<>();
    private final Set<Biome> biomes = new HashSet<>();

    DragonBreed(String skin, int color) {
        this.skin = skin;
        this.color = color;

        // ignore suffocation damage
        setImmunity(DamageSource.DROWN);
        setImmunity(DamageSource.IN_WALL);
        setImmunity(DamageSource.ON_FIRE);
        setImmunity(DamageSource.IN_FIRE);
        setImmunity(DamageSource.LAVA);
        setImmunity(DamageSource.HOT_FLOOR);
        setImmunity(DamageSource.CACTUS); // assume that cactus needles don't do much damage to animals with horned scales
        setImmunity(DamageSource.DRAGON_BREATH); // ignore damage from vanilla ender dragon. I kinda disabled this because it wouldn't make any sense, feel free to re enable
    }

    public String getSkin() {
        return skin;
    }

    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEFINED;
    }

    public int getColor() {
        return color;
    }

    public float getColorR() {
        return ((color >> 16) & 0xFF) / 255f;
    }

    public float getColorG() {
        return ((color >> 8) & 0xFF) / 255f;
    }

    public float getColorB() {
        return (color & 0xFF) / 255f;
    }

    protected final void setImmunity(DamageSource dmg) {
        immunities.add(dmg.damageType);
    }

    public boolean isImmuneToDamage(DamageSource dmg) {
        return !immunities.isEmpty() && immunities.contains(dmg.damageType);
    }

    protected final void setHabitatBlock(Block block) {
        breedBlocks.add(block);
    }

    public boolean isHabitatBlock(Block block) {
        return breedBlocks.contains(block);
    }

    protected final void setHabitatBiome(Biome biome) {
        biomes.add(biome);
    }

    public boolean isHabitatBiome(Biome biome) {
        return biomes.contains(biome);
    }

    public boolean isHabitatEnvironment(EntityTameableDragon dragon) {
        return false;
    }

    public Item getShrinkingFood() {
        return Items.POISONOUS_POTATO;
    }

    public Item getGrowingFood() {
        return Items.CARROT;
    }

    public Item getBreedingItem() {
        return Items.FISH;
    }

    public void onEnable(EntityTameableDragon dragon) {}

    public void onDisable(EntityTameableDragon dragon) {}

    public void onDeath(EntityTameableDragon dragon) {}

    public SoundEvent getLivingSound(EntityTameableDragon dragon) {
        if (dragon.isBaby()) {
            return ModSounds.ENTITY_DRAGON_HATCHLING_GROWL;
        } else {
            if (rand.nextInt(3) == 0) {
                return ModSounds.ENTITY_DRAGON_GROWL;
            } else {
                return ModSounds.ENTITY_DRAGON_BREATHE;
            }
        }
    }

    public SoundEvent getRoarSoundEvent(EntityTameableDragon dragon) {
        return dragon.isBaby() ? ModSounds.HATCHLING_DRAGON_ROAR : ModSounds.DRAGON_ROAR;
    }

    public SoundEvent getHurtSound() {
        return SoundEvents.ENTITY_ENDERDRAGON_HURT;
    }

    public SoundEvent getDeathSound() {
        return ModSounds.ENTITY_DRAGON_DEATH;
    }

    public SoundEvent getWingsSound() {
        return SoundEvents.ENTITY_ENDERDRAGON_FLAP;
    }

    public SoundEvent getStepSound() {
        return ModSounds.ENTITY_DRAGON_STEP;
    }

    public SoundEvent getEatSound() {
        return SoundEvents.ENTITY_GENERIC_EAT;
    }

    public SoundEvent getAttackSound() {
        return SoundEvents.ENTITY_GENERIC_EAT;
    }

    public boolean canChangeBreed() {
        return true;
    }

    public boolean canUseBreathWeapon() {
        return true;
    }

    public void spawnClientNodeEntity(World world, Vec3d position, Vec3d direction, BreathNode.Power power, float partialTicks) {
        world.spawnEntity(new FlameBreathFX(world, position, direction, power, partialTicks));
    }

    public SoundEffectName getBreathWeaponSoundEffect(DragonLifeStage stage, SoundState state) {
        return state.getSoundByAge(stage);
    }

    public void onLivingUpdate(EntityTameableDragon dragon) {

    }

    //    /**
    //     * creates a SoundEffectBreathWeapon that creates the sound from the dragon's mouth when breathing
    //     *
    //     * @return
    //     */
    //    public SoundEffectBreathWeapon getSoundEffectBreathWeapon(SoundController i_soundController, SoundEffectBreathWeapon.WeaponSoundUpdateLink i_weaponSoundUpdateLink) {
    //        return new SoundEffectBreathWeaponFire(i_soundController, i_weaponSoundUpdateLink);
    //    }

    public boolean isInfertile() {
        return false;
    }

    public SoundEvent getSneezeSound() {
        return ModSounds.DRAGON_SNEEZE;
    }

    public EnumParticleTypes getSneezeParticle() {
        return EnumParticleTypes.SMOKE_LARGE;
    }

    public BreathWeaponSpawnType getBreathWeaponSpawnType(EntityTameableDragon dragon) // todo make abstract
    {
        throw new UnsupportedOperationException();
    }
    // PROJECTILE = spawn a single Entity, similar to EntityFIreball for ghast
    // NODES = continuous stream of small nodes

    /**
     * return a new Breath Weapon FX Emitter based on breed
     *
     * @return
     */
    public BreathWeaponFXEmitter getBreathWeaponFXEmitter(EntityTameableDragon dragon) {
        throw new UnsupportedOperationException();
    }

    /**
     * return a new BreathWeapon based on breed
     *
     * @return
     */
    public BreathWeaponP getBreathWeapon(EntityTameableDragon dragon)  //todo make abstract
    {
        throw new UnsupportedOperationException();
    }

    public BreathNodeFactory getBreathNodeFactory(EntityTameableDragon dragon) {
        throw new UnsupportedOperationException();
    }

    public BreathProjectileFactory getBreathProjectileFactory(EntityTameableDragon dragon) {
        throw new UnsupportedOperationException();
    }

    /**
     * returns the range of the breath weapon
     *
     * @param dragonLifeStage
     * @return minimum range, maximum range, in blocks
     */
    public Pair<Float, Float> getBreathWeaponRange(DragonLifeStage dragonLifeStage) {
        return getBreathWeaponRangeDefault(dragonLifeStage);
    }

    /**
     * creates a SoundEffectBreathWeapon that creates the sound from the dragon's mouth when breathing
     *
     * @return
     */
    public SoundEffectBreathWeaponP getSoundEffectBreathWeapon(SoundController i_soundController,
                                                               SoundEffectBreathWeaponP.WeaponSoundUpdateLink i_weaponSoundUpdateLink) {
        return new SoundEffectBreathWeaponNull(i_soundController, i_weaponSoundUpdateLink);
    }

    private Pair<Float, Float> getBreathWeaponRangeDefault(DragonLifeStage dragonLifeStage) {
        float minAttackRange = 1.0F;
        float maxAttackRange = 1.0F;
        switch (dragonLifeStage) {
            case EGG:
                break;
            case HATCHLING: {
                minAttackRange = 2.0F;
                maxAttackRange = 4.0F;
                break;
            }
            case INFANT:
                minAttackRange = 2.0F;
                maxAttackRange = 4.0F;
                break;
            case PREJUVENILE:
                minAttackRange = 3.0F;
                maxAttackRange = 8.0F;
                break;
            case JUVENILE: {
                minAttackRange = 3.0F;
                maxAttackRange = 8.0F;
                break;
            }
            case ADULT: {
                minAttackRange = 3.0F;
                maxAttackRange = 25.0F;
                break;
            }
            default: {
                DragonMounts.loggerLimit.error_once("Unknown lifestage:" + dragonLifeStage);
                break;
            }
        }
        return new Pair<Float, Float>(minAttackRange, maxAttackRange);
    }

    /**
     * returns the width and height of the entity when it's an adult
     * later: may vary for different breeds
     *
     * @return a pair of [width, height] for the entity - affects the Axis Aligned Bounding Box
     */
    public Pair<Float, Float> getAdultEntitySize() {
        return new Pair<>(4.8F, 4.2F);
//    public static final float BASE_WIDTH = 4.8f; //2.4f;      make the adult twice the size it used to be
//    public static final float BASE_HEIGHT = 4.2F; //2.1f;      make the adult twice the size it used to be
    }

    /**
     * used when rendering; scale up the model by this factor for a fully-grown adult
     *
     * @return the relative scale factor (1.0 = no scaling)
     */
    public float getAdultModelRenderScaleFactor() {
        return 1.6F;  // I don't know why this is the magic number 1.6, it just gives the right size
    }

    /**
     * used for converting the model dimensions into world dimensions (see DragonHeadPositionHelper)
     * I don't know why this is the magic # of 0.1F
     * It's probably linked to getAdultModelRenderScaleFactor
     *
     * @return
     */
    public float getAdultModelScaleFactor() {
        return 0.1F;
    }

    /**
     * gets the position offset to use for a passenger on a fully-grown adult dragon
     *
     * @param isSitting       is the dragon sitting down?
     * @param passengerNumber the number (0.. max) of the passenger
     * @return the [x, y, z] of the mounting position relative to the dragon [posX, posY, posZ]
     * for smaller-than-adult sizes, multiply by
     */
    public Vec3d getAdultMountedPositionOffset(boolean isSitting, int passengerNumber) {
        double yoffset = (!isSitting ? 4.4 : 3.4);
        double yoffset2 = (!isSitting ? 2.5 : 2.1); // maybe not needed

        // dragon position is the middle of the model and the saddle is on
        // the shoulders, so move player forwards on Z axis relative to the
        // dragon's rotation to fix that
        switch (passengerNumber) {
            case 0:
                return new Vec3d(0, yoffset, 2.2);
            case 1:
                return new Vec3d(0.6, yoffset, 0.1);
            case 2:
                return new Vec3d(-0.6, yoffset, 0.1);
            case 3:
                return new Vec3d(1.6, yoffset2, 0.2);
            case 4:
                return new Vec3d(-1.6, yoffset2, 0.2);
        }
        DragonMounts.loggerLimit.error_once("Illegal passengerNumber:" + passengerNumber);
        return new Vec3d(0, yoffset, 2.2);
    }

    public abstract EnumItemBreedTypes getItemBreed(EntityTameableDragon dragon);

    public enum BreathWeaponSpawnType {PROJECTILE, NODES}

    public BreathWeapon createBreathWeapon(EntityTameableDragon dragon) {
        return new BreathWeapon(dragon);
    }
}

