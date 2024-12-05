package net.dragonmounts.objects.entity.entitytameabledragon.breath.effects;

import net.dragonmounts.DragonMountsTags;
import net.dragonmounts.objects.entity.entitytameabledragon.breath.BreathNode;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

/**
 * Created by TGG on 21/06/2015.
 * EntityFX that makes up the flame breath weapon; client side.
 *
 * Usage:
 * (1) create a new FlameBreathFX using createFlameBreathFX
 * (2) spawn it as per normal
 *
 */
public class EnderBreathFX extends ClientBreathNodeEntity {
    public static final ResourceLocation DRAGON_BREATH_TEXTURE = new ResourceLocation(DragonMountsTags.MOD_ID, "textures/entities/breath_acid.png");

    /**
     * creates a single EntityFX from the given parameters.  Applies some random spread to direction.
     *
     * @param world
     * @param position              world [x,y,z] to spawn at (inates are the centre point of the fireball)
     * @param direction             initial world direction [x,y,z] - will be normalised.
     * @param power                 the power of the ball
     * @param partialTicksHeadStart if spawning multiple EntityFX per tick, use this parameter to spread the starting
     *                              location in the direction
     */
    public EnderBreathFX(World world, Vec3d position, Vec3d direction, BreathNode.Power power, float partialTicksHeadStart) {
        super(world, position, direction, power, partialTicksHeadStart, false);
    }

    @Override
    protected EnumParticleTypes getParticleType() {
        return EnumParticleTypes.DRAGON_BREATH;
    }

    @Override
    public ResourceLocation getTexture() {
        return DRAGON_BREATH_TEXTURE;
    }
}