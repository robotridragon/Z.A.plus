package net.mcreator.zombieapocalypse.procedures;

import net.minecraft.util.DamageSource;
import net.minecraft.entity.Entity;

import net.mcreator.zombieapocalypse.ZombieApocalypseModElements;
import net.mcreator.zombieapocalypse.ZombieApocalypseMod;

import java.util.Map;

@ZombieApocalypseModElements.ModElement.Tag
public class HotblockProcedure extends ZombieApocalypseModElements.ModElement {
	public HotblockProcedure(ZombieApocalypseModElements instance) {
		super(instance, 64);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				ZombieApocalypseMod.LOGGER.warn("Failed to load dependency entity for procedure Hotblock!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.attackEntityFrom(DamageSource.HOT_FLOOR, (float) 2.5);
	}
}
