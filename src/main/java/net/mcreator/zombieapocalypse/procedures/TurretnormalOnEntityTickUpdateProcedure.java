package net.mcreator.zombieapocalypse.procedures;

import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.zombieapocalypse.ZombieApocalypseModElements;
import net.mcreator.zombieapocalypse.ZombieApocalypseMod;

import java.util.Map;

@ZombieApocalypseModElements.ModElement.Tag
public class TurretnormalOnEntityTickUpdateProcedure extends ZombieApocalypseModElements.ModElement {
	public TurretnormalOnEntityTickUpdateProcedure(ZombieApocalypseModElements instance) {
		super(instance, 94);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				ZombieApocalypseMod.LOGGER.warn("Failed to load dependency entity for procedure TurretnormalOnEntityTickUpdate!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.SLOWNESS, (int) 20, (int) 10, (false), (false)));
	}
}
