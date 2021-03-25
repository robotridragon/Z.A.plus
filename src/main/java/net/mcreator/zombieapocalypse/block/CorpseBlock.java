
package net.mcreator.zombieapocalypse.block;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.feature.template.IRuleTestType;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.OreFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.World;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IBlockReader;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.Direction;
import net.minecraft.loot.LootContext;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.Minecraft;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.mcreator.zombieapocalypse.particle.BloodParticle;
import net.mcreator.zombieapocalypse.ZombieApocalypseModElements;

import java.util.Random;
import java.util.List;
import java.util.Collections;

@ZombieApocalypseModElements.ModElement.Tag
public class CorpseBlock extends ZombieApocalypseModElements.ModElement {
	@ObjectHolder("zombie_apocalypse:corpse")
	public static final Block block = null;
	public CorpseBlock(ZombieApocalypseModElements instance) {
		super(instance, 10);
		MinecraftForge.EVENT_BUS.register(this);
		FMLJavaModLoadingContext.get().getModEventBus().register(new FeatureRegisterHandler());
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items
				.add(() -> new BlockItem(block, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName(block.getRegistryName()));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void clientLoad(FMLClientSetupEvent event) {
		RenderTypeLookup.setRenderLayer(block, RenderType.getCutout());
	}
	public static class CustomBlock extends FallingBlock {
		public CustomBlock() {
			super(Block.Properties.create(Material.ROCK).sound(SoundType.NETHERRACK).hardnessAndResistance(0.75f, 1f).setLightLevel(s -> 0)
					.harvestLevel(-1).harvestTool(ToolType.AXE).setRequiresTool().doesNotBlockMovement().notSolid().setOpaque((bs, br, bp) -> false));
			setRegistryName("corpse");
		}

		@Override
		public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
			return true;
		}

		@Override
		public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
			return 1;
		}

		@Override
		public int getFireSpreadSpeed(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
			return 1;
		}

		@Override
		public MaterialColor getMaterialColor() {
			return MaterialColor.RED;
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(Items.ROTTEN_FLESH, (int) (10)));
		}

