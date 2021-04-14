package net.mcreator.zombieapocalypse.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.IWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.Minecraft;

import net.mcreator.zombieapocalypse.item.RifleItem;
import net.mcreator.zombieapocalypse.ZombieApocalypseModElements;
import net.mcreator.zombieapocalypse.ZombieApocalypseMod;

import java.util.Map;

@ZombieApocalypseModElements.ModElement.Tag
public class SniperscopeOnKeyReleasedProcedure extends ZombieApocalypseModElements.ModElement {
	public SniperscopeOnKeyReleasedProcedure(ZombieApocalypseModElements instance) {
		super(instance, 166);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				ZombieApocalypseMod.LOGGER.warn("Failed to load dependency entity for procedure SniperscopeOnKeyReleased!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				ZombieApocalypseMod.LOGGER.warn("Failed to load dependency world for procedure SniperscopeOnKeyReleased!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		IWorld world = (IWorld) dependencies.get("world");
		if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
				.getItem() == new ItemStack(RifleItem.block, (int) (1)).getItem())) {
			entity.getPersistentData().putBoolean("Fieldoview", (true));
		} else {
			entity.getPersistentData().putBoolean("Fieldoview", (false));
		}
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public void setupFOVPlayer(EntityViewRenderEvent.FOVModifier event) {
		if (Minecraft.getInstance().player.getPersistentData().getBoolean("Fieldoview")) {
			event.setFOV((event.getFOV() - 60));
		}
	}
}
