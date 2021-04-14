
package net.mcreator.zombieapocalypse.potion;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;

import net.minecraft.world.World;
import net.minecraft.util.ResourceLocation;
import net.minecraft.potion.Potion;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effect;
import net.minecraft.entity.LivingEntity;

import net.mcreator.zombieapocalypse.procedures.RedstonesicknessrealOnPotionActiveTickProcedure;
import net.mcreator.zombieapocalypse.ZombieApocalypseModElements;

import java.util.Map;
import java.util.HashMap;

@ZombieApocalypseModElements.ModElement.Tag
public class RedstonesicknessrealPotion extends ZombieApocalypseModElements.ModElement {
	@ObjectHolder("zombie_apocalypse:redstonesicknessreal")
	public static final Effect potion = null;
	@ObjectHolder("zombie_apocalypse:redstonesicknessreal")
	public static final Potion potionType = null;
	public RedstonesicknessrealPotion(ZombieApocalypseModElements instance) {
		super(instance, 115);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@SubscribeEvent
	public void registerEffect(RegistryEvent.Register<Effect> event) {
		event.getRegistry().register(new EffectCustom());
	}

	@SubscribeEvent
	public void registerPotion(RegistryEvent.Register<Potion> event) {
		event.getRegistry().register(new PotionCustom());
	}
	public static class PotionCustom extends Potion {
		public PotionCustom() {
			super(new EffectInstance(potion, 3600));
			setRegistryName("redstonesicknessreal");
		}
	}

	public static class EffectCustom extends Effect {
		private final ResourceLocation potionIcon;
		public EffectCustom() {
			super(EffectType.HARMFUL, -3407821);
			setRegistryName("redstonesicknessreal");
			potionIcon = new ResourceLocation("zombie_apocalypse:textures/overlay.png");
		}

		@Override
		public String getName() {
			return "effect.redstonesicknessreal";
		}

		@Override
		public boolean isBeneficial() {
			return false;
		}

		@Override
		public boolean isInstant() {
			return false;
		}

		@Override
		public boolean shouldRenderInvText(EffectInstance effect) {
			return true;
		}

		@Override
		public boolean shouldRender(EffectInstance effect) {
			return true;
		}

		@Override
		public boolean shouldRenderHUD(EffectInstance effect) {
			return true;
		}

		@Override
		public void performEffect(LivingEntity entity, int amplifier) {
			World world = entity.world;
			double x = entity.getPosX();
			double y = entity.getPosY();
			double z = entity.getPosZ();
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				RedstonesicknessrealOnPotionActiveTickProcedure.executeProcedure($_dependencies);
			}
		}

		@Override
		public boolean isReady(int duration, int amplifier) {
			return true;
		}
	}
}
