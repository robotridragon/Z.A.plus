package net.mcreator.zombieapocalypse.procedures;

import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.zombieapocalypse.potion.CancerPotion;
import net.mcreator.zombieapocalypse.ZombieApocalypseModElements;
import net.mcreator.zombieapocalypse.ZombieApocalypseMod;

import java.util.Map;

@ZombieApocalypseModElements.ModElement.Tag
public class FragmentitearrowLivingEntityIsHitWithItemProcedure extends ZombieApocalypseModElements.ModElement {
	public FragmentitearrowLivingEntityIsHitWithItemProcedure(ZombieApocalypseModElements instance) {
		super(instance, 85);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				ZombieApocalypseMod.LOGGER.warn("Failed to load dependency entity for procedure FragmentitearrowLivingEntityIsHitWithItem!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(CancerPotion.potion, (int) 60, (int) 1));
	}
}
