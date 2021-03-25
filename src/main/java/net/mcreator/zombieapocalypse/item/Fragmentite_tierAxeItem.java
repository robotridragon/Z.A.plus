
package net.mcreator.zombieapocalypse.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;
import net.minecraft.item.AxeItem;

import net.mcreator.zombieapocalypse.ZombieApocalypseModElements;

@ZombieApocalypseModElements.ModElement.Tag
public class Fragmentite_tierAxeItem extends ZombieApocalypseModElements.ModElement {
	@ObjectHolder("zombie_apocalypse:fragmentite_tier_axe")
	public static final Item block = null;
	public Fragmentite_tierAxeItem(ZombieApocalypseModElements instance) {
		super(instance, 49);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new AxeItem(new IItemTier() {
			public int getMaxUses() {
				return 120;
			}

			public float getEfficiency() {
				return 6.5f;
			}

			public float getAttackDamage() {
				return 5.5f;
			}

			public int getHarvestLevel() {
				return 4;
			}

			public int getEnchantability() {
				return 7;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(FragmentiteitemItem.block, (int) (1)));
			}
		}, 1, -3f, new Item.Properties().group(ItemGroup.TOOLS)) {
		}.setRegistryName("fragmentite_tier_axe"));
	}
}
