
package net.mcreator.zombieapocalypse.enchantment;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.ItemStack;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantment;

import net.mcreator.zombieapocalypse.item.GreatdrillItem;
import net.mcreator.zombieapocalypse.ZombieApocalypseModElements;

@ZombieApocalypseModElements.ModElement.Tag
public class BedrockbreakingEnchantment extends ZombieApocalypseModElements.ModElement {
	@ObjectHolder("zombie_apocalypse:bedrockbreaking")
	public static final Enchantment enchantment = null;
	public BedrockbreakingEnchantment(ZombieApocalypseModElements instance) {
		super(instance, 78);
	}

	@Override
	public void initElements() {
		elements.enchantments.add(() -> new CustomEnchantment(EquipmentSlotType.MAINHAND).setRegistryName("bedrockbreaking"));
	}
	public static class CustomEnchantment extends Enchantment {
		public CustomEnchantment(EquipmentSlotType... slots) {
			super(Enchantment.Rarity.RARE, EnchantmentType.DIGGER, slots);
		}

		@Override
		public int getMinLevel() {
			return 1;
		}

		@Override
		public int getMaxLevel() {
			return 1;
		}

		@Override
		protected boolean canApplyTogether(Enchantment ench) {
			if (ench == Enchantments.EFFICIENCY)
				return true;
			if (ench == Enchantments.SILK_TOUCH)
				return true;
			if (ench == Enchantments.UNBREAKING)
				return true;
			if (ench == Enchantments.FORTUNE)
				return true;
			return false;
		}

		@Override
		public boolean canApplyAtEnchantingTable(ItemStack stack) {
			if (stack.getItem() == new ItemStack(GreatdrillItem.block, (int) (1)).getItem())
				return true;
			return false;
		}

		@Override
		public boolean isTreasureEnchantment() {
			return false;
		}

		@Override
		public boolean isCurse() {
			return false;
		}

		@Override
		public boolean isAllowedOnBooks() {
			return true;
		}
	}
}
