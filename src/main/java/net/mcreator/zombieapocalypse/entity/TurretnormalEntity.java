
package net.mcreator.zombieapocalypse.entity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Hand;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ActionResultType;
import net.minecraft.network.IPacket;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.ai.goal.RangedAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;

import net.mcreator.zombieapocalypse.procedures.TurretnormalOnEntityTickUpdateProcedure;
import net.mcreator.zombieapocalypse.item.IronpistolItem;
import net.mcreator.zombieapocalypse.ZombieApocalypseModElements;

import java.util.Map;
import java.util.HashMap;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@ZombieApocalypseModElements.ModElement.Tag
public class TurretnormalEntity extends ZombieApocalypseModElements.ModElement {
	public static EntityType entity = null;
	public TurretnormalEntity(ZombieApocalypseModElements instance) {
		super(instance, 93);
		FMLJavaModLoadingContext.get().getModEventBus().register(new ModelRegisterHandler());
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.CREATURE).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.4f, 0.3f)).build("turretnormal")
						.setRegistryName("turretnormal");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -10066330, -16751104, new Item.Properties().group(ItemGroup.MISC))
				.setRegistryName("turretnormal_spawn_egg"));
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		DeferredWorkQueue.runLater(this::setupAttributes);
	}
	private static class ModelRegisterHandler {
		@SubscribeEvent
		@OnlyIn(Dist.CLIENT)
		public void registerModels(ModelRegistryEvent event) {
			RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> {
				return new MobRenderer(renderManager, new Modelturretnormal(), 0.5f) {
					@Override
					public ResourceLocation getEntityTexture(Entity entity) {
						return new ResourceLocation("zombie_apocalypse:textures/turretnormal.png");
					}
				};
			});
		}
	}
	private void setupAttributes() {
		AttributeModifierMap.MutableAttribute ammma = MobEntity.func_233666_p_();
		ammma = ammma.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.3);
		ammma = ammma.createMutableAttribute(Attributes.MAX_HEALTH, 30);
		ammma = ammma.createMutableAttribute(Attributes.ARMOR, 0);
		ammma = ammma.createMutableAttribute(Attributes.ATTACK_DAMAGE, 3);
		GlobalEntityTypeAttributes.put(entity, ammma.create());
	}
	public static class CustomEntity extends CreatureEntity implements IRangedAttackMob {
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 0;
			setNoAI(false);
			enablePersistence();
		}

		@Override
		public IPacket<?> createSpawnPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			this.targetSelector.addGoal(1, new NearestAttackableTargetGoal(this, MonsterEntity.class, true, false));
			this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
			this.goalSelector.addGoal(3, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1.25, 20, 10) {
				@Override
				public boolean shouldContinueExecuting() {
					return this.shouldExecute();
				}
			});
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.ILLAGER;
		}

		@Override
		public boolean canDespawn(double distanceToClosestPlayer) {
			return false;
		}

		@Override
		public double getMountedYOffset() {
			return super.getMountedYOffset() + -0.5;
		}

		protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
			super.dropSpecialItems(source, looting, recentlyHitIn);
			this.entityDropItem(new ItemStack(Items.IRON_INGOT, (int) (1)));
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.metal.hit"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.metal.break"));
		}

		@Override
		public boolean attackEntityFrom(DamageSource source, float amount) {
			if (source.getImmediateSource() instanceof PotionEntity)
				return false;
			if (source == DamageSource.CACTUS)
				return false;
			return super.attackEntityFrom(source, amount);
		}

		@Override
		public ActionResultType func_230254_b_(PlayerEntity sourceentity, Hand hand) {
			ItemStack itemstack = sourceentity.getHeldItem(hand);
			ActionResultType retval = ActionResultType.func_233537_a_(this.world.isRemote());
			super.func_230254_b_(sourceentity, hand);
			sourceentity.startRiding(this);
			double x = this.getPosX();
			double y = this.getPosY();
			double z = this.getPosZ();
			Entity entity = this;
			return retval;
		}

		@Override
		public void baseTick() {
			super.baseTick();
			double x = this.getPosX();
			double y = this.getPosY();
			double z = this.getPosZ();
			Entity entity = this;
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				TurretnormalOnEntityTickUpdateProcedure.executeProcedure($_dependencies);
			}
		}

		public void attackEntityWithRangedAttack(LivingEntity target, float flval) {
			IronpistolItem.shoot(this, target);
		}
	}

	// Made with Blockbench 3.8.3
	// Exported for Minecraft version 1.15 - 1.16
	// Paste this class into your mod and generate all required imports
	public static class Modelturretnormal extends EntityModel<Entity> {
		private final ModelRenderer legs;
		private final ModelRenderer cube_r1;
		private final ModelRenderer cube_r2;
		private final ModelRenderer gun;
		private final ModelRenderer ammobox;
		private final ModelRenderer bb_main;
		private final ModelRenderer cube_r3;
		private final ModelRenderer cube_r4;
		public Modelturretnormal() {
			textureWidth = 64;
			textureHeight = 64;
			legs = new ModelRenderer(this);
			legs.setRotationPoint(-8.5F, 24.0F, 0.5F);
			setRotationAngle(legs, 0.0F, -1.5708F, 0.0F);
			cube_r1 = new ModelRenderer(this);
			cube_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
			legs.addChild(cube_r1);
			setRotationAngle(cube_r1, 0.48F, 0.0F, 0.0F);
			cube_r1.setTextureOffset(0, 43).addBox(-1.0F, -19.0F, -1.0F, 2.0F, 19.0F, 2.0F, 0.0F, false);
			cube_r2 = new ModelRenderer(this);
			cube_r2.setRotationPoint(0.0F, 0.0F, -17.0F);
			legs.addChild(cube_r2);
			setRotationAngle(cube_r2, -0.48F, 0.0F, 0.0F);
			cube_r2.setTextureOffset(0, 43).addBox(-1.0F, -19.0F, -1.0F, 2.0F, 19.0F, 2.0F, 0.0F, false);
			gun = new ModelRenderer(this);
			gun.setRotationPoint(2.0F, 24.0F, 0.0F);
			gun.setTextureOffset(22, 22).addBox(-2.0F, -19.25F, -20.5F, 1.0F, 1.0F, 17.0F, 0.0F, false);
			gun.setTextureOffset(0, 8).addBox(-4.5F, -21.25F, -3.5F, 5.0F, 5.0F, 11.0F, 0.0F, false);
			gun.setTextureOffset(0, 25).addBox(-2.0F, -19.25F, -22.5F, 1.0F, 1.0F, 2.0F, 0.0F, false);
			gun.setTextureOffset(0, 0).addBox(-2.0F, -20.0F, -20.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			gun.setTextureOffset(0, 0).addBox(-2.0F, -22.0F, -3.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			ammobox = new ModelRenderer(this);
			ammobox.setRotationPoint(-0.5F, -18.25F, 0.5F);
			gun.addChild(ammobox);
			setRotationAngle(ammobox, 0.0F, 1.5708F, 0.0F);
			ammobox.setTextureOffset(40, 0).addBox(-7.0F, -2.0F, 1.0F, 8.0F, 4.0F, 2.0F, 0.0F, false);
			ammobox.setTextureOffset(56, 59).addBox(-6.0F, -1.0F, 3.0F, 3.0F, 4.0F, 1.0F, 0.0F, false);
			bb_main = new ModelRenderer(this);
			bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
			cube_r3 = new ModelRenderer(this);
			cube_r3.setRotationPoint(0.0F, 0.0F, -8.0F);
			bb_main.addChild(cube_r3);
			setRotationAngle(cube_r3, -0.48F, 0.0F, 0.0F);
			cube_r3.setTextureOffset(0, 43).addBox(-1.0F, -19.0F, -1.0F, 2.0F, 19.0F, 2.0F, 0.0F, false);
			cube_r4 = new ModelRenderer(this);
			cube_r4.setRotationPoint(0.0F, 0.0F, 9.0F);
			bb_main.addChild(cube_r4);
			setRotationAngle(cube_r4, 0.48F, 0.0F, 0.0F);
			cube_r4.setTextureOffset(0, 43).addBox(-1.0F, -19.0F, -1.0F, 2.0F, 19.0F, 2.0F, 0.0F, false);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			legs.render(matrixStack, buffer, packedLight, packedOverlay);
			gun.render(matrixStack, buffer, packedLight, packedOverlay);
			bb_main.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {
			this.gun.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.gun.rotateAngleX = f4 / (180F / (float) Math.PI);
		}
	}
}
