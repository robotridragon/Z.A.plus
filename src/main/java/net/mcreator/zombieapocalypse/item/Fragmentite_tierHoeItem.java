
package net.mcreator.zombieapocalypse.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;
import net.minecraft.item.HoeItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.block.BlockState;

import net.mcreator.zombieapocalypse.procedures.WhenblockbreaksbyfragmentitetieritemProcedure;
import net.mcreator.zombieapocalypse.ZombieApocalypseModElements;

import java.util.Map;
import java.util.HashMap;

@ZombieApocalypseModElements.ModElement.Tag
public class Fragmentite_tierHoeItem extends ZombieApocalypseModElements.ModElement {
	@ObjectHolder("zombie_apocalypse:fragmentite_tier_hoe")
	public static final Item block = null;
	public Fragmentite_tierHoeItem(ZombieApocalypseModElements instance) {
		super(instance, 52);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new HoeItem(new IItemTier() {
			public int getMaxUses() {
				return 100;
			}

			public float getEfficiency() {
				return 4f;
			}

			public float getAttackDamage() {
				return -1f;
			}

			public int getHarvestLevel() {
				return 1;
			}

			public int getEnchantability() {
				return 7;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(FragmentiteitemItem.block, (int) (1)));
			}
		}, 0, -3f, new Item.Properties().group(ItemGroup.TOOLS)) {
			@Override
			public boolean onBlockDestroyed(ItemStack itemstack, World world, BlockState bl, BlockPos pos, LivingEntity entity) {
				boolean retval = super.onBlockDestroyed(itemstack, world, bl, pos, entity);
				int x = pos.getX();
				int y = pos.getY();
				int z = pos.getZ();
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("x", x);
					$_dependencies.put("y", y);
					$_dependencies.put("z", z);
					$_dependencies.put("world", world);
					WhenblockbreaksbyfragmentitetieritemProcedure.executeProcedure($_dependencies);
				}
				return retval;
			}
		}.setRegistryName("fragmentite_tier_hoe"));
	}
}
