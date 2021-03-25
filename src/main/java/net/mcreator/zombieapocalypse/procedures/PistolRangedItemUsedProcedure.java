package net.mcreator.zombieapocalypse.procedures;

import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.zombieapocalypse.item.Mm9Item;
import net.mcreator.zombieapocalypse.item.AmmobagItem;
import net.mcreator.zombieapocalypse.ZombieApocalypseModElements;
import net.mcreator.zombieapocalypse.ZombieApocalypseMod;

import java.util.Map;

@ZombieApocalypseModElements.ModElement.Tag
public class PistolRangedItemUsedProcedure extends ZombieApocalypseModElements.ModElement {
	public PistolRangedItemUsedProcedure(ZombieApocalypseModElements instance) {
		super(instance, 16);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				ZombieApocalypseMod.LOGGER.warn("Failed to load dependency entity for procedure PistolRangedItemUsed!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				ZombieApocalypseMod.LOGGER.warn("Failed to load dependency itemstack for procedure PistolRangedItemUsed!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		double fire = 0;
		fire = (double) ((fire) + 1);
		if (entity instanceof PlayerEntity)
			((PlayerEntity) entity).getCooldownTracker().setCooldown(((itemstack)).getItem(), (int) 20);
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.SLOWNESS, (int) 40, (int) 1, (false), (false)));
		if (entity instanceof PlayerEntity) {
			ItemStack _stktoremove = new ItemStack(Mm9Item.block, (int) (1));
			((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
					((PlayerEntity) entity).container.func_234641_j_());
		}
		if (((!((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(Mm9Item.block, (int) (1))) : false))
				&& (((entity instanceof PlayerEntity)
						? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(AmmobagItem.block, (int) (1)))
						: false) && ((fire) >= 1)))) {
			if (entity instanceof PlayerEntity) {
				ItemStack _stktoremove = new ItemStack(AmmobagItem.block, (int) (1));
				((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
						((PlayerEntity) entity).container.func_234641_j_());
			}
			fire = (double) 0;
		}
	}
}
