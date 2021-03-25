
package net.mcreator.zombieapocalypse.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.block.BlockState;

import net.mcreator.zombieapocalypse.procedures.VolatileiteItemIsDroppedByPlayerProcedure;
import net.mcreator.zombieapocalypse.ZombieApocalypseModElements;

import java.util.Map;
import java.util.HashMap;

@ZombieApocalypseModElements.ModElement.Tag
public class VolatileiteItem extends ZombieApocalypseModElements.ModElement {
	@ObjectHolder("zombie_apocalypse:volatileite")
	public static final Item block = null;
	public VolatileiteItem(ZombieApocalypseModElements instance) {
		super(instance, 67);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(ItemGroup.MATERIALS).maxStackSize(64).isImmuneToFire().rarity(Rarity.UNCOMMON));
			setRegistryName("volatileite");
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 1F;
		}

		@Override
		public boolean onDroppedByPlayer(ItemStack itemstack, PlayerEntity entity) {
			double x = entity.getPosX();
			double y = entity.getPosY();
			double z = entity.getPosZ();
			World world = entity.world;
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				VolatileiteItemIsDroppedByPlayerProcedure.executeProcedure($_dependencies);
			}
			return true;
		}
	}
}