		@OnlyIn(Dist.CLIENT)
		@Override
		public void animateTick(BlockState state, World world, BlockPos pos, Random random) {
			super.animateTick(state, world, pos, random);
			PlayerEntity entity = Minecraft.getInstance().player;
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
			if (true)
				for (int l = 0; l < 2; ++l) {
					double d0 = (x + random.nextFloat());
					double d1 = (y + random.nextFloat());
					double d2 = (z + random.nextFloat());
					int i1 = random.nextInt(2) * 2 - 1;
					double d3 = (random.nextFloat() - 0.5D) * 0.1D;
					double d4 = (random.nextFloat() - 0.5D) * 0.1D;
					double d5 = (random.nextFloat() - 0.5D) * 0.1D;
					world.addParticle(BloodParticle.particle, d0, d1, d2, d3, d4, d5);
				}
		}
	}
	private static Feature<OreFeatureConfig> feature = null;
	private static ConfiguredFeature<?, ?> configuredFeature = null;
	private static IRuleTestType<CustomRuleTest> CUSTOM_MATCH = null;
	private static class CustomRuleTest extends RuleTest {
		static final CustomRuleTest INSTANCE = new CustomRuleTest();
		static final com.mojang.serialization.Codec<CustomRuleTest> codec = com.mojang.serialization.Codec.unit(() -> INSTANCE);
		public boolean test(BlockState blockAt, Random random) {
			boolean blockCriteria = false;
			if (blockAt.getBlock() == Blocks.GRASS.getDefaultState().getBlock())
				blockCriteria = true;
			if (blockAt.getBlock() == Blocks.TALL_GRASS.getDefaultState().getBlock())
				blockCriteria = true;
			if (blockAt.getBlock() == Blocks.DANDELION.getDefaultState().getBlock())
				blockCriteria = true;
			if (blockAt.getBlock() == Blocks.POPPY.getDefaultState().getBlock())
				blockCriteria = true;
			if (blockAt.getBlock() == Blocks.BLUE_ORCHID.getDefaultState().getBlock())
				blockCriteria = true;
			if (blockAt.getBlock() == Blocks.ALLIUM.getDefaultState().getBlock())
				blockCriteria = true;
			if (blockAt.getBlock() == Blocks.AZURE_BLUET.getDefaultState().getBlock())
				blockCriteria = true;
			if (blockAt.getBlock() == Blocks.LILAC.getDefaultState().getBlock())
				blockCriteria = true;
			if (blockAt.getBlock() == Blocks.SUNFLOWER.getDefaultState().getBlock())
				blockCriteria = true;
			if (blockAt.getBlock() == Blocks.LILY_OF_THE_VALLEY.getDefaultState().getBlock())
				blockCriteria = true;
			if (blockAt.getBlock() == Blocks.CORNFLOWER.getDefaultState().getBlock())
				blockCriteria = true;
			if (blockAt.getBlock() == Blocks.OXEYE_DAISY.getDefaultState().getBlock())
				blockCriteria = true;
			if (blockAt.getBlock() == Blocks.PINK_TULIP.getDefaultState().getBlock())
				blockCriteria = true;
			if (blockAt.getBlock() == Blocks.WHITE_TULIP.getDefaultState().getBlock())
				blockCriteria = true;
			if (blockAt.getBlock() == Blocks.ORANGE_TULIP.getDefaultState().getBlock())
				blockCriteria = true;
			if (blockAt.getBlock() == Blocks.RED_TULIP.getDefaultState().getBlock())
				blockCriteria = true;
			if (blockAt.getBlock() == Blocks.PEONY.getDefaultState().getBlock())
				blockCriteria = true;
			if (blockAt.getBlock() == Blocks.ROSE_BUSH.getDefaultState().getBlock())
				blockCriteria = true;
			if (blockAt.getBlock() == Blocks.LARGE_FERN.getDefaultState().getBlock())
				blockCriteria = true;
			if (blockAt.getBlock() == Blocks.BROWN_MUSHROOM.getDefaultState().getBlock())
				blockCriteria = true;
			if (blockAt.getBlock() == Blocks.RED_MUSHROOM.getDefaultState().getBlock())
				blockCriteria = true;
			if (blockAt.getBlock() == Blocks.SWEET_BERRY_BUSH.getDefaultState().getBlock())
				blockCriteria = true;
			return blockCriteria;
		}

		protected IRuleTestType<?> getType() {
			return CUSTOM_MATCH;
		}
	}

	private static class FeatureRegisterHandler {
		@SubscribeEvent
		public void registerFeature(RegistryEvent.Register<Feature<?>> event) {
			CUSTOM_MATCH = Registry.register(Registry.RULE_TEST, new ResourceLocation("zombie_apocalypse:corpse_match"), () -> CustomRuleTest.codec);
			feature = new OreFeature(OreFeatureConfig.CODEC) {
				@Override
				public boolean generate(ISeedReader world, ChunkGenerator generator, Random rand, BlockPos pos, OreFeatureConfig config) {
					RegistryKey<World> dimensionType = world.getWorld().getDimensionKey();
					boolean dimensionCriteria = false;
					if (dimensionType == World.OVERWORLD)
						dimensionCriteria = true;
					if (!dimensionCriteria)
						return false;
					return super.generate(world, generator, rand, pos, config);
				}
			};
			configuredFeature = feature.withConfiguration(new OreFeatureConfig(CustomRuleTest.INSTANCE, block.getDefaultState(), 3)).range(256)
					.square().func_242731_b(1);
			event.getRegistry().register(feature.setRegistryName("corpse"));
			Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation("zombie_apocalypse:corpse"), configuredFeature);
		}
	}
	@SubscribeEvent
	public void addFeatureToBiomes(BiomeLoadingEvent event) {
		event.getGeneration().getFeatures(GenerationStage.Decoration.UNDERGROUND_ORES).add(() -> configuredFeature);
	}
}
