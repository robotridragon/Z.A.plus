
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

import net.minecraft.world.server.ServerBossInfo;
import net.minecraft.world.World;
import net.minecraft.world.BossInfo;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.network.IPacket;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.BreakDoorGoal;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.block.BlockState;

import net.mcreator.zombieapocalypse.procedures.ThedeadoneOnEntityTickUpdateProcedure;
import net.mcreator.zombieapocalypse.procedures.ThedeadoneEntityIsHurtProcedure;
import net.mcreator.zombieapocalypse.procedures.ThedeadoneEntityDiesProcedure;
import net.mcreator.zombieapocalypse.ZombieApocalypseModElements;

import java.util.Random;
import java.util.Map;
import java.util.HashMap;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@ZombieApocalypseModElements.ModElement.Tag
public class ThedeadoneEntity extends ZombieApocalypseModElements.ModElement {
	public static EntityType entity = null;
	public ThedeadoneEntity(ZombieApocalypseModElements instance) {
		super(instance, 32);
		FMLJavaModLoadingContext.get().getModEventBus().register(new ModelRegisterHandler());
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(200).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.6f, 3.8f)).build("thedeadone")
						.setRegistryName("thedeadone");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -16695546, -11468800, new Item.Properties().group(ItemGroup.MISC))
				.setRegistryName("thedeadone_spawn_egg"));
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
				return new MobRenderer(renderManager, new Modelthedeadone(), 0.5f) {
					{
						this.addLayer(new GlowingLayer<>(this));
					}
					@Override
					public ResourceLocation getEntityTexture(Entity entity) {
						return new ResourceLocation("zombie_apocalypse:textures/thedeadone.png");
					}
				};
			});
		}
	}
	private void setupAttributes() {
		AttributeModifierMap.MutableAttribute ammma = MobEntity.func_233666_p_();
		ammma = ammma.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.27);
		ammma = ammma.createMutableAttribute(Attributes.MAX_HEALTH, 150);
		ammma = ammma.createMutableAttribute(Attributes.ARMOR, 8);
		ammma = ammma.createMutableAttribute(Attributes.ATTACK_DAMAGE, 15);
		ammma = ammma.createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE, 4.699999999999999);
		ammma = ammma.createMutableAttribute(Attributes.ATTACK_KNOCKBACK, 1.6);
		GlobalEntityTypeAttributes.put(entity, ammma.create());
	}
	public static class CustomEntity extends MonsterEntity {
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 50;
			setNoAI(false);
			setCustomName(new StringTextComponent("THE DEAD ONE"));
			setCustomNameVisible(true);
			enablePersistence();
		}

		@Override
		public IPacket<?> createSpawnPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			this.targetSelector.addGoal(1, new NearestAttackableTargetGoal(this, PlayerEntity.class, false, false));
			this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2, true));
			this.goalSelector.addGoal(3, new RandomWalkingGoal(this, 1));
			this.targetSelector.addGoal(4, new HurtByTargetGoal(this).setCallsForHelp(this.getClass()));
			this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(6, new SwimGoal(this));
			this.goalSelector.addGoal(7, new BreakDoorGoal(this, e -> true));
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
		public net.minecraft.util.SoundEvent getAmbientSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.zoglin.ambient"));
		}

		@Override
		public void playStepSound(BlockPos pos, BlockState blockIn) {
			this.playSound((net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.wither.break_block")),
					0.15f, 1);
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.wither.hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.wither.spawn"));
		}

		@Override
		public boolean attackEntityFrom(DamageSource source, float amount) {
			double x = this.getPosX();
			double y = this.getPosY();
			double z = this.getPosZ();
			Entity entity = this;
			Entity sourceentity = source.getTrueSource();
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				ThedeadoneEntityIsHurtProcedure.executeProcedure($_dependencies);
			}
			if (source.getImmediateSource() instanceof PotionEntity)
				return false;
			if (source == DamageSource.FALL)
				return false;
			if (source == DamageSource.CACTUS)
				return false;
			if (source == DamageSource.DROWN)
				return false;
			if (source == DamageSource.LIGHTNING_BOLT)
				return false;
			return super.attackEntityFrom(source, amount);
		}

		@Override
		public void onDeath(DamageSource source) {
			super.onDeath(source);
			double x = this.getPosX();
			double y = this.getPosY();
			double z = this.getPosZ();
			Entity sourceentity = source.getTrueSource();
			Entity entity = this;
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("sourceentity", sourceentity);
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				ThedeadoneEntityDiesProcedure.executeProcedure($_dependencies);
			}
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
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				ThedeadoneOnEntityTickUpdateProcedure.executeProcedure($_dependencies);
			}
		}

		@Override
		public boolean isNonBoss() {
			return false;
		}
		private final ServerBossInfo bossInfo = new ServerBossInfo(this.getDisplayName(), BossInfo.Color.GREEN, BossInfo.Overlay.PROGRESS);
		@Override
		public void addTrackingPlayer(ServerPlayerEntity player) {
			super.addTrackingPlayer(player);
			this.bossInfo.addPlayer(player);
		}

		@Override
		public void removeTrackingPlayer(ServerPlayerEntity player) {
			super.removeTrackingPlayer(player);
			this.bossInfo.removePlayer(player);
		}

		@Override
		public void updateAITasks() {
			super.updateAITasks();
			this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
		}

		public void livingTick() {
			super.livingTick();
			double x = this.getPosX();
			double y = this.getPosY();
			double z = this.getPosZ();
			Random random = this.rand;
			Entity entity = this;
			if (true)
				for (int l = 0; l < 20; ++l) {
					double d0 = (x + random.nextFloat());
					double d1 = (y + random.nextFloat());
					double d2 = (z + random.nextFloat());
					int i1 = random.nextInt(2) * 2 - 1;
					double d3 = (random.nextFloat() - 0.5D) * 1.9D;
					double d4 = (random.nextFloat() - 0.5D) * 1.9D;
					double d5 = (random.nextFloat() - 0.5D) * 1.9D;
					world.addParticle(ParticleTypes.ASH, d0, d1, d2, d3, d4, d5);
				}
		}
	}

	@OnlyIn(Dist.CLIENT)
	private static class GlowingLayer<T extends Entity, M extends EntityModel<T>> extends LayerRenderer<T, M> {
		public GlowingLayer(IEntityRenderer<T, M> er) {
			super(er);
		}

		public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing,
				float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
			IVertexBuilder ivertexbuilder = bufferIn
					.getBuffer(RenderType.getEyes(new ResourceLocation("zombie_apocalypse:textures/deadoneglow.png")));
			this.getEntityModel().render(matrixStackIn, ivertexbuilder, 15728640, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
		}
	}

	// Made with Blockbench 3.7.5
	// Exported for Minecraft version 1.15
	// Paste this class into your mod and generate all required imports
	public static class Modelthedeadone extends EntityModel<Entity> {
		private final ModelRenderer body;
		private final ModelRenderer arm1;
		private final ModelRenderer bone;
		private final ModelRenderer arm0;
		private final ModelRenderer bone2;
		private final ModelRenderer head;
		private final ModelRenderer leg1;
		private final ModelRenderer leg3;
		private final ModelRenderer leg0;
		private final ModelRenderer leg2;
		public Modelthedeadone() {
			textureWidth = 128;
			textureHeight = 128;
			body = new ModelRenderer(this);
			body.setRotationPoint(0.0F, -7.0F, 0.0F);
			setRotationAngle(body, 0.1745F, 0.0F, 0.0F);
			body.setTextureOffset(0, 40).addBox(-9.0F, -2.9848F, -5.8264F, 18.0F, 12.0F, 11.0F, 0.0F, false);
			body.setTextureOffset(0, 70).addBox(-4.5F, 9.0152F, -2.8264F, 9.0F, 5.0F, 6.0F, 0.5F, false);
			body.setTextureOffset(2, 88).addBox(-4.5F, 15.0977F, -2.8834F, 9.0F, 5.0F, 6.0F, 0.5F, false);
			arm1 = new ModelRenderer(this);
			arm1.setRotationPoint(0.0F, 0.0F, 0.0F);
			setRotationAngle(arm1, -0.3927F, 0.0F, 0.0F);
			arm1.setTextureOffset(23, 38).addBox(9.0F, -9.4239F, -3.3827F, 4.0F, 18.0F, 6.0F, 0.0F, false);
			bone = new ModelRenderer(this);
			bone.setRotationPoint(0.0F, 21.1272F, -19.7486F);
			arm1.addChild(bone);
			setRotationAngle(bone, -0.6981F, 0.0F, 0.0F);
			bone.setTextureOffset(60, 58).addBox(9.0F, -24.8319F, 3.0421F, 4.0F, 18.0F, 6.0F, 0.0F, false);
			arm0 = new ModelRenderer(this);
			arm0.setRotationPoint(0.0F, -7.0F, 0.0F);
			arm0.setTextureOffset(17, 39).addBox(-13.0F, -2.5F, -3.0F, 4.0F, 18.0F, 6.0F, 0.0F, false);
			bone2 = new ModelRenderer(this);
			bone2.setRotationPoint(-22.0F, 28.1272F, -19.7486F);
			arm0.addChild(bone2);
			setRotationAngle(bone2, -0.6981F, 0.0F, 0.0F);
			bone2.setTextureOffset(60, 58).addBox(9.0F, -25.1362F, 3.2863F, 4.0F, 18.0F, 6.0F, 0.0F, false);
			head = new ModelRenderer(this);
			head.setRotationPoint(0.0F, -7.0F, -2.0F);
			head.setTextureOffset(0, 0).addBox(-4.0F, -10.0F, -8.5F, 8.0F, 8.0F, 8.0F, 0.0F, false);
			leg1 = new ModelRenderer(this);
			leg1.setRotationPoint(6.0F, 11.0F, 0.0F);
			setRotationAngle(leg1, -0.5672F, 0.0F, 0.0F);
			leg1.setTextureOffset(37, 0).addBox(-3.5F, -5.8434F, -0.5373F, 6.0F, 10.0F, 5.0F, 0.0F, true);
			leg3 = new ModelRenderer(this);
			leg3.setRotationPoint(4.0F, 11.0F, 6.0F);
			leg1.addChild(leg3);
			setRotationAngle(leg3, 0.5236F, 0.0F, 0.0F);
			leg3.setTextureOffset(60, 0).addBox(-7.5F, -8.999F, -3.0436F, 6.0F, 10.0F, 5.0F, 0.0F, true);
			leg0 = new ModelRenderer(this);
			leg0.setRotationPoint(-4.0F, 11.0F, 0.0F);
			setRotationAngle(leg0, 0.0F, 0.0436F, 0.0F);
			leg0.setTextureOffset(37, 0).addBox(-3.5F, -6.0F, 0.0F, 6.0F, 10.0F, 5.0F, 0.0F, true);
			leg2 = new ModelRenderer(this);
			leg2.setRotationPoint(4.0F, 11.0F, 6.0F);
			leg0.addChild(leg2);
			setRotationAngle(leg2, 0.5236F, 0.0F, 0.0F);
			leg2.setTextureOffset(60, 0).addBox(-7.5F, -8.866F, -2.5F, 6.0F, 10.0F, 5.0F, 0.0F, true);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			body.render(matrixStack, buffer, packedLight, packedOverlay);
			arm1.render(matrixStack, buffer, packedLight, packedOverlay);
			arm0.render(matrixStack, buffer, packedLight, packedOverlay);
			head.render(matrixStack, buffer, packedLight, packedOverlay);
			leg1.render(matrixStack, buffer, packedLight, packedOverlay);
			leg0.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {
			this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.leg0.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.leg1.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			this.arm1.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
			this.arm0.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
		}
	}
}
