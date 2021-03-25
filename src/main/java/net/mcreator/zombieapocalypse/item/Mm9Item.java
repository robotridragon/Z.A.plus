
package net.mcreator.zombieapocalypse.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.block.BlockState;

import net.mcreator.zombieapocalypse.ZombieApocalypseModElements;

@ZombieApocalypseModElements.ModElement.Tag
public class Mm9Item extends ZombieApocalypseModElements.ModElement {
	@ObjectHolder("zombie_apocalypse:mm_9")
	public static final Item block = null;
	public Mm9Item(ZombieApocalypseModElements instance) {
		super(instance, 44);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(ItemGroup.COMBAT).maxStackSize(16).rarity(Rarity.UNCOMMON));
			setRegistryName("mm_9");
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
	}
}
