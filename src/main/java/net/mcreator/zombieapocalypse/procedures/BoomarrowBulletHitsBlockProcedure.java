package net.mcreator.zombieapocalypse.procedures;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.world.Explosion;
import net.minecraft.entity.Entity;

import net.mcreator.zombieapocalypse.ZombieApocalypseModElements;
import net.mcreator.zombieapocalypse.ZombieApocalypseMod;

import java.util.Map;

@ZombieApocalypseModElements.ModElement.Tag
public class BoomarrowBulletHitsBlockProcedure extends ZombieApocalypseModElements.ModElement {
	public BoomarrowBulletHitsBlockProcedure(ZombieApocalypseModElements instance) {
		super(instance, 92);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				ZombieApocalypseMod.LOGGER.warn("Failed to load dependency entity for procedure BoomarrowBulletHitsBlock!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				ZombieApocalypseMod.LOGGER.warn("Failed to load dependency x for procedure BoomarrowBulletHitsBlock!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				ZombieApocalypseMod.LOGGER.warn("Failed to load dependency y for procedure BoomarrowBulletHitsBlock!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				ZombieApocalypseMod.LOGGER.warn("Failed to load dependency z for procedure BoomarrowBulletHitsBlock!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				ZombieApocalypseMod.LOGGER.warn("Failed to load dependency world for procedure BoomarrowBulletHitsBlock!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((((entity.getMotion().getZ()) == 0) && (((entity.getMotion().getX()) == 0) && ((entity.getMotion().getY()) == 0)))) {
			if (world instanceof World && !((World) world).isRemote) {
				((World) world).createExplosion(null, (int) x, (int) y, (int) z, (float) 4, Explosion.Mode.BREAK);
			}
		}
	}
}
