package net.mcreator.zombieapocalypse.procedures;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.block.Blocks;
import net.minecraft.block.Block;

import net.mcreator.zombieapocalypse.enchantment.BedrockbreakingEnchantment;
import net.mcreator.zombieapocalypse.ZombieApocalypseModElements;
import net.mcreator.zombieapocalypse.ZombieApocalypseMod;

import java.util.Random;
import java.util.Map;

@ZombieApocalypseModElements.ModElement.Tag
public class GreatdrillRightClickedOnBlockProcedure extends ZombieApocalypseModElements.ModElement {
	public GreatdrillRightClickedOnBlockProcedure(ZombieApocalypseModElements instance) {
		super(instance, 79);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				ZombieApocalypseMod.LOGGER.warn("Failed to load dependency entity for procedure GreatdrillRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				ZombieApocalypseMod.LOGGER.warn("Failed to load dependency itemstack for procedure GreatdrillRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				ZombieApocalypseMod.LOGGER.warn("Failed to load dependency x for procedure GreatdrillRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				ZombieApocalypseMod.LOGGER.warn("Failed to load dependency y for procedure GreatdrillRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				ZombieApocalypseMod.LOGGER.warn("Failed to load dependency z for procedure GreatdrillRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				ZombieApocalypseMod.LOGGER.warn("Failed to load dependency world for procedure GreatdrillRightClickedOnBlock!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).experienceLevel : 0) > 1)) {
			if (((!((EnchantmentHelper.getEnchantmentLevel(BedrockbreakingEnchantment.enchantment, (itemstack)) != 0)))
					&& ((((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z)))
							.getMaterial() == net.minecraft.block.material.Material.ROCK)
							&& (!((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))).getBlock() == Blocks.BEDROCK.getDefaultState()
									.getBlock())))
							&& (((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).experienceLevel : 0) > 1)))) {
				{
					ItemStack _ist = (itemstack);
					if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
						_ist.shrink(1);
						_ist.setDamage(0);
					}
				}
				if (world instanceof World) {
					Block.spawnDrops(world.getBlockState(new BlockPos((int) x, (int) y, (int) z)), (World) world,
							new BlockPos((int) x, (int) y, (int) z));
					world.destroyBlock(new BlockPos((int) x, (int) y, (int) z), false);
				}
				if (entity instanceof PlayerEntity)
					((PlayerEntity) entity).addExperienceLevel(-((int) 1));
			} else if ((((EnchantmentHelper.getEnchantmentLevel(BedrockbreakingEnchantment.enchantment, (itemstack)) != 0))
					&& (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.BEDROCK.getDefaultState().getBlock())
							&& (((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).experienceLevel : 0) > 1)))) {
				if (world instanceof World) {
					Block.spawnDrops(world.getBlockState(new BlockPos((int) x, (int) y, (int) z)), (World) world,
							new BlockPos((int) x, (int) y, (int) z));
					world.destroyBlock(new BlockPos((int) x, (int) y, (int) z), false);
				}
				{
					ItemStack _ist = (itemstack);
					if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
						_ist.shrink(1);
						_ist.setDamage(0);
					}
				}
				if (entity instanceof PlayerEntity)
					((PlayerEntity) entity).addExperienceLevel(-((int) 1));
			}
		} else {
			{
				ItemStack _ist = (itemstack);
				if (_ist.attemptDamageItem((int) 2, new Random(), null)) {
					_ist.shrink(1);
					_ist.setDamage(0);
				}
			}
			if (world instanceof World) {
				Block.spawnDrops(world.getBlockState(new BlockPos((int) x, (int) y, (int) z)), (World) world,
						new BlockPos((int) x, (int) y, (int) z));
				world.destroyBlock(new BlockPos((int) x, (int) y, (int) z), false);
			}
		}
	}
}
