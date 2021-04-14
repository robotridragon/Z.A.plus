package net.mcreator.zombieapocalypse.procedures;

import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.zombieapocalypse.item.Ak47bullitdupeItem;
import net.mcreator.zombieapocalypse.ZombieApocalypseModElements;
import net.mcreator.zombieapocalypse.ZombieApocalypseMod;

import java.util.Random;
import java.util.Map;

@ZombieApocalypseModElements.ModElement.Tag
public class Ak47RangedItemUsedProcedure extends ZombieApocalypseModElements.ModElement {
	public Ak47RangedItemUsedProcedure(ZombieApocalypseModElements instance) {
		super(instance, 149);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				ZombieApocalypseMod.LOGGER.warn("Failed to load dependency entity for procedure Ak47RangedItemUsed!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				ZombieApocalypseMod.LOGGER.warn("Failed to load dependency itemstack for procedure Ak47RangedItemUsed!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		for (int index0 = 0; index0 < (int) (4); index0++) {
			if (entity instanceof LivingEntity) {
				Entity _ent = entity;
				if (!_ent.world.isRemote()) {
					Ak47bullitdupeItem.shoot(_ent.world, (LivingEntity) entity, new Random(), (float) 4, (float) 2, (int) 0);
				}
			}
		}
		if (entity instanceof PlayerEntity)
			((PlayerEntity) entity).getCooldownTracker().setCooldown(((itemstack)).getItem(), (int) 40);
	}
}
