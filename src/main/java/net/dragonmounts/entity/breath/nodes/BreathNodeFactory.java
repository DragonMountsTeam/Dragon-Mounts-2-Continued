package net.dragonmounts.entity.breath.nodes;

import net.dragonmounts.entity.breath.DragonBreathMode;

/**
 * Created by TGG on 14/12/2015.
 */
public interface BreathNodeFactory
{
  public BreathNodeP createBreathNode(BreathNodeP.Power i_power, DragonBreathMode dragonBreathMode);
}
