package net.mcreator.zombieapocalypse.procedures;

import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.zombieapocalypse.item.Mm9Item;
import net.mcreator.zombieapocalypse.item.AmmobagItem;
import net.mcreator.zombieapocalypse.ZombieApocalypseModElements;
import net.mcreator.zombieapocalypse.ZombieApocalypseMod;

import java.util.Map;

@ZombieApocalypseModElements.ModElement.Tag
public class IronpistolCanUseRangedItemProcedure extends ZombieApocalypseModElements.ModElement {
	public IronpistolCanUseRangedItemProcedure(ZombieApocalypseModElements instance) {
		super(instance, 97);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				ZombieApocalypseMod.LOGGER.warn("Failed to load dependency entity for procedure IronpistolCanUseRangedItem!");
			return false;
		}
		Entity entity = (Entity) dependencies.get("entity");
		boolean yesno = false;
		if (((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(Mm9Item.block, (int) (1))) : false)) {
			yesno = (boolean) (true);
		} else if (((entity instanceof PlayerEntity)
				? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(AmmobagItem.block, (int) (1)))
				: false)) {
			yesno = (boolean) (true);
		} else {
			yesno = (boolean) (false);
		}
		return (yesno);
	}
}
