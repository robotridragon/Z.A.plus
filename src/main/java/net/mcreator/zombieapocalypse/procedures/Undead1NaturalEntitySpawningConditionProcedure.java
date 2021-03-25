package net.mcreator.zombieapocalypse.procedures;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;

import net.mcreator.zombieapocalypse.ZombieApocalypseModElements;
import net.mcreator.zombieapocalypse.ZombieApocalypseMod;

import java.util.Map;

@ZombieApocalypseModElements.ModElement.Tag
public class Undead1NaturalEntitySpawningConditionProcedure extends ZombieApocalypseModElements.ModElement {
	public Undead1NaturalEntitySpawningConditionProcedure(ZombieApocalypseModElements instance) {
		super(instance, 1);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				ZombieApocalypseMod.LOGGER.warn("Failed to load dependency world for procedure Undead1NaturalEntitySpawningCondition!");
			return false;
		}
		IWorld world = (IWorld) dependencies.get("world");
		boolean yes = false;
		if ((!((world instanceof World) ? ((World) world).isDaytime() : false))) {
			yes = (boolean) (true);
		} else {
			yes = (boolean) (false);
		}
		return (yes);
	}
}
