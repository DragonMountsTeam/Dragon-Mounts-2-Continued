package top.dragonmounts.objects.entity.entitytameabledragon.breeds;

import top.dragonmounts.inits.ModSounds;
import top.dragonmounts.objects.entity.entitytameabledragon.EntityTameableDragon;
import top.dragonmounts.objects.entity.entitytameabledragon.breath.BreathNode;
import top.dragonmounts.objects.entity.entitytameabledragon.breath.effects.PoisonBreathFX;
import top.dragonmounts.objects.entity.entitytameabledragon.breath.sound.SoundEffectName;
import top.dragonmounts.objects.entity.entitytameabledragon.breath.sound.SoundState;
import top.dragonmounts.objects.entity.entitytameabledragon.breath.weapons.BreathWeapon;
import top.dragonmounts.objects.entity.entitytameabledragon.breath.weapons.BreathWeaponPoison;
import top.dragonmounts.objects.entity.entitytameabledragon.helper.DragonLifeStage;
import top.dragonmounts.objects.items.EnumItemBreedTypes;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class DragonBreedZombie extends DragonBreed {
    DragonBreedZombie() {
        super("zombie", 0X5e5602);

        setImmunity(DamageSource.MAGIC);
        setImmunity(DamageSource.HOT_FLOOR);
        setImmunity(DamageSource.LIGHTNING_BOLT);
        setImmunity(DamageSource.WITHER);

        setHabitatBlock(Blocks.SOUL_SAND);
        setHabitatBlock(Blocks.NETHER_WART_BLOCK);
    }

    @Override
    public void onEnable(EntityTameableDragon dragon) {}

    @Override
    public void onDisable(EntityTameableDragon dragon) {}

    @Override
    public void onDeath(EntityTameableDragon dragon) {}

    @Override
    public void spawnClientNodeEntity(World world, Vec3d position, Vec3d direction, BreathNode.Power power, float partialTicks) {
        world.spawnEntity(new PoisonBreathFX(world, position, direction, power, partialTicks));
    }

    @Override
    public SoundEvent getLivingSound(EntityTameableDragon dragon) {
        return dragon.isBaby() ?  ModSounds.ENTITY_DRAGON_HATCHLING_GROWL : ModSounds.ZOMBIE_DRAGON_GROWL;
    }


//	@Override
//	public boolean isInfertile() {
//		return true;
//	}

    @Override
    public SoundEffectName getBreathWeaponSoundEffect(DragonLifeStage stage, SoundState state) {
        return state.ice;// why
    }

    @Override
    public EnumParticleTypes getSneezeParticle() {
        return null;
    }

    @Override
    public BreathWeapon createBreathWeapon(EntityTameableDragon dragon) {
        return new BreathWeaponPoison(dragon);
    }

    @Override
    public EnumItemBreedTypes getItemBreed(EntityTameableDragon dragon) {
        return EnumItemBreedTypes.ZOMBIE;
    }
}
